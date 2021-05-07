package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class UIController {

    private static UIController instance = null;
    public BorderPane root = new BorderPane();

    public static UIController instance() {
        if(instance == null) {
            instance = new UIController();
        }
        return instance;
    }

    //private constructor
    private UIController() { }

    //creates scene

    public Scene createScene() {
        Scene scene = new Scene(root, 800, 500);
        root.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        switchTop(new TopMenuView().createTopMenu());
        switchCenter(new CustomerView().createView());
        return scene;
    }

    public void switchCenter(Node node) {
        root.setCenter(node);
    }

    public void switchTop(Node node) {
        root.setTop(node);
    }
}
