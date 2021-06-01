package data;

import entities.Car;

import java.sql.*;
import java.util.ArrayList;

public class JDBC { // Henrik

    public static JDBC instance = new JDBC("YearProjectDB");
    protected Connection connection;

    public JDBC(String databaseName) {
        loadJdbcDriver();
        openConnection("YearProjectDB");
    }

    private boolean loadJdbcDriver() {
        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("JDBC driver loaded");
            return true;
        }
        catch (ClassNotFoundException e) {
            System.out.println("Could not load JDBC driver!");
            return false;
        }
    }

    private boolean openConnection(String databaseName) {
        String connectionString =
                "jdbc:sqlserver://localhost:1433;" +
                        "instanceName=SQLEXPRESS;" +
                        "databaseName=" + databaseName + ";" +
                        "integratedSecurity=true;";
        connection = null;
        try {
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(connectionString);
            System.out.println("Connected to database");
            return true;
        }
        catch (SQLException e) {
            System.out.println("Could not connect to database!");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
