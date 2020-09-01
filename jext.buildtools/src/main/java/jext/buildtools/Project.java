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
     *                  ('build.gradle') or a relative path as, for example, 'build/build.xml'
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

    String getName();

    String getType();

    File getDirectory();

    Properties getProperties();

    List<Module> getModules();

    Module getModule(Name name);

    /**
     * Find a module by id, full name or name
     * @param name
     * @return
     */
    Module findModule(String name);

    // ----------------------------------------------------------------------

    void setDownloader(MavenDownloader downloader);

    MavenDownloader getDownloader();

}
