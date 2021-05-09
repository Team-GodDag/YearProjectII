package view;
//Viser tilgængelige bil modeller
import data.CarModel;
import data.DataLayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class CatalogueView {

    public VBox createView() {
        TableView table = new TableView();
        ObservableList<CarModel> ModelList = FXCollections.observableArrayList(DataLayer.instance.getCarModelList());

        table.setEditable(false);
        table.setMinWidth(700);
        table.setMaxWidth(798);


        TableColumn modelName = new TableColumn<>("Model Name");
        modelName.setMinWidth(200);
        modelName.setCellValueFactory(new PropertyValueFactory<CarModel, String>("model_name"));

        TableColumn price = new TableColumn<>("Price");
        price.setMinWidth(150);
        price.setCellValueFactory(new PropertyValueFactory<CarModel, String>("price"));

        TableColumn horsepower = new TableColumn<>("Horsepower");
        horsepower.setMinWidth(150);
        horsepower.setCellValueFactory(new PropertyValueFactory<CarModel, String>("horsepower"));


        table.getColumns().addAll(modelName, price, horsepower);


        table.setItems(ModelList);

        VBox root = new VBox(table);
        root.setId("modelTable");
        return root;
    }
}
