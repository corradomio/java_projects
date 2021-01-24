package org.hls.check;

import jext.util.FileUtils;
import jext.util.JarUtils;

import java.io.File;


public class Main {
    public static void main(String[] args) {

        FileUtils.listFiles(new File("D:\\SPLGroup\\AhmedWorkspace\\check\\JDK"), 1,
            File::isDirectory,
            File::isDirectory)
            .forEach(file -> {
                System.out.println(file.getAbsolutePath());
            });

    }
}
