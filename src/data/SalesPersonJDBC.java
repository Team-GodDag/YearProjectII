package data;

import entities.SalesPerson;

import java.util.ArrayList;

public interface SalesPersonJDBC {
    ArrayList<SalesPerson> getAllSalesPerons();

    boolean addCarSeller(SalesPersonJDBCimpl salesPersonJDBCimpl);

    boolean deleteCarSeller(SalesPersonJDBCimpl salesPersonJDBCimpl);

    boolean updateCarSeller(SalesPersonJDBCimpl salesPersonJDBCimpl);

    ArrayList<SalesPerson> getCarSellerByCondition(String condition);
}
