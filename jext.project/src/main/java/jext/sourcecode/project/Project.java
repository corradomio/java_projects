package jext.sourcecode.project;

import jext.name.IdNamed;
import jext.maven.MavenDownloader;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public interface Project extends IdNamed {

    /**
     * List of properties:
     *
     *  - project.type: project type ('ant', 'maven', 'gradle', 'eclipse', 'simple')
     *  - project.module: relative path used to identify a module. Can be the name of a file
     *                  ('build.gradle') or a relative path ('build/build.xml')
     *
     *  - module.sources: comma separated list of
     *                  - file extensions ('.java')
     *                  used to select the source code files
     *                  default: '.java'
     *
     *  - module.resources: comma separated list of
     *                  - file extensions ('.xml')
     *                  - directories ('webapp')
     *                  - file patterns ('webapp/**')
     *                  used to select the resources
     *                  default: '.xml,.properties,.json,.gradle,.project,.classpath'
     *
     *  - module.exclude: comma separated list of
     *                  - directories ('test')
     *                  - file patterns ('** /test/**')
     *                  - file extensions ('.xml')
     *                  to exclude
     *                  default: 'target,build,out,.*
     *
     *  Note: it is possible to use multiple 'module.*', adding a suffix ('module.resources.1')
     */
    String RUNTIME_LIBRARY = "runtime.library";
    String PROJECT_TYPE = "project.type";
    String PROJECT_MODULE = "project.module";
    String MODULE_SOURCES = "module.sources";
    String MODULE_RESOURCES = "module.resources";
    String MODULE_EXCLUDE = "module.exclude";

    // ----------------------------------------------------------------------

    /** Project type */
    String getProjectType();

    /** Project properties */
    Properties getProperties();

    /** Path of the project in the filesystem, if it exists */
    File getProjectHome();

    /** List of all project's modules */
    List<Module> getModules();

    /** Retrieve a module by id/full name */
    Module getModule(String nameOrId);

    /** UNION of all module libraries */
    Set<Library> getLibraries();

    Library getLibrary(String libraryId);

    // ----------------------------------------------------------------------

    /** Set the library finder */
    Project setLibraryFinder(LibraryFinder lfinder);

    LibraryFinder getLibraryFinder();

    MavenDownloader getLibraryDownloader();

    // ----------------------------------------------------------------------

    /** The project runtime libraries */
    // Library getRuntimeLibrary();

    // ----------------------------------------------------------------------

    double getComplexity(double threshold);

}
