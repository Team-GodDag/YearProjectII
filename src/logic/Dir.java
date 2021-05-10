package logic;

import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import view.CustomerView;
import view.NewOfferView;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Dir {

    public static void save(){
        VBox vbMenu = new VBox();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\Lars_\\Documents"));

        CustomerView cv = new CustomerView();

        Window page = cv.createView().getScene().getWindow();
        Window stage = vbMenu.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Csv File", ".csv"));

        try{
            fileChooser.showOpenDialog(page);
             WriteCSV.writeCsvFile("test");

        }
        catch (Exception e){

        }

    }
    VBox vbMenu;
    FileChooser fileChooser = new FileChooser();


    public void initialize(URL location, ResourceBundle resources){
        fileChooser.setInitialDirectory(new File("C:\\Users\\Lars_\\Documents"));
    }
    private void handleSaveClick(ActionEvent event){
        Window stage = vbMenu.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Csv File", ".csv"));

        try{
            fileChooser.showOpenDialog(stage);
           // CsvWriter.saveToCsv(NewOfferView);

        }
        catch (Exception e){

        }

    }
}
