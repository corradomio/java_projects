package jext.sourcecode.project;

import jext.maven.MavenCoords;

import java.util.Collection;

public interface LibraryFinder {

    /**
     * Create a new library finder attached to the specified project.
     * It inherits the current finder configuration
     *
     * @param project project owner of the finder
     * @return a new library finder
     */
    LibraryFinder newFinder(Project project);

    /**
     * Assign the project to this finder
     *
     * TODO: to remove!
     *
     * @param project project owner of this finder
     */
    void setProject(Project project);

    /**
     * Retrieve the project owner of this finder
     *
     * @return project owner of the finder
     */
    Project getProject();

    /**
     * Programming language supported by the libraries handled by this finder
     *
     * @return the name of the programming language
     */
    String getLanguage();

    /**
     * Library downloader used to download external libraries
     *
     * @return library downloader
     */
    LibraryDownloader getDownloader();

    /**
     * List of runtime libraries configured
     * @return list of runtime libraries configured
     */
    Collection<Library> getRuntimeLibraries();

    /**
     * Retrieve the runtime library.
     * Note the runtime library contains the runtime version
     *
     * @param libraryName name of the runtime library
     * @return the selected runtime library of the default one
     */
    Library getRuntimeLibrary(String libraryName);

    /**
     * Retrieve the external library specified by the maven coordinates.
     * At minimum it is necessary to specify artifactId and version.
     * If no version is specified, it is selected the latest one
     * @param coords library's maven coordinates
     * @return the library selected
     */
    Library getLibrary(MavenCoords coords);

    /**
     * Retrieve the latest version of the library specified by the
     * Maven coordinates.
     * At minimum it is necessary to specify artifactId.
     * @param coords library's maven coordinates
     * @return the latest version or the empty string
     */
    String getLatestVersion(MavenCoords coords);

}
