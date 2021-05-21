package view;
// når der skal oprettes et tilbud/ordre

import data.CreditRator;
import entities.Car;
import factories.CarListFactory;
import factories.SalesPersonListFactory;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class NewOfferView {
    SaveToCsv saveToCsv = new SaveToCsv();
    NotifyWindow notifyWindow = new NotifyWindow();

    PeriodCalculator periodCalculator = new PeriodCalculator();
    PriceFormat priceFormat = new PriceFormat();
    SalesLimit salesLimit = new SalesLimit();
    MonthPayCalc monthPayCalc = new MonthPayCalc();
    PaymentCalc paymentCalc = new PaymentCalc();
    WriteOnlyNumbers writeOnlyNumbers = new WriteOnlyNumbers();

    TextField nameTextField, priceTextField;
    Text creditRatingText;




    public Node createView() {
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
        salesGrid.setHgap(16);
        //------------------------------------//

        Line topLine = new Line(100,150,1095,150);
        topLine.setStroke(Color.SILVER);
        salesGrid.add(topLine,0,1,13,1);

        Line buttomLine = new Line(100,150,1095,150);
        buttomLine.setStroke(Color.SILVER);
        salesGrid.add(buttomLine,0,16,13,1);

        Line middleLine = new Line();
        middleLine.setStartX(350);
        middleLine.setStartY(-100);
        middleLine.setEndX(350);
        middleLine.setEndY(330);
        middleLine.setStroke(Color.SILVER);
        salesGrid.add(middleLine,4,0,9,20);
//
        Text leftDescriptionText = new Text("Kunde Oplysninger");
        leftDescriptionText.setUnderline(true);
        GridPane.setConstraints(leftDescriptionText,0,2);

        Text rightDescriptionText = new Text("Udregnet Tilbud:");
        rightDescriptionText.setUnderline(true);
        GridPane.setConstraints(rightDescriptionText,7,2);

        TextFieldCheck textFieldCheck = new TextFieldCheck();
        Label cprLabel = new Label("CPR: ");
        GridPane.setConstraints(cprLabel,0,0);
        TextField cprTextField = new TextField("");
        writeOnlyNumbers.input(cprTextField);
        textFieldCheck.addTextLimiter(cprTextField,10);

//        cprTextField.setTranslateX(40);
//        cprTextField.setTranslateY(0);
        cprTextField.setPromptText("ddMMyyXXXX");
        GridPane.setConstraints(cprTextField,1,0);
        Button getButton = new Button("Get Rating");
//        getButton.setTranslateX(200);

        GridPane.setConstraints(getButton,2,0);



        creditRatingText = new Text();
        creditRatingText.setFont(Font.font("Verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
        creditRatingText.setFill(Color.GREEN);

        Label creditLabel = new Label("Credit Rating: ");
//        creditLabel.setTranslateX(280);
//        creditLabel.setTranslateY(0);
        GridPane.setConstraints(creditLabel,3,0);
//        creditRatingText.setTranslateX(360);
//        creditRatingText.setTranslateY(0);
        GridPane.setConstraints(creditRatingText,4,0);


        Label nameLabel = new Label("Name: ");
        nameTextField = new TextField();
        nameTextField.setPromptText("Name");
        GridPane.setConstraints(nameLabel,0,4);
        GridPane.setConstraints(nameTextField,1,4);

        Label phoneLabel = new Label("Phone: ");
        TextField phoneTextField = new TextField();
        phoneTextField.setPromptText("Phone Number");
        writeOnlyNumbers.input(phoneTextField);
        textFieldCheck.addTextLimiter(phoneTextField,8);


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

        Label zipCodeLabel = new Label("Zip Code: ");
        TextField zipCodeTextField = new TextField();
        zipCodeTextField.setPromptText("Zip Code");
        textFieldCheck.addTextLimiter(zipCodeTextField,4);
        writeOnlyNumbers.input(zipCodeTextField);
        GridPane.setConstraints(zipCodeLabel,0,8);
        GridPane.setConstraints(zipCodeTextField,1,8);

        Label carModelLabel = new Label("Car Model: ");
        ComboBox carModelCombobox = new ComboBox<>(FXCollections.observableArrayList(CarListFactory.createCarList()));
        carModelCombobox.setPromptText("Car Model");
        GridPane.setConstraints(carModelLabel,0,9);
        GridPane.setConstraints(carModelCombobox,1,9);
        carModelCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    setCarInfo((Car)newValue);

                }
            }
        );

        Label priceLabel = new Label("Price: ");
        priceTextField = new TextField();
        priceTextField.setPromptText("Price");
        GridPane.setConstraints(priceLabel,0,10);
        GridPane.setConstraints(priceTextField,1,10);

        Label downPaymentLabel = new Label("Down Payment: ");
        TextField downPaymentTextField = new TextField();
        downPaymentTextField.setPromptText("Down Payment:");
        writeOnlyNumbers.input(downPaymentTextField);
        downPaymentTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(Double.valueOf(downPaymentTextField.getText()) > Double.valueOf(priceTextField.getText())  ){
                    downPaymentTextField.setText("");
                }
            }
        });

        GridPane.setConstraints(downPaymentLabel,0,11);
        GridPane.setConstraints(downPaymentTextField,1,11);

        Label startLabel = new Label("Start Date: ");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Pick Start Date");
        GridPane.setConstraints(startLabel,0,12);
        GridPane.setConstraints(startDatePicker,1,12);
        Label endLabel = new Label("End Date: ");
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPromptText("Pick End Date");

        endDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate endDate, boolean empty) {
                if (endDate.isBefore(startDatePicker.getValue())) {
                    setStyle("-fx-background-color: #ffc0cb; -fx-text-fill: darkgray;");
                    setDisable(true);
                }
            }
        });

        GridPane.setConstraints(endLabel,0,13);
        GridPane.setConstraints(endDatePicker,1,13);

        Label salesPersonLabel = new Label("Sales Person: ");
        ComboBox salesPersonCombobox = new ComboBox<>(FXCollections.observableArrayList(SalesPersonListFactory.createSalesPersonList()));
        salesPersonCombobox.setPromptText("Sales Person:");
        GridPane.setConstraints(salesPersonLabel,0,14);
        GridPane.setConstraints(salesPersonCombobox,1,14);

        Button calcQuoteButton = new Button("Calculate Sales Offer");


        GridPane.setConstraints(calcQuoteButton,0,17);

        Button clearButton = new Button("      Clear       ");
