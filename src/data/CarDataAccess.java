package data;

import entities.Car;
import java.util.ArrayList;

public interface CarDataAccess {
    //Laver af Rikke
//    ArrayList<Car> getAllCars();

    boolean addCar(Car car);

    boolean deleteCar(Car car);

    boolean updateCar(Car car);

//    ArrayList<Car> getCarsByCondition(String condition);

    ArrayList<Car> getAllCars();

    Car getCarByName(String modelName);
}
