package view;
//Viser tilgængelige bil modeller
import entities.Car;
import factories.CarDataAccessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class CatalogueView { // Henrik

    public VBox createView() {
        TableView table = new TableView();
        ObservableList<Car> observableCarList = FXCollections.observableArrayList(CarDataAccessor.getCarDataAccess().getAllCars());

        table.setEditable(false);
        table.setMinWidth(700);
        table.setMaxWidth(798);


        TableColumn modelName = new TableColumn<>("Model");
        modelName.setMinWidth(200);
        modelName.setCellValueFactory(new PropertyValueFactory<Car, String>("name"));

        TableColumn price = new TableColumn<>("Pris");
        price.setMinWidth(150);
        price.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));

        TableColumn horsepower = new TableColumn<>("HK");
        horsepower.setMinWidth(150);
        horsepower.setCellValueFactory(new PropertyValueFactory<Car, String>("horsepower"));


        table.getColumns().addAll(modelName, price, horsepower);
        table.setItems(observableCarList);

        VBox root = new VBox(table);
        root.setId("modelTable");
        return root;
    }
}
