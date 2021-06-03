package dataaccessors;

import data.CarDataAccess;
import data.CarJDBC;


public class CarDataAccessor {
    // Lavet af Rikke

    public static CarDataAccess getCarDataAccess(){
    //get the wanted dataaccessobject
        return new CarJDBC();
    }
}
