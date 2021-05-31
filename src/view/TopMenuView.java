package view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class TopMenuView {
    public Node createTopMenu(){
        Button customerButton = new Button("Kunder");
        customerButton.setOnAction(click -> UIManager.instance().switchCenter(new CustomerView().createView()));

        Button newSalesButton = new Button("Nyt tilbud");
        newSalesButton.setOnAction(click -> UIManager.instance().switchCenter(new NewOfferView().createView()));

        Button newCustomerButton = new Button("Ny kunde");
        newCustomerButton.setOnAction(click -> UIManager.instance().switchCenter(new NewCustomerView().createView()));

        Button catalogButton = new Button("Bilkatalog");
        catalogButton.setOnAction(click -> UIManager.instance().switchCenter(new CatalogueView().createView()));

        Button casesButton = new Button("Sager");
        casesButton.setOnAction(click -> UIManager.instance().switchCenter(new AllCasesView().createView()));
        ToolBar root = new ToolBar(customerButton, newSalesButton, newCustomerButton, catalogButton, casesButton);
        root.setPrefWidth(700);
        return root;
    }
}
