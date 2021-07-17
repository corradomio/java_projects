package org.hls.check;

import com.sun.jna.Structure;

import java.lang.reflect.Field;


public class App5 {

    public static void main(String[] args) throws Exception {
        Point2 p2 = new Point2();
        Field[] fields = Point2.class.getFields();
        Object o = fields[0].get(p2);

        Point p = new Point();
        Line l = new Line();

        System.out.println("ok");
    }
}
