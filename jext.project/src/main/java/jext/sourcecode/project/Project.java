package jext.sourcecode.project;

import jext.maven.MavenDownloader;
import jext.name.IdNamed;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.function.Predicate;

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
     *                  default: 'target,out,.*
     *
     *  Note: it is possible to use multiple 'module.*', adding a suffix ('module.resources.1')
     */
    String RUNTIME_LIBRARY = "runtime.library";
    String PROJECT_TYPE = "project.type";
    String PROJECT_MODULE = "project.module";
    String MODULE_SOURCES = "module.sources";
    String MODULE_RESOURCES = "module.resources";
    String MODULE_EXCLUDE = "module.exclude";

    String ROOT_MODULE_NAME = "";
    String MODULE_DEFINITION = "module.definition";
    String MODULE_DEFINITION_BY_HEURISTIC = "heuristic";
    String MODULE_DEFINITION_BY_CONFIGURATION = "configuration";
    String MODULE_DEFINITION_BY_CONFIGURATION_FILE = "configurationFile";
    String MODULE_DEFINITION_BY_SOURCE_ROOTS = "sourceRoots";

    // ----------------------------------------------------------------------

    void setResourceFilter(Predicate<String> selector);

    // ----------------------------------------------------------------------

    /** Project type */
    String getProjectType();

    /** Project properties */
    Properties getProperties();

    /** Path of the project in the filesystem, if it exists */
    File getProjectHome();

    // ----------------------------------------------------------------------

    /** Set the library finder */
    Project setLibraryFinder(LibraryFinder lfinder);

    LibraryFinder getLibraryFinder();

    MavenDownloader getLibraryDownloader();

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    List<Source> getSources();

    Source getSource(String sourceId);

    // ----------------------------------------------------------------------
    // Modules
    // ----------------------------------------------------------------------

    /** List of all project's modules */
    List<Module> getModules();

    default Module getModule() {
        return getModule(ROOT_MODULE_NAME);
    }

    /** Retrieve a module by id/full name */
    Module getModule(String nameOrId);

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    enum LibrariesSelector {
        USED,
        UNUSED,
        ALL,
    }

    /** UNION of all module libraries with the HIGHEST version */
    /*Set<Library>*/LibrarySet getLibraries();

    /** List of module libraries RESOLVED with HIGHEST version */
    Set<Library> getLibraries(Module module);

    /** Library by name or id */
    Library getLibrary(String nameOrId);

    /** Library with HIGHEST version */
    Library getLibrary(Library library);

    Set<LibraryRepository> getLibraryRepositories();
    LibraryRepository getLibraryRepository(String librepoId);

    // ----------------------------------------------------------------------
    // Extras
    // ----------------------------------------------------------------------

    double getComplexity(double threshold);

    // ----------------------------------------------------------------------
    // Abort
    // ----------------------------------------------------------------------

    void abort();

    boolean isAborted();

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
