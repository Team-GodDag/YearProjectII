package view;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.CreditRateToInterestRate;
import logic.WriteCSV;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("BuyYoFerrari");
        primaryStage.setScene(UIController.instance().createScene());
        primaryStage.show();


    }


    public static void main(String[] args) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        launch(args);

    }
}
