package manager;

import administration.Administration;
import dao.DAO;
import management.Management;
import model.Bus;
import model.City;
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
    private Printer printer;
    private Worker worker;
    private static Manager manager;

    private List<Driver> drivers;
    private List<Passenger> passengers;
    private List<Bus> buses;
    private List<Route> routes;
    private List<City> cities;

    public Manager() {
        this.dao = new DAO();
        this.administration = new Administration();
        this.management = new Management();
        this.printer = new Printer();
        this.worker = new Worker();
        this.init();
    }

    private void init() {
        try {
            this.drivers = dao.selectAllDrivers();
            this.passengers = dao.selectAllPassengers();
            this.buses = dao.selectAllBuses();
            this.routes = dao.selectAllRoutes();
            this.cities = dao.selectAllCities();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    public void execute() {
        boolean exit = false;

        while (!exit) {
            printer.welcome();

            switch (worker.askInt("Introduce an option")) {
                case 1:
                    this.administration.execute(cities,buses, drivers, routes);
                    break;
                case 2:
                    this.management.execute(routes, passengers);
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
