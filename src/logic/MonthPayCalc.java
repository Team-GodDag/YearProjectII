package logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MonthPayCalc {
    private  double monthPrice;
    PriceFormat formatter = new PriceFormat();


    public double monthlyPay(String startDate, String endDate, double price){

        //price = Double.valueOf(formatter.carPriceFormatter(price));
        String dateString1FromJDatePicker = startDate;
        String dateString2FromJDatePicker = endDate;

        LocalDate from = LocalDate.parse(dateString1FromJDatePicker);
        LocalDate to = LocalDate.parse(dateString2FromJDatePicker);
        double differenceInMonths =  ChronoUnit.MONTHS.between(from, to);
        if(differenceInMonths < 1){
            return this.monthPrice = price;
        }
        return this.monthPrice = Double.valueOf(price / differenceInMonths);
    }
}
