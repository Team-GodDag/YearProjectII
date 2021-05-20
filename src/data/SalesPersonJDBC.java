package data;

import entities.SalesPerson;
import logic.AllSalesPersons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SalesPersonJDBC extends SalesPerson implements SalesPersonDataAccess { // Henrik

    @Override
    public ArrayList<SalesPerson> getAllSalesPersons() {
        return getSalesPersonByCondition("0 = 0");
    }

    @Override
    public boolean addSalesPerson(SalesPersonJDBC salesPersonJDBC) {
        try {
            String sql = "INSERT INTO carsellers VALUES ('" +
                    salesPersonJDBC.getFirstname()    + "', '" +
                    salesPersonJDBC.getLastname()     + "', '" +
                    salesPersonJDBC.getEmail()        + "', '" +
                    salesPersonJDBC.getAddress()      + "', '" +
                    salesPersonJDBC.getPhonenumber()  + "', '" +
                    salesPersonJDBC.getLimit()        + ")";

            System.out.println(sql);
            Statement statement = JDBC.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()){
                int autoKey = resultSet.getInt(1);
                salesPersonJDBC.setId(autoKey);
            }
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSalesPerson(SalesPersonJDBC salesPersonJDBC) {       //bør nok renames
        try {
            String condition = "id=" + salesPersonJDBC.getId();
            String sql = "DELETE FROM carsellers WHERE " + condition;
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
    public boolean updateSalesPerson(SalesPersonJDBC salesPersonJDBC) {
        try {
            StringBuffer assignments = new StringBuffer();
            assignments.append("firstname='"    + salesPersonJDBC.getFirstname()    + "', ");
            assignments.append("lastname='"     + salesPersonJDBC.getLastname()     + "', ");
            assignments.append("email='"        + salesPersonJDBC.getEmail()        + "', ");
            assignments.append("phonenumber='"  + salesPersonJDBC.getPhonenumber()  + "', ");
            assignments.append("limit='"        + salesPersonJDBC.getLimit());


            String condition = "id=" + salesPersonJDBC.getId();

            String sql = "UPDATE carsellers SET " + assignments +
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
    public ArrayList<SalesPerson> getSalesPersonByCondition(String condition) {
        ArrayList<SalesPerson> salespersons = new ArrayList<>();
        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM carsellers WHERE " + condition;
            Statement statement = JDBC.instance.connection.createStatement();
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
                salespersons.add(salesPerson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salespersons;
    }

}
