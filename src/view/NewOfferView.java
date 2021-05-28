package view;
// når der skal oprettes et tilbud/ordre

import data.CarDataAccess;
import data.CreditRator;
import entities.Car;
import entities.Customer;
import entities.Offer;
import entities.SalesPerson;
import factories.CarListFactory;
import factories.CustomerListFactory;
import factories.OfferListFactory;
import factories.SalesPersonListFactory;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
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
    NotifyWindow notifyWindow = new NotifyWindow(); //skal nok bare bare lokal variabel

    PeriodCalculator periodCalculator = new PeriodCalculator();     //Behøver måske ikke egen klasse
    PriceFormat priceFormat = new PriceFormat();            //Hvorfor ligger den her?
    SalesLimit salesLimit = new SalesLimit();               //flyt til metode i stedet?
    MonthPayCalc monthPayCalc = new MonthPayCalc();         //behøver den egen klasse?
    PaymentCalculator paymentCalculator = new PaymentCalculator();
    WriteOnlyNumbers writeOnlyNumbers = new WriteOnlyNumbers();     //bruges kun i downpayment - keep local

    TextField cprTextField;
    Text creditRatingText, nameText, emailText, addressText, phoneText, priceText;
    Customer customer;
    SalesPerson salesPerson;
    LocalDate payStartLocalDate, payEndLocalDate;
    Date saleDate, payStartDate, payEndDate;
    Car car;
    boolean needsApproval;
    String status;

    public Node createView() {
        Stage window = new Stage();     //bruges til NotifyManager-popup; skal måske flyttes til relevante sted
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        saleDate = new Date();


        //GridPane Design//
        //------------------------------------//
        GridPane salesGrid = new GridPane();
        salesGrid.setPadding(new Insets(15,10,10,20));
        salesGrid.setMinSize(10, 10);
        //salesGrid.setGridLinesVisible(true);
        salesGrid.setVgap(16);
        salesGrid.setHgap(16);
        //------------------------------------//

//TOP PART START------------------------------------------------------------------------------------//
        Line topLine = new Line(100,150,1095,150);
        topLine.setStroke(Color.SILVER);
        salesGrid.add(topLine,0,1,13,1);

        Line bottomLine = new Line(100,150,1095,150);
        bottomLine.setStroke(Color.SILVER);
        salesGrid.add(bottomLine,0,16,13,1);

        Line middleLine = new Line();
        middleLine.setStartX(350);
        middleLine.setStartY(-100);
        middleLine.setEndX(350);
        middleLine.setEndY(330);
        middleLine.setStroke(Color.SILVER);
        salesGrid.add(middleLine,4,0,9,20);



        LimitedTextField limitedTextField = new LimitedTextField();   //Lars' hjemmelavede klasse m. tilhørende metode, der begrænser længde af input
        Label cprLabel = new Label("CPR: ");
        GridPane.setConstraints(cprLabel,0,0);
        cprTextField = new TextField("");
        writeOnlyNumbers.input(cprTextField);
        limitedTextField.addTextLimiter(cprTextField,10);

//        cprTextField.setTranslateX(40);
//        cprTextField.setTranslateY(0);
        cprTextField.setPromptText("1234567890");
        GridPane.setConstraints(cprTextField,1,0);
        Button getButton = new Button("Hent");
//        getButton.setTranslateX(200);

        GridPane.setConstraints(getButton,2,0);

        creditRatingText = new Text();
        creditRatingText.setFont(Font.font("Verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
        creditRatingText.setFill(Color.GREEN);

        Label creditLabel = new Label("Kreditværdighed:");
//        creditLabel.setTranslateX(280);
//        creditLabel.setTranslateY(0);
        GridPane.setConstraints(creditLabel,3,0);
//        creditRatingText.setTranslateX(360);
//        creditRatingText.setTranslateY(0);
        GridPane.setConstraints(creditRatingText,4,0);

//TOP PART END------------------------------------------------------------------------------------//

//LEFT SIDE-----------------------------------------------------------------------------------//START
        Text leftDescriptionText = new Text("Kundeoplysninger");
        leftDescriptionText.setUnderline(true);
        GridPane.setConstraints(leftDescriptionText,0,2);





        Label nameLabel = new Label("Kunde:");
        nameText = new Text();
        GridPane.setConstraints(nameLabel,0,4);
        GridPane.setConstraints(nameText,1,4);

        Label phoneLabel = new Label("Telefon:");
        phoneText = new Text();
        GridPane.setConstraints(phoneLabel,0,5);
        GridPane.setConstraints(phoneText,1,5);

        Label emailLabel = new Label("Email: ");
        emailText = new Text();
        GridPane.setConstraints(emailLabel,0,6);
        GridPane.setConstraints(emailText,1,6);

        Label addressLabel = new Label("Adresse:");
        addressText = new Text();
        GridPane.setConstraints(addressLabel,0,7);
        GridPane.setConstraints(addressText,1,7);


        Label carModelLabel = new Label("Bilmodel: ");
        ComboBox carModelCombobox = new ComboBox<>(FXCollections.observableArrayList(CarListFactory.getCarDataAccess().getAllCars()));
        carModelCombobox.setPromptText("Vælg model");
        GridPane.setConstraints(carModelLabel,0,9);
        GridPane.setConstraints(carModelCombobox,1,9);
        carModelCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    car = (Car) newValue;
                    setCarInfo(car);
                    paymentCalculator.setCar(car);
                    checkApprovalNeed(car);
                }
            }
        );

        Label priceLabel = new Label("Pris: ");
        priceText = new Text();
        GridPane.setConstraints(priceLabel,0,10);
        GridPane.setConstraints(priceText,1,10);

        Label downPaymentLabel = new Label("Kontant udbetaling:");
        TextField downPaymentTextField = new TextField();
        downPaymentTextField.setPromptText("Indtast beløb");
        writeOnlyNumbers.input(downPaymentTextField);
        downPaymentTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {     //sikrer, at der ikke kan indtastes andet end tal i udbetalingsfelt
            if (!newValue) { //when focus lost
                if(Double.parseDouble(downPaymentTextField.getText()) > car.getPrice()) {       //virker lidt shoddy but idk
                    downPaymentTextField.setText("");
                }
            }
        });


