package administration;

import dao.DAO;
import model.Bus;
import model.Driver;
import utils.Printer;
import worker.Worker;

import java.util.ArrayList;
import java.util.List;

public class Administration {

    private Printer printer;
    private Worker worker;
    private DAO dao;

    private List<Driver> drivers;
    private List<Bus> buses;

    private Driver driver;

    public Administration() {
        printer = new Printer();
        worker = new Worker();
        driver = new Driver();
        drivers = new ArrayList<>();
        buses = new ArrayList<>();
        dao = new DAO();
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
    }

    private void vehicleRegistration() {
        int tuition = worker.askInt("Introduce the tuition of your vehicle: ");
        int seating = worker.askInt("Introduce how many seats have your bus");

        buses.add(new Bus(tuition, seating));
        dao.insertNewVehicle(tuition, seating);
    }

    private void unsubscribeVehicle() {
    }

    private void routeRegistration() {

    }

    private void unsubscribeExistingRoute() {
    }

    private void showListRoutes() {

    }

}
