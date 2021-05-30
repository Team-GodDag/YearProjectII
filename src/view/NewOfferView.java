package view;
// når der skal oprettes et tilbud/ordre

import entities.Car;
import entities.Customer;
import entities.Offer;
import entities.SalesPerson;
import DataAccessors.CarDataAccessor;
import DataAccessors.CustomerDataAccessor;
import DataAccessors.OfferDataAccessor;
import DataAccessors.SalesPersonDataAccessor;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public class NewOfferView {
    SaveToCsv saveToCsv; //har flyttet initialisering til det sted, hvor den bruges
    NotifyWindow notifyWindow = new NotifyWindow(); //skal nok bare bare lokal variabel

    PeriodCalculator periodCalculator = new PeriodCalculator();     //Behøver måske ikke egen klasse
    PriceFormat priceFormat = new PriceFormat();                    //Fordel v. BigDecimal er, at den indeholder formattering
    MonthPayCalc monthPayCalc = new MonthPayCalc();                 //behøver den egen klasse?
    PaymentCalculator paymentCalculator = new PaymentCalculator();
    WriteOnlyNumbers writeOnlyNumbers = new WriteOnlyNumbers();     //bruges kun i downpayment - keep local

    TextField cprTextField;
    Text creditRatingText, nameText, emailText, addressText, phoneText, priceText;
    Customer customer;
    SalesPerson salesPerson;
    LocalDate saleDate, payStartLocalDate, payEndLocalDate;
    Car car;
    boolean needsApproval;
    String status;

    public Node createView() {

//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        saleDate = LocalDate.now();


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

        cprTextField.setPromptText("1234567890");
        GridPane.setConstraints(cprTextField,1,0);
        Button getButton = new Button("Hent");

        GridPane.setConstraints(getButton,2,0);

        creditRatingText = new Text();
        creditRatingText.setFont(Font.font("Verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
        creditRatingText.setFill(Color.GREEN);

        Label creditLabel = new Label("Kreditværdighed:");
        GridPane.setConstraints(creditLabel,3,0);
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
        ComboBox carModelCombobox = new ComboBox<>(FXCollections.observableArrayList(CarDataAccessor.getCarDataAccess().getAllCars()));
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
                if(paymentCalculator.getDownPayment() > car.getPrice()) {       //virker lidt shoddy but idk
                    downPaymentTextField.setText("");
                }
            }
        });

        GridPane.setConstraints(downPaymentLabel,0,11);
        GridPane.setConstraints(downPaymentTextField,1,11);

//DATES BEGIN-------------------------------------------------------------------------------------------------------------//

        Label startDateLabel = new Label("Startdato");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Vælg dato");
        startDatePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                payStartLocalDate = startDatePicker.getValue();
            }
        });
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
        endDatePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                payEndLocalDate = endDatePicker.getValue();
            }
        });



        GridPane.setConstraints(endDateLabel,0,13);
        GridPane.setConstraints(endDatePicker,1,13);

