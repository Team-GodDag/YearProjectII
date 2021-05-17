package view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.awt.*;

public class Test {

    public GridPane createView(){
        GridPane root = new GridPane();
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        Button button = new Button();
        Text text = new Text("Hej");
        Text text2 = new Text("Hej");
root.add(text,0,1);
root.add(text2,10,1);
root.setGridLinesVisible(true);


        return root;
    }
}
