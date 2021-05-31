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

public class PeriodCalculator {         //BEHØVER DEN EGEN KLASSE?

    private  int timeDifferenceInYears;         //skal vi kalde en setTimeDiffYears el. lign. i PaymentCalculator?

    public int yearsBetweenDates(LocalDate startDate, LocalDate endDate){

        LocalDate dateString1FromJDatePicker = startDate;
        LocalDate dateString2FromJDatePicker = endDate;
        LocalDate from = dateString1FromJDatePicker;
        LocalDate to = dateString2FromJDatePicker;
        int differenceInDays = (int) ChronoUnit.DAYS.between(from, to);
        timeDifferenceInYears = (differenceInDays / 365);

        return timeDifferenceInYears;
    }

    public int getTimeDifferenceInYears() {
        return timeDifferenceInYears;
    }
}

