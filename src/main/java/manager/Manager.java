package manager;

import administration.Administration;
import dao.DAO;
import management.Management;
import model.Bus;
import model.Driver;
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
            buses = dao.selectAllBuses();
            routes = dao.selectAllRoutes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    public void run() {
        printer.welcome();

        int option = worker.askInt("Introduce an option:");

        boolean exit = false;
        while (!exit) {
            switch (option) {
                case 1:
                    administration.run(buses, drivers, routes);
                    break;
                case 2:
                    management.run();
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
