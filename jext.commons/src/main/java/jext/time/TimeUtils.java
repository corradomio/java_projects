package jext.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    private static final SimpleDateFormat ID_FORMAT   = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");


    public static String formatId(Date date) {
        return ID_FORMAT.format(date);
    }

    public static String format(Date date){
        return DATE_FORMAT.format(date);
    }

    /**
     * interval ::= value                   -- in millis
     *           |  value unit              -- in unit ('ms', 's')
     *           |  value (':' value)+       -- mm:ss | hh:mm:ss | dd:hh:mm:ss
     * @param interval
     * @return
     */
    public static long toMillis(String interval) {
        int n = interval.length();
        long time = 0;
        if(interval.contains(":")) {
            String[] parts = interval.split(":");
            switch (parts.length) {
                case 4: // dd:hh:mm:ss
                    time = ((Long.parseLong(parts[0])*24 +
                             Long.parseLong(parts[1]))*60 +
                             Long.parseLong(parts[2]))*60 +
                             Long.parseLong(parts[3]);
                    break;
                case 3: // hh:mm:ss
                    time = (Long.parseLong(parts[0])*60 +
                            Long.parseLong(parts[1]))*60 +
                            Long.parseLong(parts[2]);
                    break;
                default: // mm:ss
                    time =  Long.parseLong(parts[0])*60 +
                            Long.parseLong(parts[1]);
                    break;
            }

            return time*1000;
        }
        else if (interval.endsWith("ms"))
            return Long.parseLong(interval.substring(0, n-2));
        else if (interval.endsWith("s"))
            return Long.parseLong(interval.substring(0, n-1))*1000;
        else
            return Long.parseLong(interval);
    }
}
