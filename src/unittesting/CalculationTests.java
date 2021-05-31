package unittesting;

import data.CreditRator;
import data.InterestRate;
import dataaccessors.CarDataAccessor;
import entities.Car;
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

    @Test
    void calculateDownpaymentInterestRate() {
        Car car = new Car();
        car.setPrice(5000000);
        PaymentCalculator paymentCalculator = new PaymentCalculator();
        paymentCalculator.setCar(car);
        paymentCalculator.setDownPayment(1000);
        assertEquals(1, paymentCalculator.calculateDownPaymentInterestRate());
        //find en specifik bil og hent prisen, så indtast XX downpayment, og assert om resultatet bliver 0/1
    }

    @Test   //samme som ovenfor, no?
    void downPaymentInterestRate() {     //how to test???
        PaymentCalculator calculator = new PaymentCalculator();
        ;
        calculator.getDownPayment();
        assertEquals(1, calculator.getDownPaymentInterestRate());
    }

    @Test
    void periodInterestRate() {
        // Test om udbetalings perioder over 3 år, udløser 1% extra rente
        PeriodCalculator periodCalculator = new PeriodCalculator();
        PaymentCalculator paymentCalculator = new PaymentCalculator();
        LocalDate dateOne = LocalDate.of(2021, 05, 05);
        LocalDate dateTwo = LocalDate.of(2025, 05, 05);
        assertEquals(1, paymentCalculator.calculatePaymentPeriodInterestRate(periodCalculator.yearsBetweenDates(dateOne, dateTwo)));
    }

    @Test
    void calculateCarPriceAfterDownpayment() {
        Car car = new Car();
        car.setPrice(5000000);
        PaymentCalculator paymentCalculator = new PaymentCalculator();
        paymentCalculator.setCar(car);
        paymentCalculator.setDownPayment(1000);
        assertEquals(4999000, paymentCalculator.calculateCarPriceAfterDownPayment());
    }

//    @Test
//    void too_high_downpayment_should_throw_exception() {
//        Car car = new Car();
//        car.setPrice(5000);
//        PaymentCalculator paymentCalculator = new PaymentCalculator();
//        paymentCalculator.setDownPayment(6000);
//        assertThrows(ArithmeticException)
//    }

    @Test
    void downPaymentInterest2(){
        PaymentCalculator paymentCalc = new PaymentCalculator();
        Car car = new Car();
        car.setPrice(5000);
        paymentCalc.setDownPayment(4000);
        paymentCalc.setCar(car);
        assertEquals(0, paymentCalc.calculateDownPaymentInterestRate());
    }

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
//    @Test
//    void totalInterestRate2() {
//        PaymentCalculator paymentCalc = new PaymentCalculator();
//        PeriodCalculator periodCalculator = new PeriodCalculator();
//        LocalDate dateOne = LocalDate.of(2021, 05, 05);
//        LocalDate dateTwo = LocalDate.of(2025, 05 ,05);
//
//        double rkiAndBankInterestRate = 5;
//        double paymentPeriodInterestRate = paymentCalc.calculatePaymentPeriodInterestRate((periodCalculator.yearsBetweenDates(dateOne, dateTwo)));
////        double downPaymentInterestRate = paymentCalc.calculateDownPaymentInterestRate((10000000.0,9000000.0));
//
//        assertEquals(6,paymentCalc.calculateTotalInterests(rkiAndBankInterestRate,paymentPeriodInterestRate,downPaymentInterestRate));
//    }


}

