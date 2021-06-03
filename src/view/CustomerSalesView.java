package view;
//pop up når du skal se tidligere salg
import dataaccessors.CustomerDataAccessor;
import dataaccessors.OfferDataAccessor;
import entities.Customer;
import entities.Offer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.Date;

public class CustomerSalesView {
    // Lavet af Henrik

    public VBox createView(Customer customer) {
        TableView table = new TableView();
        ObservableList<Offer> offers = FXCollections.observableArrayList(OfferDataAccessor.getOfferDataAccess().getOffersByCustomer(customer));

        table.setEditable(false);
        table.setMinWidth(750);
        table.setMinHeight(700);

        TableColumn created = new TableColumn<>("Oprettet");
        created.setMinWidth(250);
        created.setCellValueFactory(new PropertyValueFactory<Offer, Date>("dateOfSale"));

        TableColumn modelName = new TableColumn<>("Bilmodel");
        modelName.setMinWidth(250);
        modelName.setCellValueFactory(new PropertyValueFactory<Offer, Integer>("carId"));

        TableColumn salesPerson = new TableColumn<>("Solgt af");
        salesPerson.setMinWidth(230);
        salesPerson.setCellValueFactory(new PropertyValueFactory<Offer, Integer>("salesPersonId"));

        TableColumn status = new TableColumn<>("Status");
        status.setMinWidth(230);
        status.setCellValueFactory(new PropertyValueFactory<Offer, String>("status"));

        table.getColumns().addAll(modelName, created, salesPerson,status);

        table.setItems(offers);

        VBox root = new VBox(table);
        return root;
    }
}