//        clearButton.setTranslateX(150);
//        clearButton.setTranslateY(598);
        GridPane.setConstraints(clearButton,1,17);





        //------------------------------------//
                    //Right Side//

        Label createdLbl = new Label("Created:");
        Text createdTxt = new Text();
        GridPane.setConstraints(createdLbl,7,4);
        GridPane.setConstraints(createdTxt,8,4);

        Label buyerLbl = new Label("Buyer:");
        Text buyerTxt = new Text();
        GridPane.setConstraints(buyerLbl,7,5);
        GridPane.setConstraints(buyerTxt,8,5);

        Label carModelLbl = new Label("Car model:");
        Text carModelTxt = new Text();
        GridPane.setConstraints(carModelLbl,7,6);
        GridPane.setConstraints(carModelTxt,8,6);

        Label carPriceLbl = new Label("Car price:");
        Text carPriceTxt = new Text("");
        GridPane.setConstraints(carPriceLbl,7,7);
        GridPane.setConstraints(carPriceTxt,8,7,2,1);

        Label downPayLbl = new Label("Down payment:");
        Text downPayTxt = new Text("");
        GridPane.setConstraints(downPayLbl,7,8);
        GridPane.setConstraints(downPayTxt,8,8);

        Label payPeriodLbl = new Label("Payment Period:");
        Text payPeriodTxt = new Text("");
        GridPane.setConstraints(payPeriodLbl,7,9);
        GridPane.setConstraints(payPeriodTxt,8,9);

        Label offerSalesPersLbl = new Label("Salesperson:");
        Text offerSalesPersTxt = new Text();
        GridPane.setConstraints(offerSalesPersLbl,7,10);
        GridPane.setConstraints(offerSalesPersTxt,8,10);

        Label approvedByLbl = new Label("Approved by:");
        Text approvedByTxt = new Text();
        GridPane.setConstraints(approvedByLbl,7,11);
        GridPane.setConstraints(approvedByTxt,8,11,3,1);

        Label creditRatingLbl = new Label("Credit Rating:");
        Text creditRatingTxt = new Text();
        GridPane.setConstraints(creditRatingLbl,11,4);
        GridPane.setConstraints(creditRatingTxt,12,4);

        Label bankInterestLbl = new Label("Bank Interest:");
        Text bankInterestTxt = new Text();
        GridPane.setConstraints(bankInterestLbl,11,5);
        GridPane.setConstraints(bankInterestTxt,12,5);

        Label downPayInterestLbl = new Label("Downpayment Interest");
        Text downPayInterestTxt = new Text();
        GridPane.setConstraints(downPayInterestLbl,11,6);
        GridPane.setConstraints(downPayInterestTxt,12,6);

        Label periodPayInterestLbl = new Label("Period Interest:");
        Text periodPayInterestTxt = new Text();
        GridPane.setConstraints(periodPayInterestLbl,11,7);
        GridPane.setConstraints(periodPayInterestTxt,12,7);


        Label totalInterestRateLbl = new Label("Total Interest Rate:");
        Text totalInterestRateTxt = new Text();
        GridPane.setConstraints(totalInterestRateLbl,11,8);
        GridPane.setConstraints(totalInterestRateTxt,12,8);

        Text amountTxt = new Text("Beløb: ");
        amountTxt.setUnderline(true);
        GridPane.setConstraints(amountTxt,11,10);


        Label priceAfterDownPayLbl = new Label("Price After Down Payment:");
        Text priceAfterDownPayTxt = new Text();
        GridPane.setConstraints(priceAfterDownPayLbl,11,11);
        GridPane.setConstraints(priceAfterDownPayTxt,12,11,4,1);

        Label totalPriceLbl = new Label("Total Price With Interests:");
        Text totalPriceTxt = new Text();
        GridPane.setConstraints(totalPriceLbl,11,12);
        GridPane.setConstraints(totalPriceTxt,12,12,3,1);

        Label monthPayLbl = new Label("Monthly Payment:");
        Text monthPayTxt = new Text();
        GridPane.setConstraints(monthPayLbl,11,13);
        GridPane.setConstraints(monthPayTxt,12,13,3,1);


        Button acceptBtn = new Button("Accept Offer");
        acceptBtn.setDisable(true);
