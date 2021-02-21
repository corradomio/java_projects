package test.p11;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    static final SimpleDateFormat formatter = null;

    Date startTime;
    long duration;

    public String toString() {
        return "MaxTimeCondition" + duration + "," + formatter.format(startTime);
    }
}
