package unittesting;

import data.CreditRator;
import data.InterestRate;
import logic.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.NewOfferView;

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
    void downPaymentCalc(){
        PaymentCalc paymentCalc = new PaymentCalc();
        double carPrice = 2000000;
        double downPayment = 999999;
        assertEquals(1,paymentCalc.downPaymentCalc(carPrice,downPayment));
    }
    @Test
    void downPaymentCalc2(){
        PaymentCalc paymentCalc = new PaymentCalc();
        double carPrice = 2000000;
        double downPayment = 1000001;
        assertEquals(0,paymentCalc.downPaymentCalc(carPrice,downPayment));
    }
    @Test
    void downPaymentCalc3(){
        PaymentCalc paymentCalc = new PaymentCalc();
        double carPrice = 2000000;
        double downPayment = 2000001;
        assertEquals(-1,paymentCalc.downPaymentCalc(carPrice,downPayment));
    }
    @Test
    void periodInterestRate(){
        NewOfferView newOfferView;
        PeriodCalculator periodCalculator = new PeriodCalculator();
        PaymentCalc paymentCalc = new PaymentCalc();
        assertEquals(1,paymentCalc.periodInterestRate(periodCalculator.yearsBetweenDates("2021-05-19","2027-05-19")));
    }
    @Test
    void periodInterestRate2(){
        NewOfferView newOfferView;
        PeriodCalculator periodCalculator = new PeriodCalculator();
        PaymentCalc paymentCalc = new PaymentCalc();
        assertEquals(0,paymentCalc.periodInterestRate(periodCalculator.yearsBetweenDates("2021-05-19","2023-05-19")));
    }
    @Test
    void downPaymentInterest(){
        PaymentCalc paymentCalc = new PaymentCalc();
        assertEquals(1,paymentCalc.downPaymentCalc(10000000.0,100.0));
    }
    @Test
    void downPaymentInterest2(){
        PaymentCalc paymentCalc = new PaymentCalc();
        assertEquals(0,paymentCalc.downPaymentCalc(10000000.0,9000000.0));
    }
    @Test
    void downPaymentInterest3(){
        PaymentCalc paymentCalc = new PaymentCalc();
        assertEquals(-1,paymentCalc.downPaymentCalc(1000000.0,9000000.0));
    }
    @Test
    void totalInterestRate(){
        PaymentCalc paymentCalc = new PaymentCalc();
        PeriodCalculator periodCalculator = new PeriodCalculator();

        double rkiAndBankInterestRate = 5;
        double paymentPeriodInterestRate =paymentCalc.periodInterestRate(periodCalculator.yearsBetweenDates("2021-05-19","2027-05-19"));
        double downPaymentInterestRate = paymentCalc.downPaymentCalc(10000000.0,2000000.0);

        assertEquals(7,paymentCalc.calculateTotalInterests(rkiAndBankInterestRate,paymentPeriodInterestRate,downPaymentInterestRate));
    }
    @Test
    void totalInterestRate2(){
        PaymentCalc paymentCalc = new PaymentCalc();
        PeriodCalculator periodCalculator = new PeriodCalculator();

        double rkiAndBankInterestRate = 5;
        double paymentPeriodInterestRate =paymentCalc.periodInterestRate(periodCalculator.yearsBetweenDates("2021-05-19","2027-05-19"));
        double downPaymentInterestRate = paymentCalc.downPaymentCalc(10000000.0,9000000.0);

        assertEquals(6,paymentCalc.calculateTotalInterests(rkiAndBankInterestRate,paymentPeriodInterestRate,downPaymentInterestRate));
    }
    @Test
    void carPriceAfterDownPayment(){
        PaymentCalc paymentCalc = new PaymentCalc();

        assertEquals(400000,paymentCalc.carPriceAfterDownPayment(450000,50000));
    }
    @Test
    void carPriceAfterDownPayment2(){
        PaymentCalc paymentCalc = new PaymentCalc();

        assertEquals(-1,paymentCalc.carPriceAfterDownPayment(450000,500000));
    }
    @Test
    void totalCarPriceWithInterest(){
        PaymentCalc paymentCalc = new PaymentCalc();
        assertEquals(1620000,paymentCalc.totalCarPrice(2000000,500000,8));
    }
    @Test
    void totalCarPriceWithInterest2(){
        PaymentCalc paymentCalc = new PaymentCalc();
        assertEquals(-1,paymentCalc.totalCarPrice(2000000,5000000,8));
    }
    @Test
    void testSalesLimit(){
        SalesLimit salesLimit = new SalesLimit();
        assertEquals("Need Sales Manager",salesLimit.approval(1000001,1,"John"));
    }
    @Test
    void testSalesLimit2(){
        SalesLimit salesLimit = new SalesLimit();
        assertEquals(" John",salesLimit.approval(1000000,1,"John"));
    }
    @Test
    void monthlyPayCalc(){
        MonthPayCalc monthPayCalc = new MonthPayCalc();
        assertEquals(100000,monthPayCalc.monthlyPay("2021-05-19","2022-05-19",1200000));
    }
    @Test
    void rki9CifreTest(){
        assertThrows(NumberFormatException.class,
                ()->{CreditRator.i().rate("123456789").toString();
                });
    }

//    PaymentCalc
//    SQL database
//    SælgerNavn
//    KundeNavn
//    BilNavn
//    BilPris
//    Ordre*
    }




