package logic;

import data.CreditRator;
import data.InterestRate;
import entities.Car;


public class PaymentCalculator {

    // Lavet af Rikke, Rune, Lars

    private double baseBankInterestRate;
    private double rkiAndBankInterestRate;
    private boolean rkiOK;

    private double downPaymentInterestRate;
    private int paymentPeriod;
    private double paymentPeriodInterestRate;

    private double priceAfterDownPayment;
    private double totalPrice;
    private double totalInterestRate;
    private String creditRating;

    private Car car;
    private double downPayment;


    public boolean rkiInterestCalc(String creditRating) {

        rkiAndBankInterestRate = baseBankInterestRate;

        if (creditRating.equals("A")) {
            rkiOK = true;
            rkiAndBankInterestRate += 1;
        } if(creditRating.equals("B")) {
            rkiOK = true;
            rkiAndBankInterestRate += 2;
        } if(creditRating.equals("C")) {
            rkiOK = true;
            rkiAndBankInterestRate += 3;
        } else if(creditRating.equals("D")) {
            rkiOK = false;
        }
        return rkiOK;
    }


    public double calculateCarPriceAfterDownPayment() {
            priceAfterDownPayment = car.getPrice() - downPayment;
            if(downPayment > car.getPrice()) {
                throw new ArithmeticException("Udbetaling overstiger bilens pris");
            } else {
            priceAfterDownPayment = car.getPrice() - downPayment;
        }
            return priceAfterDownPayment;
    }

    public double calculateDownPaymentInterestRate() {
        if(car.getPrice() >= downPayment) {
            if ((downPayment / car.getPrice()) < 0.5) {
                downPaymentInterestRate = 1.0;
                System.out.println(downPaymentInterestRate);
            }
        }else if(downPayment > car.getPrice()){
            downPaymentInterestRate = -1;
        }
        return downPaymentInterestRate;
    }

    public void totalCarPrice() {  //behøver ikke downpayment som argument
        totalPrice = (car.getPrice() - downPayment) * (1 + (totalInterestRate /100));
    }

    public double calculatePaymentPeriodInterestRate(int paymentYears) {
        if(paymentYears < 0) {
            throw new ArithmeticException("Vælg en gyldig afbetalingsperiode");
        } else if (paymentYears > 3) {
            System.out.println("payment period interest rate >3y: " + paymentPeriodInterestRate);
            paymentPeriodInterestRate = 1;
        }
        System.out.println("payment period interest rate end: " + paymentPeriodInterestRate);
        return paymentPeriodInterestRate;
    }
    public void calculateTotalInterestRate() {
        totalInterestRate = rkiAndBankInterestRate + paymentPeriodInterestRate + downPaymentInterestRate;
    }

    public void calculateAll() {
        rkiInterestCalc(creditRating);
        calculateCarPriceAfterDownPayment();
        calculateDownPaymentInterestRate();
        calculatePaymentPeriodInterestRate(paymentPeriod);
        calculateTotalInterestRate();
        totalCarPrice();
    }

    public String fetchCreditRating(String cprInput) {
        creditRating = String.valueOf(CreditRator.i().rate(cprInput));
        return creditRating;
    }

    public double fetchBankInterestRate() {
        baseBankInterestRate = InterestRate.i().todaysRate();
        return baseBankInterestRate;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public boolean isRkiOK() {
        return rkiOK;
    }

    public int getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(int paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public double getRkiAndBankInterestRate() {
        return rkiAndBankInterestRate;
    }

    public double getBaseBankInterestRate() {
        return baseBankInterestRate;
    }

    public double getDownPaymentInterestRate() {
        return downPaymentInterestRate;
    }

    public double getPaymentPeriodInterestRate() {  //bruger vi ikke, fordi vi kalder calculatePaymentPeriodInterestRate og får værdi retur
        return paymentPeriodInterestRate;
    }

    public double getPriceAfterDownPayment() {
        return priceAfterDownPayment;
    }

    public double getTotalInterestRate() {
        return totalInterestRate;
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
