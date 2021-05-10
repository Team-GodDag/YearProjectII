package view;

import data.CarModel;
import data.DataLayer;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.Car;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("BuyYoFerrari");
        primaryStage.setScene(UIController.instance().createScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        DataLayer datalayer = new DataLayer("YearProjectDB");
       // System.out.println(CarModel.getAllCarModels());
        launch(args);
    }
}
