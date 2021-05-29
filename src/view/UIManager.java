package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

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
