package org.hls.check;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.java.libraries.JarLibrary;
import jext.util.FileUtils;

import java.io.File;

public class CheckLibraryVersions {

    public static void main(String[] args) {

        FileUtils.listFiles(new File("D:\\Java\\Spark\\spark-3.2.1-bin-hadoop3.2\\jars"), ".jar")
                .forEach(lib -> {
                    Library l = new JarLibrary(lib);
                    System.out.println(l.getVersion());
                    System.out.println(l.getLicense().getType());
                });

    }
}
