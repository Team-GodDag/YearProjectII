package logic;

import data.CreditRator;
import data.InterestRate;
import entities.Car;
import entities.Offer;

import java.math.BigDecimal;


public class PaymentCalculator {
    // Lavet af Rikke, Rune, Lars

    private BigDecimal baseBankInterestRate;
    private BigDecimal rkiAndBankInterestRate;
    private boolean rkiOK;

    private BigDecimal downPaymentInterestRate;
    private int paymentPeriod;
    private BigDecimal paymentPeriodInterestRate;

    private BigDecimal priceAfterDownPayment;
    private BigDecimal totalPrice;
    private BigDecimal totalInterestRate;
    private String creditRating;

    private Car car;
    private BigDecimal downPayment;

//    private Offer offer;


    public boolean rkiInterestCalc(String creditRating) {

        rkiAndBankInterestRate = baseBankInterestRate;

        if (creditRating.equals("A")) {
            rkiOK = true;
            rkiAndBankInterestRate = rkiAndBankInterestRate.add(BigDecimal.valueOf(1));

        } if(creditRating.equals("B")) {
            rkiOK = true;
            rkiAndBankInterestRate = rkiAndBankInterestRate.add(BigDecimal.valueOf(2));

        } if(creditRating.equals("C")) {
            rkiOK = true;
            rkiAndBankInterestRate = rkiAndBankInterestRate.add(BigDecimal.valueOf(3));

        } else if(creditRating.equals("D")) {
            rkiOK = false;
        }
        return rkiOK;
    }


    public BigDecimal calculateCarPriceAfterDownPayment() {
            priceAfterDownPayment = car.getPrice().subtract(downPayment);

            if(downPayment.compareTo(car.getPrice()) > 0) {
                throw new ArithmeticException("Udbetaling overstiger bilens pris");
        }
            return priceAfterDownPayment;
    }

    public BigDecimal calculateDownPaymentInterestRate() {
        //if carPrice is more than or equal to downpayment
        if(car.getPrice().compareTo(downPayment) >= 0) {
            //and if downpayment is less than half carPrice
            if ((downPayment.compareTo(car.getPrice())) < 0.5) {
                //additional interest rate of 1%p
                downPaymentInterestRate = BigDecimal.valueOf(1);
                System.out.println(downPaymentInterestRate);
            }
        //else if downpayment is larger than carPrice
        } //else if(car.getPrice().compareTo(downPayment) < 0) {
//            downPaymentInterestRate = downPaymentInterestRate.add(BigDecimal.valueOf(-1)); //det lyder ikke helt sikkert...
//        }
        return downPaymentInterestRate;
    }

    public void totalCarPrice() {  //virkelig lang........
        totalPrice = ((car.getPrice().subtract(downPayment)).multiply(BigDecimal.valueOf(1).add(totalInterestRate.divide(BigDecimal.valueOf(100)))));//(car.getPrice() - downPayment) * (1 + (totalInterestRate /100));
    }

    public BigDecimal calculatePaymentPeriodInterestRate(int paymentYears) {
        paymentPeriodInterestRate = BigDecimal.valueOf(0);
        if(paymentYears < 0) {
            throw new ArithmeticException("Vælg en gyldig afbetalingsperiode");
        } else if (paymentYears >= 3) {
            System.out.println("payment period interest rate >3y: " + paymentPeriodInterestRate);
            paymentPeriodInterestRate = BigDecimal.valueOf(1);
        }
        System.out.println("payment period interest rate end: " + paymentPeriodInterestRate);
        return paymentPeriodInterestRate;
    }

    public void calculateTotalInterestRate() {
        totalInterestRate = rkiAndBankInterestRate.add(paymentPeriodInterestRate).add(downPaymentInterestRate);
        System.out.println("rki: " + rkiAndBankInterestRate + " payperiod: " + paymentPeriodInterestRate + " downpay: " + downPaymentInterestRate);
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

    public BigDecimal fetchBankInterestRate() {
        baseBankInterestRate = BigDecimal.valueOf(InterestRate.i().todaysRate());
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

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public BigDecimal getRkiAndBankInterestRate() {
        return rkiAndBankInterestRate;
    }

    public BigDecimal getBaseBankInterestRate() {
        return baseBankInterestRate;
    }

    public BigDecimal getDownPaymentInterestRate() {
        return downPaymentInterestRate;
    }

    public BigDecimal getPaymentPeriodInterestRate() {  //bruger vi ikke, fordi vi kalder calculatePaymentPeriodInterestRate og får værdi retur
        return paymentPeriodInterestRate;
    }

    public BigDecimal getPriceAfterDownPayment() {
        return priceAfterDownPayment;
    }

    public BigDecimal getTotalInterestRate() {
        return totalInterestRate;
    }

    public BigDecimal getTotalCarPrice() {
        return totalPrice;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
