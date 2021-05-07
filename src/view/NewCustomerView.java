package view;
// Når der skal oprettes en ny kunde
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class NewCustomerView {

    public GridPane createView() {

        Label nameLbl = new Label("Name: ");
        TextField nameTxtF = new TextField();
        nameTxtF.setPromptText("Anders Andersen");

        Label phoneLbl = new Label("Phone: ");
        TextField phoneTxtF = new TextField();
        phoneTxtF.setPromptText("(+45)12345678");

        Label emailLbl = new Label("E-mail: ");
        TextField emailTxtF = new TextField();
        emailTxtF.setPromptText("anders@andersenmail.dk");

        Label adressLbl = new Label("Adress: ");
        TextField adressTxtF = new TextField();
        adressTxtF.setPromptText("Andersengade 1, 1010 Andersby");  //overvej vej, nr, postnummer og land

        Label cprLbl = new Label("CPR: ");
        TextField cprTxtF = new TextField();
        cprTxtF.setPromptText("012345-6789");

        GridPane root = new GridPane();
        root.addRow(0, nameLbl, nameTxtF);
        root.addRow(1, phoneLbl, phoneTxtF);
        root.addRow(2, emailLbl, emailTxtF);
        root.addRow(3, adressLbl, adressTxtF);
        root.addColumn(2, cprLbl);
        root.addColumn(3, cprTxtF);
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(20, 20, 20, 20));
        return root;
    }

}
