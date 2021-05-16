package data;

import logic.AllCarModels;
import entities.Car;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarJDBC extends Car implements CarDataAccess { // Henrik
    Car car = new Car();

    @Override
    public ArrayList<Car> getAllCarModels() {
        return getCarsByCondition("0 = 0");
    }

    @Override
    public boolean addCar(CarJDBC carJDBC) {
        try {
            String sql = "INSERT INTO carmodels VALUES ('" +
                    carJDBC.getModel_name() + "', '" +
                    carJDBC.getPrice() + "', '" +
                    carJDBC.getHorsepower() +")";

            System.out.println(sql);
            Statement statement = JDBC.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()){
                int autoKey = resultSet.getInt(1);
                carJDBC.setId(autoKey);
            }
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCar(CarJDBC carJDBC) {
        try {
            String condition = "id=" + carJDBC.getId();
            String sql = "DELETE FROM carmodels WHERE " + condition;
            System.out.println(sql);
            Statement statement = JDBC.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            return (affectedRows == 1);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCar(CarJDBC carJDBC) {
        try {
            StringBuffer assignments = new StringBuffer();
            assignments.append("model_name='" + carJDBC.getModel_name() + "', ");
            assignments.append("price='" + carJDBC.getPrice() + "', ");
            assignments.append("horsepower='" + carJDBC.getHorsepower());

            String condition = "id=" + carJDBC.getId();

            String sql = "UPDATE carmodels SET " + assignments +
                    " WHERE " + condition;

            System.out.println(sql);
            Statement statement = JDBC.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);
            return (affectedRows == 1);

        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Car> getCarsByCondition(String condition) {
        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM carmodels WHERE " + condition;
            Statement statement = JDBC.instance.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {// iteration starter 'before first'
                int id = resultSet.getInt("car_model_id");
                String modelName = resultSet.getString("model_name");
                String price = resultSet.getString("price");
                String horsepower = resultSet.getString("horsepower");

                Car carmodel = new Car(id, modelName, price, horsepower);
                AllCarModels.allCars.add(carmodel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AllCarModels.allCars;
    }

}