//        acceptBtn.setTranslateX(520);
//        acceptBtn.setTranslateY(598);
        Button csvBtn = new Button("Save as CSV");
        csvBtn.setDisable(true);
        GridPane.setConstraints(csvBtn,7,17);
        GridPane.setConstraints(acceptBtn,8,17);



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
                zipCodeLabel,
                zipCodeTextField,
                carModelLabel,
                carModelCombobox,
                priceLabel,
                priceTextField,
                downPaymentLabel,
                downPaymentTextField,
                startLabel,
                startDatePicker,
                endLabel,
                endDatePicker,
                salesPersonLabel,
                salesPersonCombobox,
                calcQuoteButton,
                clearButton,
                rightDescriptionText,
                carPriceLbl,
                carPriceTxt,
                createdLbl,
                createdTxt,
                buyerLbl,
                buyerTxt,
                offerSalesPersLbl,
                offerSalesPersTxt,
                carModelLbl,
                carModelTxt,
                approvedByLbl,
                approvedByTxt,
                payPeriodLbl,
                payPeriodTxt,
                downPayLbl,
                downPayTxt,
                creditRatingLbl,
                creditRatingTxt,
                bankInterestLbl,
                bankInterestTxt,
                priceAfterDownPayLbl,
                priceAfterDownPayTxt,
                totalInterestRateLbl,
                totalInterestRateTxt,
                downPayInterestLbl,
                downPayInterestTxt,
                periodPayInterestLbl,
                periodPayInterestTxt,
                amountTxt,
                totalPriceLbl,
                totalPriceTxt,
                monthPayLbl,
                monthPayTxt,
                csvBtn,
                acceptBtn
        );
        BooleanBinding cprBooleanBind = cprTextField.textProperty().length().isNotEqualTo(10);
        getButton.disableProperty().bind(cprBooleanBind);


        BooleanBinding calcBind = (
                creditRatingText.textProperty().isEmpty()
                .or(nameTextField.textProperty().isEmpty())
                .or(phoneTextField.textProperty().isEmpty())
                .or(emailTextField.textProperty().isEmpty())
                .or(adressTextField.textProperty().isEmpty())
                .or(zipCodeTextField.textProperty().isEmpty())
                .or(carModelCombobox.valueProperty().isNull())
                .or(priceTextField.textProperty().isEmpty())
                .or(downPaymentTextField.textProperty().isEmpty())
                .or(startDatePicker.valueProperty().isNull())
                .or(endDatePicker.valueProperty().isNull())
                .or(salesPersonCombobox.valueProperty().isNull())
        );
        calcQuoteButton.disableProperty().bind(calcBind);


        getButton.setOnAction(click -> CompletableFuture.runAsync(() -> {
            creditRatingText.setText(String.valueOf(CreditRator.i().rate(cprTextField.getText())));
        }));

        clearButton.setOnAction(click -> UIController.instance().switchCenter(new NewOfferView().createView()));

        calcQuoteButton.setOnAction(click -> {
            creditRatingTxt.setText(creditRatingText.getText());
            bankInterestTxt.setText((paymentCalc.rkiInterestCalc(creditRatingTxt.getText())) + " %");
            carPriceTxt.setText(priceFormat.formatter(priceTextField.getText()) + " Kr.");
            downPayTxt.setText(priceFormat.formatter(downPaymentTextField.getText())+ " Kr.");
            downPayInterestTxt.setText(paymentCalc.downPaymentCalc(Double.valueOf(priceTextField.getText()),Double.valueOf(downPaymentTextField.getText()))+ " %");
            createdTxt.setText(dateFormat.format(date));
            buyerTxt.setText(nameTextField.getText());
            payPeriodTxt.setText(periodCalculator.yearsBetweenDates(startDatePicker.getValue().toString(),endDatePicker.getValue().toString()) + " Years");
            offerSalesPersTxt.setText(salesPersonCombobox.getValue().toString());
            carModelTxt.setText(carModelCombobox.getValue().toString());
            periodPayInterestTxt.setText(paymentCalc.periodInterestRate(periodCalculator.yearsBetweenDates(startDatePicker.getValue().toString(),endDatePicker.getValue().toString())) + " %");
            totalInterestRateTxt.setText(String.valueOf(paymentCalc.calculateTotalInterest()));
            totalInterestRateTxt.setUnderline(true);
            totalPriceTxt.setText(String.valueOf(paymentCalc.totalCarPrice(Double.valueOf(priceTextField.getText()),Double.valueOf(downPaymentTextField.getText()),Double.valueOf(totalInterestRateTxt.getText()))));
            totalPriceTxt.setUnderline(true);
            approvedByTxt.setText(salesLimit.approval(Double.valueOf(priceTextField.getText()),Double.valueOf(downPaymentTextField.getText()), salesPersonCombobox.getValue().toString()));
            monthPayTxt.setText(priceFormat.formatter(monthPayCalc.monthlyPay(startDatePicker.getValue().toString(),endDatePicker.getValue().toString(),paymentCalc.totalCarPrice(Double.valueOf(priceTextField.getText()),Double.valueOf(downPaymentTextField.getText()),Double.valueOf(totalInterestRateTxt.getText())))) + " Kr.");
            monthPayTxt.setUnderline(true);
            priceAfterDownPayTxt.setText(priceFormat.formatter(paymentCalc.carPriceAfterDownPayment(Double.valueOf(priceTextField.getText()),Double.valueOf(downPaymentTextField.getText()))) + " Kr.");
            priceAfterDownPayTxt.setUnderline(true);
            csvBtn.setDisable(false);
            acceptBtn.setDisable(false);
            paymentCalc.setDownPayment(Double.valueOf(downPaymentTextField.getText())); /////!!!!!!
        });

        acceptBtn.setOnAction(click -> {
            if( (Double.valueOf(priceTextField.getText()) - Double.valueOf(downPaymentTextField.getText()) ) >= 1000000 ){
//************                OfferDataAccess ofda = new OfferJDBC().addOffer()
                notifyWindow.notifySalesManager(salesPersonCombobox.getValue().toString());
            }else{
                notifyWindow.acceptOffer(salesPersonCombobox.getValue().toString());
            }
        });

        csvBtn.setOnAction(click -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Csv files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(window);

            if (file != null) {
                saveToCsv.saveOffer(
                        file,createdTxt.getText(),buyerTxt.getText(),carModelTxt.getText(),carPriceTxt.getText(),downPayTxt.getText(),priceAfterDownPayTxt.getText(),
                        payPeriodTxt.getText(),creditRatingTxt.getText(),bankInterestTxt.getText(),
                        downPayInterestTxt.getText(),periodPayInterestTxt.getText(),totalInterestRateTxt.getText(),totalPriceTxt.getText(),
                        monthPayTxt.getText(),approvedByTxt.getText()
                );
            }
        });

        HBox root = new HBox(salesGrid);
        //root.setPrefWidth(700);
        return root;
    }
    private void setCarInfo(Car car) {
        priceTextField.setText(car.getPrice());
    }

}