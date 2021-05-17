package logic;

import java.text.DecimalFormat;

public class PaymentCalc {

    private double rkiInterestRate;
    private double downPaymentInterestRate;
    private double paymentYearsInterestRate;
    private double priceAfterDownPayment;
    private double totalInterest;

    public double rkiInterestCalc(String creditRating) {


        double rkiInterestRate = 0.0;
        DecimalFormat df = new DecimalFormat("#,##");

        //double rkiInterestRate;

        if (creditRating == "A") {
            rkiInterestRate = InterestRate.i().todaysRate();
            rkiInterestRate += 1;
            this.rkiInterestRate = Double.valueOf(df.format(rkiInterestRate));
            System.out.println(rkiInterestRate);
            //return rkiInterestRate;

        } if(creditRating == "B") {
            rkiInterestRate = InterestRate.i().todaysRate();
            rkiInterestRate += 2;
            this.rkiInterestRate = Double.valueOf(df.format(rkiInterestRate));
            System.out.println(rkiInterestRate);
            //return rkiInterestRate;

        }if(creditRating == "C") {
            rkiInterestRate = InterestRate.i().todaysRate();
            rkiInterestRate += 3;
            this.rkiInterestRate = Double.valueOf(df.format(rkiInterestRate));
            System.out.println(rkiInterestRate);
            //return rkiInterestRate;

        }else{
            this.rkiInterestRate = Double.valueOf(df.format(rkiInterestRate));
            System.out.println(rkiInterestRate);
           // return rkiInterestRate;
        }
        return this.rkiInterestRate = Double.valueOf(df.format(rkiInterestRate));
    }

    public  double downPaymentCalc(Double price, Double downPayment){

        double downPaymentInterestRate = 0.0;
        //String result;

        if( (downPayment/price) < 0.5 ){
            downPaymentInterestRate = 1.0;
            //result = String.valueOf(downPaymentInterestRate);
            this.downPaymentInterestRate = Double.valueOf(downPaymentInterestRate);
            System.out.println(downPaymentInterestRate);
            return Double.valueOf(downPaymentInterestRate);
        }
        //result = String.valueOf(downPaymentInterestRate);
        System.out.println("FEJL");
        this.downPaymentInterestRate = Double.valueOf(downPaymentInterestRate);
        System.out.println(downPaymentInterestRate);
        return Double.valueOf(downPaymentInterestRate);


    }

    public double periodInterestRate(int paymentYears){
        double paymentYearsInterestRate = 0.0;
        if(paymentYears > 3) {
            paymentYearsInterestRate = 1;
            this.paymentYearsInterestRate = Double.valueOf(paymentYearsInterestRate);
            System.out.println(paymentYearsInterestRate);
            return Double.valueOf(paymentYearsInterestRate);
        }
        this.paymentYearsInterestRate = Double.valueOf(paymentYearsInterestRate);
        System.out.println(paymentYearsInterestRate);
        return Double.valueOf(paymentYearsInterestRate);
    }

    public double calculateTotalInterest(){
        double totalInterest = rkiInterestRate + paymentYearsInterestRate + downPaymentInterestRate;
        System.out.println(totalInterest);
        this.totalInterest = Double.valueOf(totalInterest);
        return Double.valueOf(totalInterest);

    }
    public double carPriceAfterDownPayment(double carPrice, double downpayment){
        this.priceAfterDownPayment = carPrice - downpayment;
        return priceAfterDownPayment;
    }

    public double totalCarPrice(double price, double downPayment){

       double totalPrice = (price - downPayment) * (1 + (totalInterest /100));
       return Double.valueOf(totalPrice);
    }
}