//DATES END -----------------------------------------------------------------------------------------------------------------------------------------//



        Label salesPersonLabel = new Label("Sælger");
        ComboBox salesPersonCombobox = new ComboBox<>(FXCollections.observableArrayList(SalesPersonDataAccessor.createSalesPersonList()));
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

        Button clearButton = new Button("Annuller");
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

        Label bankInterestLbl = new Label("Rentesats:");                //bankrente + rente baseret på kreditværdighed
        Text bankInterestTxt = new Text();
        GridPane.setConstraints(bankInterestLbl,11,5);
        GridPane.setConstraints(bankInterestTxt,12,5);

        Label downPayInterestLbl = new Label("Udbetalingsrente:");
        Text downPayInterestTxt = new Text();
        GridPane.setConstraints(downPayInterestLbl,11,6);
        GridPane.setConstraints(downPayInterestTxt,12,6);

        Label periodPayInterestLbl = new Label("Løbetidsrente:");         //andet navn?
        Text periodPayInterestTxt = new Text();
        GridPane.setConstraints(periodPayInterestLbl,11,7);
        GridPane.setConstraints(periodPayInterestTxt,12,7);


        Label totalInterestRateLbl = new Label("Total rente:");
        Text totalInterestRateTxt = new Text();
        GridPane.setConstraints(totalInterestRateLbl,11,8);
        GridPane.setConstraints(totalInterestRateTxt,12,8);

        Text amountTxt = new Text("Beløb: ");   //fungerer blot som underoverskrift til et "her er de beløb, du skal bekymre dig om"-afsnit
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

        Button csvBtn = new Button("Eksportér til CSV");
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

        getButton.setOnAction(click -> checkCustomer());

        clearButton.setOnAction(click -> UIManager.instance().switchCenter(new NewOfferView().createView()));        //shoddy?

        calcQuoteButton.setOnAction(click -> {
            setStatus(needsApproval);
            paymentCalculator.setDownPayment(Double.parseDouble(downPaymentTextField.getText()));
            paymentCalculator.calculateAll();

            if(!paymentCalculator.isRkiIsOK()) {
                saleDeniedAlert();
            }

            creditRatingTxt.setText(creditRatingText.getText());    //skal hentes fra paymentcalc eller tilsvarende
            bankInterestTxt.setText(priceFormat.formatter(paymentCalculator.getRkiAndBankInterestRate()));
            carPriceTxt.setText(priceFormat.formatter(car.getPrice()));
            downPayTxt.setText(priceFormat.formatter(paymentCalculator.getDownPayment()));
            downPayInterestTxt.setText(priceFormat.formatter(paymentCalculator.getDownPaymentInterestRate())); //String.valueOf(paymentCalc.downPaymentCalc(Double.valueOf(priceTextField.getText()), Double.valueOf(downPaymentTextField.getText()))))
            createdTxt.setText(String.valueOf(saleDate)); //dateFormat.format(saleDate)
            buyerTxt.setText(customer.getFirstName() + " " + customer.getLastName());
            payPeriodTxt.setText(periodCalculator.yearsBetweenDates(payStartLocalDate, payEndLocalDate) + " år");
            offerSalesPersTxt.setText(salesPerson.getFirstname() + " " + salesPerson.getLastname());
            carModelTxt.setText(car.getName());
            periodPayInterestTxt.setText(paymentCalculator.calculatePaymentPeriodInterestRate(periodCalculator.getTimeDifferenceInYears()) + "%");  //paymentCalculator.calculatePaymentPeriodInterestRate(periodCalculator.yearsBetweenDates(startDatePicker.getValue().toString(),endDatePicker.getValue().toString())))
            totalInterestRateTxt.setText(String.valueOf(priceFormat.formatter(paymentCalculator.getTotalInterest()))); //String.valueOf(paymentCalc.calculateTotalInterest())
            totalInterestRateTxt.setUnderline(true);
            totalPriceTxt.setText(String.valueOf(priceFormat.formatter(paymentCalculator.getTotalCarPrice())));    //priceFormat.formatter(paymentCalc.totalCarPrice(Double.parseDouble(priceTextField.getText()),Double.parseDouble(downPaymentTextField.getText())))
            totalPriceTxt.setUnderline(true);
            statusText.setText(status);
            monthPayTxt.setText(priceFormat.formatter(monthPayCalc.monthlyPay(payStartLocalDate, payEndLocalDate, paymentCalculator.getPriceAfterDownPayment())));
            monthPayTxt.setUnderline(true);
            priceAfterDownPayTxt.setText(priceFormat.formatter(paymentCalculator.getPriceAfterDownPayment()));
            priceAfterDownPayTxt.setUnderline(true);
            csvBtn.setDisable(false);
            acceptBtn.setDisable(false);

            System.out.println("interest rate test " + paymentCalculator.getCombinedInterestRate());

        });


        csvBtn.setOnAction(click -> {       //lokal metode - lokale variable -_-
            FileChooser fileChooser = new FileChooser();
            Stage window = new Stage();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Csv files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(window);

            if (file != null) {
                saveToCsv = new SaveToCsv();
                saveToCsv.saveOfferToCSV(   //bør vi lave den smartere?
                        file, createdTxt.getText(), buyerTxt.getText(), carModelTxt.getText(), carPriceTxt.getText(), downPayTxt.getText(), priceAfterDownPayTxt.getText(),
                        payPeriodTxt.getText(),creditRatingTxt.getText(),bankInterestTxt.getText(),
                        downPayInterestTxt.getText(),periodPayInterestTxt.getText(),totalInterestRateTxt.getText(),totalPriceTxt.getText(),
                        monthPayTxt.getText(),statusText.getText()
                );
            }
        });

        acceptBtn.setOnAction(click -> {
            saveOffertoDB(customer, car, salesPerson, paymentCalculator.getCreditRating(), paymentCalculator, saleDate, payStartLocalDate, payEndLocalDate, status);
        });                                                                     //vil hellere hente fra paymentCalc

        HBox root = new HBox(salesGrid);
        //root.setPrefWidth(700);
        return root;
    }




//PRIVATE METHODS--------------------------------------------------------

//    private void setCreditRating(String cprInput) {       //virker ikke som den står her
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                creditRatingText.setText(paymentCalculator.fetchCreditRating(cprInput));   //setText(paymentCalculator.fetchCreditRating(cprTextField.getText()));
//            }
//        });
//    }

    private void setCarInfo(Car car) {
        priceText.setText(String.valueOf(car.getPrice()));
    }

    private void checkCustomer() {
        String cprInput = cprTextField.getText();
        this.customer = CustomerDataAccessor.getCustomerDataAccess().getCustomerByCpr(cprInput);       //skal i privat metode
        if (customer != null) {
            if (customer.isGoodGuy()) {
                setCustomerInfo(customer);
                System.out.println(customer.isGoodGuy());
//                setCreditRating(cprTextField.getText());          //virker ikke
                requestRkiRating(cprInput);     //CompletableFuture.runAsync(() -> creditRatingText.setText(paymentCalculator.fetchCreditRating(cprInput)));
                requestBankRate();
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

    private void requestRkiRating(String cprInput) {
        CompletableFuture.runAsync(() -> CompletableFuture.runAsync(() -> creditRatingText.setText(paymentCalculator.fetchCreditRating(cprInput))));
    }

    private void requestBankRate() {
        CompletableFuture.runAsync(() -> paymentCalculator.fetchBankInterestRate());
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

    private void checkApprovalNeed(Car car) {
        if(car.getPrice() > 1000000) {
            needsApproval = true;
        } else {
            needsApproval = false;
        }
    }

    private void setStatus(boolean needsApproval) {
        if(needsApproval) {
            status = "Afventer godkendelse";
        } else {
            status = "Aktiv";
        }
    }

    private void saveOffertoDB(Customer customer, Car car, SalesPerson salesperson, String creditRating, PaymentCalculator paymentCalculator, LocalDate saleDate, LocalDate startDate, LocalDate endDate, String status) {
        OfferDataAccessor.addOffer(new Offer(customer, car, salesperson, creditRating, paymentCalculator, saleDate, startDate, endDate, status));
    }

    private void saveToCSV() {

    }

    private void saleDeniedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Handel nægtet");
        alert.setHeaderText("Vi handler ikke med denne kunde");
        alert.setContentText("Årsagen kan være tidligere dårlig opførsel eller at vedkommende er registreret hos RKI");
        alert.setHeight(400);
        alert.showAndWait();
    }

    private void setRightText() {

    }

}