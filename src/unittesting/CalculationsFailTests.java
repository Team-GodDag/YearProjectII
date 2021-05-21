package unittesting;

import data.CreditRator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class CalculationsFailTests {


    @Test
    void rki9CifreTest(){
        assertThrows(NumberFormatException.class,
                ()->{
                    CreditRator.i().rate("123456789").toString();
                });
    }
}
