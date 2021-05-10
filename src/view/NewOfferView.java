package view;
// når der skal oprettes et tilbud/ordre
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
import logic.CsvWriter;
import logic.Dir;

import java.time.LocalDate;

public class NewOfferView {

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
        line.setStroke(Color.SILVER);
        salesGrid.add(line,0,1,70,1);





        Label cprLabel = new Label("CPR: ");
        GridPane.setConstraints(cprLabel,0,0);
        TextField cprTextField = new TextField("");
        cprTextField.setPromptText("XXXXXX-XXXX");
        GridPane.setConstraints(cprTextField,1,0);
        Button getButton = new Button("Get");
        GridPane.setConstraints(getButton,2,0);


        Label creditLabel = new Label("Credit Rating: ");
        GridPane.setConstraints(creditLabel,55,0);
        Text creditRatingTextField = new Text();
        GridPane.setConstraints(creditRatingTextField,56,0);

        Label nameLabel = new Label("Name: ");
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");
        GridPane.setConstraints(nameLabel,0,3);
        GridPane.setConstraints(nameTextField,1,3);

        Label phoneLabel = new Label("Phone: ");
        TextField phoneTextField = new TextField();
        phoneTextField.setPromptText("Phone Number");
        GridPane.setConstraints(phoneLabel,0,4);
        GridPane.setConstraints(phoneTextField,1,4);

        Label emailLabel = new Label("Email: ");
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        GridPane.setConstraints(emailLabel,0,5);
        GridPane.setConstraints(emailTextField,1,5);

        Label adressLabel = new Label("Adress: ");
        TextField adressTextField = new TextField();
        adressTextField.setPromptText("Adress");
        GridPane.setConstraints(adressLabel,0,6);
        GridPane.setConstraints(adressTextField,1,6);

        Label carModelLabel = new Label("Car Model: ");
        ComboBox carModelCombobox = new ComboBox<String>(FXCollections.observableArrayList("Slow", "Slower", "Henrik"));
        carModelCombobox.setPromptText("Car Model");
        GridPane.setConstraints(carModelLabel,0,7);
        GridPane.setConstraints(carModelCombobox,1,7);

        Label priceLabel = new Label("Price: ");
        TextField priceTextField = new TextField();
        priceTextField.setPromptText("Price");
        GridPane.setConstraints(priceLabel,0,8);
        GridPane.setConstraints(priceTextField,1,8);

        Label downPaymentLabel = new Label("Down Payment: ");
        TextField downPaymentTextField = new TextField();
        downPaymentTextField.setPromptText("Down Payment:");
        GridPane.setConstraints(downPaymentLabel,0,9);
        GridPane.setConstraints(downPaymentTextField,1,9);

        Label installmentPeriodLabel = new Label("Down Payment: ");
        ComboBox installmentPeriodCombobox = new ComboBox<String>(FXCollections.observableArrayList("1", "2", "3","4","5"));
        installmentPeriodCombobox.setPromptText("Number Of Years:");
        GridPane.setConstraints(installmentPeriodLabel,0,10);
        GridPane.setConstraints(installmentPeriodCombobox,1,10);

        Label startLabel = new Label("Start Date: ");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Pick Start Date");
        GridPane.setConstraints(startLabel,0,11);
        GridPane.setConstraints(startDatePicker,1,11);

        Label endLabel = new Label("End Date: ");
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPromptText("Pick End Date");
        GridPane.setConstraints(endLabel,0,12);
        GridPane.setConstraints(endDatePicker,1,12);

        Label salesPersonLabel = new Label("Sales Person: ");
        ComboBox salesPersonCombobox = new ComboBox<String>(FXCollections.observableArrayList("John", "Carsten", "Henning"));
        salesPersonCombobox.setPromptText("Sales Person:");
        GridPane.setConstraints(salesPersonLabel,0,13);
        GridPane.setConstraints(salesPersonCombobox,1,13);

        Button calcQuoteButton = new Button("Calculate Sales Offer");
        calcQuoteButton.setOnAction(click -> UIController.instance().switchCenter(new CaseDetailsView().createScene()));
//        calcQuoteButton.setOnAction(klick ->
//                CsvWriter.saveToCsv(
//                nameTextField.getText(),
//                phoneTextField.getText(),
//                emailTextField.getText(),
//                adressTextField.getText(),
//                carModelCombobox.getValue().toString(),
//                priceTextField.getText(),
//                downPaymentTextField.getText(),
//                installmentPeriodCombobox.getValue().toString(),
//                salesPersonCombobox.getValue().toString()
//                ));
        GridPane.setConstraints(calcQuoteButton,19,15);

        Button clearButton = new Button("      Clear       ");
        GridPane.setConstraints(clearButton,20,15);



        salesGrid.getChildren().addAll(

                cprLabel,
                cprTextField,
                getButton,
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
                carModelCombobox,
                priceLabel,
                priceTextField,
                downPaymentLabel,
                downPaymentTextField,
                installmentPeriodLabel,
                installmentPeriodCombobox,
                startLabel,
                startDatePicker,
                endLabel,
                endDatePicker,
                salesPersonLabel,
                salesPersonCombobox,
                calcQuoteButton,
                clearButton


        );

//        BooleanBinding booleanBind = cprTextField.textProperty().isEmpty()
//                .or(creditRatingTextField.textProperty().isEmpty())
//                .or(nameTextField.textProperty().isEmpty())
//                .or(phoneTextField.textProperty().isEmpty())
//                .or(emailTextField.textProperty().isEmpty())
//                .or(adressTextField.textProperty().isEmpty())
//                .or(carModelCombobox.valueProperty().isNull())
//                .or(priceTextField.textProperty().isEmpty())
//                .or(downPaymentTextField.textProperty().isEmpty())
//                .or(installmentPeriodCombobox.valueProperty().isNull())
//                .or(salesPersonCombobox.valueProperty().isNull());
//        calcQuoteButton.disableProperty().bind(booleanBind);




        HBox root = new HBox(salesGrid);
        root.setPrefWidth(700);
        return root;
    }

}