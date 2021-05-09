package logic;

import data.CarModel;

import java.util.ArrayList;

public class AllCarModels {
    public static ArrayList<CarModel> allCarModels = new ArrayList<>();

    public void addTeam(CarModel carModel){
        allCarModels.add(carModel);
    }
}
