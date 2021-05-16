package data;

import entities.Car;

import java.util.ArrayList;

public interface CarDataAccess {
    ArrayList<Car> getAllCarModels();

    boolean addCar(CarJDBC carJDBC);

    boolean deleteCar(CarJDBC carJDBC);

    boolean updateCar(CarJDBC carJDBC);

    ArrayList<Car> getCarsByCondition(String condition);
}
