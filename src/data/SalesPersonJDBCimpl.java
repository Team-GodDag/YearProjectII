package data;

import entities.SalesPerson;
import logic.AllSalesPersons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SalesPersonJDBCimpl extends SalesPerson implements SalesPersonJDBC { // Henrik
    SalesPersonJDBCimpl salesPersonJDBCimpl = new SalesPersonJDBCimpl();

    @Override
    public ArrayList<SalesPerson> getAllSalesPerons() {
        return getCarSellerByCondition("0 = 0");
    }

    @Override
    public boolean addCarSeller(SalesPersonJDBCimpl salesPersonJDBCimpl) {
        try {
            String sql = "INSERT INTO carsellers VALUES ('" +
                    salesPersonJDBCimpl.getFirstname()    + "', '" +
                    salesPersonJDBCimpl.getLastname()     + "', '" +
                    salesPersonJDBCimpl.getEmail()        + "', '" +
                    salesPersonJDBCimpl.getAddress()      + "', '" +
                    salesPersonJDBCimpl.getPhonenumber()  + "', '" +
                    salesPersonJDBCimpl.getLimit()        + ")";

            System.out.println(sql);
            Statement statement = JDBC.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()){
                int autoKey = resultSet.getInt(1);
                salesPersonJDBCimpl.setId(autoKey);
            }
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCarSeller(SalesPersonJDBCimpl salesPersonJDBCimpl) {       //bør nok renames
        try {
            String condition = "id=" + salesPersonJDBCimpl.getId();
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
    public boolean updateCarSeller(SalesPersonJDBCimpl salesPersonJDBCimpl) {
        try {
            StringBuffer assignments = new StringBuffer();
            assignments.append("firstname='" + salesPersonJDBCimpl.getFirstname() + "', ");
            assignments.append("lastname='" + salesPersonJDBCimpl.getLastname() + "', ");
            assignments.append("email='" + salesPersonJDBCimpl.getEmail());
            assignments.append("phonenumber='" + salesPersonJDBCimpl.getPhonenumber());
            assignments.append("limit='" + salesPersonJDBCimpl.getLimit());


            String condition = "id=" + salesPersonJDBCimpl.getId();

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
    public ArrayList<SalesPerson> getCarSellerByCondition(String condition) {
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
                AllSalesPersons.allSalesPersons.add(salesPerson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AllSalesPersons.allSalesPersons;
    }

}
