package view;
// Start side
// Viser nuværende kunder i databasen
import entities.Customer;
import dataaccessors.CustomerDataAccessor;
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
import java.util.Optional;


public class CustomerView {

    private Customer customer;
    private Text nameText, phoneText, emailText, adressText, cprText, historyText, customerNumText;
    private TextField searchTextField;
    private ListView listView;

    public Node createView() {

        searchTextField = new TextField();
        searchTextField.setPrefWidth(200);
        searchTextField.setPromptText("Søg efter CPR Nummer");


        Button goButton = new Button("Søg");
        goButton.setPrefWidth(50);
        goButton.setOnAction(click -> {
            Customer searchedCustomer = CustomerDataAccessor.getCustomerDataAccess().getCustomerByCpr(searchTextField.getText());
            setCustomerInfo(searchedCustomer);
        });


//FILTERED LISTVIEW ---------------------START
        listView = new ListView();
        listView.setPrefHeight(600);
        List<Customer> customerList = new ArrayList<Customer>();        //skal den have sit eget interface?
        ObservableList<Customer> observableCustomerlist = FXCollections.observableArrayList(CustomerDataAccessor.getCustomerDataAccess().getAllCustomers());

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observableValue, Customer prevCustomer, Customer nextCustomer) {
                setCustomerInfo(nextCustomer);
            }
        });



        FilteredList<Customer> filteredCustomerList = new FilteredList<>(observableCustomerlist, p -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCustomerList.setPredicate(customer -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowercaseFilter = newValue.toLowerCase();
                if (customer.getCpr().toLowerCase().contains(lowercaseFilter)) {
                    return true;
                } else if(customer.getFirstName().toLowerCase().contains(lowercaseFilter)) {
                    return true;
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

//LISTVIEW ----------------------------------------------------------------------------------END

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
//------------------------------------------------------------------------
        Label cprLabel = new Label("CPR Nr.: ");    //MÅ IKKE VISES!!!!
        cprText = new Text();
        GridPane.setConstraints(cprLabel,0,4);
        GridPane.setConstraints(cprText,1,4);
//------------------------------------------------------------------------

        Label historyLabel = new Label("Historik: ");
        GridPane.setConstraints(historyLabel,0,5);
        historyText = new Text();
        GridPane.setConstraints(historyText,1,5);

        Label customerNumLabel = new Label("Kundenummer.: ");
        GridPane.setConstraints(customerNumLabel, 5,1);
        customerNumText = new Text();                  //skal det med? er det id?
        GridPane.setConstraints(customerNumText,6,1);

        Label formerSalesLabel = new Label("Tidligere Salg: ");
        GridPane.setConstraints(formerSalesLabel, 5,3);
        //Text formerSalesText = new Text("(3)");
        Button seButton = new Button("   Se   ");
        seButton.setOnAction(Klik -> UIManager.instance().switchCenter(new CustomerSalesView().createView(customer)));       //ku måske bruge et interface
        GridPane.setConstraints(seButton,7,3);
        //GridPane.setConstraints(formerSalesText,6,3);

        Button blacklistButton = new Button("Blacklist");   //skal sidde et bedre sted
        blacklistButton.setVisible(false);
        blacklistButton.setOnAction(click -> {
            blacklistWarning();
            blacklistButton.setVisible(false);
        });

        Button editButton = new Button("  Rediger  ");
        Button saveButton = new Button("  Gem  ");
        Button cancelEditButton = new Button("Annuller");

        editButton.setOnAction(click -> {
            editButton.setDisable(true);
            cancelEditButton.setVisible(true);
            saveButton.setVisible(true);
            blacklistButton.setVisible(true);
        });

        saveButton.setVisible(false);
        saveButton.setOnAction(click -> {
            CustomerDataAccessor.getCustomerDataAccess().updateCustomer(customer);
            saveButton.setVisible(false);
            cancelEditButton.setVisible(false);
            blacklistButton.setVisible(false);
        });

        cancelEditButton.setVisible(false);
        cancelEditButton.setOnAction(click -> {
            editButton.setDisable(false);
            saveButton.setVisible(false);
            cancelEditButton.setVisible(false);
            blacklistButton.setVisible(false);
            setCustomerInfo(customer);
        });






        //saveButton.setDisable(true);
        // GridPane.setConstraints(saveButton,2,6);
//        HBox crudBox = new HBox(editButton, saveButton, blacklistButton, cancelEditButton);
//        crudBox.setSpacing(5);
        userInfoPane.addRow(10, editButton, saveButton, blacklistButton, cancelEditButton);
//        GridPane.setConstraints(crudBox,0,8);

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
                customerNumLabel,
                customerNumText,
                formerSalesLabel,
                //formerSalesText,
                seButton
//                blacklistButton,
//                cancelEditButton,
//                crudBox
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
        historyText.setText(checkCustomerHistory(customer));
        customerNumText.setText(String.valueOf(customer.getId()));

    }

    public String checkCustomerHistory(Customer customer) {
        if(customer.isGoodGuy()) {
            return "God";
        } else {
            return "Dårlig";
        }
    }

    private void blacklistCustomer(Customer customer) {
        customer.setGoodGuy(false);
        CustomerDataAccessor.getCustomerDataAccess().updateCustomer(customer);
    }

    private void blacklistWarning() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Advarsel");
        alert.setHeaderText("Er du sikker?");
        alert.setContentText("En blacklistet kunde vil få annulleret igangværende køb og kan ikke handle her igen. Er du indforstået med dette, tryk da OK.");
        alert.setHeight(400);

        Optional<ButtonType> result  = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            blacklistCustomer(customer);
        }
    }

}
