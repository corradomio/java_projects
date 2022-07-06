package jext.sourcecode.project.none;

import jext.maven.MavenCoords;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectException;
import jext.util.Parameters;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NullLibraryFinder implements LibraryFinder {

    // ----------------------------------------------------------------------
    // Singleton
    // ----------------------------------------------------------------------

    private static class NullDownloader implements LibraryDownloader {

        @Override
        public String getName() {
            return "null";
        }

        @Override
        public void addRepository(String name, String url) {

        }

        @Override
        public LibraryDownloader newDownloader() {
            return this;
        }

        @Override
        public void checkArtifacts(Collection<MavenCoords> artifacts, boolean b, boolean parallel) {

        }
    }


    // ----------------------------------------------------------------------
    // Singleton
    // ----------------------------------------------------------------------

    private static final LibraryDownloader DOWNLOADER = new NullDownloader();
    private static final LibraryFinder INSTANCE = new NullLibraryFinder() {
        @Override
        public void setProject(Project project) {

        }
    };

    public static LibraryFinder instance() {
        return INSTANCE;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Project project;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private NullLibraryFinder() {

    }

    private NullLibraryFinder(Project project) {
        this.project = project;
    }

    public LibraryFinder newFinder(Project project) {
        return new NullLibraryFinder(project);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String getLanguage() {
        return project != null ? project.getLanguage() : "";
    }

    @Override
    public LibraryDownloader getDownloader() {
        return DOWNLOADER;
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public Library getLibrary(MavenCoords coords) {
        return null;
    }

    @Override
    public String getLatestVersion(String libraryName) {
        return "";
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        return "";
    }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Library> getRuntimeLibraries() {
        return Collections.emptyList();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
