package logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MonthPayCalc {
    private  double monthPrice;
    PriceFormat formatter = new PriceFormat();


    public double monthlyPay(LocalDate startDate, LocalDate endDate, double price){

        //price = Double.valueOf(formatter.carPriceFormatter(price));
//        String dateString1FromJDatePicker = startDate;
//        String dateString2FromJDatePicker = endDate;

        LocalDate from = startDate;
        LocalDate to = endDate;
        double differenceInMonths =  ChronoUnit.MONTHS.between(from, to);
        if(differenceInMonths < 1) {
            return this.monthPrice = price;
        }
        return this.monthPrice = price / differenceInMonths;
    }
}
