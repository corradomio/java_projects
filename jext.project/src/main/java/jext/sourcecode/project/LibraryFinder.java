package jext.sourcecode.project;

import jext.maven.MavenCoords;
import jext.util.Parameters;

public interface LibraryFinder {

    <LF extends LibraryFinder> LF setProject(Project project);

    <LF extends LibraryFinder> LF setDownloader(LibraryDownloader ld);
    LibraryDownloader getDownloader();

    <LF extends LibraryFinder> LF configure(Parameters params);

    Library getRuntimeLibrary(String libraryName);

    Library getLibrary(MavenCoords coords);

    String getLatestVersion(MavenCoords coords);

}
