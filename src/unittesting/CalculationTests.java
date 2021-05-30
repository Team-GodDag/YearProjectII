package unittesting;

import data.CreditRator;
import data.InterestRate;
import logic.PaymentCalculator;
import logic.PeriodCalculator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculationTests {

    @Test
    void getBankInterestRate(){
        assertNotNull(InterestRate.i().todaysRate());
    }

    @Test
    void assumeBankInterestRate(){
        assert(InterestRate.i().todaysRate()>= 3 && InterestRate.i().todaysRate()<=9);
    }
    @Test
    void failBankInterestRate(){
        assertFalse(InterestRate.i().todaysRate()> 9 || InterestRate.i().todaysRate()<3);
    }
    @Test
    void getRkiInterestRating(){
        String expectedRatings[] = {"A","B","C","D"};
        List<String> expectedTitlesList = Arrays.asList(expectedRatings);
        assertTrue(expectedTitlesList.contains((CreditRator.i().rate("1234567898").toString())));
    }
//    @Test
//    void downPaymentCalc(){
//        ArrayList<Car> cars = CarListFactory.createCarList();
//        //find en specifik bil og hent prisen, så indtast XX downpayment, og assert om resultatet bliver 0/1
//    }
    @Test
    void periodInterestRate(){
        // Test om udbetalings perioder over 3 år, udløser 1% extra rente
        PeriodCalculator periodCalculator = new PeriodCalculator();
        PaymentCalculator paymentCalculator = new PaymentCalculator();
        LocalDate dateOne = LocalDate.of(2021, 05, 05);
        LocalDate dateTwo = LocalDate.of(2025, 05, 05);
        assertEquals(1, paymentCalculator.calculatePaymentPeriodInterestRate(periodCalculator.yearsBetweenDates(dateOne, dateTwo)));
    }

//    @Test
//    void downPaymentInterest() {     //how to test???
//        PaymentCalculator calculator = new PaymentCalculator();
//        calculator.setCar();
//        assertEquals(1,calculator.calculateDownPaymentInterest(calculator.,100.0);
//    }

//    @Test
//    void downPaymentInterest2(){
//        PaymentCalc paymentCalc = new PaymentCalc();
//        assertEquals(0,paymentCalc.downPaymentCalc(10000000.0,9000000.0));
//    }

//    @Test
//    void totalInterestRate(){
//        PaymentCalc paymentCalc = new PaymentCalc();
//        PeriodCalculator periodCalculator = new PeriodCalculator();
//
//        double rkiAndBankInterestRate = 5;
//        double paymentPeriodInterestRate =paymentCalc.periodInterestRate(periodCalculator.yearsBetweenDates("2021-05-19","2027-05-19"));
//        double downPaymentInterestRate = paymentCalc.downPaymentCalc(10000000.0,2000000.0);
//
//        assertEquals(7,paymentCalc.calculateTotalInterests(rkiAndBankInterestRate,paymentPeriodInterestRate,downPaymentInterestRate));  //snydetest
//    }
//    void totalInterestRate2(){
//        PaymentCalc paymentCalc = new PaymentCalc();
//        PeriodCalculator periodCalculator = new PeriodCalculator();
//
//        double rkiAndBankInterestRate = 5;
//        double paymentPeriodInterestRate =paymentCalc.periodInterestRate(periodCalculator.yearsBetweenDates("2021-05-19","2027-05-19"));
//        double downPaymentInterestRate = paymentCalc.downPaymentCalc(10000000.0,9000000.0);
//
//        assertEquals(6,paymentCalc.calculateTotalInterests(rkiAndBankInterestRate,paymentPeriodInterestRate,downPaymentInterestRate));
//    }


}

