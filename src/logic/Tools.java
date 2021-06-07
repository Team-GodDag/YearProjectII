package logic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Tools {
    // Lavet af Rikke og Lars

    public BigDecimal monthlyPay(LocalDate startDate, LocalDate endDate, BigDecimal price) {

        BigDecimal differenceInMonths  = new BigDecimal(ChronoUnit.MONTHS.between(startDate, endDate));
        if (differenceInMonths.compareTo(BigDecimal.valueOf(1)) <= 0) {
            return price;
        }
        return  (price.divide(differenceInMonths));
    }

    public void inputNumbersOnly(final TextField tf) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

        public void makeInputNumbersOnly(final TextField tf) {
            tf.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        tf.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
        }

    public void numberNotTooBigValidator(TextField appliedToTxtField, Text biggerNumber, TextField smallerNumber){
        appliedToTxtField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if(Double.parseDouble(smallerNumber.getText()) > Double.parseDouble(biggerNumber.getText())  ){
                    smallerNumber.setText("");
                    appliedToTxtField.setText("");
                }
            }
        });
    }

    public int yearsBetweenDates(LocalDate from, LocalDate to) {
        int timeDifferenceInYears;

        int differenceInDays = (int) ChronoUnit.DAYS.between(from, to);
        timeDifferenceInYears = (differenceInDays / 365);


        return timeDifferenceInYears;
    }

    public String formatter(BigDecimal carPrice) {
        DecimalFormat dfGerman = new DecimalFormat("#,###.##",
            new DecimalFormatSymbols(Locale.GERMAN));
        return dfGerman.format(carPrice);
    }
}

