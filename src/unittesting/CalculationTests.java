package unittesting;

import data.CarDataAccess;
import data.CarJDBC;
import logic.CreditRator;
import logic.InterestRate;
import org.junit.jupiter.api.Test;
import view.NewOfferView;

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
    void getRkiRating(){
        assertEquals("A",CreditRator.i().rate("1234567890")||"B",CreditRator.i().rate("1234567890")||"C",CreditRator.i().rate("1234567890")||"D",CreditRator.i().rate("1234567890"));
    }
    @Test
    void downPaymentCalc(){
        CarDataAccess carDataAccess = new CarJDBC();
        assert(carDataAccess.getCarsByCondition(car_model_id=1));
    }
}
