package logic;

//Hvis udbetalingen er under 50 % tillÃ¦gges +1procentpoint.

public class DownpaymentToInterestRate {

    public double downPaymentCalc(Double price, Double downPayment){
        int downPaymentInterestRate = 0;

        if( (price/downPayment) < 0.5 ){
            downPaymentInterestRate = 1;
            return downPaymentInterestRate;
        }
        return downPaymentInterestRate;
    }

}