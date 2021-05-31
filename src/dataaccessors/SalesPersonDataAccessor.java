package dataaccessors;

import data.SalesPersonDataAccess;
import data.SalesPersonJDBC;

public class SalesPersonDataAccessor {

    public static SalesPersonDataAccess getSalesPersonDataAccess() {
        return new SalesPersonJDBC();
    }
}
