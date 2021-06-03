package dataaccessors;

import data.CustomerDataAccess;
import data.CustomerJDBC;


public class CustomerDataAccessor {              //skal den ligge i logic?

    public static CustomerDataAccess getCustomerDataAccess() {
        return new CustomerJDBC();
    }

}
