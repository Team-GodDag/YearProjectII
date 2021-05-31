package dataaccessors;

import data.SalesPersonDataAccess;
import data.SalesPersonJDBC;
import entities.SalesPerson;

import java.util.ArrayList;

public class SalesPersonDataAccessor {

    public static ArrayList<SalesPerson> createSalesPersonList() {
        return new SalesPersonJDBC().getAllSalesPersons();
    }

    public static SalesPersonDataAccess getSalesPersonDataAccess() {
        return new SalesPersonJDBC();
    }
}
