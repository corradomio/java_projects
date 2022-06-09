package jext.sourcecode.project.python;

import jext.logging.Logger;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectFactory;

import java.io.File;
import java.util.Properties;

import static jext.sourcecode.project.python.GuessProjectType.guessProjectType;

public class PythonProjectFactory extends ProjectFactory {

    public Project newProject(String projectName, File projectHome, Properties properties) {

        String projectType = guessProjectType(projectHome, properties);
        Project project;

        if (PythonProject.TYPE.equals(projectType))
            project = new PythonProject(projectName, projectHome, properties);
        else {
            Logger.getLogger(PythonProjectFactory.class).errorf("Project type %s unknown", projectType);
            project = new PythonProject(projectName, projectHome, properties);
        }

        LibraryFinder lfinder = new PythonLibraryFinder();
        project.setLibraryFinder(lfinder);

        return project;
    }
}
