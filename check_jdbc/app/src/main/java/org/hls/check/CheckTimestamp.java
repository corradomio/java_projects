package org.hls.check;


import ae.ac.ebtic.sql.bt.btproxy.ISO8601DateFormat;

import java.util.Date;

public class CheckTimestamp {

    public static void main(String[] args) {
        Date date = new Date();
        //stance(DateFormat.ISO_8601
        System.out.println(ISO8601DateFormat.formatTimestamp(date));
    }
}
