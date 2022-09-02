package org.hls.check;

import jext.sourcecode.project.python.util.Requirements;

import java.io.File;

public class CheckRequirements {

    public static void main(String[] args) {
        Requirements req = new Requirements(new File("requirements.txt"));

        req.getLibraries().forEach(coords -> {
            System.out.println(coords);
        });

    }
}
