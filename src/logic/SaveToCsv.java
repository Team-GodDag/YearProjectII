package logic;



import view.NewOfferView;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveToCsv {
    // Lavet af Lars

    public  void saveOfferToCSV(File stageName, String created, String name, String carModel, String price,
                                String downPayment, String priceAfterDp, String paymentPeriod, String creditRating, String bankInterest,
                                String downPaymentInterest, String periodInterest, String totalInterest, String totalPrice,
                                String monthlyPayment, String approvedBy) {

        String csvHeader =
                        "Created;" +
                        "Name;" +
                        "Car Model;"+
                        "Price;"+
                        "Down Payment;"+
                        "Price After Down Payment;"+
                        "Payment Period (Years);"+
                        "Credit Ratin;" +
                        "Bankt Interest;"+
                        "Downpayment Interest;"+
                        "Period Interest;"+
                        "Total Interest;"+
                        "Total price With Interest;"+
                        "Monthly Payment;"+
                        "Approved By;";
        try {
            PrintWriter pw;
            pw = new PrintWriter(stageName);

            pw.write(csvHeader + "\n");

            pw.write(created + ";");
            pw.write(name + ";");
            pw.write(carModel + ";");
            pw.write(price + ";");
            pw.write(downPayment + ";");
            pw.write(priceAfterDp + ";");
            pw.write(paymentPeriod + ";");
            pw.write(creditRating + ";");
            pw.write(bankInterest + ";");
            pw.write(downPaymentInterest + ";");
            pw.write(periodInterest + ";");
            pw.write(totalInterest + ";");
            pw.write(totalPrice + ";");
            pw.write(monthlyPayment + ";");
            pw.write(approvedBy + ";");

            pw.flush();
            pw.close();

            System.out.println("Finished writing the file");
        } catch (IOException ex) {
            Logger.getLogger(NewOfferView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
