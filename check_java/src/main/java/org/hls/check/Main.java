package org.hls.check;

import jext.nio.file.WildcardFileFilter;
import jext.util.FileUtils;
import jext.util.JarUtils;
import jext.util.Wildcard;

import java.io.File;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {

        WildcardFileFilter wcff = new WildcardFileFilter();

        wcff.addPattern("D:/Projects.github/java_projects/jext.*/src/main/java/jext/nio/file/Ciccio.java");

        System.out.println(wcff.accept("D:/Projects.github/java_projects/jext.commons/src/main/java/jext/nio/file/Ciccio.java"));
        System.out.println(wcff.accept("D:/Projects.github/java_projects/jext.commons/src/main/java/jext/nio/pinco/Ciccio.java"));

    }
}
