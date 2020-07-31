package jext.buildtools;

import jext.buildtools.maven.MavenDownloader;

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
     *  - module.resources: list of file extensions used to select the resources
     *                  '.xml,.properties,.gradle'
     *  - maven.libraries: file(s) (separated by ',') used to extract list of maven libraries
     */
    String PROJECT_TYPE = "project.type";
    String PROJECT_MODULE = "project.module";
    String MODULE_RESOURCES = "module.resources";
    String MAVEN_LIBRARIES = "maven.libraries";

    String getName();
    String getType();

    File getDirectory();

    Properties getProperties();

    Module getModule(Name moduleName);
    Module findModule(String name);

    List<Module> getModules();

    void setDownloader(MavenDownloader downloader);
    MavenDownloader getDownloader();
}
