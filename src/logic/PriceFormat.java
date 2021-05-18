package logic;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PriceFormat {
    private String carPrice;
    private double carPriceDouble;

    public String formatter(String carPrice) {
        DecimalFormat dfGerman = new DecimalFormat("#,###.##",
                new DecimalFormatSymbols(Locale.GERMAN));
        String input = carPrice;
        double d = Double.parseDouble(input);

        return this.carPrice = dfGerman.format(d);

    }

    public String formatter(double carPrice) {
        DecimalFormat dfGerman = new DecimalFormat("#,###.##",
                new DecimalFormatSymbols(Locale.GERMAN));
        String input = String.valueOf(carPrice);
        double d = Double.parseDouble(input);
        return this.carPrice = String.valueOf(dfGerman.format(d));
    }
}
