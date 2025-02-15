package org.hls.check;

import jext.cache.CacheManager;
import jext.util.logging.Logger;
import jext.sourcecode.project.LibraryFinderManager;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectAnalyzer;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.csharp.util.CSharpProjectFile;
import jext.sourcecode.project.lfm.ConfigurableLibraryFinderManager;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class CheckCSharp {

    static void test1() throws Exception {

        LibraryFinderManager lfm = ConfigurableLibraryFinderManager.getManager(
                new File("config/extlibsconfig.xml"));

        Project project = Projects.newProject(new File(
                        //"D:\\SPLGroup\\spl-workspaces\\dev-workspace\\workspace\\example_repo\\elasticsearch"
                        // "D:\\SPLGroup\\spl-workspaces\\sample-projects\\cocome-maven-project"
                        // "D:\\Projects.github\\other_projects\\commons-lang"
                        // "D:\\Projects.github\\ml_projects\\elasticsearch-5.6.16"
                        // "D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7"
                        // "D:\\SPLGroup\\spl-workspaces\\_anis_project\\JunoWS-2021"
                        // "D:\\SPLGroup\\spl-workspaces\\etsalat-workspace\\Etisalat\\etisala-project-ebtic"
                        // "D:\\Projects.github\\java_projects\\jext.linalg\\.spl\\project-info.json"
                        // "D:\\Projects.github\\other_projects\\spark-3.2.1"
                        // "D:\\Projects.github\\other_projects\\hibernate-orm-5.2.0"
                        // "D:\\SPLGroup\\spl-workspaces\\sample-projects\\ForSalwa"
                        // "D:\\Projects\\Python\\django-4.0.3"
                        // "D:\\Projects\\Python\\flask-2.1.2"
                        "D:\\Projects\\CSharp\\Apache-Lucene.Net-4.8.0"
                ),
                PropertiesUtils.properties(
                        "runtime.library", "auto"
                        // "runtime.library", "jdk8"
                        // , "org.gradle.daemon", "false"
                        // , "org.gradle.java.home", "D:\\Java\\Jdk18.0"
                        // , "jdk8", "D:\\Java\\Jdk8.0"
                        , "module.exclude", "test*,example*,__pycache__"
                ),
                lfm
        );

        System.out.println(project.getModules().getModule().getRuntimeLibrary());

        project.getLibraries().forEach(System.out::println);

        // ProjectDump.dump(project, 0);
        // ProjectAnalyzer.analyzeProject(project, new File("project-info.json"));
        // ProjectAnalyzer.analyzeSources(project, new File("source-info.json"));

        String jsonFile = String.format("%s-source-project-r00.json", project.getId());
        ProjectAnalyzer.analyzeProject(project, new File(jsonFile));

    }

    static void test2() {
        CSharpProjectFile cspf = new CSharpProjectFile(
                new File("D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80"),
                new File("D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\Library\\WinFormControls\\CsvTools.WinFormControlsLibrary.csproj"));

        cspf.getLocalReferences()
                .forEach(lr -> {
                    System.out.printf("%s: %s\n", lr.name, lr.file);
                });
    }

    public static void main(String[] args) {
        try {
            Logger.configure();
            CacheManager.configure();

            // test1();
            test2();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Parallel.shutdown();
            CacheManager.shutdown();
        }
    }
}
