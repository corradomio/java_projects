package org.hls.check;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class App {

    public static void main(String[] args) throws IOException {
        try(JarFile jf = new JarFile(new File("D:\\Java\\Jdk1.8.0.x64\\jre\\lib\\rt.jar"))) {
            ZipEntry ze = jf.getEntry("java/lang/Byte.class");
            System.out.println(ze);
            ze = jf.getEntry("java/lang/Byte1.class");
            System.out.println(ze);
        }

        try(JarFile jf = new JarFile(new File("D:\\Java\\Jdk15.0.x64\\jmods\\java.base.jmod"))) {
            ZipEntry ze = jf.getEntry("classes/java/lang/Byte.class");
            System.out.println(ze);
            ze = jf.getEntry("classes/java/lang/Byte1.class");
            System.out.println(ze);
        }
    }
}
