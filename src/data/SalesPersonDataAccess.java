package data;

import entities.SalesPerson;

import java.util.ArrayList;

public interface SalesPersonDataAccess {
    ArrayList<SalesPerson> getAllSalesPersons();

    boolean addSalesPerson(SalesPersonJDBC salesPersonJDBC);

    boolean deleteSalesPerson(SalesPersonJDBC salesPersonJDBC);

    boolean updateSalesPerson(SalesPersonJDBC salesPersonJDBC);

    ArrayList<SalesPerson> getSalesPersonByCondition(String condition);
}
