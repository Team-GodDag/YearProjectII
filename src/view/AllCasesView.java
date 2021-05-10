package view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

//, køber, sælger, status, bil,
public class AllCasesView {

    public VBox createView() {
        TableView table = new TableView();
        //ObservableList<CarModels> ModelList = FXCollections.observableArrayList(DataLayer.instance.GetCarModels());

        table.setEditable(false);
        table.setMinHeight(700);



        TableColumn salesDate = new TableColumn<>("Sales Date");
        salesDate.setMinWidth(157);
        //modelName.setCellValueFactory(new PropertyValueFactory<CarModels, String>("modelname"));

        TableColumn caseNumber = new TableColumn<>("Case Number");
        caseNumber.setMinWidth(157);
        //price.setCellValueFactory(new PropertyValueFactory<CarModels, Integer>("price"));

        TableColumn customer = new TableColumn<>("Customer");
        customer.setMinWidth(157);
        //horsepower.setCellValueFactory(new PropertyValueFactory<CarModels, Integer>("horsepower"));

        TableColumn salesPerson = new TableColumn<>("Sales Person");
        salesPerson.setMinWidth(157);
        //horsepower.setCellValueFactory(new PropertyValueFactory<CarModels, Integer>("horsepower"));

        TableColumn carType = new TableColumn<>("Car Sold");
        carType.setMinWidth(161);
        //horsepower.setCellValueFactory(new PropertyValueFactory<CarModels, Integer>("horsepower"));

        TableColumn status = new TableColumn<>("Status");
        status.setMinWidth(157);
        //horsepower.setCellValueFactory(new PropertyValueFactory<CarModels, Integer>("horsepower"));


        table.getColumns().addAll(salesDate, caseNumber, customer,carType, salesPerson,status);


        //table.setItems(ModelList);

        VBox root = new VBox(table);
        root.setId("modelTable");
        return root;
    }
}
