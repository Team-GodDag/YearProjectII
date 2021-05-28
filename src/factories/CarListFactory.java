package factories;

import data.CarDataAccess;
import data.CarJDBC;
import entities.Car;

import java.util.ArrayList;

public class CarListFactory {

    public static CarDataAccess getCarDataAccess(){
    //get the wanted dataaccessobject
        return new CarJDBC();
    }
}
