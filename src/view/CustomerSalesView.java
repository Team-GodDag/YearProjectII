package view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class CustomerSalesView {
    public VBox createView() {
        TableView table = new TableView();
        //ObservableList<CarModels> ModelList = FXCollections.observableArrayList(DataLayer.instance.GetCarModels());

        table.setEditable(false);
        table.setMinWidth(750);
        table.setMinHeight(700);
        //table.setMaxWidth(798);


        TableColumn modelName = new TableColumn<>("Model Name");
        modelName.setMinWidth(250);
        //modelName.setCellValueFactory(new PropertyValueFactory<CarModels, String>("modelname"));

        TableColumn created = new TableColumn<>("Created");
        created.setMinWidth(250);
        //price.setCellValueFactory(new PropertyValueFactory<CarModels, Integer>("price"));

        TableColumn salesPerson = new TableColumn<>("Sales Person");
        salesPerson.setMinWidth(230);
        //horsepower.setCellValueFactory(new PropertyValueFactory<CarModels, Integer>("horsepower"));

        TableColumn status = new TableColumn<>("Status");
        status.setMinWidth(230);
        //horsepower.setCellValueFactory(new PropertyValueFactory<CarModels, Integer>("horsepower"));


        table.getColumns().addAll(modelName, created, salesPerson,status);


        //table.setItems(ModelList);

        VBox root = new VBox(table);
        root.setId("modelTable");
        return root;
    }
}
