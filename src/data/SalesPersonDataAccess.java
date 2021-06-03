package data;

import entities.SalesPerson;
import java.util.ArrayList;

public interface SalesPersonDataAccess {
    //Laver af Rikke
    ArrayList<SalesPerson> getAllSalesPersons();

    boolean addSalesPerson(SalesPersonJDBC salesPersonJDBC);

    boolean deleteSalesPerson(SalesPersonJDBC salesPersonJDBC);

    boolean updateSalesPerson(SalesPersonJDBC salesPersonJDBC);

    ArrayList<SalesPerson> getSalesPersonByCondition(String condition);
}