//DATES BEGIN-------------------------------------------------------------------------------------------------------------//
        GridPane.setConstraints(downPaymentLabel,0,11);
        GridPane.setConstraints(downPaymentTextField,1,11);

        Label startDateLabel = new Label("Startdato");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Vælg dato");
        GridPane.setConstraints(startDateLabel,0,12);
        GridPane.setConstraints(startDatePicker,1,12);

        Label endDateLabel = new Label("Slutdato");
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPromptText("Vælg dato");

        endDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate endDate, boolean empty) {
                if (endDate.isBefore(startDatePicker.getValue())) {
                    setStyle("-fx-background-color: #ffc0cb; -fx-text-fill: darkgray;");
                    setDisable(true);
                }
            }
        });

        payStartLocalDate = startDatePicker.getValue();         //skal konverteres fra LocalDate t. Date, enten her eller i save-metoden
        payEndLocalDate = endDatePicker.getValue();

        GridPane.setConstraints(endDateLabel,0,13);
        GridPane.setConstraints(endDatePicker,1,13);

//DATES END -----------------------------------------------------------------------------------------------------------------------------------------//



        Label salesPersonLabel = new Label("Sælger");
        ComboBox salesPersonCombobox = new ComboBox<>(FXCollections.observableArrayList(SalesPersonListFactory.createSalesPersonList()));
        salesPersonCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object personOne, Object chosenPerson) {
                salesPerson = (SalesPerson) chosenPerson;
            }
        });

        salesPersonCombobox.setPromptText("Vælg");
        GridPane.setConstraints(salesPersonLabel,0,14);
        GridPane.setConstraints(salesPersonCombobox,1,14);

        Button calcQuoteButton = new Button("Beregn tilbud");
        GridPane.setConstraints(calcQuoteButton,0,17);

        Button clearButton = new Button("      Slet       ");
        GridPane.setConstraints(clearButton,1,17);

