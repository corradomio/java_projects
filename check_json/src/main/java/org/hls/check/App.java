package org.hls.check;

import jext.util.JSONUtils;
import jext.util.LongHash;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class App {

    public static void main(String[] args) throws IOException {
        Object o = JSONUtils.load(new File("test.json"), HashMap.class);
        JSONUtils.save(new File("out.json"), o);
    }

}
