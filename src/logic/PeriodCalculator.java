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
    }
}

