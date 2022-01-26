package org.hls.check;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckTimestamp {

    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d);
        System.out.println(d.getTime());
        System.out.println(System.currentTimeMillis());
        Date d0 = new Date(1970,1,1, 0, 0, 0);

        System.out.println(d0.getTime());

        // Date d = new Date();
        // d = new Date(2021, 1, 25, 11, 25, 33);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(df.format(d));

    }
}
