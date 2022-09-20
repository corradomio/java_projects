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

        dumprtlibs(lfm);
        // checkPython(lfm);
        // checkCSharp(lfm);
        checkCSharpRTLibs(lfm);
    }

    private static void checkCSharpRTLibs(LibraryFinderManager lfm) {

        LibraryFinder lf = lfm.getLibraryFinder("csharp");
        lf.getRuntimeLibraries().forEach(rtl -> {
            System.out.println(rtl.getName());
        });

    }

    private static void checkPython(LibraryFinderManager lfm) {
        MavenCoords artifact;

        LibraryFinder lf = lfm.getLibraryFinder("python");

        // artifact = MavenCoords.pypi("networkx", "2.8.6");
        // artifact = MavenCoords.pypi("autograd", "1.4");
        // artifact = MavenCoords.pypi("neo4j", "4.4.6");
        // artifact = MavenCoords.pypi("gurobipy", "9.5.2");
        artifact = MavenCoords.pypi("flask", "2.2.2");

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
