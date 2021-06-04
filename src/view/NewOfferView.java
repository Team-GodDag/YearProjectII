package view;
// når der skal oprettes et tilbud/ordre

import entities.Car;
import entities.Customer;
import entities.Offer;
import entities.SalesPerson;
import dataaccessors.CarDataAccessor;
import dataaccessors.CustomerDataAccessor;
import dataaccessors.OfferDataAccessor;
import dataaccessors.SalesPersonDataAccessor;
import javafx.application.Platform;
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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class NewOfferView {
    //Lavet af Rikke og Lars
    private SaveToCsv saveToCsv;

    private PaymentCalculator paymentCalculator = new PaymentCalculator();
    private Tools tools = new Tools();
    private TextField cprTextField;
    private Text creditRatingText, nameText, emailText, addressText, phoneText, priceText;
    private Customer customer;
    private SalesPerson salesPerson;
    private LocalDate saleDate, payStartLocalDate, payEndLocalDate;
    private Car car;
    private boolean needsApproval;
    private String status;

    public Node createView() {


        //GridPane Design//
        //------------------------------------//
        GridPane salesGrid = new GridPane();
        salesGrid.setPadding(new Insets(15, 10, 10, 20));
        salesGrid.setMinSize(10, 10);
        salesGrid.setVgap(16);
        salesGrid.setHgap(16);
        //------------------------------------//


//TOP PART START------------------------------------------------------------------------------------//
        Line topLine = new Line(100, 150, 1095, 150);
        topLine.setStroke(Color.SILVER);
        salesGrid.add(topLine, 0, 1, 13, 1);

        Line bottomLine = new Line(100, 150, 1095, 150);
        bottomLine.setStroke(Color.SILVER);
        salesGrid.add(bottomLine, 0, 16, 13, 1);

        Line middleLine = new Line();
        middleLine.setStartX(350);
        middleLine.setStartY(-100);
        middleLine.setEndX(350);
        middleLine.setEndY(330);
        middleLine.setStroke(Color.SILVER);
        salesGrid.add(middleLine, 4, 0, 9, 20);


        LimitedTextField limitedTextField = new LimitedTextField();
        Label cprLabel = new Label("CPR: ");
        GridPane.setConstraints(cprLabel, 0, 0);
        cprTextField = new TextField("");
        tools.makeInputNumbersOnly(cprTextField);
        limitedTextField.addTextLimiter(cprTextField, 10);

        cprTextField.setPromptText("1234567890");
        GridPane.setConstraints(cprTextField, 1, 0);
        Button getButton = new Button("Hent");

        GridPane.setConstraints(getButton, 2, 0);

        creditRatingText = new Text();
        creditRatingText.setFont(Font.font("Verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
        creditRatingText.setFill(Color.GREEN);


        creditRatingText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if (newValue.equals("D")) {
                    Platform.runLater(() -> {
                        saleDeniedAlert();
                        UIManager.instance().switchCenter(new NewOfferView().createView());
                    });

                } else {
                    requestBankRate();
                }
            }
        });


        Label creditLabel = new Label("Kreditværdighed:");
        GridPane.setConstraints(creditLabel, 3, 0);
        GridPane.setConstraints(creditRatingText, 4, 0);

//TOP PART END------------------------------------------------------------------------------------//

//LEFT SIDE-----------------------------------------------------------------------------------//START
        Text leftDescriptionText = new Text("Kundeoplysninger");
        leftDescriptionText.setUnderline(true);
        GridPane.setConstraints(leftDescriptionText, 0, 2);

        Label nameLabel = new Label("Kunde:");
        nameText = new Text();
        GridPane.setConstraints(nameLabel, 0, 4);
        GridPane.setConstraints(nameText, 1, 4);

        Label phoneLabel = new Label("Telefon:");
        phoneText = new Text();
        GridPane.setConstraints(phoneLabel, 0, 5);
        GridPane.setConstraints(phoneText, 1, 5);

        Label emailLabel = new Label("Email: ");
        emailText = new Text();
        GridPane.setConstraints(emailLabel, 0, 6);
        GridPane.setConstraints(emailText, 1, 6);

        Label addressLabel = new Label("Adresse:");
        addressText = new Text();
        GridPane.setConstraints(addressLabel, 0, 7);
        GridPane.setConstraints(addressText, 1, 7);


        Label carModelLabel = new Label("Bilmodel: ");
        ComboBox carModelCombobox = new ComboBox<>(FXCollections.observableArrayList(CarDataAccessor.getCarDataAccess().getAllCars()));
        carModelCombobox.setPromptText("Vælg model");
        GridPane.setConstraints(carModelLabel, 0, 9);
        GridPane.setConstraints(carModelCombobox, 1, 9);
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
        GridPane.setConstraints(priceLabel, 0, 10);
        GridPane.setConstraints(priceText, 1, 10);

        Label downPaymentLabel = new Label("Kontant udbetaling:");
        TextField downPaymentTextField = new TextField();
        downPaymentTextField.setPromptText("Indtast beløb");
        tools.makeInputNumbersOnly(downPaymentTextField);
        tools.numberNotTooBigValidator(downPaymentTextField, priceText, downPaymentTextField);


        GridPane.setConstraints(downPaymentLabel, 0, 11);
        GridPane.setConstraints(downPaymentTextField, 1, 11);

//DATES BEGIN-------------------------------------------------------------------------------------------------------------//

        saleDate = LocalDate.now();
        Label startDateLabel = new Label("Startdato");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Vælg dato");
        startDatePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                payStartLocalDate = startDatePicker.getValue();
            }
        });
        startDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate startDate, boolean empty) {
                if (startDate.isBefore(LocalDate.now())) {
                    setStyle("-fx-background-color: #ffc0cb; -fx-text-fill: darkgray;");
                    setDisable(true);
                }
            }
        });

        GridPane.setConstraints(startDateLabel, 0, 12);
        GridPane.setConstraints(startDatePicker, 1, 12);

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
        BooleanBinding endDateBind = (startDatePicker.valueProperty().isNull());
        endDatePicker.disableProperty().bind(endDateBind);

        GridPane.setConstraints(endDateLabel, 0, 13);
        GridPane.setConstraints(endDatePicker, 1, 13);

