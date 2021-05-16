package view;
//Viser tilgængelige bil modeller
import entities.Car;
import data.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class CatalogueView { // Henrik

    public VBox createView() {
        TableView table = new TableView();
        ObservableList<Car> ModelList = FXCollections.observableArrayList(JDBC.instance.getCarModelList()); //exposing data-lag, nono

        table.setEditable(false);
        table.setMinWidth(700);
        table.setMaxWidth(798);


        TableColumn modelName = new TableColumn<>("Model Name");
        modelName.setMinWidth(200);
        modelName.setCellValueFactory(new PropertyValueFactory<Car, String>("model_name"));

        TableColumn price = new TableColumn<>("Price");
        price.setMinWidth(150);
        price.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));

        TableColumn horsepower = new TableColumn<>("Horsepower");
        horsepower.setMinWidth(150);
        horsepower.setCellValueFactory(new PropertyValueFactory<Car, String>("horsepower"));


        table.getColumns().addAll(modelName, price, horsepower);


        table.setItems(ModelList);

        VBox root = new VBox(table);
        root.setId("modelTable");
        return root;
    }
}
