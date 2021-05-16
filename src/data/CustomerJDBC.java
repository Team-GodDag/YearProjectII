package data;

import java.util.ArrayList;

public interface CustomerJDBC {
    ArrayList<CustomerEntity> getAllCustomers();

    boolean addCustomer(CustomerEntity customer);

    boolean deleteCustomer(CustomerEntity customer);

    boolean updateCustomer(CustomerEntity customer);

    ArrayList<CustomerEntity> getCustomersByCondition(String condition);
}
