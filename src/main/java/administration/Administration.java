package administration;

import dao.DAO;
import model.Bus;
import model.Driver;
import model.Route;
import utils.Printer;
import worker.Worker;

import java.sql.SQLException;
import java.util.List;

public class Administration {

    private final Printer printer;
    private final Worker worker;
    private final DAO dao;

    private Driver driver;

    public Administration() {
        dao = new DAO();
        printer = new Printer();
        worker = new Worker();
        driver = new Driver();
    }

    public void run(List<Bus> buses, List<Driver> drivers, List<Route> routes) {
        printer.administrationmenu();

        try {
            int option = worker.askInt("Introduce an option: ");

            switch (option) {
                case 1:
                    driverRegistration(drivers);
                    break;
                case 2:
                    unsubscribeDriver(drivers, routes);
                    break;
                case 3:
                    vehicleRegistration(buses);
                    break;
                case 4:
                    unsubscribeVehicle(buses);
                    break;
                case 5:
                    routeRegistration(routes);
                    break;
                case 6:
                    unsubscribeExistingRoute(routes);
                    break;
                case 7:
                    showListRoutes(routes);
                    break;
                default:
                    printer.needToIntroduceAnOption();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void driverRegistration(List<Driver> drivers) throws SQLException {
        String dni = worker.askString("Introduce your DNI: ");
        String name = worker.askString("Introduce your name: ");
        String surname = worker.askString("Introduce your surname: ");
        drivers.add(new Driver(dni, name, surname));
        dao.insertNewDriver(dni, name, surname);
    }

    private void unsubscribeDriver(List<Driver> drivers, List<Route> routes) throws SQLException {
        printAllDrivers(drivers);
        if (drivers.isEmpty()) {
            System.out.println("There are not drivers registered yet.");
            return;
        }
        String dni = worker.askString("What driver do you want to unsubscribe? \n " + "To delete you need to choose the dni");
        searchDriver(drivers, routes, dni);
        dao.deleteDriver(dni);
    }

    private void printAllDrivers(List<Driver> drivers) {
        drivers.forEach(driver1 -> {
            System.out.println(driver1.toString());
        });
    }

    private void searchDriver(List<Driver> drivers, List<Route> routes, String dni) {
        drivers.forEach(driver1 -> {
            routes.forEach(route -> {
                if (dni.equals(driver1.getDni())) {
                    if (driver1.getDni().equals(route.getDni())) printer.cantDeleteThisDriver();
                } else printer.driverDontExistInTheSystem();
            });
        });
    }

    private void vehicleRegistration(List<Bus> buses) throws SQLException {
        String tuition = worker.askString("Introduce the tuition of your vehicle: ");
        int seating = worker.askInt("Introduce how many seats have your bus");
        buses.add(new Bus(tuition, seating));
        dao.insertNewVehicle(tuition, seating);
    }

    private void unsubscribeVehicle(List<Bus> buses) throws SQLException {
        printAllBuses(buses);
        String tuition = worker.askString("What vehicle do you want unsubscribe? \n" +
                "To delete you need to introduce the tuition");
        dao.deleteVehicle(tuition);
    }

    private void printAllBuses(List<Bus> buses) {
        buses.forEach(bus -> {
            System.out.println(bus.toString());
        });
    }

    private void routeRegistration(List<Route> routes) throws SQLException {
        System.out.println("Register a new Route: ");
        int id_route = worker.askInt("Introduce the id of your route");
        String tuition = worker.askString("Introduce the tuition of the vehicle");
        String driverDNI = worker.askString("Introduce the DNI of the driver");
        String departure = worker.askString("Introduce the date of departure");
        String arrive = worker.askString("Introduce the date of arrive");
        int origin = worker.askInt("Introduce your city origin");
        int destiny = worker.askInt("Introduce the city of destination");

        routes.add(new Route(id_route, tuition, driverDNI, origin, destiny, departure, arrive));
        dao.insertNewRoute(id_route, tuition, driverDNI, origin, destiny, departure, arrive);
    }

    private void unsubscribeExistingRoute(List<Route> routes) throws SQLException {
        printAllRoutes(routes);
        int route_id = worker.askInt("What route do you want to delete?");
        dao.deleteExistentRoute(route_id);
    }

    private void printAllRoutes(List<Route> routes) {
        routes.forEach(route -> {
            System.out.println(route.toString());
        });
    }

    private void showListRoutes(List<Route> routes) {
        routes.forEach(route -> {
            System.out.println(route.toString());
        });
    }
}
