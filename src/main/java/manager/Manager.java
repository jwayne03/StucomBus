package manager;

import administration.Administration;
import dao.DAO;
import management.Management;
import model.Bus;
import model.Driver;
import model.Passenger;
import model.Route;
import utils.Printer;
import worker.Worker;

import java.sql.SQLException;
import java.util.List;

public class Manager {

    private DAO dao;
    private Administration administration;
    private Management management;
    private static Manager manager;
    private final Printer printer;
    private final Worker worker;

    private List<Driver> drivers;
    private List<Passenger> passengers;
    private List<Bus> buses;
    private List<Route> routes;

    public Manager() {
        dao = new DAO();
        administration = new Administration();
        management = new Management();
        printer = new Printer();
        worker = new Worker();
        init();
    }

    private void init() {
        try {
            drivers = dao.selectAllDrivers();
            passengers = dao.selectAllPassengers();
            buses = dao.selectAllBuses();
            routes = dao.selectAllRoutes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager ;
    }

    public void run() {
        boolean exit = false;

        while (!exit) {
            printer.welcome();

            switch (worker.askInt("Introduce an option")) {
                case 1:
                    administration.run(buses, drivers, routes);
                    break;
                case 2:
                    management.run(routes,passengers);
                    break;
                case 0:
                    printer.userHasChooseToExit();
                    exit = true;
                default:
                    printer.needToIntroduceAnOption();
                    break;
            }
        }
    }
}
