package view;

import entities.Offer;
import dataaccessors.OfferDataAccessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class AllCasesView {
    // Lavet af Henrik

    public VBox createView() {
        TableView tableView = new TableView();
        ObservableList<Offer> observableOfferList = FXCollections.observableArrayList(OfferDataAccessor.getOfferDataAccess().getAllOffers());

        tableView.setEditable(false);
        tableView.setMinHeight(700);


        TableColumn caseNumberColumn = new TableColumn<>("Sagsnummer");
        caseNumberColumn.setMinWidth(180);
        caseNumberColumn.setCellValueFactory(new PropertyValueFactory<Offer, Integer>("id"));

        TableColumn saleDateColumn = new TableColumn<>("Oprettet");
        saleDateColumn.setMinWidth(180);
        saleDateColumn.setCellValueFactory(new PropertyValueFactory<Offer, String>("dateOfSale"));

        TableColumn customerColumn = new TableColumn<>("Kunde");
        customerColumn.setMinWidth(180);
        customerColumn.setCellValueFactory(new PropertyValueFactory<Offer, String>("customerId"));

        TableColumn salesPersonColumn = new TableColumn<>("Sælger");
        salesPersonColumn.setMinWidth(180);
        salesPersonColumn.setCellValueFactory(new PropertyValueFactory<Offer, Integer>("salesPersonId"));

        TableColumn carColumn = new TableColumn<>("Bil");
        carColumn.setMinWidth(180);
        carColumn.setCellValueFactory(new PropertyValueFactory<Offer, Integer>("carId"));

        TableColumn statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(203);
        statusColumn.setCellValueFactory(new PropertyValueFactory<Offer, String>("status"));

        tableView.getColumns().addAll(saleDateColumn, caseNumberColumn, customerColumn, carColumn, salesPersonColumn, statusColumn);
        tableView.setItems(observableOfferList);

        VBox root = new VBox(tableView);
        root.setId("modelTable");
        return root;
    }
}
