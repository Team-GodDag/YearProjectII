package data;

import logic.AllCarModels;

import java.sql.*;
import java.util.ArrayList;

public class DataLayer {

    public static DataLayer instance = new DataLayer("YearProjectDB");
    private Connection connection;

    public  DataLayer(String databaseName) {
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

    public ArrayList<CarModel> getAllCarModels() {
        return getCarModelsByCondition("0 = 0");
    }

    public boolean addTeams(CarModel carModel) {
        try {
            String sql = "INSERT INTO carmodels VALUES ('" +
                    carModel.getModel_name() + "', '" +
                    carModel.getPrice() + "', '" +
                    carModel.getHorsepower() +")";

            System.out.println(sql);
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()){
                int autoKey = resultSet.getInt(1);
                carModel.setId(autoKey);
            }
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCarModel(CarModel carModel) {
        try {
            String condition = "id=" + carModel.getId();
            String sql = "DELETE FROM carmodels WHERE " + condition;
            System.out.println(sql);
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            return (affectedRows == 1);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTeam(CarModel carModel) {
        try {
            StringBuffer assignments = new StringBuffer();
            assignments.append("model_name='" + carModel.getModel_name() + "', ");
            assignments.append("Vundet='" + carModel.getPrice() + "', ");
            assignments.append("Tabte='" + carModel.getHorsepower());

            String condition = "id=" + carModel.getId();

            String sql = "UPDATE carmodels SET " + assignments +
                    " WHERE " + condition;

            System.out.println(sql);
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);
            return (affectedRows == 1);

        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ArrayList<CarModel> getCarModelsByCondition(String condition) {
        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM carmodels WHERE " + condition;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {// iteration starter 'before first'
                int id = resultSet.getInt("car_model_id");
                String modelName = resultSet.getString("model_name");
                String price = resultSet.getString("price");
                String horsepower = resultSet.getString("horsepower");

                CarModel carmodel = new CarModel(id, modelName, price, horsepower);
                AllCarModels.allCarModels.add(carmodel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AllCarModels.allCarModels;
    }

    public ArrayList<CarModel> getCarModelList() {
        ArrayList<CarModel> carModelTable = new ArrayList<>();

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


                CarModel carModel = new CarModel(id, modelName, price, horsepower);
                carModelTable.add(carModel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModelTable;
    }
}
