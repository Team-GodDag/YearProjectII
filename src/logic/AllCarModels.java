package logic;

import entities.CarEntity;

import java.util.ArrayList;

public class AllCarModels {
    public static ArrayList<CarEntity> allCars = new ArrayList<>();

    public void addCar(CarEntity car){
        allCars.add(car);
    }
}
