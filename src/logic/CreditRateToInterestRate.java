package logic;

/*
    Hvis kundens kreditværdighed er A, bruges bankens rentesats +1procentpoint.
    Hvis kundens kreditværdighed er B, bruges bankens rentesats +2 procentpoint.
    Hvis kundens kreditværdighed er C, bruges bankens rentesats +3 procentpoint.
*/

import view.NewOfferView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CreditRateToInterestRate {

    //Metode der kalder på cpr nummer, det skal bruges i todaysRate() fra interestRate... (Spørg Rune)
    //==> spytter et bogstav ud som skal bruges i rkiInterestCalc

    public String rkiInterestCalc(String creditRating) {


        double rkiInterestRate = 0.0;
        DecimalFormat df = new DecimalFormat("#,##");

        //double rkiInterestRate;

        if (creditRating == "A") {
            rkiInterestRate = InterestRate.i().todaysRate();
            rkiInterestRate += 1;
            return df.format(rkiInterestRate);

        } if(creditRating == "B") {
            rkiInterestRate = InterestRate.i().todaysRate();
            rkiInterestRate += 2;
            return df.format(rkiInterestRate);

        }if(creditRating == "C") {
            rkiInterestRate = InterestRate.i().todaysRate();
            rkiInterestRate += 3;
            return df.format(rkiInterestRate);

        }else{
            return df.format(rkiInterestRate);
        }
    }

}



