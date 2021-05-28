package data;

import entities.Car;

import java.util.ArrayList;

public interface CarDataAccess {
//    ArrayList<Car> getAllCars();

    boolean addCar(Car car);

    boolean deleteCar(Car car);

    boolean updateCar(Car car);

    ArrayList<Car> getCarsByCondition(String condition);
}
