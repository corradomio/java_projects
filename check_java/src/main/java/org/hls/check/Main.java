package org.hls.check;

import jext.util.JarUtils;

import java.io.File;


public class Main {
    public static void main(String[] args) {

        System.out.println(JarUtils.containsClass(
            new File("D:\\Starspace\\Users\\Corrado Mio\\.spl\\.extlib\\androidx\\appcompat\\appcompat\\1.0.0\\appcompat-1.0.0.aar"),
            "androidx.appcompat.app.ActionBar"
        ));


    }
}
