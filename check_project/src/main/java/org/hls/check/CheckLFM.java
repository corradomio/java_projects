package org.hls.check;

import jext.maven.MavenCoords;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryFinderManager;
import jext.sourcecode.project.lfm.ConfigurableLibraryFinderManager;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CheckLFM {

    public static void main(String[] args) {
        File configurationFile = new File("config/extlibsconfig.xml");

        LibraryFinderManager lfm = ConfigurableLibraryFinderManager.getManager(configurationFile);

        //dumprtlibs(lfm);
        //checkPython(lfm);
        checkCSharp(lfm);
    }

    private static void checkPython(LibraryFinderManager lfm) {
        MavenCoords artifact = MavenCoords.of("networkx", "2.8.6");
        LibraryFinder lf = lfm.getLibraryFinder("python");

        lf.getDownloader().checkArtifacts(Arrays.asList(artifact), false);

        Library l = lf.getLibrary(artifact);

        List<File> files = l.getFiles();
        System.out.println(files);
    }

    private static void checkCSharp(LibraryFinderManager lfm) {
        MavenCoords artifact;

        artifact = MavenCoords.of("Newtonsoft.Json", "13.0.1");
        artifact = MavenCoords.nuget("Lucene.Net/4.8.0-beta00016 ");

        LibraryFinder lf = lfm.getLibraryFinder("csharp");

        lf.getDownloader().checkArtifacts(Arrays.asList(artifact), false);

        Library l = lf.getLibrary(artifact);

        List<File> files = l.getFiles();
        System.out.println(files);
    }


    private static void dumprtlibs(LibraryFinderManager lfm) {

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
