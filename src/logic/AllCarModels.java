package logic;

import java.util.ArrayList;

public class AllCarModels {
    public static ArrayList<Car> allCars = new ArrayList<>();

    public void addCar(Car car){
        allCars.add(car);
    }
}
