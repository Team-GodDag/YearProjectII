package factories;

import data.CustomerDataAccess;
import data.CustomerJDBC;


public class CustomerDataAccessor {              //skal den ligge i logic?

//    public static ArrayList<Customer> getAllCustomers() {
//        return new CustomerJDBC().getAllCustomers();
//    }
//
//    public static Customer getCustomerByCpr(String cpr) {
//        return new CustomerJDBC().getCustomerByCpr(cpr);
//    }
//
//    public static void addCustomer(Customer customer) {
//        new CustomerJDBC().addCustomer(customer);
//    }
//
//    public static void updateCustomer(Customer customer) {
//        new CustomerJDBC().updateCustomer(customer);
//    }

    public static CustomerDataAccess getCustomerDataAccess() {
        return new CustomerJDBC();
    }

}
