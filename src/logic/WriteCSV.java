package logic;

import view.NewOfferView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteCSV {
  //  NewOfferView newOfferView = new NewOfferView();


    public static void writeCsvFile(String fileName){

        //newOfferView.createView().




        try(PrintWriter pw = new PrintWriter(fileName)){
            pw.write("Name" + ";" + "Model" + ";" + "Price" + ";" + "Sales Person"+"\n");
            pw.write("Lars" + ";");
            pw.write("Duster" + ";");
            pw.write("1,2 mio" + ";");
            pw.write("John");

            pw.flush();
            pw.close();

            System.out.println("Finished writing the file");

        }catch(FileNotFoundException e){
            System.out.println("Error writing to file");
            e.printStackTrace();
        }
    }



}