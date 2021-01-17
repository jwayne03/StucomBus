package administration;

import dao.DAO;
import model.Bus;
import model.Driver;
import utils.Printer;
import worker.Worker;

import java.sql.SQLException;
import java.util.List;

public class Administration {

    private Printer printer;
    private Worker worker;
    private DAO dao;

    private List<Driver> drivers;
    private List<Bus> buses;

    private Driver driver;

    public Administration() {
        try {
            dao = new DAO();
            printer = new Printer();
            worker = new Worker();
            driver = new Driver();
            drivers = dao.selectAllDrivers();
            buses = dao.selectAllBuses();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        printer.administrationmenu();

        int option = worker.askInt("Introduce an option: ");

        switch (option) {
            case 1:
                driverRegistration();
                break;
            case 2:
                unsubscribeDriver();
                break;
            case 3:
                vehicleRegistration();
                break;
            case 4:
                unsubscribeVehicle();
                break;
            case 5:
                routeRegistration();
                break;
            case 6:
                unsubscribeExistingRoute();
                break;
            case 7:
                showListRoutes();
                break;
            default:
                printer.needToIntroduceAnOption();
                break;
        }
    }

    private void driverRegistration() {
        String dni = worker.askString("Introduce your DNI: ");
        String name = worker.askString("Introduce your name: ");
        String surname = worker.askString("Introduce your surname: ");

        drivers.add(new Driver(dni, name, surname));
        dao.insertNewDriver(dni, name, surname);
    }

    private void unsubscribeDriver() {
        for (Driver driver : drivers) {
            System.out.println(driver.toString());
        }
        String dni = worker.askString("What driver do you want to unsubscribe? \n " +
                "To delete you need to choose the dni");
        dao.deleteDriver(dni);
    }

    private void vehicleRegistration() {
        String tuition = worker.askString("Introduce the tuition of your vehicle: ");
        int seating = worker.askInt("Introduce how many seats have your bus");

        buses.add(new Bus(tuition, seating));
        dao.insertNewVehicle(tuition, seating);
    }

    private void unsubscribeVehicle() {
        for (Bus bus : buses) {
            System.out.println(bus.toString());
        }

        String tuition = worker.askString("What vehicle do you want unsubscribe? \n" +
                "To delete you need to introduce the tuition");
        dao.deleteVehicle(tuition);
    }

    private void routeRegistration() {
        System.out.println("Register a new Route: ");
        int id_route = worker.askInt("Introduce the id of your route");
        String tuition = worker.askString("Introduce the tuition of the vehicle");
        String driverDNI = worker.askString("Introduce the DNI of the driver");
        String departure = worker.askString("Introduce the date of departure");
        String arrive = worker.askString("Introduce the date of arrive");
        int origin = worker.askInt("Introduce your city origin");
        int destiny = worker.askInt("Introduce the city of destination");
    }

    private void unsubscribeExistingRoute() {
    }

    private void showListRoutes() {

    }

}
