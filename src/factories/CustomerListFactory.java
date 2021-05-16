package factories;

import data.CustomerJDBC;
import entities.Customer;

import java.util.ArrayList;

public class CustomerListFactory {

    public static ArrayList<Customer> createCustomerList() {
        return new CustomerJDBC().getAllCustomers();
    }
}
