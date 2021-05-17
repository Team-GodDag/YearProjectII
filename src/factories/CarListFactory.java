package factories;

import data.CarJDBC;
import entities.Car;

import java.util.ArrayList;

public class CarListFactory {

    public static ArrayList<Car> createCarList() {
        return new CarJDBC().getAllCars();
    }
}
