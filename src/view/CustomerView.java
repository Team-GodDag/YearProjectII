package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class CustomerView {

    public Node createView(){
        TextField searchTextField = new TextField();
        searchTextField.setPrefWidth(200);
        searchTextField.setPromptText("Søg efter CPR Nummer");
        Button goButton = new Button("Søg");
        goButton.setPrefWidth(50);
        ListView listView = new ListView();
        listView.setPrefHeight(600);
        Label emptyLabel = new Label(" ");

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


//
//
        Label nameLabel = new Label("Navn: ");
        GridPane.setConstraints(nameLabel,0,0);
        Text nameText = new Text("Lars Nielsen");
        GridPane.setConstraints(nameText,1,0);

        Label phoneLabel = new Label("Telefon: ");
        GridPane.setConstraints(phoneLabel,0,1);
        Text phoneText = new Text("40316887");
        GridPane.setConstraints(phoneText,1,1);

        Label emailLabel = new Label("Email: ");
        GridPane.setConstraints(emailLabel,0,2);
        Text emailText = new Text("lars@hotmail.com");
        GridPane.setConstraints(emailText,1,2);

        Label adressLabel = new Label("Adresse: ");
        GridPane.setConstraints(adressLabel,0,3);
        Text adressText = new Text("Skur 3 TV.");
        GridPane.setConstraints(adressText,1,3);

        Label cprLabel = new Label("CPR Nr.: ");
        GridPane.setConstraints(cprLabel,0,4);
        Text cprText = new Text("123456-7899");
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
        Text customerNumText = new Text("01");
        GridPane.setConstraints(customerNumText,6,1);

        Label formerSalesLabel = new Label("Tidligere Salg: ");
        GridPane.setConstraints(formerSalesLabel, 5,3);
        Text formerSalesText = new Text("(3)");
        Button seButton = new Button("   Se   ");
        seButton.setOnAction(Klik -> UIController.instance().switchCenter(new CustomerSalesView().createView()));
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

}
