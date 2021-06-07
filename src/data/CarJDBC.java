package data;

import entities.Car;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarJDBC implements CarDataAccess {
      //Lavet af Henrik

//    @Override
    public ArrayList<Car> getAllCars() {
        return getCarsByCondition("0 = 0");
    }

    @Override
    public boolean addCar(Car car) {
        try {
            String sql = "INSERT INTO carmodels VALUES ('" +
                    car.getName()     + "', '" +
                    car.getPrice()          + "', '" +
                    car.getHorsepower()     + ")";

            System.out.println(sql);
            Statement statement = JDBC.instance().connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()){
                int autoKey = resultSet.getInt(1);
                car.setId(autoKey);
            }
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCar(Car car) {
        try {
            String condition = "id=" + car.getId();
            String sql = "DELETE FROM carmodels WHERE " + condition;
            System.out.println(sql);
            Statement statement = JDBC.instance().connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            return (affectedRows == 1);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCar(Car car) {
        try {
            StringBuffer assignments = new StringBuffer();
            assignments.append("model_name='"   + car.getName() + "', ");
            assignments.append("price='"        + car.getPrice() + "', ");
            assignments.append("horsepower='"   + car.getHorsepower());

            String condition = "id=" + car.getId();

            String sql = "UPDATE carmodels SET " + assignments +
                    " WHERE " + condition;

            System.out.println(sql);
            Statement statement = JDBC.instance().connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);
            return (affectedRows == 1);

        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    @Override
    public ArrayList<Car> getCarsByCondition(String condition) {
        ArrayList<Car> cars = new ArrayList<>();
        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM carmodels WHERE " + condition;
            Statement statement = JDBC.instance().connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {// iteration starter 'before first'
                int id              = resultSet.getInt("car_model_id");
                String modelName    = resultSet.getString("model_name");
                BigDecimal price        = resultSet.getBigDecimal("price");
                String horsepower   = resultSet.getString("horsepower");

                Car car = new Car(id, modelName, price, horsepower);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    public Car getCarByName(String modelName) {
        ArrayList<Car> result = getCarsByCondition("model_name=" + modelName);

        if (result.size() > 0)
            return result.get(0);
        else
            return null;
    }


}
