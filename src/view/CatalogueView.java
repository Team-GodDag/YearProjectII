package view;
import entities.Car;
import dataaccessors.CarDataAccessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class CatalogueView {
    // Lavet af Henrik

    public VBox createView() {
        TableView table = new TableView();
        ObservableList<Car> observableCarList = FXCollections.observableArrayList(CarDataAccessor.getCarDataAccess().getAllCars());

        table.setEditable(false);
        table.setMinWidth(1098);
        table.setMinHeight(680);

        TableColumn modelName = new TableColumn<>("Model");
        modelName.setMinWidth(300);
        modelName.setCellValueFactory(new PropertyValueFactory<Car, String>("name"));

        TableColumn price = new TableColumn<>("Pris");
        price.setMinWidth(300);
        price.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));

        TableColumn horsepower = new TableColumn<>("HK");
        horsepower.setMinWidth(495);
        horsepower.setCellValueFactory(new PropertyValueFactory<Car, String>("horsepower"));

        table.getColumns().addAll(modelName, price, horsepower);
        table.setItems(observableCarList);

        VBox root = new VBox(table);
        return root;
    }
}
