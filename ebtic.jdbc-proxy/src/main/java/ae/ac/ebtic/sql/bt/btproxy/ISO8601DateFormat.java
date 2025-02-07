package ae.ac.ebtic.sql.bt.btproxy;

import jext.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/*
    ISO 8601    "yyyy-MM-dd'T'HH:mm'Z'"

    java.util.Date
        java.sql.Date
        java.sql.Time
        java.sql.Timestamp
 */

public class ISO8601DateFormat {
    private static final TimeZone TIMEZONE = TimeZone.getTimeZone("UTC");
    private static DateFormat TIMESTAMP_FORMAT;
    private static DateFormat TIME_FORMAT;
    private static DateFormat DATE_FORMAT;
    static {
        TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        TIMESTAMP_FORMAT.setTimeZone(TIMEZONE);

        TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    }

    public static String formatTimestamp(Date date) {
        return TIMESTAMP_FORMAT.format(date);
    }

    public static String formatTime(Date date) {
        return TIME_FORMAT.format(date);
    }
    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static Date parse(String date) throws SQLException {
        try {
            if (date.contains(":") && !date.contains("-"))
                return TIME_FORMAT.parse(date);
            else if (!date.contains(":") && date.contains("-"))
                return DATE_FORMAT.parse(date);
            else
                return TIMESTAMP_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new SQLException(e.getMessage(), date);
        }
    }
}
