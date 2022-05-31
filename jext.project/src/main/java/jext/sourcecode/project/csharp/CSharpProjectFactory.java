package jext.sourcecode.project.csharp;

import jext.logging.Logger;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectFactory;
import jext.sourcecode.project.info.InfoProject;
import jext.sourcecode.resources.csharp.CSharpLibraryDownloader;
import jext.sourcecode.resources.csharp.CSharpLibraryFinder;

import java.io.File;
import java.util.Properties;

import static jext.sourcecode.project.csharp.GuessProjectType.guessProjectType;


public class CSharpProjectFactory extends ProjectFactory {

    @Override
    public Project newProject(String projectName, File projectHome, Properties properties) {

        String projectType = guessProjectType(projectHome, properties);
        Project project;

        if (CSharpProject.TYPE.equals(projectType))
            project = new CSharpProject(projectName, projectHome, properties);
        else if (InfoProject.TYPE.equals(projectType))
            project = new InfoProject(projectName, projectHome, properties);
        else {
            Logger.getLogger(CSharpProjectFactory.class).errorf("Project type %s unknown", projectType);
            project = new CSharpProject(projectName, projectHome, properties);
        }

        LibraryDownloader ld = new CSharpLibraryDownloader();
        LibraryFinder lfinder = new CSharpLibraryFinder().setDownloader(ld);
        project.setLibraryFinder(lfinder);

        return project;
    }
}
