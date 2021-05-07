package view;
//Viser tilgængelige bil modeller
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class CatalogueView {

    public VBox createView() {
        TableView table = new TableView();
        //ObservableList<_CarModels_> ModelList = FXCollections.observableArrayList(DataLayer.instance.GetCarModels());

        table.setEditable(false);
        table.setMinWidth(700);
        table.setMaxWidth(798);


        TableColumn modelName = new TableColumn<>("Model Name");
        modelName.setMinWidth(200);
        //modelName.setCellValueFactory(new PropertyValueFactory<CarModels, String>("modelname"));

        TableColumn price = new TableColumn<>("Price");
        price.setMinWidth(150);
        //price.setCellValueFactory(new PropertyValueFactory<CarModels, Integer>("price"));

        TableColumn horsepower = new TableColumn<>("Horsepower");
        horsepower.setMinWidth(150);
        //horsepower.setCellValueFactory(new PropertyValueFactory<CarModels, Integer>("horsepower"));


        table.getColumns().addAll(modelName, price, horsepower);


        //table.setItems(ModelList);

        VBox root = new VBox(table);
        root.setId("modelTable");
        return root;
    }
}
