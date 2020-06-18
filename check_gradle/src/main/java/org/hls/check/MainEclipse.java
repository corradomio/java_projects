package org.hls.check;

import jext.buildtools.eclipse.EclipseProject;
import jext.buildtools.util.Dump;

import java.io.File;

public class MainEclipse {

    public static void main(String[] args) {
        EclipseProject p = new EclipseProject(new File("D:\\Mathematica"));

        Dump.project(p);
    }
}
