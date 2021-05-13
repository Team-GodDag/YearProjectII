package data;

import logic.Car;
import logic.SalesPerson;
import logic.AllSalesPersons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarSeller extends SalesPerson{ // Henrik
    CarSeller carSeller = new CarSeller();

    public ArrayList<SalesPerson> getAllSalesPerons() {
        return getCarSellerByCondition("0 = 0");
    }

    public boolean addCarSeller(CarSeller carSeller) {
        try {
            String sql = "INSERT INTO carsellers VALUES ('" +
                    carSeller.getModel_name() + "', '" +
                    carSeller.getPrice() + "', '" +
                    carSeller.getHorsepower() +")";

            System.out.println(sql);
            Statement statement = DataLayer.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()){
                int autoKey = resultSet.getInt(1);
                carSeller.setId(autoKey);
            }
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCarSeller(CarSeller carSeller) {
        try {
            String condition = "id=" + carSeller.getId();
            String sql = "DELETE FROM carsellers WHERE " + condition;
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

    public boolean updateCarSeller(CarSeller carSeller) {
        try {
            StringBuffer assignments = new StringBuffer();
            assignments.append("firstname='" + carSeller.getModel_name() + "', ");
            assignments.append("lastname='" + carSeller.getPrice() + "', ");
            assignments.append("email='" + carSeller.getHorsepower());
            assignments.append("phonenumber='" + carSeller.getHorsepower());
            assignments.append("limit='" + carSeller.getHorsepower());


            String condition = "id=" + carSeller.getId();

            String sql = "UPDATE carsellers SET " + assignments +
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

    private ArrayList<SalesPerson> getCarSellerByCondition(String condition) {
        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM carsellers WHERE " + condition;
            Statement statement = DataLayer.instance.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {// iteration starter 'before first'
                int id = resultSet.getInt("seller_id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phonenumber = resultSet.getString("phonenumber");
                String limit = resultSet.getString("limit");

                SalesPerson salesPerson = new SalesPerson(id, firstname, lastname, email ,address , phonenumber, limit );
                AllSalesPersons.allSalesPersons.add(salesPerson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AllSalesPersons.allSalesPersons;
    }

}
