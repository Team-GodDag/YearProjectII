package factories;

import data.CustomerJDBC;
import entities.Customer;

import java.util.ArrayList;

public class CustomerListFactory {              //skal den ligge i logic?

    public static ArrayList<Customer> getAllCustomers() {
        return new CustomerJDBC().getAllCustomers();
    }

    public static Customer getCustomerByCpr(String cpr) {
        return new CustomerJDBC().getCustomerByCpr(cpr);
    }

    public static void addCustomer(Customer customer) {
        new CustomerJDBC().addCustomer(customer);
    }

    public static void updateCustomer(Customer customer) {
        new CustomerJDBC().updateCustomer(customer);
    }

}
