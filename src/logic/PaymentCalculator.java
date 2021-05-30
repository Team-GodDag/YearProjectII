package logic;

import data.CreditRator;
import data.InterestRate;
import entities.Car;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DecimalFormat;
import java.util.function.Consumer;

public class PaymentCalculator {


    private double baseBankInterestRate;
    private double rkiAndBankInterestRate;
    private boolean rkiIsOK;

    private double downPaymentInterestRate;
    private int paymentPeriod;
    private double paymentPeriodInterestRate;

    private double priceAfterDownPayment;
    private double totalPrice;
    private double totalInterest;
    private String creditRating;          //hent fra CreditRator

    private Car car;
    private double downPayment = 0;
    PeriodCalculator periodCalculator;



    public boolean rkiInterestCalc(String creditRating) {

        rkiAndBankInterestRate = baseBankInterestRate;
//        baseBankInterestRate = InterestRate.i().todaysRate();
        DecimalFormat df = new DecimalFormat("#,##");       //formattering bør egentlig ske i View, ikke logik

        //double rkiAndBankInterestRate;

        if (creditRating.equals("A")) {
            rkiIsOK = true;
            rkiAndBankInterestRate += 1;
            System.out.println(rkiAndBankInterestRate);

        } if(creditRating.equals("B")) {
            rkiIsOK = true;
            rkiAndBankInterestRate += 2;
//            this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
            System.out.println(rkiAndBankInterestRate);
            //return rkiAndBankInterestRate;

        } if(creditRating.equals("C")) {
            rkiIsOK = true;
            rkiAndBankInterestRate += 3;
//            this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
            System.out.println(rkiAndBankInterestRate);
            //return rkiAndBankInterestRate;

        } else if(creditRating.equals("D")) {
            rkiIsOK = false;
            System.out.println(rkiAndBankInterestRate);
        }
        rkiAndBankInterestRate = Double.parseDouble(df.format(rkiAndBankInterestRate));
        return rkiIsOK;
    }


    public void calculateCarPriceAfterDownPayment() {
        try {
            priceAfterDownPayment = car.getPrice() - downPayment;
//            if(downPayment > car.getPrice()) {
//                System.out.println("Udbetaling overstiger bilens pris");        //do pop-up
//            } else {
//                priceAfterDownPayment = car.getPrice() - downPayment;
//            }
        } catch(ArithmeticException e) {
            e.printStackTrace();
        }
    }

    public void calculateDownPaymentInterest() {
        if(car.getPrice() >= downPayment) {
            if ((downPayment / car.getPrice()) < 0.5) {
                downPaymentInterestRate = 1.0;
                System.out.println(downPaymentInterestRate);
            }
        }
    }

    public void totalCarPrice() {  //behøver ikke downpayment som argument
        totalPrice = (car.getPrice() - downPayment) * (1 + (totalInterest /100));

        if(downPayment > car.getPrice()){
            throw new ArithmeticException("Udbetaling overstiger bilens pris");     //return -1;
        }
    }

    public double calculatePaymentPeriodInterestRate(int paymentYears) { //bør måske tælle måneder i stedet?
//        paymentPeriodInterestRate = 0.0;
        if(paymentYears < 0) {
            throw new ArithmeticException("Vælg en gyldig periode");              //????? godt nok?
        } else if (paymentYears > 3) {
            paymentPeriodInterestRate = 1;
            System.out.println("payment period interest rat: " + paymentPeriodInterestRate);
        }
        System.out.println("payment period interest rat: " + paymentPeriodInterestRate);
        return paymentPeriodInterestRate;
    }

    public void calculateTotalInterest() {
        totalInterest = rkiAndBankInterestRate + paymentPeriodInterestRate + downPaymentInterestRate;
    }

//-------------------------------------------------------
    //snydemetode til unit testing
    public double calculateTotalInterests(double rkiAndBankInterestRate,double paymentPeriodInterestRate, double downPaymentInterestRate){
        //double totalInterest = rkiAndBankInterestRate + paymentPeriodInterestRate + downPaymentInterestRate;
        //System.out.println(totalInterest);
        this.totalInterest = rkiAndBankInterestRate+paymentPeriodInterestRate + downPaymentInterestRate;
        return totalInterest;
    }
//-------------------------------------------------------

    public void calculateAll() {
        rkiInterestCalc(creditRating);
        calculateCarPriceAfterDownPayment();
        calculateDownPaymentInterest();
        calculateTotalInterest();
        totalCarPrice();
    }

    public String fetchCreditRating(String cprInput) {
        creditRating = String.valueOf(CreditRator.i().rate(cprInput));
        return creditRating;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public double fetchInterestRate() {
        baseBankInterestRate = InterestRate.i().todaysRate();
        return baseBankInterestRate;
    }

    public boolean isRkiIsOK() {
        return rkiIsOK;
    }

    public int getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(int paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public double getCombinedInterestRate() {
        return rkiAndBankInterestRate;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public double getRkiAndBankInterestRate() {
        return rkiAndBankInterestRate;
    }

    public double getBaseBankInterestRate() {   //denne skal kalde metode i InterestRate
        return baseBankInterestRate;
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

    public double getTotalCarPrice() {
        return totalPrice;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
