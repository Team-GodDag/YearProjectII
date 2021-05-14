package view;
// når der skal oprettes et tilbud/ordre
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.*;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class NewOfferView {
    CreditRateToInterestRate creditRateToInterestRate = new CreditRateToInterestRate();
    DownpaymentToInterestRate downpaymentToInterestRate = new DownpaymentToInterestRate();
    PaymentPeriodToInterestRate paymentPeriodToInterestRate = new PaymentPeriodToInterestRate();
    TotalInterest totalInterest = new TotalInterest();


    public Node createView(){
        Stage window = new Stage();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();


        //GridPane Design//
        //------------------------------------//
        GridPane salesGrid = new GridPane();
        salesGrid.setPadding(new Insets(15,10,10,20));
        salesGrid.setMinSize(10, 10);
        //salesGrid.setGridLinesVisible(true);
        salesGrid.setVgap(16);
        salesGrid.setHgap(5);
        //------------------------------------//

//        Line topLine = new Line(100,150,930,150);
//        topLine.setStroke(Color.SILVER);
//        salesGrid.add(topLine,0,1,2,1);
//
//        Line buttomLine = new Line(100,150,930,150);
//        buttomLine.setStroke(Color.SILVER);
//        salesGrid.add(buttomLine,0,16,2,1);
//
//        Line middleLine = new Line();
//        middleLine.setStartX(500);
//        middleLine.setStartY(-180);
//        middleLine.setEndX(500);
//        middleLine.setEndY(320);
//        middleLine.setStroke(Color.SILVER);
//        salesGrid.add(middleLine,6,0,15,18);
//
        Text leftDescriptionText = new Text("Kunde Oplysninger");
        leftDescriptionText.setUnderline(true);
        GridPane.setConstraints(leftDescriptionText,0,2);

        Text rightDescriptionText = new Text("Udregnet Tilbud:");
        rightDescriptionText.setUnderline(true);
        GridPane.setConstraints(rightDescriptionText,7,2);








        Label cprLabel = new Label("CPR: ");
        GridPane.setConstraints(cprLabel,0,0);
        TextField cprTextField = new TextField("");
        cprTextField.setPromptText("dd-mm-yy-xx");
        GridPane.setConstraints(cprTextField,1,0);
        Button getButton = new Button("Get");

        GridPane.setConstraints(getButton,2,0);



        Text creditRatingText = new Text();
        creditRatingText.setFont(Font.font("Verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
        creditRatingText.setFill(Color.GREEN);

        Label creditLabel = new Label("Credit Rating: ");
        GridPane.setConstraints(creditLabel,3,0);
        creditRatingText.setTranslateX(425);
        creditRatingText.setTranslateY(0);
        //GridPane.setConstraints(creditRatingText,4,0);



        Label nameLabel = new Label("Name: ");
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");
        GridPane.setConstraints(nameLabel,0,4);
        GridPane.setConstraints(nameTextField,1,4);

        Label phoneLabel = new Label("Phone: ");
        TextField phoneTextField = new TextField();
        phoneTextField.setPromptText("Phone Number");
        GridPane.setConstraints(phoneLabel,0,5);
        GridPane.setConstraints(phoneTextField,1,5);

        Label emailLabel = new Label("Email: ");
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        GridPane.setConstraints(emailLabel,0,6);
        GridPane.setConstraints(emailTextField,1,6);

        Label adressLabel = new Label("Adress: ");
        TextField adressTextField = new TextField();
        adressTextField.setPromptText("Adress");
        GridPane.setConstraints(adressLabel,0,7);
        GridPane.setConstraints(adressTextField,1,7);

        Label carModelLabel = new Label("Car Model: ");
        ComboBox carModelCombobox = new ComboBox<String>(FXCollections.observableArrayList("Slow", "Slower", "Henrik"));
        carModelCombobox.setPromptText("Car Model");
        GridPane.setConstraints(carModelLabel,0,8);
        GridPane.setConstraints(carModelCombobox,1,8);

        Label priceLabel = new Label("Price: ");
        TextField priceTextField = new TextField();
        priceTextField.setPromptText("Price");
        GridPane.setConstraints(priceLabel,0,9);
        GridPane.setConstraints(priceTextField,1,9);

        Label downPaymentLabel = new Label("Down Payment: ");
        TextField downPaymentTextField = new TextField();
        downPaymentTextField.setPromptText("Down Payment:");
        GridPane.setConstraints(downPaymentLabel,0,10);
        GridPane.setConstraints(downPaymentTextField,1,10);

        Label installmentPeriodLabel = new Label("Payment Period: ");
        ComboBox installmentPeriodCombobox = new ComboBox<Integer>(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        installmentPeriodCombobox.setPromptText("Number Of Years:");
        GridPane.setConstraints(installmentPeriodLabel,0,11);
        GridPane.setConstraints(installmentPeriodCombobox,1,11);

        Label startLabel = new Label("Start Date: ");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Pick Start Date");
        GridPane.setConstraints(startLabel,0,12);
        GridPane.setConstraints(startDatePicker,1,12);

        Label endLabel = new Label("End Date: ");
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPromptText("Pick End Date");
        GridPane.setConstraints(endLabel,0,13);
        GridPane.setConstraints(endDatePicker,1,13);

        Label salesPersonLabel = new Label("Sales Person: ");
        ComboBox salesPersonCombobox = new ComboBox<String>(FXCollections.observableArrayList("John", "Carsten", "Henning"));
        salesPersonCombobox.setPromptText("Sales Person:");
        GridPane.setConstraints(salesPersonLabel,0,14);
        GridPane.setConstraints(salesPersonCombobox,1,14);

        Button calcQuoteButton = new Button("Calculate Sales Offer");
        //calcQuoteButton.setOnAction(click -> UIController.instance().switchCenter(new CaseDetailsView().createScene()));
//        calcQuoteButton.setOnAction(click -> {
//            FileChooser fileChooser = new FileChooser();
//
//            //Set extension filter for text files
//            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Csv files (*.csv)", "*.csv");
//            fileChooser.getExtensionFilters().add(extFilter);
//
//            //Show save file dialog
//            File file = fileChooser.showSaveDialog(window);
//
//            if (file != null) {
//                SaveToCsv.saveOffer(
//                file,
//                nameTextField.getText(),
//                phoneTextField.getText(),
//                emailTextField.getText(),
//                adressTextField.getText(),
//                carModelCombobox.getValue().toString(),
//                priceTextField.getText(),
//                downPaymentTextField.getText(),
//                installmentPeriodCombobox.getValue().toString(),
//                salesPersonCombobox.getValue().toString()
//                        );
//            }
//        });

        GridPane.setConstraints(calcQuoteButton,0,17);

        Button clearButton = new Button("      Clear       ");
        GridPane.setConstraints(clearButton,1,17);

        //------------------------------------//
                    //Right Side//



        Label caseNoLbl = new Label("Case No.:");
        Text caseNoTxt = new Text();
        GridPane.setConstraints(caseNoLbl,7,4);
        GridPane.setConstraints(caseNoTxt,8,4);

        Label createdLbl = new Label("Created:");
        Text createdTxt = new Text();
        GridPane.setConstraints(createdLbl,7,5);
        GridPane.setConstraints(createdTxt,8,5);

        Label buyerLbl = new Label("Buyer:");
        Text buyerTxt = new Text();
        GridPane.setConstraints(buyerLbl,7,6);
        GridPane.setConstraints(buyerTxt,8,6);

        Label offerSalesPersLbl = new Label("Salesperson:");
        Text offerSalesPersTxt = new Text();
        GridPane.setConstraints(offerSalesPersLbl,7,7);
        GridPane.setConstraints(offerSalesPersTxt,8,7);

        Label cprLbl = new Label("CPR:");
        Text cprTxt = new Text();
        GridPane.setConstraints(cprLbl,7,8);
        GridPane.setConstraints(cprTxt,8,8);

        Label creditWorthLbl = new Label("Creditworthiness:");
        Text creditWorthTxt = new Text();
        GridPane.setConstraints(creditWorthLbl,7,9);
        GridPane.setConstraints(creditWorthTxt,8,9);

        Label carModelLbl = new Label("Car model:");
        Text carModelTxt = new Text();
        GridPane.setConstraints(carModelLbl,7,10);
        GridPane.setConstraints(carModelTxt,8,10);

        Label carPriceLbl = new Label("Car price:");
        Text carPriceTxt = new Text("");
        GridPane.setConstraints(carPriceLbl,7,11);
        GridPane.setConstraints(carPriceTxt,8,11);

        Label approvedByLbl = new Label("Approved by:");
        Text approvedByTxt = new Text();
        GridPane.setConstraints(approvedByLbl,7,12);
        GridPane.setConstraints(approvedByTxt,8,12);


        Label downPayLbl = new Label("Down payment:");
        Text downPayTxt = new Text("");
        GridPane.setConstraints(downPayLbl,30,4);
        GridPane.setConstraints(downPayTxt,31,4);

        Label creditRatingLbl = new Label("Credit Rating:");
        Text creditRatingTxt = new Text();
        GridPane.setConstraints(creditRatingLbl,30,5);
        GridPane.setConstraints(creditRatingTxt,31,5);

        Label bankInterestLbl = new Label("Bank Interest:");
        Text bankInterestTxt = new Text();
        GridPane.setConstraints(bankInterestLbl,30,6);
        GridPane.setConstraints(bankInterestTxt,31,6);

        Label downPayInterestLbl = new Label("Downpayment Interest");
        Text downPayInterestTxt = new Text();
        GridPane.setConstraints(downPayInterestLbl,30,7);
        GridPane.setConstraints(downPayInterestTxt,31,7);

        Label periodPayInterestLbl = new Label("Period Interest:");
        Text periodPayInterestTxt = new Text();
        GridPane.setConstraints(periodPayInterestLbl,30,8);
        GridPane.setConstraints(periodPayInterestTxt,31,8);


        Label totalInterestRateLbl = new Label("Total Interest Rate:");
        Text totalInterestRateTxt = new Text();
        GridPane.setConstraints(totalInterestRateLbl,30,9);
        GridPane.setConstraints(totalInterestRateTxt,31,9);

        Label totalPriceLbl = new Label("Total Price:");
        Text totalPriceTxt = new Text();
        GridPane.setConstraints(totalPriceLbl,30,10);
        GridPane.setConstraints(totalPriceTxt,31,10);

        Label startDateLbl = new Label("Start date:");
        Text startDateTxt = new Text();
        GridPane.setConstraints(startDateLbl,30,11);
        GridPane.setConstraints(startDateTxt,31,11);

        Label endDateLbl = new Label("End date:");
        Text endDateTxt = new Text();
        GridPane.setConstraints(endDateLbl,30,12);
        GridPane.setConstraints(endDateTxt,31,12);

//        Button printBtn = new Button("Print as PDF");
//        Button csvBtn = new Button("Save as CSV");
//        GridPane.setConstraints(createdLbl,20,6);
//        GridPane.setConstraints(createdTxt,21,6);



        salesGrid.getChildren().addAll(

                leftDescriptionText,
                cprLabel,
                cprTextField,
                getButton,
                creditLabel,
                creditRatingText,
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
                clearButton,
                rightDescriptionText,
                caseNoLbl,
                caseNoTxt,
                carPriceLbl,
                carPriceTxt,
                createdLbl,
                createdTxt,
                buyerLbl,
                buyerTxt,
                offerSalesPersLbl,
                offerSalesPersTxt,
                cprLbl,
                cprTxt,
                creditWorthLbl,
                creditWorthTxt,
                carModelLbl,
                carModelTxt,
                approvedByLbl,
                approvedByTxt,
                downPayLbl,
                downPayTxt,
                creditRatingLbl,
                creditRatingTxt,
                bankInterestLbl,
                bankInterestTxt,
                totalInterestRateLbl,
                totalInterestRateTxt,
                downPayInterestLbl,
                downPayInterestTxt,
                periodPayInterestLbl,
                periodPayInterestTxt,
                totalPriceLbl,
                totalPriceTxt,
                startDateLbl,
                startDateTxt,
                endDateLbl,
                endDateTxt


        );

//        BooleanBinding booleanBind = cprTextField.textProperty().isEmpty()
//                .or(creditRatingText.textProperty().isEmpty())
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
//        Rating creditRating = CreditRator.i().rate(cprTextField.getText());
        //get current date time with Date()
//        Date date = new Date();

        getButton.setOnAction(click -> CreditRator.i().rate(cprTextField.getText()));
        getButton.setOnAction(click -> creditRatingText.setText(String.valueOf(CreditRator.i().rate(cprTextField.getText()))));
        calcQuoteButton.setOnAction(click -> {
            creditRatingTxt.setText(creditRatingText.getText());
            bankInterestTxt.setText((totalInterest.rkiInterestCalc(creditRatingTxt.getText())) + " %");
            carPriceTxt.setText(priceTextField.getText() + " Kr.");
            downPayTxt.setText(downPaymentTextField.getText()+ " Kr.");
            downPayInterestTxt.setText(totalInterest.downPaymentCalc(Double.valueOf(priceTextField.getText()),Double.valueOf(downPaymentTextField.getText()))+ " %");
            createdTxt.setText(dateFormat.format(date));
            buyerTxt.setText(nameLabel.getText());
            offerSalesPersTxt.setText(salesPersonCombobox.getValue().toString());
            cprTxt.setText(cprTextField.getText());
            carModelTxt.setText(carModelCombobox.getValue().toString());
            periodPayInterestTxt.setText(totalInterest.periodInterestRate(Integer.valueOf((Integer) installmentPeriodCombobox.getValue()))+ " %");
            totalInterestRateTxt.setText(String.valueOf(totalInterest.calculateTotalInterest()) + " %");
            totalPriceTxt.setText(String.valueOf(totalInterest.totalCarPrice(Double.valueOf(priceTextField.getText()))) + " Kr.");
            //totalInterestRateTxt.setText(totalInterest.calculateTotalInterest(creditRateToInterestRate.rkiInterestCalc(creditRatingTxt.getText()), 2,3));
        });

        //creditRatingText.getStart
        System.out.println();


        HBox root = new HBox(salesGrid);
        root.setPrefWidth(700);
        return root;
    }

}