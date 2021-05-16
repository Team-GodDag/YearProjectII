package data;

import entities.Customer;

import java.util.ArrayList;

public interface CustomerDataAccess {
    ArrayList<Customer> getAllCustomers();

    boolean addCustomer(Customer customer);

    boolean deleteCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    ArrayList<Customer> getCustomersByCondition(String condition);
}
