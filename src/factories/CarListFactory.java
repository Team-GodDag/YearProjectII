package factories;

import data.CarJDBC;
import entities.Car;

import java.util.ArrayList;

public class CarListFactory {

    public static ArrayList<Car> createCarList() {      //skal der fiksfakses med
        return new CarJDBC().getAllCars();
    }
}
