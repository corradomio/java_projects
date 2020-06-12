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
}
