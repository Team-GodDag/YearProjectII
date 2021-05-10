package logic;

/*
    Hvis kundens kreditværdighed er A, bruges bankens rentesats +1procentpoint.
    Hvis kundens kreditværdighed er B, bruges bankens rentesats +2 procentpoint.
    Hvis kundens kreditværdighed er C, bruges bankens rentesats +3 procentpoint.
*/

import view.NewOfferView;

public class CreditRateToInterestRate {

    //Metode der kalder på cpr nummer, det skal bruges i todaysRate() fra interestRate... (Spørg Rune)
    //==> spytter et bogstav ud som skal bruges i rkiInterestCalc


    public static double rkiInterestCalc(String creditRating) {

        double rkiInterestRate;

        if (creditRating == "A") {
            rkiInterestRate = InterestRate.i().todaysRate();
            rkiInterestRate += 1;
            System.out.println(InterestRate.i().todaysRate());
            System.out.println(rkiInterestRate);
            return rkiInterestRate;

        } if(creditRating == "B") {
            rkiInterestRate = InterestRate.i().todaysRate();
            rkiInterestRate += 2;
            System.out.println(InterestRate.i().todaysRate());
            System.out.println(rkiInterestRate);
            return rkiInterestRate;

        }if(creditRating == "C") {
            rkiInterestRate = InterestRate.i().todaysRate();
            rkiInterestRate += 3;
            System.out.println(InterestRate.i().todaysRate());
            System.out.println(rkiInterestRate);
            return rkiInterestRate;

        }else{
            return -1;
        }
    }

}



