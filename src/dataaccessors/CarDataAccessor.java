package dataaccessors;

import data.CarDataAccess;
import data.CarJDBC;


public class CarDataAccessor {

    public static CarDataAccess getCarDataAccess(){
    //get the wanted dataaccessobject
        return new CarJDBC();
    }
}
