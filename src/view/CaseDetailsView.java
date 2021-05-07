package view;
//når man på tilbud som er oprettet allerede
//pop up når når NewOfferView tilbud udregnes
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class CaseDetailsView {

    public GridPane createScene() {

        Label caseNoLbl = new Label("Case No.:");
        Text caseNoTxt = new Text();

        Label carPriceLbl = new Label("Car price:");
        Text carPriceTxt = new Text();

        Label createdLbl = new Label("Created:");
        Text createdTxt = new Text();

        Label downPayLbl = new Label("Down payment:");
        Text downPayTxt = new Text();

        Label salesPersLbl = new Label("Salesperson:");
        Text salesPersTxt = new Text();

        Label interestRateLbl = new Label("Interest rate:");
        Text interestRateTxt = new Text();

        Label buyerLbl = new Label("Buyer:");
        Text buyerTxt = new Text();

        Label cprLbl = new Label("CPR:");
        Text cprTxt = new Text();

        Label totalPriceLbl = new Label("Price:");
        Text totalPriceTxt = new Text();

        Label creditWorthLbl = new Label("Creditworthiness:");
        Text creditWorthTxt = new Text();

        Label carModelLbl = new Label("Car model:");
        Text carModelTxt = new Text();

        Label approvedByLbl = new Label("Approved by:");
        Text approvedByTxt = new Text();

        Label startDateLbl = new Label("Start date:");
        Text startDateTxt = new Text();

        Label endDateLbl = new Label("End date:");
        Text endDateTxt = new Text();

        Button printBtn = new Button("Print");

        GridPane root = new GridPane();
        root.addRow(0, caseNoLbl, caseNoTxt, carPriceLbl, carPriceTxt);
        root.addRow(1, createdLbl, createdTxt, downPayLbl, downPayTxt);
        root.addRow(2, salesPersLbl, salesPersTxt, interestRateLbl, interestRateTxt);
        root.addRow(3, cprLbl, cprTxt, totalPriceLbl, totalPriceTxt);
        root.addRow(4, creditWorthLbl, creditWorthTxt, startDateLbl, startDateTxt);
        root.addRow(5, carModelLbl, carModelTxt, endDateLbl, endDateTxt);
        root.addRow(6, approvedByLbl, approvedByTxt);
        root.addRow(7, printBtn);

        root.setHgap(50);
        root.setVgap(20);
        root.setPadding(new Insets(20, 20, 20, 20));
        return root;
    }
}
