package jext.time;

public class DurationUtils {

    /**
     *
     *      DD:HH:MM:SS[.CCC]
     *         MM:SS[.CCC]
     *            SS.[CCC]  (WITH dot)
     *      '10.d' | '10.0h' | '10.0m' | '10.0s' == '10.'
     *      '10' -> in milliseconds
     *
     * @param duration (default milliseconds)
     * @return duration in milliseconds
     */
    public static long parse(String duration) {
        int l = duration.length();
        double seconds = 0;
        if (duration.contains(":")) {
            String[] parts = duration.split(":");
            int at=0;
            switch(parts.length) {
                //  DD:HH:MM:SS.CCC
                case 4:
                    seconds += Long.parseLong(parts[at++])*24*60*60;
                    // seconds += Long.parseLong(parts[1])*60*60;
                    // seconds += Long.parseLong(parts[2])*60;
                    // seconds += (long)(Double.parseDouble(parts[3]));
                    // break;
                //  HH:MM:SS.CCC
                case 3:
                    seconds += Long.parseLong(parts[at++])*60*60;
                    // seconds += Long.parseLong(parts[1])*60;
                    // seconds += (long)(Double.parseDouble(parts[2]));
                    // break;
                // MM:SS.CCC
                case 2:
                    seconds += Long.parseLong(parts[at++])*60;
                    seconds += (long)(Double.parseDouble(parts[at]));
                    break;
            }
        }
        else if (duration.endsWith("s")) {
            seconds = Double.parseDouble(duration.substring(0, l-1));
        }
        else if (duration.endsWith("m")) {
            seconds = Double.parseDouble(duration.substring(0, l-1))*60;
        }
        else if (duration.endsWith("h")) {
            seconds = Double.parseDouble(duration.substring(0, l-1))*60*60;
        }
        else if (duration.endsWith("d")) {
            seconds = Double.parseDouble(duration.substring(0, l-1))*24*60*60;
        }
        else if (duration.contains(".")) {
            seconds = Double.parseDouble(duration);
        }
        else {
            seconds = Double.parseDouble(duration)/1000.;
        }

        return (long)(seconds*1000);
    }
}
