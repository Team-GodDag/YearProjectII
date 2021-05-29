package logic;

import data.CreditRator;
import data.InterestRate;
import entities.Car;

import java.text.DecimalFormat;
import java.util.function.Consumer;

public class PaymentCalculator {

    private double rkiAndBankInterestRate;
    private double baseBankInterestRate/* = InterestRate.i().todaysRate()*/;

    private double downPaymentInterestRate;
    private int paymentPeriod;
    private double paymentPeriodInterestRate;

    private double priceAfterDownPayment;
    private double totalInterest;
    private double totalPrice;
    private String creditRate;          //hent fra CreditRator
    private Car car;
    private double downPayment = 0;



    public double rkiInterestCalc(String creditRating) {
        rkiAndBankInterestRate = 0.0;
        baseBankInterestRate = InterestRate.i().todaysRate();
        DecimalFormat df = new DecimalFormat("#,##");       //formattering bør ske i View, ikke logik

        //double rkiAndBankInterestRate;

        if (creditRating.equals("A")) {
            rkiAndBankInterestRate = InterestRate.i().todaysRate();
            rkiAndBankInterestRate += 1;
//            this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
            System.out.println(rkiAndBankInterestRate);
            //return rkiAndBankInterestRate;

        } if(creditRating.equals("B")) {
            rkiAndBankInterestRate = InterestRate.i().todaysRate(); //uhensigtsmæssigt at hente den flere gange
            rkiAndBankInterestRate += 2;
//            this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
            System.out.println(rkiAndBankInterestRate);
            //return rkiAndBankInterestRate;

        } if(creditRating.equals("C")) {
            rkiAndBankInterestRate = InterestRate.i().todaysRate();
            rkiAndBankInterestRate += 3;
//            this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
            System.out.println(rkiAndBankInterestRate);
            //return rkiAndBankInterestRate;

        } else {
            this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
            System.out.println(rkiAndBankInterestRate);
           // return rkiAndBankInterestRate;
        }
        return this.rkiAndBankInterestRate = Double.valueOf(df.format(rkiAndBankInterestRate));
    }

    public void calculateCarPriceAfterDownPayment(){
        if(downPayment > car.getPrice()){
            System.out.println("Udbetaling overstiger bilens pris");
        } else {
            priceAfterDownPayment = car.getPrice() - downPayment;
        }
    }

    public void calculateDownPaymentInterest() {
//        downPaymentInterestRate = 0.0;
        if(car.getPrice() >= downPayment) {
            if ((downPayment / car.getPrice()) < 0.5) {
                downPaymentInterestRate = 1.0;
                System.out.println(downPaymentInterestRate);
            }
        }
    }

    public void totalCarPrice() {  //behøver ikke downpayment som argument
        if(downPayment > car.getPrice()){
            throw new ArithmeticException("Udbetaling overstiger bilens pris");//return -1;
        }
        totalPrice = (car.getPrice() - downPayment) * (1 + (totalInterest /100));
//       return totalPrice;
    }

    public double calculatePaymentPeriodInterestRate(int paymentYears) { //bør måske tælle måneder i stedet?
        paymentPeriodInterestRate = 0.0;
        if(paymentYears < 0) {
            return -1;      //hvorfor ikke returnere en fejlbesked i stedet?
        } else if (paymentYears > 3) {
                paymentPeriodInterestRate = 1;
//                this.paymentPeriodInterestRate = Double.valueOf(paymentPeriodInterestRate); //hvad sker der her??? unødvendig
                System.out.println(paymentPeriodInterestRate);
                return paymentPeriodInterestRate;
        }
//        this.paymentPeriodInterestRate = paymentPeriodInterestRate;
        System.out.println(paymentPeriodInterestRate);
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
        calculateCarPriceAfterDownPayment();
        calculateDownPaymentInterest();
        totalCarPrice();
    }

    public void getCreditRating(String cprInput, Consumer<Enum> callback) {
        CreditRator.i().rate(cprInput);
    }

    public int getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(int paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
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

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
