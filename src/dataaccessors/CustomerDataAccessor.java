package dataaccessors;

import data.CustomerDataAccess;
import data.CustomerJDBC;


public class CustomerDataAccessor {
    // Lavet af Rikke

    public static CustomerDataAccess getCustomerDataAccess() {
        return new CustomerJDBC();
    }

}
