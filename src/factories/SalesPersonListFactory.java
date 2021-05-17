package factories;

import data.SalesPersonJDBC;
import entities.SalesPerson;

import java.util.ArrayList;

public class SalesPersonListFactory {

    public static ArrayList<SalesPerson> createSalesPersonList() {
        return new SalesPersonJDBC().getAllSalesPersons();
    }
}
