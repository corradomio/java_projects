package jext.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    private static final SimpleDateFormat ID_FORMAT   = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    // ----------------------------------------------------------------------
    // Format date
    // ----------------------------------------------------------------------

    public static String formatId(Date date) {
        return ID_FORMAT.format(date);
    }

    public static String format(Date date){
        return DATE_FORMAT.format(date);
    }

    public static String formatDate(long timestamp) {
        return format(new Date(timestamp));
    }

    // ----------------------------------------------------------------------
    // Parse interval
    // ----------------------------------------------------------------------

    public static long parse(String interval) {
        return toMillis(interval);
    }

    // ----------------------------------------------------------------------
    // Format interval
    // ----------------------------------------------------------------------

    public static String format(long millis) {
        return format(millis, false);
    }

    public static String format(long millis, boolean showMillis) {
        if (showMillis)
            return toIntervalMillis(millis);
        else
            return toInterval(millis);
    }


    /**
     * Retrieve the unit measure useful to represent the value as an integer
     * value time
     *
     * @param millis milliseconds
     * @return the time measure unit
     */
    public static String unitOf(long millis) {
        if (millis > 24*60*60*1000)
            return "d";
        if (millis > 60*60)
            return "h";
        if (millis % 1000 != 0)
            return "ms";
        millis /= 1000;
        if (millis % 60 != 0)
            return "s";
        millis /= 60;
        if (millis % 60 != 0)
            return "min";
        return "h";
    }

    public static String format(long millis, String unit) {
        long factor;

        if (millis == Long.MAX_VALUE)
            return "inf";

        if (unit == null || unit.length() == 0)
            unit = unitOf(millis);

        if (unit.startsWith("i"))
            return toInterval(millis);

        factor = 1000;
        if (unit.equals("ms"))
            return String.format("%d ms", millis);
        if (unit.equals("s")) {
            long frac = millis % factor;
            if (frac == 0)
                return String.format("%d s", millis / factor);
            else
                return String.format("%.3f s", millis / ((float)factor));
        }

        factor *= 60;
        if (unit.equals("min") || unit.equals("m")) {
            long frac = millis % factor;
            if (frac == 0)
                return String.format("%d min", millis / factor);
            else
                return String.format("%.3f min", millis / ((float)factor));
        }

        factor *= 60;
        if (unit.equals("h") || unit.equals("hr")) {
            long frac = millis % factor;
            if (frac == 0)
                return String.format("%d h", millis / factor);
            else
                return String.format("%.3f h", millis / ((float)factor));
        }

        factor *= 24;
        if (unit.equals("d")) {
            long frac = millis % factor;
            if (frac == 0)
                return String.format("%d d", millis / factor);
            else
                return String.format("%.3f d", millis / ((float)factor));
        }
        else
            return String.format("%.3f s", millis/1000.);
    }

    // ----------------------------------------------------------------------

    /**
     * interval ::= value                   -- integer in millis or float in seconds
     *           |  value unit              -- in unit ('ms', 's', 'm'|'min', 'h'|'hr', 'd')
     *           |  value (':' value)+      -- mm:ss.ccc | hh:mm:ss.ccc | dd:hh:mm:ss.ccc
     *
     * if value doesn't contain a ':' or a time unit, it it interpreted in two way
     * 1) if it contains a '.', it is expressed in seconds
     * 2) if it is an integer value, it is expressed in milliseconds
     *
     * 'm'  is supported BUT IT IS AN ERROR: the correct unit measure for minutes is 'min'
     * 'hr' is supported BUT IT IS AN ERROR: the correct unit measure for hours is 'h'
     *
     * @param interval time interval in one of several formats
     * @return time in milliseconds
     */
    private static long toMillis(String interval) {
        int n = interval.length();

        if (n==0 || interval.equals("0"))
            return 0;

        // mm:ss[.ccc] | hh:mm:ss[.ccc] | dd:hh:mm:ss[.ccc]
        if (interval.contains(":"))
            return parseInterval(interval);

        float value;
        if (interval.endsWith("ms") || interval.endsWith("hr"))
            value = Float.parseFloat(interval.substring(0, n-2).trim());
        else if (interval.endsWith("min"))
            value = Float.parseFloat(interval.substring(0, n-3).trim());
        else if (interval.endsWith("s") || interval.endsWith("m") || interval.endsWith("h") || interval.endsWith("d"))
            value = Float.parseFloat(interval.substring(0, n-1).trim());
        else
            value = Float.parseFloat(interval.trim());

        // '3.5 ms'
        if (interval.endsWith("ms"))
            return (long)(value);
        // '3.5 s'
        else if (interval.endsWith("s"))
            return (long)(value*1000);
        // '3.5 m'  ==  '3.5 min'
        else if (interval.endsWith("m"))
            return (long)(value*60*1000);
        // '3.5 min'
        else if (interval.endsWith("min"))
            return (long)(value*60*1000);
        // '3.5 h'
        else if (interval.endsWith("h"))
            return (long)(value*60*60*1000);
        // '3.5 hr'
        else if (interval.endsWith("hr"))
            return (long)(value*60*60*1000);
        // '3.5 d'
        else if (interval.endsWith("d"))
            return (long)(value*24*60*60*1000);
        // '3.5' -> seconds
        else if (interval.contains("."))
            return (long)(value*1000);
        // '35' -> milliseconds
        else
            return (long)(value);
    }

    /**
     *
     *      HH:MM:SS[.CCC]
     *         MM:SS[.CCC]
     *            SS[.CCC]
     *      10.0h' | '10.0m' | '10.0s'
     *
     *
     * @param duration (default seconds)
     * @return duration in milliseconds
     */
    private static long parseInterval(String duration) {
        String[] parts = duration.split(":");
        float millis = 0;
        int i = 0;
        switch(parts.length) {
            case 4:
                // dd:hh:mm:ss[.ccc]
                millis += Float.parseFloat(parts[i++].trim())*24*60*60;
                // millis += Float.parseFloat(parts[i++].trim())*60*60;
                // millis += Float.parseFloat(parts[i++].trim())*60;
                // millis += Float.parseFloat(parts[i++].trim());
                // break;
            case 3:
                // hh:mm:ss[.ccc]
                millis += Float.parseFloat(parts[i++].trim())*60*60;
                // millis += Float.parseFloat(parts[i++].trim())*60;
                // millis += Float.parseFloat(parts[i++].trim());
                // break;
            case 2:
                // mm:ss[.ccc]
                millis += Float.parseFloat(parts[i++].trim())*60;
                // millis += Float.parseFloat(parts[i++].trim());
                // break;
            default:
                // ss[.ccc]
                millis += Float.parseFloat(parts[i]);
                break;
        }
        return (long)(millis*1000+0.5);
    }

    // ----------------------------------------------------------------------

    private static String toInterval(long millis) {
        if (millis == 0)
            return "0";
        if (millis == Long.MAX_VALUE)
            return "inf";

        long temp = (millis + 500)/1000;
        long seconds = temp % 60;
        temp = temp / 60;
        long minutes = temp % 60;
        temp = temp / 60;
        long hours = temp % 24;
        temp = temp / 24;
        long days = temp;

        if (days != 0)
            return String.format("%d:%02d:%02d:%02d", days, hours, minutes, seconds);
        else if (hours != 0)
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        else if (minutes != 0)
            return String.format("%d:%02d", minutes, seconds);
        else
            return String.format("%ds", seconds);
    }

    private static String toIntervalMillis(long millis) {
        if (millis == Long.MAX_VALUE)
            return "inf";
        long frac = millis % 1000;
        if (frac == 0)
            return toInterval(millis);

        long temp = millis;
        temp = temp/1000;
        long seconds = temp % 60;
        temp  = temp / 60;
        long minutes = temp % 60;
        temp  = temp / 60;
        long hours = temp % 24;
        temp = temp / 24;
        long days = temp;

        if (days != 0)
            return String.format("%d:%02d:%02d:%02d.%03d", days, hours, minutes, seconds, frac);
        else if (hours != 0)
            return String.format("%d:%02d:%02d.%03d", hours, minutes, seconds, frac);
        else if (minutes != 0)
            return String.format("%d:%02d.%03d", minutes, seconds, frac);
        else
            return String.format("%2d.%03d", seconds, frac);
    }

}
