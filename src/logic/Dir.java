package logic;


import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;
import java.io.File;
import java.io.PrintStream;


public class Dir {

    String text = "Hello World";


    public void displaySave(ActionEvent event) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Save as");

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Type", "*"));
        fc.setTitle("Save as");
        File file = fc.showSaveDialog(window);
        if(file == null){
            try{
                PrintStream ps = new PrintStream(file);
                ps.println(text);
            }catch (Exception e){
                System.out.println("Error");

            }
        }

    }
}
