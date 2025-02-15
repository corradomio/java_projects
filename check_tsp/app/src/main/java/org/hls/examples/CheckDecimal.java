package org.hls.examples;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CheckDecimal {

    public static void main(String[] args) {
        // This is to show symbol . instead of ,
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        // Define the maximum number of decimals (number of symbols #)
        // DecimalFormat df = new DecimalFormat("#.######", otherSymbols);
        DecimalFormat df = new DecimalFormat("#.######");

        // type: double
        System.out.println(df.format(5.0)); // writes 5
        System.out.println(df.format(7.3)); // writes 7.3
        System.out.println(df.format(9.000003)); // writes 9.000003

        // type: float
        System.out.println(df.format(9.000003f)); // writes 9.000002861

    }
}
