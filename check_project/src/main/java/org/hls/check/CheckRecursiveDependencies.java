package org.hls.check;

import jext.cache.CacheManager;
import jext.util.logging.Logger;
import jext.maven.MavenCoords;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryFinderManager;
import jext.sourcecode.project.lfm.ConfigurableLibraryFinderManager;
import jext.util.concurrent.Parallel;

import java.io.File;

public class CheckRecursiveDependencies {

    static void checkcsharp(LibraryFinderManager lfm) {
        LibraryFinder lf = lfm.getLibraryFinder("csharp");

        Library l;

        // l = lf.getLibrary(MavenCoords.of("serilog", "2.10.0"));
        // l.getFiles().forEach(f -> {
        //     System.out.println(f);
        // });

        l = lf.getLibrary(MavenCoords.of("Serilog.Sinks.File", "5.0.0"));
        System.out.println(l.getFiles());
        System.out.println(l.getDependencies());
    }

    static void checkjave(LibraryFinderManager lfm) {
        LibraryFinder lf = lfm.getLibraryFinder("java");

        Library l;

        l = lf.getLibrary(MavenCoords.of("ch.qos.logback", "logback-classic", "1.2.3"));
        System.out.println(l.getFiles());
        System.out.println(l.getDependencies());
    }


    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        File configurationFile = new File("config/extlibsconfig.xml");

        LibraryFinderManager lfm = ConfigurableLibraryFinderManager.getManager(configurationFile);

        checkcsharp(lfm);
        checkjave(lfm);

        Parallel.shutdown();
        CacheManager.shutdown();
    }
}