//LEFT SIDE-----------------------------------------------------------------------------------//END

        Text rightDescriptionText = new Text("Udregnet tilbud:");
        rightDescriptionText.setUnderline(true);
        GridPane.setConstraints(rightDescriptionText,7,2);


        Label createdLbl = new Label("Oprettet:");
        Text createdTxt = new Text();
        GridPane.setConstraints(createdLbl,7,4);
        GridPane.setConstraints(createdTxt,8,4);

        Label buyerLbl = new Label("Køber:");
        Text buyerTxt = new Text();
        GridPane.setConstraints(buyerLbl,7,5);
        GridPane.setConstraints(buyerTxt,8,5);

        Label carModelLbl = new Label("Bil:");
        Text carModelTxt = new Text();
        GridPane.setConstraints(carModelLbl,7,6);
        GridPane.setConstraints(carModelTxt,8,6);

        Label carPriceLbl = new Label("Bilens grundpris:");
        Text carPriceTxt = new Text("");
        GridPane.setConstraints(carPriceLbl,7,7);
        GridPane.setConstraints(carPriceTxt,8,7,2,1);

        Label downPayLbl = new Label("Kontant udbetaling:");
        Text downPayTxt = new Text("");
        GridPane.setConstraints(downPayLbl,7,8);
        GridPane.setConstraints(downPayTxt,8,8);

        Label payPeriodLbl = new Label("Afbetalingsperiode:");
        Text payPeriodTxt = new Text("");
        GridPane.setConstraints(payPeriodLbl,7,9);
        GridPane.setConstraints(payPeriodTxt,8,9);

        Label offerSalesPersLbl = new Label("Sælger:");
        Text offerSalesPersTxt = new Text();
        GridPane.setConstraints(offerSalesPersLbl,7,10);
        GridPane.setConstraints(offerSalesPersTxt,8,10);

        Label statusLabel = new Label("Status:");
        Text statusText = new Text();
        GridPane.setConstraints(statusLabel,7,11);
        GridPane.setConstraints(statusText,8,11,3,1);

        Label creditRatingLbl = new Label("Kreditværdighed:");
        Text creditRatingTxt = new Text();
        GridPane.setConstraints(creditRatingLbl,11,4);
        GridPane.setConstraints(creditRatingTxt,12,4);

        Label bankInterestLbl = new Label("Bankrente:");         //"rentesats"?
        Text bankInterestTxt = new Text();
        GridPane.setConstraints(bankInterestLbl,11,5);
        GridPane.setConstraints(bankInterestTxt,12,5);

        Label downPayInterestLbl = new Label("Udbetalingsrente:");
        Text downPayInterestTxt = new Text();
        GridPane.setConstraints(downPayInterestLbl,11,6);
        GridPane.setConstraints(downPayInterestTxt,12,6);

        Label periodPayInterestLbl = new Label("Løbetidsrente:");   //andet navn?
        Text periodPayInterestTxt = new Text();
        GridPane.setConstraints(periodPayInterestLbl,11,7);
        GridPane.setConstraints(periodPayInterestTxt,12,7);


        Label totalInterestRateLbl = new Label("Total rente:");
        Text totalInterestRateTxt = new Text();
        GridPane.setConstraints(totalInterestRateLbl,11,8);
        GridPane.setConstraints(totalInterestRateTxt,12,8);

        Text amountTxt = new Text("Beløb: ");   //underoverskrift til et "her er de beløb, du skal bekymre dig om"-afsnit
        amountTxt.setUnderline(true);
        GridPane.setConstraints(amountTxt,11,10);


        Label priceAfterDownPayLbl = new Label("Pris efter udbetaling:");
        Text priceAfterDownPayTxt = new Text();
        GridPane.setConstraints(priceAfterDownPayLbl,11,11);
        GridPane.setConstraints(priceAfterDownPayTxt,12,11,4,1);

        Label totalPriceLbl = new Label("Totalpris inkl. renter:");
        Text totalPriceTxt = new Text();
        GridPane.setConstraints(totalPriceLbl,11,12);
        GridPane.setConstraints(totalPriceTxt,12,12,3,1);

        Label monthPayLbl = new Label("Månedlig afbetaling:");
        Text monthPayTxt = new Text();
        GridPane.setConstraints(monthPayLbl,11,13);
        GridPane.setConstraints(monthPayTxt,12,13,3,1);


        Button acceptBtn = new Button("Opret");
        acceptBtn.setDisable(true);

        Button csvBtn = new Button("Eksportér");
        csvBtn.setDisable(true);
        GridPane.setConstraints(csvBtn,7,17);
        GridPane.setConstraints(acceptBtn,8,17);


        salesGrid.getChildren().addAll(         //kig på, hvor mange af disse, der skal fjernes
                leftDescriptionText,
                cprLabel,
                cprTextField,
                getButton,
                creditLabel,
                creditRatingText,
                nameLabel,
                nameText,
                phoneLabel,
                phoneText,
                emailLabel,
                emailText,
                addressLabel,
                addressText,
                carModelLabel,
                carModelCombobox,
                priceLabel,
                priceText,
                downPaymentLabel,
                downPaymentTextField,
                startDateLabel,
                startDatePicker,
                endDateLabel,
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
                statusLabel,
                statusText,
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
                .or(nameText.textProperty().isEmpty())
                .or(phoneText.textProperty().isEmpty())
                .or(emailText.textProperty().isEmpty())
                .or(addressText.textProperty().isEmpty())
                .or(carModelCombobox.valueProperty().isNull())
                .or(priceText.textProperty().isEmpty())
                .or(downPaymentTextField.textProperty().isEmpty())
                .or(startDatePicker.valueProperty().isNull())
                .or(endDatePicker.valueProperty().isNull())
                .or(salesPersonCombobox.valueProperty().isNull())
        );

        calcQuoteButton.disableProperty().bind(calcBind);

        getButton.setOnAction(click -> {
            String cprInput = cprTextField.getText();
            paymentCalculator.getCreditRating(cprInput, NewOfferView.this::setCreditRating);  //virker ikke endnu'
            checkCustomer();
            });

        clearButton.setOnAction(click -> UIController.instance().switchCenter(new NewOfferView().createView()));        //shoddy?

        calcQuoteButton.setOnAction(click -> {      //needs major overhaul
            setStatus(needsApproval);
            paymentCalculator.setDownPayment(Double.parseDouble(downPaymentTextField.getText()));
            paymentCalculator.calculateAll();

            creditRatingTxt.setText(creditRatingText.getText());    //skal hentes fra paymentcalc eller tilsvarende
            bankInterestTxt.setText(priceFormat.formatter(paymentCalculator.getBaseBankInterestRate()));
            carPriceTxt.setText(priceFormat.formatter(car.getPrice()));
            downPayTxt.setText(priceFormat.formatter(paymentCalculator.getDownPayment()));
            downPayInterestTxt.setText(priceFormat.formatter(paymentCalculator.getDownPaymentInterestRate())); //String.valueOf(paymentCalc.downPaymentCalc(Double.valueOf(priceTextField.getText()), Double.valueOf(downPaymentTextField.getText()))))
            createdTxt.setText(dateFormat.format(saleDate));
            buyerTxt.setText(customer.getFirstName() + " " + customer.getLastName());
    /**/        payPeriodTxt.setText(periodCalculator.yearsBetweenDates(startDatePicker.getValue().toString(), endDatePicker.getValue().toString()) + " år");
            offerSalesPersTxt.setText(salesPerson.getFirstname() + " " + salesPerson.getLastname());
            carModelTxt.setText(car.getName());
    /**/        periodPayInterestTxt.setText(String.valueOf(paymentCalculator.calculatePaymentPeriodInterestRate(periodCalculator.yearsBetweenDates(startDatePicker.getValue().toString(),endDatePicker.getValue().toString()))));
            totalInterestRateTxt.setText(String.valueOf(priceFormat.formatter(paymentCalculator.getTotalInterest()))); //String.valueOf(paymentCalc.calculateTotalInterest())
            totalInterestRateTxt.setUnderline(true);
    /**/        totalPriceTxt.setText(String.valueOf(priceFormat.formatter(paymentCalculator.getPriceAfterDownPayment())));    //priceFormat.formatter(paymentCalc.totalCarPrice(Double.parseDouble(priceTextField.getText()),Double.parseDouble(downPaymentTextField.getText())))
            totalPriceTxt.setUnderline(true);
            statusText.setText(status);
            monthPayTxt.setText(priceFormat.formatter(monthPayCalc.monthlyPay(startDatePicker.getValue().toString(), endDatePicker.getValue().toString(), paymentCalculator.getPriceAfterDownPayment())));
            monthPayTxt.setUnderline(true);
    /**/        priceAfterDownPayTxt.setText(priceFormat.formatter(paymentCalculator.getPriceAfterDownPayment()));
            priceAfterDownPayTxt.setUnderline(true);
            csvBtn.setDisable(false);
            acceptBtn.setDisable(false);
//            paymentCalc.setPaymentPeriodInterestRate(paymentCalc.getPaymentPeriodInterestRate());
            //paymentCalc.setPriceAfterDownPayment(Double.valueOf(priceAfterDownPayTxt.getText()));
//            paymentCalc.setTotalInterest(Double.parseDouble(totalInterestRateTxt.getText()));       //wat dis

            System.out.println("interest rate test " + paymentCalculator.getInterestRate());

        });

        acceptBtn.setOnAction(click -> {
            //save method
        });

        csvBtn.setOnAction(click -> {       //lokal metode
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Csv files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(window);

            if (file != null) {
                saveToCsv.saveOfferToCSV(   //bør vi lave den smartere?
                        file,createdTxt.getText(), buyerTxt.getText(), carModelTxt.getText(), carPriceTxt.getText(), downPayTxt.getText(), priceAfterDownPayTxt.getText(),
                        payPeriodTxt.getText(),creditRatingTxt.getText(),bankInterestTxt.getText(),
                        downPayInterestTxt.getText(),periodPayInterestTxt.getText(),totalInterestRateTxt.getText(),totalPriceTxt.getText(),
                        monthPayTxt.getText(),statusText.getText()
                );
            }
        });

        acceptBtn.setOnAction(click -> {
            saveOffertoDB(customer, car, salesPerson, creditRatingTxt.getText(), status, paymentCalculator, saleDate, payStartDate, payEndDate);
        });                                                                     //vil hellere hente fra paymentCalc

        HBox root = new HBox(salesGrid);
        //root.setPrefWidth(700);
        return root;
    }




