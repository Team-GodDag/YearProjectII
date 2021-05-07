package view;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class QuotationView {

    public Node createView(){

        //GridPane Design//
        //------------------------------------//
        GridPane salesGrid = new GridPane();
        salesGrid.setPadding(new Insets(15,10,10,20));
        salesGrid.setMinSize(10, 10);
        // salesGrid.setAlignment(Pos.TOP_CENTER);
        salesGrid.setVgap(16);
        salesGrid.setHgap(5);
        //------------------------------------//

        Line line = new Line(100,150,1010,150);
        salesGrid.add(line,0,1,2,1);





        Label cprLabel = new Label("CPR: ");
        GridPane.setConstraints(cprLabel,0,0);
        TextField cprTextField = new TextField("");
        cprTextField.setDisable(true);
        GridPane.setConstraints(cprTextField,1,0);


        Label creditLabel = new Label("Credit Rating: ");
        GridPane.setConstraints(creditLabel,69,0);
        Text creditRatingTextField = new Text("A");
        GridPane.setConstraints(creditRatingTextField,70,0);

        Label nameLabel = new Label("Name: ");
        TextField nameTextField = new TextField();
        nameTextField.setDisable(true);
        GridPane.setConstraints(nameLabel,0,3);
        GridPane.setConstraints(nameTextField,1,3);

        Label phoneLabel = new Label("Phone: ");
        TextField phoneTextField = new TextField();
        phoneTextField.setDisable(true);
        GridPane.setConstraints(phoneLabel,0,4);
        GridPane.setConstraints(phoneTextField,1,4);

        Label emailLabel = new Label("Email: ");
        TextField emailTextField = new TextField();
        emailTextField.setDisable(true);
        GridPane.setConstraints(emailLabel,0,5);
        GridPane.setConstraints(emailTextField,1,5);

        Label adressLabel = new Label("Adress: ");
        TextField adressTextField = new TextField();
        adressTextField.setDisable(true);
        GridPane.setConstraints(adressLabel,0,6);
        GridPane.setConstraints(adressTextField,1,6);

        Label carModelLabel = new Label("Car Model: ");
        TextField carModelTextField = new TextField();
        carModelTextField.setDisable(true);
        GridPane.setConstraints(carModelLabel,0,7);
        GridPane.setConstraints(carModelTextField,1,7);

        Label priceLabel = new Label("Price: ");
        TextField priceTextField = new TextField();
        priceTextField.setDisable(true);
        GridPane.setConstraints(priceLabel,0,8);
        GridPane.setConstraints(priceTextField,1,8);

        Label downPaymentLabel = new Label("Down Payment: ");
        TextField downPaymentTextField = new TextField();
        downPaymentTextField.setDisable(true);
        GridPane.setConstraints(downPaymentLabel,0,9);
        GridPane.setConstraints(downPaymentTextField,1,9);

        Label installmentPeriodLabel = new Label("Down Payment: ");
        TextField installmentPeriodTextField = new TextField();
        installmentPeriodTextField.setDisable(true);
        GridPane.setConstraints(installmentPeriodLabel,0,10);
        GridPane.setConstraints(installmentPeriodTextField,1,10);

        Label salesPersonLabel = new Label("Sales Person: ");
        TextField salesPersonTextField = new TextField();
        salesPersonTextField.setDisable(true);
        GridPane.setConstraints(salesPersonLabel,0,11);
        GridPane.setConstraints(salesPersonTextField,1,11);

        Button printQuoteButton = new Button("print Quote");
        GridPane.setConstraints(printQuoteButton,19,15);


        salesGrid.getChildren().addAll(

                cprLabel,
                cprTextField,
                creditLabel,
                creditRatingTextField,
                nameLabel,
                nameTextField,
                phoneLabel,
                phoneTextField,
                emailLabel,
                emailTextField,
                adressLabel,
                adressTextField,
                carModelLabel,
                carModelTextField,
                priceLabel,
                priceTextField,
                downPaymentLabel,
                downPaymentTextField,
                installmentPeriodLabel,
                installmentPeriodTextField,
                salesPersonLabel,
                salesPersonTextField,
                printQuoteButton

        );


        HBox root = new HBox(salesGrid);
        root.setPrefWidth(700);
        return root;
    }

}
