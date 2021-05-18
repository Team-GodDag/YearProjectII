package logic;

import javafx.scene.text.Text;
import view.CustomerView;

import javax.swing.*;
import javax.swing.plaf.OptionPaneUI;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CsvWriter {

    public static void saveToCsv(String name, String phoneNumber, String email, String adress, String carModel, String price, String downPayment, String paymentPeriod, String salesPerson){
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
        try{
            FileWriter fw = new FileWriter("OfferOverview.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

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


            pw.flush();
            pw.close();
            System.out.println("Finished writing the file");
            JOptionPane.showMessageDialog(null, "Offer Has Been Saved");

        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Offer Has Not Been Saved");
            e.printStackTrace();
        }
    }
}
