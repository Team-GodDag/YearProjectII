package view;
// Start side
// Viser nuværende kunder i databasen
import entities.Customer;
import factories.CustomerListFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CustomerView {

    private Customer customer;
    private Text nameText, phoneText, emailText, adressText, cprText;
    private TextField searchTextField;
    private ListView listView;

    public Node createView() {

        searchTextField = new TextField();
        searchTextField.setPrefWidth(200);
        searchTextField.setPromptText("Søg efter CPR Nummer");


        Button goButton = new Button("Søg");
        goButton.setPrefWidth(50);
        goButton.setOnAction(click -> {
            Customer searchedCustomer = CustomerListFactory.getCustomerByCpr(searchTextField.getText());
            setCustomerInfo(searchedCustomer);
        });




//LISTVIEW ---------------------START
        listView = new ListView();
        listView.setPrefHeight(600);
        List<Customer> customerList = new ArrayList<Customer>(CustomerListFactory.getAllCustomers());        //skal den have sit eget interface?
        ObservableList<Customer> observableCustomerlist = FXCollections.observableArrayList(customerList);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observableValue, Customer prevCustomer, Customer nextCustomer) {
                setCustomerInfo(nextCustomer);
            }
        });


//FILTERED LIST TIL BRUG I SØGEFUNKTION - NOT DONE 17/5
        FilteredList<Customer> filteredCustomerList = new FilteredList<>(observableCustomerlist, p -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCustomerList.setPredicate(customer -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowercaseFilter = newValue.toLowerCase();
//                String searchStr = searchTextField.getText();
                if (customer.getCpr().toLowerCase().contains(lowercaseFilter)) {
                    return true;
                } else if(customer.getFirstName().toLowerCase().contains(lowercaseFilter)) {
                    return true; //filteredCustomerList.setPredicate(predicate -> predicate.getCpr().contains(searchStr));
                }
                return false;
            });
        });
        SortedList<Customer> sortedCustomerList = new SortedList<>(filteredCustomerList);

        listView.setItems(sortedCustomerList);

        listView.setCellFactory(new Callback<ListView<Customer>, ListCell<Customer>>() {  
            @Override
            public ListCell<Customer> call(ListView<Customer> names) {
                ListCell<Customer> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(Customer customer, boolean bool) {
                        super.updateItem(customer, bool);
                        if(customer != null) {
                            setText(customer.getFirstName() + " " + customer.getLastName());
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });

//LISTVIEW ---------------------END

        Label emptyLabel = new Label(" ");              //????????

        HBox topHbox = new HBox(searchTextField,emptyLabel,goButton);
        topHbox.setSpacing(5);

        VBox listViewBox = new VBox(topHbox,emptyLabel,listView);
        listViewBox.setPadding(new Insets(10,10,10,20));


        //-------------------------------------------------------------//
        GridPane userInfoPane = new GridPane();
        //userInfoPane.setGridLinesVisible(true);
        userInfoPane.setPadding(new Insets(50,50,50,50));
        userInfoPane.setMinWidth(150);
        userInfoPane.setVgap(16);
        userInfoPane.setHgap(20);

        Label nameLabel = new Label("Navn: ");
        nameText = new Text();
        GridPane.setConstraints(nameLabel,0,0);
        GridPane.setConstraints(nameText,1,0);

        Label phoneLabel = new Label("Telefon: ");
        phoneText = new Text();
        GridPane.setConstraints(phoneLabel,0,1);
        GridPane.setConstraints(phoneText,1,1);

        Label emailLabel = new Label("Email: ");
        emailText = new Text();
        GridPane.setConstraints(emailLabel,0,2);
        GridPane.setConstraints(emailText,1,2);

        Label adressLabel = new Label("Adresse: ");
        adressText = new Text();
        GridPane.setConstraints(adressLabel,0,3);
        GridPane.setConstraints(adressText,1,3);

        Label cprLabel = new Label("CPR Nr.: ");
        cprText = new Text();
        GridPane.setConstraints(cprLabel,0,4);
        GridPane.setConstraints(cprText,1,4);

        Label historyLabel = new Label("Historik: ");
        GridPane.setConstraints(historyLabel,0,5);
        Text historyText = new Text("Han er rig, no worries");
        GridPane.setConstraints(historyText,1,5);

        Label rkiLabel = new Label("RKI Kategori: ");
        GridPane.setConstraints(rkiLabel,5,0);
        Text rkiText = new Text("A");
        GridPane.setConstraints(rkiText,6,0);

        Label customerNumLabel = new Label("Kunde Nr.: ");
        GridPane.setConstraints(customerNumLabel, 5,1);
        Text customerNumText = new Text("01");                  //skal det med? er det id?
        GridPane.setConstraints(customerNumText,6,1);

        Label formerSalesLabel = new Label("Tidligere Salg: ");
        GridPane.setConstraints(formerSalesLabel, 5,3);
        Text formerSalesText = new Text("(3)");
        Button seButton = new Button("   Se   ");
        seButton.setOnAction(Klik -> UIController.instance().switchCenter(new CustomerSalesView().createView()));       //ku måske bruge et interface
        GridPane.setConstraints(seButton,7,3);
        GridPane.setConstraints(formerSalesText,6,3);

        Button editButton = new Button("  Edit  ");
        //GridPane.setConstraints(editButton,1,6);
        Button saveButton = new Button("  Save  ");
        saveButton.setDisable(true);
        // GridPane.setConstraints(saveButton,2,6);
        HBox crudBox = new HBox(editButton,saveButton);
        crudBox.setSpacing(5);
        GridPane.setConstraints(crudBox,0,8);

//        Label customerNumLabel = new Label("Kunde nummer: 00001");
//        GridPane.setConstraints(rkiLabel,11,0);

        userInfoPane.getChildren().addAll(
                nameLabel,
                nameText,
                phoneLabel,
                phoneText,
                emailLabel,
                emailText,
                adressLabel,
                adressText,
                cprLabel,
                cprText,
                historyLabel,
                historyText,
                rkiLabel,
                rkiText,
                customerNumLabel,
                customerNumText,
                formerSalesLabel,
                formerSalesText,
                seButton,
                crudBox
        );


        HBox gridBox = new HBox(userInfoPane);
        HBox root = new HBox(listViewBox,gridBox);
        root.setPrefWidth(700);
        return root;
    }

    private void setCustomerInfo(Customer customer) {
        this.customer = customer;
        if(customer == null) {
            return;
        }
        cprText.setText(customer.getCpr());
        emailText.setText(customer.getEmail());
        nameText.setText(customer.getFirstName() + " " + customer.getLastName());
        phoneText.setText(customer.getPhone());
        adressText.setText(customer.getAddress());
    }

}
