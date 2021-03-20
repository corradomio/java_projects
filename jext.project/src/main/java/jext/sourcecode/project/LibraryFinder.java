package jext.sourcecode.project;

import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.util.Parameters;

public interface LibraryFinder {

    LibraryFinder setProject(Project project);

    LibraryFinder configure(Parameters params);

    Library getLibrary(MavenCoords coords);

    RuntimeLibrary getRuntimeLibrary(String libraryName);

    MavenDownloader getDownloader();

}
