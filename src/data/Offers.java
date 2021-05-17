package data;

import logic.AllCarModels;
import logic.Car;
import logic.OfferLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Offers extends Car{ // Henrik
    Car car = new Car();

    public ArrayList<Car> getAllCarModels() {
        return getCarModelsByCondition("0 = 0");
    }

    public boolean addNewCarModel(OfferLogic offerLogic) {
        try {
            String sql = "INSERT INTO quotes VALUES ('" +
                    offerLogic.getModel_name() + "', '" +
                    offerLogic.getPrice() + "', '" +
                    offerLogic.getPrice() + "', '" +
                    offerLogic.getPrice() + "', '" +
                    offerLogic.getPrice() + "', '" +
                    offerLogic.getPrice() + "', '" +
                    offerLogic.getPrice() + "', '" +
                    offerLogic.getPrice() + "', '" +
                    offerLogic.getHorsepower() +")";

            System.out.println(sql);
            Statement statement = DataLayer.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()){
                int autoKey = resultSet.getInt(1);
                offerLogic.setId(autoKey);
            }
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCarModel(Offers carModel) {
        try {
            String condition = "id=" + off.getId();
            String sql = "DELETE FROM quotes WHERE " + condition;
            System.out.println(sql);
            Statement statement = DataLayer.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            return (affectedRows == 1);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCarModel(Offers carModel) {
        try {
            StringBuffer assignments = new StringBuffer();
            assignments.append("model_name='" + carModel.getModel_name() + "', ");
            assignments.append("price='" + carModel.getPrice() + "', ");
            assignments.append("horsepower='" + carModel.getHorsepower());

            String condition = "id=" + carModel.getId();

            String sql = "UPDATE quotes SET " + assignments +
                    " WHERE " + condition;

            System.out.println(sql);
            Statement statement = DataLayer.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);
            return (affectedRows == 1);

        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ArrayList<Car> getCarModelsByCondition(String condition) {
        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM quotes WHERE " + condition;
            Statement statement = DataLayer.instance.connection.createStatement();
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
