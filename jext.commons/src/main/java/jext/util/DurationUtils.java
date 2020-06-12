package jext.util;

public class DurationUtils {

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
    public static long parse(String duration) {
        int l = duration.length();
        double millis = 0;
        if (duration.contains(":")) {
            String[] parts = duration.split(":");
            switch(parts.length) {
                case 3:
                    millis += Long.parseLong(parts[0])*60*60;
                    millis += Long.parseLong(parts[1])*60;
                    millis += (long)(Double.parseDouble(parts[2]));
                    break;
                case 2:
                    millis += Long.parseLong(parts[0])*60;
                    millis += (long)(Double.parseDouble(parts[1]));
                    break;
                default:
                    millis += (long)(Double.parseDouble(parts[0]));
                    break;
            }
        }
        else if (duration.endsWith("s")) {
            millis = Double.parseDouble(duration.substring(0, l-1));
        }
        else if (duration.endsWith("m")) {
            millis = Double.parseDouble(duration.substring(0, l-1))*60;
        }
        else if (duration.endsWith("h")) {
            millis = Double.parseDouble(duration.substring(0, l-1))*60*60;
        }
        else {
            millis = Double.parseDouble(duration);
        }

        return (int)(millis*1000);
    }
}
