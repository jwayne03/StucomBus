package dao;

import exception.DatabaseException;
import model.Bus;
import model.City;
import model.Driver;
import model.Passenger;
import model.Route;
import query.Query;
import utils.Printer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private Connection connection;
    private Printer printer;

    public DAO() {
        try {
            this.printer = new Printer();
            this.connect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void connect() throws DatabaseException {
        try {
            String URL = "jdbc:mysql://localhost:3306/stucombus?useSSL=false&serverTimezone=UTC";
            String USER = "root";
            String PASSWORD = "root";
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (this.connection != null) this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ******************* SELECTS *******************

    public List<Driver> selectAllDrivers() throws SQLException {
        this.connect();
        List<model.Driver> drivers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(Query.SELECT_ALL_DRIVERS)) {
                while (resultSet.next()) {
                    model.Driver driver = new model.Driver();
                    driver.setDni(resultSet.getString("dni"));
                    driver.setName(resultSet.getString("nombre"));
                    driver.setSurname(resultSet.getString("apellido"));
                    drivers.add(driver);
                }
            }
        }
        this.disconnect();
        return drivers;
    }

    public List<Passenger> selectAllPassengers() throws SQLException {
        this.connect();
        List<Passenger> passengers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(Query.SELECT_ALL_PASSENGERS)) {
                while (resultSet.next()) {
                    Passenger passenger = new Passenger();
                    passenger.setDni(resultSet.getString("dni"));
                    passenger.setName(resultSet.getString("nombre"));
                    passenger.setSurname(resultSet.getString("apellido"));
                    passengers.add(passenger);
                }
            }
        }
        this.disconnect();
        return passengers;
    }

    public List<Bus> selectAllBuses() throws SQLException {
        this.connect();
        List<Bus> buses = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(Query.SELECT_ALL_BUSES)) {
                while (resultSet.next()) {
                    Bus bus = new Bus();
                    bus.setTuition(resultSet.getString("matricula"));
                    bus.setSeating(resultSet.getInt("asientos"));
                    buses.add(bus);
                }
            }
        }
        this.disconnect();
        return buses;
    }

    public List<Route> selectAllRoutes() throws SQLException {
        this.connect();
        List<Route> routes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(Query.SELECT_ALL_ROUTES)) {
                while (resultSet.next()) {
                    Route route = new Route();
                    route.setRoute_id(resultSet.getInt("idRuta"));
                    route.setTuition(resultSet.getString("matricula"));
                    route.setDni(resultSet.getString("conductorDNI"));
                    route.setDeparture(resultSet.getString("fecha_salida"));
                    route.setArrive(resultSet.getString("fecha_llegada"));
                    route.setOrigin(resultSet.getInt("ciudad_origen"));
                    route.setDestination(resultSet.getInt("ciudad_destino"));
                    routes.add(route);
                }
            }
        }
        this.disconnect();
        return routes;
    }

    public List<Route> selectAllPassengerRoutes() throws SQLException {
        this.connect();
        List<Route> routes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(Query.SELECT_ALL_PASSENGERROUTES)) {
                while (resultSet.next()) {
                    Route route = new Route();
                    route.setRoute_id(resultSet.getInt("idRuta"));
                    route.setDni(resultSet.getString("dni"));
                    route.setPassengerRoute_id(resultSet.getInt("idPasajeroRuta"));
                    routes.add(route);
                }
            }
        }
        this.disconnect();
        return routes;
    }

    public List<City> selectAllCities() throws SQLException {
        this.connect();
        List<City> cities = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(Query.SELECT_ALL_CITIES)) {
                while (resultSet.next()) {
                    City city = new City();
                    city.setCity_id(resultSet.getInt("id"));
                    city.setName(resultSet.getString("nombre"));
                    cities.add(city);
                }
            }
        }
        this.disconnect();
        return cities;
    }

    // ******************* INSERTS *******************

    public void insertNewDriver(String dni, String name, String surname) throws SQLException {
        this.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_INTO_CONDUCTOR)) {
            preparedStatement.setString(1, dni);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            printer.driverCreated(name, surname, dni);
        }
        this.disconnect();
    }

    public void insertNewVehicle(String tuition, int seating) throws SQLException {
        this.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_INTO_BUS)) {
            preparedStatement.setString(1, tuition);
            preparedStatement.setInt(2, seating);
            preparedStatement.executeUpdate(Query.INSERT_INTO_BUS);
            System.out.println("Vehicle registered: ");
            System.out.println("Tuition: " + tuition);
            System.out.println("Seating: " + seating);
        }
        this.disconnect();
    }

    public void insertNewRoute(int route_id, String tuition, String dni, int origin, int destination,
                               String departure, String arrive) throws SQLException {
        this.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_INTO_ROUTE)) {
            preparedStatement.setInt(1, route_id);
            preparedStatement.setString(2, tuition);
            preparedStatement.setString(3, dni);
            preparedStatement.setInt(4, origin);
            preparedStatement.setInt(5, destination);
            preparedStatement.setString(6, departure);
            preparedStatement.setString(7, arrive);
            preparedStatement.executeUpdate();
        }
        this.disconnect();
    }

    public void insertNewPassenger(String dni, String name, String surname) throws SQLException {
        this.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_INTO_PASSENGER)) {
            preparedStatement.setString(1, dni);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.executeUpdate();
            printer.passengerCreated(name);
        }
        this.disconnect();
    }

    public void insertNewPassengerRoute(int route_id, String dni, int passengerRoute_id) throws SQLException {
        this.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_INTO_PASSENGERROUTES)) {
            preparedStatement.setInt(1, passengerRoute_id);
            preparedStatement.setInt(2, route_id);
            preparedStatement.setString(3, dni);
            preparedStatement.executeUpdate();
            printer.thePassengerHasBeenAddedToRoute();
        }
        this.disconnect();
    }

    // ******************* DELETES *******************

    public void deleteDriver(String dni) throws SQLException {
        this.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_DRIVER_DNI)) {
            preparedStatement.setString(1, dni);
            preparedStatement.executeUpdate();
        }
        this.disconnect();
    }

    public void deleteVehicle(String tuition) throws SQLException {
        this.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_BUS_TUITION)) {
            preparedStatement.setString(1, tuition);
            preparedStatement.executeUpdate();
        }
        this.disconnect();
    }

    public void deleteExistentRoute(int route_id) throws SQLException {
        this.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_ROUTE_IDROUTE)) {
            preparedStatement.setInt(1, route_id);
        }
        this.disconnect();
    }
}
