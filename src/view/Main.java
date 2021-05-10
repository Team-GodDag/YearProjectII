package view;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.WriteCSV;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("BuyYoFerrari");
        primaryStage.setScene(UIController.instance().createScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        //WriteCSV.writeCsvFile("CsvTest.csv");
    }
}
