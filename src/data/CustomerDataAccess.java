package data;

import entities.Customer;

import java.util.ArrayList;

public interface CustomerDataAccess {
//    ArrayList<Customer> getAllCustomers();  //skal den være her??? (for samtlige klasser)

    boolean addCustomer(Customer customer);

    boolean deleteCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    Customer getCustomerByCpr(String cpr);

    //ArrayList<Customer> getCustomersByCondition(String condition);
}
