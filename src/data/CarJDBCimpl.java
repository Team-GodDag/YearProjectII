package data;

import logic.AllCarModels;
import entities.CarEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarJDBCimpl extends CarEntity implements CarJDBC { // Henrik
    CarEntity car = new CarEntity();

    @Override
    public ArrayList<CarEntity> getAllCarModels() {
        return getCarsByCondition("0 = 0");
    }

    @Override
    public boolean addCar(CarJDBCimpl carJDBCimpl) {
        try {
            String sql = "INSERT INTO carmodels VALUES ('" +
                    carJDBCimpl.getModel_name() + "', '" +
                    carJDBCimpl.getPrice() + "', '" +
                    carJDBCimpl.getHorsepower() +")";

            System.out.println(sql);
            Statement statement = JDBC.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()){
                int autoKey = resultSet.getInt(1);
                carJDBCimpl.setId(autoKey);
            }
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCar(CarJDBCimpl carJDBCimpl) {
        try {
            String condition = "id=" + carJDBCimpl.getId();
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
    public boolean updateCar(CarJDBCimpl carJDBCimpl) {
        try {
            StringBuffer assignments = new StringBuffer();
            assignments.append("model_name='" + carJDBCimpl.getModel_name() + "', ");
            assignments.append("price='" + carJDBCimpl.getPrice() + "', ");
            assignments.append("horsepower='" + carJDBCimpl.getHorsepower());

            String condition = "id=" + carJDBCimpl.getId();

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
    public ArrayList<CarEntity> getCarsByCondition(String condition) {
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

                CarEntity carmodel = new CarEntity(id, modelName, price, horsepower);
                AllCarModels.allCars.add(carmodel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AllCarModels.allCars;
    }

}
