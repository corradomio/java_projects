package jext.sourcecode.project;

import jext.maven.MavenCoords;
import jext.util.Parameters;

public interface LibraryFinder {

    LibraryFinder setProject(Project project);

    LibraryFinder configure(Parameters params);

    Library getRuntimeLibrary(String libraryName);

    Library getLibrary(MavenCoords coords);

    String getLatestVersion(MavenCoords coords);

    LibraryDownloader getLibraryDownloader();

}
