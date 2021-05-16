package data;

import logic.Car;

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


    public ArrayList<Car> getCarModelList() {
        ArrayList<Car> carModelTable = new ArrayList<>();

        try {
            String sql = "SELECT * FROM carmodels ORDER BY car_model_id DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            // iteration starter 'before first'
            while (resultSet.next()) {
                // hent data fra denne række
                int id = resultSet.getInt("car_model_id");
                String modelName = resultSet.getString("model_name");
                String price = resultSet.getString("price");
                String horsepower = resultSet.getString("horsepower");


                Car carModel = new Car(id, modelName, price, horsepower);
                carModelTable.add(carModel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModelTable;
    }

}
