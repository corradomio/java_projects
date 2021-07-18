package org.hls.check;

import java.lang.reflect.Field;

public class Base {

    public void check() throws IllegalAccessException {
        Field[] fields = getClass().getFields();
        Object o = fields[0].get(this);
    }
}
