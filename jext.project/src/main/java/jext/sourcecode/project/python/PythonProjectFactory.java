package jext.sourcecode.project.python;

import jext.logging.Logger;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectFactory;
import jext.sourcecode.project.info.InfoProject;
import jext.sourcecode.project.java.JavaProjectFactory;
import jext.sourcecode.project.python.simple.SimpleProject;
import jext.sourcecode.resources.java.JavaLibraryDownloader;
import jext.sourcecode.resources.java.JavaLibraryFinder;
import jext.sourcecode.resources.python.PythonLibraryDownloader;
import jext.sourcecode.resources.python.PythonLibraryFinder;

import java.io.File;
import java.util.Properties;

import static jext.sourcecode.project.python.GuessProjectType.guessProjectType;

public class PythonProjectFactory extends ProjectFactory {

    public Project newProject(String projectName, File projectHome, Properties properties) {

        String projectType = guessProjectType(projectHome, properties);
        Project project;

        if (SimpleProject.TYPE.equals(projectType))
            project = new SimpleProject(projectName, projectHome, properties);
        else if (InfoProject.TYPE.equals(projectType))
            project = new InfoProject(projectName, projectHome, properties);
        else {
            Logger.getLogger(JavaProjectFactory.class).errorf("Project type %s unknown", projectType);
            project = new SimpleProject(projectName, projectHome, properties);
        }

        LibraryDownloader ld = new PythonLibraryDownloader();
        LibraryFinder lfinder = new PythonLibraryFinder().setDownloader(ld);
        project.setLibraryFinder(lfinder);

        return project;
    }
}
