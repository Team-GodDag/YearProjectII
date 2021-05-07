package view;

import com.sun.javafx.scene.control.CustomColorDialog;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;

public class TopMenuView {
    public Node createTopMenu(){
        Button customerButton = new Button("Customers");
        customerButton.setOnAction(click -> UIController.instance().switchCenter(new CustomerView().createView()));

        Button newSalesButton = new Button("New Quote");

        Button newCustomerButton = new Button("New Customer");
        newCustomerButton.setOnAction(click -> UIController.instance().switchCenter(new NewCustomerView().createView()));

        Button catalogButton = new Button("Catalogue");
        catalogButton.setOnAction(click -> UIController.instance().switchCenter(new CatalogueView().createView()));

        Button casesButton = new Button("Cases");
        // skal pege til AllCasesView
        ToolBar root = new ToolBar(customerButton, newSalesButton, newCustomerButton, catalogButton, casesButton);
        root.setPrefWidth(700);
        return root;
    }
}
