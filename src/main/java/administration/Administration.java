package administration;

import dao.DAO;
import exception.DatabaseException;
import model.Bus;
import model.City;
import model.Driver;
import model.Route;
import utils.Printer;
import worker.Worker;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

public class Administration {

    private final Printer printer;
    private final Worker worker;
    private final DAO dao;

    private Driver driver;

    public Administration() {
        this.dao = new DAO();
        this.printer = new Printer();
        this.worker = new Worker();
        this.driver = new Driver();
    }

    public void execute(List<City> cities, List<Bus> buses, List<Driver> drivers, List<Route> routes) {
        printer.administrationmenu();

        try {
            switch (worker.askInt("Introduce an option: ")) {
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
                    routeRegistration(cities, routes);
                    break;
                case 6:
                    unsubscribeExistingRoute(routes);
                    break;
                case 7:
                    showListRoutes(routes);
                    break;
                case 8:
                    showBusesAssosiatedDrivers(drivers, buses, routes);
                    break;
                case 9:
                    addNewCity(cities);
                    break;
                case 10:
                    unsubscribeExistentCity(cities);
                    break;
                case 11:
                    showCityList(cities);
                    break;
                default:
                    printer.needToIntroduceAnOption();
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void driverRegistration(List<Driver> drivers) throws SQLException {
        String dni = worker.askString("Introduce your DNI: ");
        String name = worker.askString("Introduce your name: ");
        String surname = worker.askString("Introduce your surname: ");
        isDriverExists(drivers, dni);

        drivers.add(new Driver(dni, name, surname));
        dao.insertNewDriver(dni, name, surname);
    }

    private void isDriverExists(List<Driver> drivers, String dni) {
        if (checkDriverIfExist(dni, drivers)) {
            printer.thisDriverAlreadyExist();
        } else {
            printer.driverHasCreateSuccessfully();
        }
    }

    private boolean checkDriverIfExist(String dni, List<Driver> drivers) {
        for (Driver driver : drivers) {
            if (driver.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    public void unsubscribeDriver(List<Driver> drivers, List<Route> routes) throws SQLException {
        printAllDrivers(drivers);
        if (drivers.isEmpty()) {
            System.out.println("There are not drivers registered yet.");
            return;
        }

        String dni = worker.askString("What driver do you want to unsubscribe? \n " + "To delete you need to choose the dni");
        if (!checkDriverIfExist(dni, drivers)) {
            printer.thisDriverNoExists();
            return;
        }
        deleteDriverByPosition(drivers, dni);
        searchDriver(drivers, routes, dni);
        dao.deleteDriver(dni);
        printer.driverRemovedSuccessfully();
    }

    private void deleteDriverByPosition(List<Driver> drivers, String dni) {
        drivers.removeIf(driver -> driver.getDni().equals(dni));
    }

    private void printAllDrivers(List<Driver> drivers) {
        drivers.forEach(driver1 -> {
            System.out.println(driver1.toString());
        });
    }

    private void searchDriver(List<Driver> drivers, List<Route> routes, String dni) {
        for (Driver driver : drivers) {
            for (Route route : routes) {
                if (dni.equals(driver.getDni())) {
                    if (driver.getDni().equals(route.getDni())) {
                        printer.cantDeleteThisDriver();
                    } else {
                        printer.driverDontExistInTheSystem();
                    }
                    return;
                }
            }
        }
    }

    private void vehicleRegistration(List<Bus> buses) throws SQLException {
        String tuition = worker.askString("Introduce the tuition of your vehicle: ");
        int seating = worker.askInt("Introduce how many seats have your bus");
        buses.add(new Bus(tuition, seating));
        dao.insertNewVehicle(tuition, seating);
    }

    private void unsubscribeVehicle(List<Bus> buses) throws SQLException {
        printAllBuses(buses);
        if (buses.isEmpty()) {
            printer.thereAreNotVehiclesRegisterYet();
            return;
        }
        String tuition = worker.askString("What vehicle do you want unsubscribe? \n" +
                "To delete you need to introduce the tuition");
        dao.deleteVehicle(tuition);
    }

    private void printAllBuses(List<Bus> buses) {
        buses.forEach(bus -> {
            System.out.println(bus.toString());
        });
    }

    private void routeRegistration(List<City> cities, List<Route> routes) throws SQLException {
        System.out.println("Register a new Route: ");
        int id_route = worker.askInt("Introduce the id of your route");
        String tuition = worker.askString("Introduce the tuition of the vehicle");
        String driverDNI = worker.askString("Introduce the DNI of the driver");
        String departure = worker.askString("Introduce the date of departure");
        String arrive = worker.askString("Introduce the date of arrive");

        cities.forEach(city -> {
            System.out.println(city.toString());
        });

        int origin = worker.askInt("Introduce your city origin");
        int destiny = worker.askInt("Introduce the city of destination");

        if (origin <= 0 || origin >= 7 || destiny <= 0 || destiny >= 7) {
            System.out.println("You need to introduce between 1 and 6");
            return;
        }

        routes.add(new Route(id_route, tuition, driverDNI, origin, destiny, departure, arrive));
        dao.insertNewRoute(id_route, tuition, driverDNI, origin, destiny, departure, arrive);
        printer.routeReserved(id_route, tuition, driverDNI, origin, destiny, departure, arrive);
    }

    private void unsubscribeExistingRoute(List<Route> routes) throws SQLException {
        printAllRoutes(routes);
        if (routes.isEmpty()) {
            printer.thereAreNoRoutes();
            return;
        }
        int route_id = worker.askInt("What route do you want to delete?");
        checkExistingRoute(routes, route_id);
        dao.deleteExistentRoute(route_id);
    }

    private void checkExistingRoute(List<Route> routes, int route_id) {
        for (Route route : routes) {
            if (route.getRoute_id() != route_id) {
                printer.thereAreNotExistingRoutes();
            }
            return;
        }
    }

    private void printAllRoutes(List<Route> routes) {
        routes.forEach(route -> {
            System.out.println(route.toString());
        });
    }

    private void showListRoutes(List<Route> routes) {
        if (routes.isEmpty()) System.out.println("There are not routes");
        routes.forEach(route -> {
            System.out.println(route.toString());
        });
    }

    private void showBusesAssosiatedDrivers(List<Driver> drivers, List<Bus> buses, List<Route> routes) throws DatabaseException {
        int counter = 1;

        for (Driver driver : drivers) {
            System.out.println(counter + " - " + driver.toString());
            counter++;
        }

        int driver_id = worker.askInt("Introduce the number what do you want to consult: ");

        for (Driver driver : drivers) {
            if (drivers.get(driver_id - 1).getDni().equals(driver.getDni())) {
                for (Route route : routes) {
                    if (route.getDriver().equals(driver.getDni())) {
                        for (Bus bus : buses) {
                            System.out.println(bus.toString());
                            return;
                        }
                    } else {
                        throw new DatabaseException("This driver don't have buses associated");
                    }
                }
            } else {
                throw new DatabaseException("This driver doens't exist");
            }
        }
    }

    private void addNewCity(List<City> cities) throws SQLException {
        String cityName = worker.askString("Introduce the name of the city do you want to register: ");

        if (this.checkIfThisCityExist(cities, cityName)) {
            System.out.println("You can't introduce the name city, try again.");
            return;
        } else {
            cities.add(new City(cities.size() + 2, cityName));
            this.dao.insertNewCity(cities.size() + 1, cityName);
            return;
        }
    }

    private boolean checkIfThisCityExist(List<City> cities, String cityName) {
        for (City city : cities) {
            if (cityName.equalsIgnoreCase(city.getName())) {
                return true;
            }
        }
        return false;
    }

    private void unsubscribeExistentCity(List<City> cities) throws SQLException {
        this.showCities(cities);
        System.out.println("(Introduce the ID of the city do you want to delete)");
        int city_id = worker.askInt("What city do you want to delete?");

        if (this.checkCityExist(cities, city_id)) {
            for (City city : cities) {
                if (city_id == city.getCity_id()) {
                    this.dao.deleteExistentCity(city_id);
                    break;
                }
            }
        } else {
            System.out.println("This city doesn't exist");
            return;
        }
    }

    private boolean checkCityExist(List<City> cities, int city_id) {
        for (City city : cities) {
            if (city_id == city.getCity_id()) {
                return true;
            }
        }
        return false;
    }

    private void showCities(List<City> cities) {
        for (City city : cities) {
            System.out.println(city.toString());
        }
    }

    private void showCityList(List<City> cities) {
        for (City city : cities) {
            System.out.println(city.toString());
        }
    }
}