//PRIVATE METHODS--------------------------------------------------------

    private void setCreditRating(String rating) {       //INCOMLETE!!!!
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                creditRatingText.setText(rating);
            }
        });
    }

    private void setCarInfo(Car car) {
        priceText.setText(String.valueOf(car.getPrice()));
    }

    private void checkCustomer() {
        this.customer = CustomerListFactory.getCustomerByCpr(cprTextField.getText());       //skal i privat metode
        if (customer != null) {
            if (customer.isGoodGuy() != false) {
                setCustomerInfo(customer);
                System.out.println(customer.isGoodGuy());
                /* *****!!!!!!*/        CompletableFuture.runAsync(() -> creditRatingText.setText(String.valueOf(CreditRator.i().rate(cprTextField.getText()))));   //bør kalde lokal klassevariabel creditRating i paymentCalc
            } else {
                saleDeniedAlert();
            }
        } else {
            Scene newCustomerScene = new Scene(new NewCustomerView().createView());
            Stage newCustomerPopUp = new Stage();
            newCustomerPopUp.setScene(newCustomerScene);
            newCustomerPopUp.initModality(Modality.WINDOW_MODAL);
            newCustomerPopUp.setTitle("Opret ny kunde");
            newCustomerPopUp.showAndWait();
            setCustomerInfo(customer);  //virker ikke efter hensigten
        }
    }

    private void setCustomerInfo(Customer customer) {
        try {
            cprTextField.setText(customer.getCpr());
            nameText.setText(customer.getFirstName() + " " + customer.getLastName());
            addressText.setText(customer.getAddress());
            emailText.setText(customer.getEmail());
            phoneText.setText(customer.getPhone());
        }
        catch (NullPointerException e) {        //kan ikke huske, om den virker
            e.printStackTrace();
        }
    }

    private boolean checkApprovalNeed(Car car) {
        if(car.getPrice() > 1000000) {
            needsApproval = true;
        } else {
            needsApproval = false;
        }
        return needsApproval;
    }

    private String setStatus(boolean needsApproval) {
        if(needsApproval) {
            status = "Awaiting approval";
        } else {
            status = "Active";
        }
        return status;
    }

    private void saveOffertoDB(Customer customer, Car car, SalesPerson salesperson, String creditRating, String status, PaymentCalculator paymentCalculator, Date saleDate, Date startDate, Date endDate) {
        OfferListFactory.addOffer(new Offer(customer, car, salesperson, creditRating, status, paymentCalculator, saleDate, startDate, endDate));
    }

    private Alert saleDeniedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Handel nægtet");
        alert.setHeaderText("Vi handler ikke med svumpukler");
        alert.setContentText("Han må prøve at svindle en anden forhandler");
        alert.showAndWait();
        return alert;
    }

}