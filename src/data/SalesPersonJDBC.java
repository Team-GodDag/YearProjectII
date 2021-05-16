package data;

import entities.SalesPerson;

import java.util.ArrayList;

public interface SalesPersonJDBC {
    ArrayList<SalesPerson> getAllSalesPersons();

    boolean addSalesPerson(SalesPersonJDBCimpl salesPersonJDBCimpl);

    boolean deleteSalesPerson(SalesPersonJDBCimpl salesPersonJDBCimpl);

    boolean updateSalesPerson(SalesPersonJDBCimpl salesPersonJDBCimpl);

    ArrayList<SalesPerson> getSalesPersonByCondition(String condition);
}
