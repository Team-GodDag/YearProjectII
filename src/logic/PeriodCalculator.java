package logic;

import javafx.scene.control.DatePicker;

import java.text.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class PeriodCalculator {
    private  int timeDifferenceInYears;


    public int yearsBetweenDates(String startDate, String endDate){


        String dateString1FromJDatePicker = startDate;
        String dateString2FromJDatePicker = endDate;

        LocalDate from = LocalDate.parse(dateString1FromJDatePicker);
        LocalDate to = LocalDate.parse(dateString2FromJDatePicker);
        int differenceInDays = (int) ChronoUnit.DAYS.between(from, to);
        this.timeDifferenceInYears = (differenceInDays / 365);

        return timeDifferenceInYears;



//        LocalDate start = LocalDate.parse(startDate);
//        LocalDate end = LocalDate.parse(endDate);
//        long p = ChronoUnit.DAYS.between(start, end);
//
//        System.out.println("P" + p);
//        System.out.println("EndDATE" + end);
//
//        Duration duration = Duration.between(start, end);
//        double timeDifferenceInDays = duration.toDays();
//        this.timeDifferenceInYears = (timeDifferenceInDays / 365);
//        System.out.println(timeDifferenceInDays);
       // System.out.println(timeDifferenceInYears);


    }
}

