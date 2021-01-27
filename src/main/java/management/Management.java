package management;

import dao.DAO;
import model.Passenger;
import model.Route;
import utils.Printer;
import worker.Worker;

import java.sql.SQLException;
import java.util.List;

public class Management {

    private final Printer printer;
    private final Worker worker;
    private final DAO dao;

    public Management() {
        dao = new DAO();
        printer = new Printer();
        worker = new Worker();
    }

    public void run(List<Route> routes, List<Passenger> passengers) {
        printer.managementmenu();

        try {
            switch (worker.askInt("Introduce an option: ")) {
                case 1:
                    assignRoutePassenger(passengers);
                    break;
                case 2:
                    deletePassengerRoute(routes, passengers);
                    break;
                case 3:
                    seePassengerRoute();
                    break;
                default:
                    printer.needToIntroduceAnOption();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void assignRoutePassenger(List<Passenger> passengers) throws SQLException {
        String dni = worker.askString("Introduce your DNI: ");
        String name = worker.askString("Introduce your name: ");
        String surname = worker.askString("Introduce your surname");
        createNewPassenger(passengers, dni, name, surname);
    }

    private void createNewPassenger(List<Passenger> passengers, String dni, String name, String surname) throws SQLException {
        if (ifDniExist(dni, passengers)) {
            System.out.println("This dni already exists, please try again.");
        } else {
            passengers.add(new Passenger(dni, name, surname));
            dao.insertNewPassenger(dni, name, surname);
        }
    }

    private boolean ifDniExist(String dni, List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            if (dni.equals(passenger.getDni())) return true;
        }
        return false;
    }

    private void deletePassengerRoute(List<Route> routes, List<Passenger> passengers) {
        showPassengerRoutes(routes, passengers);
    }

    private void showPassengerRoutes(List<Route> routes, List<Passenger> passengers) {
        passengers.forEach(passenger -> {
            routes.forEach(route -> {
                System.out.println(passenger.getName() + " " + route.getRoute_id());
            });
        });
    }

    private void seePassengerRoute() {

    }
}
