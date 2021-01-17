package dao;

import model.Bus;
import model.Driver;
import model.Route;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private Connection connection;

    public DAO() {
        connect();
    }

    public void connect() {
        try {
            String URL = "jdbc:mysql://localhost:3306/stucombus?serverTimezone=UTC";
            String USER = "root";
            String PASSWORD = "Epsa2014!";
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ******************* SELECTS *******************

    public List<Driver> selectAllDrivers() throws SQLException {
        String query = "SELECT * FROM conductor";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<model.Driver> drivers = new ArrayList<>();

        while (resultSet.next()) {
            model.Driver driver = new model.Driver();
            driver.setDni(resultSet.getString("dni"));
            driver.setName(resultSet.getString("nombre"));
            driver.setSurname(resultSet.getString("apellido"));
            drivers.add(driver);
        }
        resultSet.close();
        statement.close();
        return drivers;
    }

    public List<Bus> selectAllBuses() throws SQLException {
        String query = "SELECT * FROM autobus";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Bus> buses = new ArrayList<>();

        while (resultSet.next()) {
            Bus bus = new Bus();
            bus.setTuition(resultSet.getString("matricula"));
            bus.setSeating(resultSet.getInt("asientos"));
            buses.add(bus);
        }
        statement.close();
        resultSet.close();
        return buses;
    }

    public List<Route> selectAllRoutes() throws SQLException {
        String query = "SELECT * FROM ruta";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Route> routes = new ArrayList<>();

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
        statement.close();
        resultSet.close();
        return routes;
    }

    // ******************* INSERTS *******************

    public void insertNewDriver(String dni, String name, String surname) {
        try {
            String query = "INSERT INTO conductor (dni, nombre, apellido) VALUES (" + dni + ", '" + name + "', '" + surname + "');";
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Element created " + name + " " + surname + " " + dni);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewVehicle(String tuition, int seating) {
        try {
            String query = "INSERT INTO autobus (matricula, asientos) VALUES (" + tuition + ", " + seating + ")";
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Vehicle registered: ");
            System.out.println("Tuition: " + tuition);
            System.out.println("Seatings: " + seating);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ******************* DELETES *******************

    public void deleteDriver(String dni) {
        try {
            String query = "DELETE FROM conductor WHERE dni='" + dni + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVehicle(String tuition) {
        try {
            String query = "DELETE FROM autobus WHERE matricula='" + tuition + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
