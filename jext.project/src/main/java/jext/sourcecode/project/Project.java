package jext.sourcecode.project;

import jext.maven.MavenDownloader;
import jext.name.IdNamed;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Optional;
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

    /**
     * Set a filter to skip the source files not necessary for the analysis.
     * Generally, it is used to skip the "test" files.
     *
     *
     * @param selector an object that return true if the source file must be filtered
     */
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

    /** Retrieve the Library finder */
    LibraryFinder   getLibraryFinder();

    /** Equivalent to 'getLibraryFinder().getLibraryDownloader()' */
    MavenDownloader getLibraryDownloader();

    // ----------------------------------------------------------------------
    // Module/Source
    // ----------------------------------------------------------------------

    /** Retrieve all modules */
    Modules getModules();

    /** retreive all sources (in all modules) */
    Sources getSources();

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    /** UNION of all module libraries with the HIGHEST version */
    LibrarySet getLibraries();

    /** List of module libraries RESOLVED with HIGHEST version */
    Set<Library> getLibraries(Module module);

    /** Runtimelibrary assigned at project level */
    String getRuntimeLibrary();

    // ----------------------------------------------------------------------
    // LibraryRepositories
    // ----------------------------------------------------------------------

    Set<LibraryRepository> getLibraryRepositories();
    LibraryRepository getLibraryRepository(String librepoId);

    // ----------------------------------------------------------------------
    // Abort
    // ----------------------------------------------------------------------

    void abort();

    boolean isAborted();

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
