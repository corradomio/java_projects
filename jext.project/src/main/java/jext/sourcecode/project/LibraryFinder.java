package jext.sourcecode.project;

import jext.maven.MavenCoords;

import java.util.Collection;

public interface LibraryFinder {

    LibraryFinder newFinder(Project project);

    void setProject(Project project);

    Project getProject();

    String getLanguage();

    LibraryDownloader getDownloader();

    Library getRuntimeLibrary(String libraryName);

    Collection<Library> getRuntimeLibraries();

    Library getLibrary(MavenCoords coords);

    String getLatestVersion(MavenCoords coords);

}
