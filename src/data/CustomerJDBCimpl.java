package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerJDBCimpl implements CustomerJDBC {

    @Override
    public ArrayList<CustomerEntity> getAllCustomers() {
        return getCustomerByCondition("0 = 0");
    }

    @Override
    public boolean addCustomer(CustomerEntity customer) {
        try {
            String sql = "INSERT INTO customers VALUES ('" +
                    customer.getId()            + "', '" +
                    customer.getFirstname()     + "', '" +
                    customer.getLastname()      + "', '" +
                    customer.getEmail()         + "', '" +
                    customer.getAddress()       + "', '" +
                    customer.getPhonenumber()   + "', '" +

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
            assignments.append("cpr='") + customer.getCpr() + "', ");
            assignments.append("firstname='" + customer.getFirstname() + "', ");
            assignments.append("lastname='" + customer.getLastname() + "', ");
            assignments.append("email='" + customer.getEmail());
            assignments.append("address='" + customer.getAddress());
            assignments.append("phonenumber='" + customer.getPhone());


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
                int id = resultSet.getInt("seller_id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phonenumber = resultSet.getString("phonenumber");
                String limit = resultSet.getString("limit");

                CustomerEntity customer = new CustomerEntity(id, firstname, lastname, email ,address , phonenumber, limit );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
