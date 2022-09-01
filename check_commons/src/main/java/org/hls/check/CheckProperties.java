package org.hls.check;

import jext.util.Properties;

public class CheckProperties {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty("p0", "zero");
        props.setProperty("p1", "${p0} one");
        props.setProperty("p2", "$(p1) two");
        props.setProperty("p3", "$(p4) three");
        props.setProperty("p4", "$(p3) four");

        System.out.println(props.getProperty("p0"));
        System.out.println(props.getProperty("p1"));
        System.out.println(props.getProperty("p2"));
        System.out.println(props.getProperty("p3"));
    }
}
