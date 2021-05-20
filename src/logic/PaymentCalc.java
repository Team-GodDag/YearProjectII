package logic;

import java.text.DecimalFormat;

public class PaymentCalc {

    private double rkiAndBankInterestRate;
    private double bankInterestRate;
    private double downPaymentInterestRate;
    private double paymentPeriodInterestRate;
    private double priceAfterDownPayment;
    private double totalInterest;

    private double downPayment = 0;

    public double rkiInterestCalc(String creditRating) {

        rkiAndBankInterestRate = 0.0;
        bankInterestRate = InterestRate.i().todaysRate();
        DecimalFormat df = new DecimalFormat("#,##");

        //double rkiAndBankInterestRate;

        if (creditRating == "A") {
            rkiAndBankInterestRate = InterestRate.i().todaysRate();
            rkiAndBankInterestRate += 1;
            this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
            System.out.println(rkiAndBankInterestRate);
            //return rkiAndBankInterestRate;

        } if(creditRating == "B") {
            rkiAndBankInterestRate = InterestRate.i().todaysRate();
            rkiAndBankInterestRate += 2;
            this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
            System.out.println(rkiAndBankInterestRate);
            //return rkiAndBankInterestRate;

        }if(creditRating == "C") {
            rkiAndBankInterestRate = InterestRate.i().todaysRate();
            rkiAndBankInterestRate += 3;
            this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
            System.out.println(rkiAndBankInterestRate);
            //return rkiAndBankInterestRate;

        }else{
            this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
            System.out.println(rkiAndBankInterestRate);
           // return rkiAndBankInterestRate;
        }
        return this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
    }

    public  double downPaymentCalc(Double price, Double downPayment){

        double downPaymentInterestRate = 0.0;
        if(price>=downPayment) {
            if ((downPayment / price) < 0.5) {
                downPaymentInterestRate = 1.0;
                this.downPaymentInterestRate = Double.valueOf(downPaymentInterestRate);
                System.out.println(downPaymentInterestRate);
                return Double.valueOf(downPaymentInterestRate);
            }
            this.downPaymentInterestRate = Double.valueOf(downPaymentInterestRate);
            return Double.valueOf(downPaymentInterestRate);
        }else
            return -1;

    }

    public double periodInterestRate(int paymentYears){
        paymentPeriodInterestRate = 0.0;
        if(paymentYears<0){
            return -1;
        }else {
            if (paymentYears > 3) {
                paymentPeriodInterestRate = 1;
                this.paymentPeriodInterestRate = Double.valueOf(paymentPeriodInterestRate);
                System.out.println(paymentPeriodInterestRate);
                return Double.valueOf(paymentPeriodInterestRate);
            }
            this.paymentPeriodInterestRate = Double.valueOf(paymentPeriodInterestRate);
            System.out.println(paymentPeriodInterestRate);
            return Double.valueOf(paymentPeriodInterestRate);
        }
    }

    public double calculateTotalInterest(){
        double totalInterest = rkiAndBankInterestRate + paymentPeriodInterestRate + downPaymentInterestRate;
        System.out.println(totalInterest);
        this.totalInterest = Double.valueOf(totalInterest);
        return Double.valueOf(totalInterest);

    }
    public double calculateTotalInterests(double rkiAndBankInterestRate,double paymentPeriodInterestRate,double downPaymentInterestRate){
        //double totalInterest = rkiAndBankInterestRate + paymentPeriodInterestRate + downPaymentInterestRate;
        //System.out.println(totalInterest);
        this.totalInterest = Double.valueOf(rkiAndBankInterestRate+paymentPeriodInterestRate+downPaymentInterestRate);
        return Double.valueOf(totalInterest);

    }
    public double carPriceAfterDownPayment(double carPrice, double downPayment){
        if(downPayment>carPrice){
            return -1;
        }
        this.priceAfterDownPayment = carPrice - downPayment;
        return priceAfterDownPayment;
    }

    public double totalCarPrice(double price, double downPayment){
        if(downPayment>price){
            return -1;
        }
       double totalPrice = (price - downPayment) * (1 + (totalInterest /100));
       return Double.valueOf(totalPrice);
    }

    public double getInterestRate() {
        return rkiAndBankInterestRate;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public double getRkiAndBankInterestRate() {
        return rkiAndBankInterestRate;
    }

    public void setDownPaymentInterestRate(double downPaymentInterestRate) {
        this.downPaymentInterestRate = downPaymentInterestRate;
    }

    public void setPaymentPeriodInterestRate(double paymentPeriodInterestRate) {
        this.paymentPeriodInterestRate = paymentPeriodInterestRate;
    }

    public void setPriceAfterDownPayment(double priceAfterDownPayment) {
        this.priceAfterDownPayment = priceAfterDownPayment;
    }

    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public void setDownPayment(String downpayMent) {
        this.downPayment = Double.valueOf(downpayMent);
    }

    public double getBankInterestRate() {
        return bankInterestRate;
    }

    public double getDownPaymentInterestRate() {
        return downPaymentInterestRate;
    }

    public double getPaymentPeriodInterestRate() {
        return paymentPeriodInterestRate;
    }

    public double getPriceAfterDownPayment() {
        return priceAfterDownPayment;
    }

    public double getTotalInterest() {
        return totalInterest;
    }
}
