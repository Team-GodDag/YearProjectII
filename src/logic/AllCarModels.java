package logic;

import entities.Car;

import java.util.ArrayList;

public class AllCarModels {     //TO BE DELETED
    public static ArrayList<Car> allCars = new ArrayList<>();

    public void addCar(Car car){
        allCars.add(car);
    }
}
