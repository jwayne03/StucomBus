package management;

import dao.DAO;
import model.Passenger;
import utils.Printer;
import worker.Worker;

import java.sql.SQLException;
import java.util.List;

public class Management {

    private Printer printer;
    private Worker worker;
    private final DAO dao;

    public Management() {
        dao = new DAO();
        printer = new Printer();
        worker = new Worker();
    }

    public void run(List<Passenger> passengers) {
        printer.managementmenu();

        try {
            switch (worker.askInt("Introduce an option: ")) {
                case 1:
                    assignRoutePassenger(passengers);
                    break;
                case 2:
                    deletePassengerRoute();
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

        if (ifDniExist(dni, passengers)) {
            System.out.println("This dni already exists, please try again.");
        }

        // TODO: control don't create or insert to the database if the dni is wrong
        passengers.add(new Passenger(dni, name, surname));
        dao.insertNewPassenger(dni, name, surname);
    }

    private boolean ifDniExist(String dni, List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            if (dni.equals(passenger.getDni())) {
                return true;
            }
        }
        return false;
    }

    private void deletePassengerRoute() {

    }

    private void seePassengerRoute() {

    }
}
