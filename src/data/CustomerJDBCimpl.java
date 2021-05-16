package data;

import entities.CustomerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerJDBCimpl implements CustomerJDBC {

    @Override
    public ArrayList<CustomerEntity> getAllCustomers() {
        return getCustomersByCondition("0 = 0");
    }

    @Override
    public boolean addCustomer(CustomerEntity customer) {
        try {
            String sql = "INSERT INTO customers VALUES ('" +
                    customer.getId()            + "', '" +
                    customer.getCpr()           + "', '" +
                    customer.getFirstName()     + "', '" +
                    customer.getLastName()      + "', '" +
                    customer.getEmail()         + "', '" +
                    customer.getAddress()       + "', '" +
                    customer.getPhone()         + ")";

            System.out.println(sql);
            Statement statement = JDBC.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()){
                int autoKey = resultSet.getInt(1);
                customer.setId(autoKey);
            }
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(CustomerEntity customer) {
        try {
            String condition = "id=" + customer.getId();
            String sql = "DELETE FROM customers WHERE " + condition;
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
    public boolean updateCustomer(CustomerEntity customer) {
        try {
            StringBuffer assignments = new StringBuffer();
            assignments.append("cpr='"          + customer.getCpr() + "', ");
            assignments.append("firstname='"    + customer.getFirstName() + "', ");
            assignments.append("lastname='"     + customer.getLastName() + "', ");
            assignments.append("email='"        + customer.getEmail());
            assignments.append("address='"      + customer.getAddress());
            assignments.append("phonenumber='"  + customer.getPhone());


            String condition = "id=" + customer.getId();

            String sql = "UPDATE customers SET " + assignments +
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
    public ArrayList<CustomerEntity> getCustomersByCondition(String condition) {
        ArrayList<CustomerEntity> customers = new ArrayList<CustomerEntity>();
        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM customers WHERE " + condition;
            Statement statement = JDBC.instance.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {// iteration starter 'before first'
                int id              = resultSet.getInt("customer_id");
                String cpr          = resultSet.getString("cpr");
                String firstName    = resultSet.getString("firstname");
                String lastName     = resultSet.getString("lastname");
                String email        = resultSet.getString("email");
                String address      = resultSet.getString("address");
                String phone  = resultSet.getString("phonenumber");

                CustomerEntity customer = new CustomerEntity(id, cpr, firstName, lastName, email ,address , phone);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
