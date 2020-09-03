package jext.buildtools;

import jext.maven.MavenDownloader;

import java.io.File;
import java.util.List;
import java.util.Properties;

public interface Project {

    /**
     * List of properties:
     *
     *  - project.type: project type ('ant', 'maven', 'gradle', 'eclipse', 'simple')
     *  - project.module: relative path used to identify a module. Can be the name of a file
     *                  ('build.gradle') or a relative path ('build/build.xml')
     *  - module.resources: comma separated list of
     *                  - file extensions ('.xml')
     *                  - directories ('webapp')
     *                  - file patterns ('webapp/**')
     *                  used to select the resources
     *                  default: '.xml,.properties,.gradle'
     *  - module.exclude: comma separated list of
     *                  - directories ('test')
     *                  - file patterns ('** /test/**')
     *                  to exclude
     *  - maven.libraries: file(s) (separated by ',') used to extract list of maven libraries
     *
     *  Note: it is possible to use multiple 'module.*', adding a suffix ('module.resources.1')
     */
    String PROJECT_TYPE = "project.type";
    String PROJECT_MODULE = "project.module";
    String MODULE_RESOURCES = "module.resources";
    String MODULE_EXCLUDE = "module.exclude";
    String MAVEN_LIBRARIES = "maven.libraries";

    /** Project name */
    String getName();

    /** Project type */
    String getProjectType();

    /** Project home directory */
    File getDirectory();

    /** Project properties */
    Properties getProperties();

    /** Flat list of project modules */
    List<Module> getModules();

    // /** Retrieve the module with the specified name */
    // Module getModule(Name name);

    /**
     * Find a module by id, full name or name
     */
    Module findModule(String name);

    // ----------------------------------------------------------------------
    // MavenDownloader
    // ----------------------------------------------------------------------

    /** Set the MavenDownloader */
    void setDownloader(MavenDownloader downloader);

    /** Retrieve the maven downloader */
    MavenDownloader getDownloader();

    // ----------------------------------------------------------------------
    // Directories
    // ----------------------------------------------------------------------

    List<File> getDirectories(File baseDir);

}
