package view;
// Når der skal oprettes en ny kunde
import entities.Customer;
import factories.CustomerListFactory;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class NewCustomerView {

    TextField firstNameTxtF, lastNameTxtF, phoneTxtF, emailTxtF, addressTxtF, zipTxtF, cprTxtF;

    public GridPane createView() {

        Label firstNameLbl = new Label("Name: ");
        firstNameTxtF = new TextField();
        firstNameTxtF.setPromptText("Anders");

        Label lastNameLbl = new Label("Efternavn: ");
        lastNameTxtF = new TextField();
        lastNameTxtF.setPromptText("Andersen");

        Label phoneLbl = new Label("Phone: ");
        phoneTxtF = new TextField();
        phoneTxtF.setPromptText("(+45)12345678");

        Label emailLbl = new Label("E-mail: ");
        emailTxtF = new TextField();
        emailTxtF.setPromptText("anders@andersenmail.dk");

        Label addressLbl = new Label("Adress: ");
        addressTxtF = new TextField();
        addressTxtF.setPromptText("Andersengade 1, 1010 Andersby");

        Label zipLbl = new Label("Postnummer: ");
        zipTxtF = new TextField();
        zipTxtF.setPromptText("Postnummer");

        Label cprLbl = new Label("CPR: ");
        cprTxtF = new TextField();
        cprTxtF.setPromptText("012345-6789");

        Button saveBtn = new Button("Gem");
        saveBtn.setOnAction(click -> {
            saveCustomer(cprTxtF, firstNameTxtF, lastNameTxtF, emailTxtF, addressTxtF, zipTxtF, phoneTxtF);
        });

        GridPane root = new GridPane();
        root.addRow(0, firstNameLbl, firstNameTxtF);
        root.addRow(0, lastNameLbl, lastNameTxtF);
        root.addRow(1, phoneLbl, phoneTxtF);
        root.addRow(2, emailLbl, emailTxtF);
        root.addRow(3, addressLbl, addressTxtF);
        root.addRow(4, zipLbl, zipTxtF);
        root.addColumn(2, cprLbl);
        root.addColumn(3, cprTxtF);
        root.addRow(5, saveBtn);
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(20, 20, 20, 20));
        return root;
    }

    private void saveCustomer(TextField cpr, TextField firstName, TextField lastName, TextField email, TextField address, TextField zipTxtF, TextField phone) {
        CustomerListFactory.addCustomer(new Customer(cpr.getText(), firstName.getText(), lastName.getText(), email.getText(), address.getText() + " " + zipTxtF.getText(), phone.getText()));

    }

}
