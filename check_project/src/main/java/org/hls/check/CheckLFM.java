package org.hls.check;

import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryFinderManager;
import jext.sourcecode.project.lfm.ConfigurableLibraryFinderManager;

import java.io.File;

public class CheckLFM {

    public static void main(String[] args) {
        File configurationFile = new File("config/extlibsconfig.xml");

        LibraryFinderManager lfm = ConfigurableLibraryFinderManager.getManager(configurationFile);

        lfm.getLibraryFinders().forEach(CheckLFM::dump);

        // LibraryFinder jlf = lfm.getLibraryFinder("java");
        // LibraryFinder cslf = lfm.getLibraryFinder("csharp");
        //
        // Library rtlib = cslf.getRuntimeLibrary("net6.0");
        // rtlib.getFiles().forEach(System.out::println);

        System.out.println("done");
    }

    static void dump(LibraryFinder lf) {
        System.out.println(lf.getLanguage());
        lf.getRuntimeLibraries().forEach(rtlib -> {
            System.out.printf("... %s: %s\n", rtlib.getName().getName(), rtlib.getVersion());
        });
    }
}
