package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

public class UIManager {

    private static UIManager instance = null;
    public BorderPane root = new BorderPane();

    public static UIManager instance() {
        if(instance == null) {
            instance = new UIManager();
        }
        return instance;
    }

    //private constructor
    private UIManager() { }

    //creates scene

    public Scene createScene() {
        Scene scene = new Scene(root, 1100, 700);
        root.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        switchTop(new TopMenuView().createTopMenu());
        switchCenter(new LoginView().createView());
        return scene;
    }

    public void switchCenter(Node node) {
        root.setCenter(node);
    }

    public void switchTop(Node node) {
        root.setTop(node);
    }


}
