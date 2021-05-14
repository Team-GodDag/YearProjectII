package logic;

//Hvis udbetalingen er under 50 % tillÃ¦gges +1procentpoint.

import java.text.DecimalFormat;

public class DownpaymentToInterestRate {

    public  double downPaymentCalc(Double price, Double downPayment){

        double downPaymentInterestRate = 0.0;
        //String result;

        if( (downPayment/price) < 0.5 ){
            downPaymentInterestRate = 1.0;
            //result = String.valueOf(downPaymentInterestRate);
            return downPaymentInterestRate;
        }
        //result = String.valueOf(downPaymentInterestRate);
        System.out.println("FEJL");
        return downPaymentInterestRate;


    }

}