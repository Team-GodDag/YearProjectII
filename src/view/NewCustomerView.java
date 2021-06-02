package view;
// Når der skal oprettes en ny kunde
import entities.Customer;
import dataaccessors.CustomerDataAccessor;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.LimitedTextField;


public class NewCustomerView {

    TextField firstNameTxtF, lastNameTxtF, phoneTxtF, emailTxtF, addressTxtF;
    LimitedTextField limitedCprTextField;

    public GridPane createView() {

        Label firstNameLbl = new Label("Fornavn: ");
        firstNameTxtF = new TextField();
        firstNameTxtF.setPromptText("Anders");

        Label lastNameLbl = new Label("Efternavn: ");
        lastNameTxtF = new TextField();
        lastNameTxtF.setPromptText("Andersen");

        Label phoneLbl = new Label("Telefon: ");
        phoneTxtF = new TextField();
        phoneTxtF.setPromptText("(+45)12345678");

        Label emailLbl = new Label("E-mail: ");
        emailTxtF = new TextField();
        emailTxtF.setPromptText("anders@andersenmail.dk");

        Label addressLbl = new Label("Adresse og postnummer: ");
        addressTxtF = new TextField();
        addressTxtF.setPromptText("Andersengade 1, 1010 Andersby");

//        Label zipLbl = new Label("Postnummer: ");
//        zipTxtF = new TextField();
//        zipTxtF.setPromptText("Postnummer");

        Label cprLbl = new Label("CPR: ");
        limitedCprTextField = new LimitedTextField();
        limitedCprTextField.addLimiter( 10);
        limitedCprTextField.setPromptText("0123456789");

        Button saveBtn = new Button("Gem");
        saveBtn.setOnAction(click -> {
            saveCustomer(limitedCprTextField, firstNameTxtF, lastNameTxtF, emailTxtF, addressTxtF, phoneTxtF);
            saveBtn.getScene().getWindow().hide();
        });

        BooleanBinding saveBind = (
                        limitedCprTextField.textProperty().length().isNotEqualTo(10)
                        .or(firstNameTxtF.textProperty().isEmpty())
                        .or(lastNameTxtF.textProperty().isEmpty())

        );
        saveBtn.disableProperty().bind(saveBind);

        GridPane root = new GridPane();
        root.addRow(0, firstNameLbl, firstNameTxtF);
        root.addRow(0, lastNameLbl, lastNameTxtF);
        root.addRow(1, phoneLbl, phoneTxtF);
        root.addRow(2, emailLbl, emailTxtF);
        root.addRow(3, addressLbl, addressTxtF);
        root.addColumn(2, cprLbl);
        root.addColumn(3, limitedCprTextField);
        root.addRow(5, saveBtn);
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(20, 20, 20, 20));
        return root;
    }

    private void saveCustomer(TextField cpr, TextField firstName, TextField lastName, TextField email, TextField address, TextField phone) {
        CustomerDataAccessor.getCustomerDataAccess().addCustomer(new Customer(cpr.getText(), firstName.getText(), lastName.getText(), email.getText(), address.getText(), phone.getText()));
    }


}
