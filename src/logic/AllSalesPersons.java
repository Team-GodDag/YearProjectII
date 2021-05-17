package logic;

import entities.SalesPerson;

import java.util.ArrayList;

public class AllSalesPersons {  //TO BE DELETED
    public static ArrayList<SalesPerson> allSalesPersons = new ArrayList<>();

    public void addCar(SalesPerson salesPerson){
        allSalesPersons.add(salesPerson);
    }
}
