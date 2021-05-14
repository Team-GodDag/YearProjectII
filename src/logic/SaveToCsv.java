package logic;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.NewOfferView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveToCsv {
    public static void saveOffer(File stageName, String name, String phoneNumber, String email, String adress, String carModel, String price, String downPayment, String paymentPeriod, String salesPerson) {
        String csvHeader =
                "Name;" +
                        "Phone Number;" +
                        "Email;"+
                        "Adress;"+
                        "Car Model;"+
                        "Price;"+
                        "Down Payment;"+
                        "Payment Period (Years);"+
                        "Sales Person";
        try {
            PrintWriter pw;
            pw = new PrintWriter(stageName);

            pw.write(csvHeader + "\n");
            pw.write(name + ";");
            pw.write(phoneNumber + ";");
            pw.write(email + ";");
            pw.write(adress + ";");
            pw.write(carModel + ";");
            pw.write(price + ";");
            pw.write(downPayment + ";");
            pw.write(paymentPeriod + ";");
            pw.write(salesPerson);


            pw.close();
            System.out.println("Finished writing the file");
        } catch (IOException ex) {
            Logger.getLogger(NewOfferView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
