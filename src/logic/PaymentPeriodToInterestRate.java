package logic;

//Hvis tilbagebetalingen planlægges over mere end 3 år tillægges +1 procentpoint.

public class PaymentPeriodToInterestRate {

    public int periodInterestRate(int paymentYears){
        int paymentYearsInterestRate = 0;
        if(paymentYears > 3) {
            paymentYearsInterestRate = 1;
            return paymentYearsInterestRate;
        }
        return paymentYearsInterestRate;
    }
}