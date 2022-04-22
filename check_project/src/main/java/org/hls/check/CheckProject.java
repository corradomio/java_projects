package org.hls.check;

import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectAnalyzer;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectDump;
import jext.sourcecode.resources.libraries.JavaLibraryFinder;
import jext.util.JSONUtils;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.io.IOException;

public class CheckProject {

    public static void main(String[] args) {
        try {
            Logger.configure();
            CacheManager.configure();

            LibraryFinder lfinder = new JavaLibraryFinder()
                .addLibrary("jdk8", "D:\\Java\\Jdk8.0")
                .addLibrary("jdk11", "D:\\Java\\Jdk11.0");


            Project project = Projects.newProject(new File(
                //"D:\\SPLGroup\\spl-workspaces\\dev-workspace\\workspace\\example_repo\\elasticsearch"
                // "D:\\SPLGroup\\example_repo\\cocome-maven-project"
                // "D:\\Projects.github\\other_projects\\commons-lang"
                // "D:\\Projects.github\\ml_projects\\elasticsearch-5.6.16"
                // "D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7"
                // "D:\\SPLGroup\\spl-workspaces\\_anis_project\\JunoWS-2021"
                // "D:\\SPLGroup\\spl-workspaces\\etsalat-workspace\\Etisalat\\etisala-project-ebtic"
                // "D:\\Projects.github\\java_projects\\jext.linalg\\.spl\\project-info.json"
                // "D:\\Projects.github\\other_projects\\spark-3.2.1"
                // "D:\\Projects.github\\other_projects\\hibernate-orm-5.2.0"
                "D:\\SPLGroup\\spl-workspaces\\sample-projects\\ForSalwa"
                ),
                PropertiesUtils.properties(
                    "runtime.library", "auto"
                    // , "org.gradle.daemon", "false"
                    // , "org.gradle.java.home", "D:\\Java\\Jdk18.0"
                    // , "jdk8", "D:\\Java\\Jdk8.0"
                ));

            project.setLibraryFinder(lfinder);
            project.getModules().getModule().getRuntimeLibrary();

            ProjectDump.dump(project, 0);

            // ProjectAnalyzer.analyzeProject(project, new File("project-info.json"));
            // ProjectAnalyzer.analyzeSources(project, new File("source-info.json"));

        // } catch (IOException e) {
        //     e.printStackTrace();
        } finally {
            Parallel.shutdown();
            CacheManager.shutdown();
        }
    }
}