//DATES END -----------------------------------------------------------------------------------------------------------------------------------------//


        Label salesPersonLabel = new Label("Sælger");
        ComboBox salesPersonCombobox = new ComboBox<>(FXCollections.observableArrayList(SalesPersonDataAccessor.getSalesPersonDataAccess().getAllSalesPersons()));
        salesPersonCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object personOne, Object chosenPerson) {
                salesPerson = (SalesPerson) chosenPerson;
            }
        });

        salesPersonCombobox.setPromptText("Vælg");
        GridPane.setConstraints(salesPersonLabel, 0, 14);
        GridPane.setConstraints(salesPersonCombobox, 1, 14);

        Button calcQuoteButton = new Button("Beregn tilbud");
        GridPane.setConstraints(calcQuoteButton, 0, 17);

        Button clearButton = new Button("Annuller");
        GridPane.setConstraints(clearButton, 1, 17);

//LEFT SIDE-----------------------------------------------------------------------------------//END

        Text rightDescriptionText = new Text("Udregnet tilbud:");
        rightDescriptionText.setUnderline(true);
        GridPane.setConstraints(rightDescriptionText, 7, 2);


        Label createdLbl = new Label("Oprettet:");
        Text createdTxt = new Text();
        GridPane.setConstraints(createdLbl, 7, 4);
        GridPane.setConstraints(createdTxt, 8, 4);

        Label buyerLbl = new Label("Køber:");
        Text buyerTxt = new Text();
        GridPane.setConstraints(buyerLbl, 7, 5);
        GridPane.setConstraints(buyerTxt, 8, 5);

        Label carModelLbl = new Label("Bil:");
        Text carModelTxt = new Text();
        GridPane.setConstraints(carModelLbl, 7, 6);
        GridPane.setConstraints(carModelTxt, 8, 6);

        Label carPriceLbl = new Label("Bilens grundpris:");
        Text carPriceTxt = new Text("");
        GridPane.setConstraints(carPriceLbl, 7, 7);
        GridPane.setConstraints(carPriceTxt, 8, 7, 2, 1);

        Label downPayLbl = new Label("Kontant udbetaling:");
        Text downPayTxt = new Text("");
        GridPane.setConstraints(downPayLbl, 7, 8);
        GridPane.setConstraints(downPayTxt, 8, 8);

        Label payPeriodLbl = new Label("Afbetalingsperiode:");
        Text payPeriodTxt = new Text("");
        GridPane.setConstraints(payPeriodLbl, 7, 9);
        GridPane.setConstraints(payPeriodTxt, 8, 9);

        Label offerSalesPersLbl = new Label("Sælger:");
        Text offerSalesPersTxt = new Text();
        GridPane.setConstraints(offerSalesPersLbl, 7, 10);
        GridPane.setConstraints(offerSalesPersTxt, 8, 10);

        Label statusLabel = new Label("Status:");
        Text statusText = new Text();
        GridPane.setConstraints(statusLabel, 7, 11);
        GridPane.setConstraints(statusText, 8, 11, 3, 1);

        Label creditRatingLbl = new Label("Kreditværdighed:");
        Text creditRatingTxt = new Text();

        GridPane.setConstraints(creditRatingLbl, 11, 4);
        GridPane.setConstraints(creditRatingTxt, 12, 4);

        Label bankInterestLbl = new Label("Rentesats:");
        Text bankInterestTxt = new Text();
        GridPane.setConstraints(bankInterestLbl, 11, 5);
        GridPane.setConstraints(bankInterestTxt, 12, 5);

        Label downPayInterestLbl = new Label("Udbetalingsrente:");
        Text downPayInterestTxt = new Text();
        GridPane.setConstraints(downPayInterestLbl, 11, 6);
        GridPane.setConstraints(downPayInterestTxt, 12, 6);

        Label periodPayInterestLbl = new Label("Løbetidsrente:");
        Text periodPayInterestTxt = new Text();
        GridPane.setConstraints(periodPayInterestLbl, 11, 7);
        GridPane.setConstraints(periodPayInterestTxt, 12, 7);


        Label totalInterestRateLbl = new Label("Total rente:");
        Text totalInterestRateTxt = new Text();
        GridPane.setConstraints(totalInterestRateLbl, 11, 8);
        GridPane.setConstraints(totalInterestRateTxt, 12, 8);

        Text amountTxt = new Text("Beløb: ");
        amountTxt.setUnderline(true);
        GridPane.setConstraints(amountTxt, 11, 10);


        Label priceAfterDownPayLbl = new Label("Pris efter udbetaling:");
        Text priceAfterDownPayTxt = new Text();
        GridPane.setConstraints(priceAfterDownPayLbl, 11, 11);
        GridPane.setConstraints(priceAfterDownPayTxt, 12, 11, 4, 1);

        Label totalPriceLbl = new Label("Totalpris inkl. renter:");
        Text totalPriceTxt = new Text();
        GridPane.setConstraints(totalPriceLbl, 11, 12);
        GridPane.setConstraints(totalPriceTxt, 12, 12, 3, 1);

        Label monthPayLbl = new Label("Månedlig afbetaling:");
        Text monthPayTxt = new Text();
        GridPane.setConstraints(monthPayLbl, 11, 13);
        GridPane.setConstraints(monthPayTxt, 12, 13, 3, 1);


        Button acceptBtn = new Button("Opret");
        acceptBtn.setDisable(true);

        Button csvBtn = new Button("Eksportér til CSV");
        csvBtn.setDisable(true);
        GridPane.setConstraints(csvBtn, 7, 17);
        GridPane.setConstraints(acceptBtn, 8, 17);


        salesGrid.getChildren().addAll(
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

        clearButton.setOnAction(click -> UIManager.instance().switchCenter(new NewOfferView().createView()));

        calcQuoteButton.setOnAction(click -> {
            paymentCalculator.setDownPayment(Double.parseDouble(downPaymentTextField.getText()));
            periodPayInterestTxt.setText(paymentCalculator.calculatePaymentPeriodInterestRate(tools.yearsBetweenDates(payStartLocalDate, payEndLocalDate)) + "%");
            paymentCalculator.calculateAll();
            creditRatingTxt.setText(creditRatingText.getText());
            bankInterestTxt.setText(tools.formatter(paymentCalculator.getRkiAndBankInterestRate()));
            carPriceTxt.setText(tools.formatter(car.getPrice()));
            downPayTxt.setText(tools.formatter(paymentCalculator.getDownPayment()));
            downPayInterestTxt.setText(tools.formatter(paymentCalculator.getDownPaymentInterestRate()));
            createdTxt.setText(String.valueOf(saleDate));
            buyerTxt.setText(customer.getFirstName() + " " + customer.getLastName());
            payPeriodTxt.setText(tools.yearsBetweenDates(payStartLocalDate, payEndLocalDate) + " år");
            offerSalesPersTxt.setText(salesPerson.getFirstname() + " " + salesPerson.getLastname());
            carModelTxt.setText(car.getName());
            totalInterestRateTxt.setText(String.valueOf(tools.formatter(paymentCalculator.getTotalInterestRate())));
            totalInterestRateTxt.setUnderline(true);
            totalPriceTxt.setText(String.valueOf(tools.formatter(paymentCalculator.getTotalCarPrice())));
            totalPriceTxt.setUnderline(true);
            statusText.setText(status);
            monthPayTxt.setText(tools.formatter(tools.monthlyPay(payStartLocalDate, payEndLocalDate, paymentCalculator.getTotalCarPrice())));
            monthPayTxt.setUnderline(true);
            priceAfterDownPayTxt.setText(tools.formatter(paymentCalculator.getPriceAfterDownPayment()));
            priceAfterDownPayTxt.setUnderline(true);
            csvBtn.setDisable(false);
            acceptBtn.setDisable(false);

            if (!paymentCalculator.isRkiOK()) {
                saleDeniedAlert();
                acceptBtn.setDisable(true);
                csvBtn.setDisable(true);
            }


        });


        csvBtn.setOnAction(click -> {
            FileChooser fileChooser = new FileChooser();
            Stage window = new Stage();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Csv files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(window);

            if (file != null) {
                saveToCsv = new SaveToCsv();
                saveToCsv.saveOfferToCSV(
                        file, createdTxt.getText(), buyerTxt.getText(), carModelTxt.getText(), carPriceTxt.getText(), downPayTxt.getText(), priceAfterDownPayTxt.getText(),
                        payPeriodTxt.getText(), creditRatingTxt.getText(), bankInterestTxt.getText(),
                        downPayInterestTxt.getText(), periodPayInterestTxt.getText(), totalInterestRateTxt.getText(), totalPriceTxt.getText(),
                        monthPayTxt.getText(), statusText.getText()
                );
            }
        });

        acceptBtn.setOnAction(click -> {
            saveOffertoDB(customer, car, salesPerson, paymentCalculator.getCreditRating(), paymentCalculator, saleDate, payStartLocalDate, payEndLocalDate, status);
            if (needsApproval) {
                try {
                    alertSalesManager();
                    Platform.runLater(() -> {
                        try {
                            Notify.sendEmail();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                doneWithOfferConfirmation();
            } else if(!needsApproval){
                doneWithOfferConfirmation();
            }
        });

        HBox root = new HBox(salesGrid);
        return root;
    }


//PRIVATE METHODS--------------------------------------------------------


    private void setCarInfo(Car car) {
        priceText.setText(String.valueOf(car.getPrice()));
    }

    private void alertSalesManager() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Salgschef kontaktet");
        alert.setHeaderText("Email er blevet sendt til salgschefen");
        alert.setContentText("Prisen overstiger beløbsgrænsen" + "\n" +
                             "Tilbuddet skal godkendes salgschef før det kan aktiveres");
        alert.setHeight(400);
        alert.showAndWait();
    }


    private void checkCustomer() {
        String cprInput = cprTextField.getText();
        this.customer = CustomerDataAccessor.getCustomerDataAccess().getCustomerByCpr(cprInput);
        if (customer != null) {
            if (customer.isGoodGuy()) {
                setCustomerInfo(customer);
                System.out.println(customer.isGoodGuy());
                requestRkiRating(cprInput);
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
        }
    }


    private void requestRkiRating(String cprInput) {
        CompletableFuture.runAsync(() -> creditRatingText.setText(paymentCalculator.fetchCreditRating(cprInput)));
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
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void checkApprovalNeed(Car car) {
        if (car.getPrice() > 5000000) {
            needsApproval = true;
        } else {
            needsApproval = false;
        }
        setStatus(needsApproval);
    }

    private void setStatus(boolean needsApproval) {
        if (needsApproval) {
            status = "Afventer godkendelse";
        } else {
            status = "Aktiv";
        }
    }

    private void saveOffertoDB(Customer customer, Car car, SalesPerson salesperson, String creditRating, PaymentCalculator paymentCalculator, LocalDate saleDate, LocalDate startDate, LocalDate endDate, String status) {
        OfferDataAccessor.getOfferDataAccess().addOffer(new Offer(customer, car, salesperson, creditRating, paymentCalculator, saleDate, startDate, endDate, status));
    }


    private void saleDeniedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Handel nægtet");
        alert.setHeaderText("Vi handler ikke med denne kunde");
        alert.setContentText("Årsagen kan være tidligere dårlig opførsel eller at vedkommende er registreret hos RKI");
        alert.setHeight(400);
        alert.showAndWait();
    }

    private void doneWithOfferConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Tilbud gemt");
        alert.setHeaderText("Tilbuddet er gemt, er du færdig med det? ");
        alert.setContentText("Tryk OK hvis du er færdig med tilbuddet" + "\n" +
                             "Tryk Cancel hvis du vil have mulighed for at gemme til csv");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            UIManager.instance().switchCenter(new NewOfferView().createView());
        }
    }
}