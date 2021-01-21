package dao;

import model.Bus;
import model.Driver;
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
    private final Printer printer;

    public DAO() {
        printer = new Printer();
        connect();
    }

    public void connect() {
        try {
            String URL = "jdbc:mysql://localhost:3306/stucombus?useSSL=false";
            String USER = "root";
            String PASSWORD = "root";
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ******************* SELECTS *******************

    public List<Driver> selectAllDrivers() throws SQLException {
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
        return drivers;
    }

    public List<Bus> selectAllBuses() throws SQLException {
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
        return buses;
    }

    public List<Route> selectAllRoutes() throws SQLException {
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

    // ******************* INSERTS *******************

    public void insertNewDriver(String dni, String name, String surname) throws SQLException {
        this.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_INTO_CONDUCTOR)) {
            preparedStatement.setString(1, dni);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Element created " + name + " " + surname + " " + dni);
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
            printer.routeRegisteredSuccessfully(route_id, tuition, dni, origin, destination, departure, arrive);
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
