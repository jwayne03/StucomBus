package dao;

import java.sql.*;

public class DAO {

    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/stucombus?serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "Epsa2014!";

    public DAO() {
        connect();
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!connection.isValid(1000)) connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewDriver(String dni, String name, String surname) {
        try {
            String query = "INSERT INTO conductor (dni, nombre, apellido) VALUES (" + dni + ", '" + name + "', '" + surname + "');";
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Element created" + name + surname + dni);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewVehicle(int tuition, int seating) {
        try {
            String query = "INSERT INTO autobus (matricula, asientos) VALUES (" + tuition +", "+ seating + ")";
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Vehicle registered: ");
            System.out.println("Tuition: " + tuition);
            System.out.println("Seatings: " + seating);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
