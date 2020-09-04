package org.hls.check;

import jext.util.JarUtils;

import java.io.File;

public class CheckJar {

    public static void main(String[] args) {

        File jarFile = new File("D:\\SPLGroup\\BTProjects\\FieldForecast 20110131\\lib\\FOSFramework.jar");
        String clazz = "com.bt.fos.framework.common.FrameWorkConfigFileReader";

        System.out.println(JarUtils.containsClass(jarFile, clazz));

        File jmodFile = new File("D:\\Java\\Jdk10.0.x64\\jmods\\java.activation.jmod");
        String claxx = "javax.activation.CommandMap";

        System.out.println(JarUtils.containsClass(jmodFile, claxx));

    }
}
