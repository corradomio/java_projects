package org.hls.check;

import com.scitools.understand.UnderstandException;
import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.maven.MavenDownloader;
import jext.scitools.und.Ent;
import jext.scitools.und.Ref;
import jext.scitools.und.UndDatabase;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectAnalyzer;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectUtils;
import jext.sourcecode.resources.libraries.JavaLibraryFinder;
import jext.util.MapUtils;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckUndDb {

    private static void analyze(File undDir) throws UnderstandException {
        Set<String> kinds = new HashSet<>();

        try(UndDatabase db  = UndDatabase.database(undDir, "java", 8).open()) {

            Ent[] ents = db.ents("");
            for (Ent ent : ents) {
                System.out.println(ent.longname());

                Ref[] refs = ent.refs("", "");
                for(Ref ref : refs) {
                    System.out.print("... ");
                    System.out.printf("%s: %s\n", ref.scope().name(), ref.ent().name());
                }

                String kind = ent.kind().name();
                String[] labels = kind.split(" ");
                for (String label : labels)
                    kinds.add(label);
            }

            System.out.println(ents.length);
            System.out.println(kinds);
        }
    }

    public static void main(String[] args) throws UnderstandException, IOException {
        Logger.configure();
        CacheManager.configure();
        File projectDir = new File(
                // "D:\\Projects.github\\other_projects\\hibernate-orm"
                // "D:\\Projects.github\\other_projects\\deeplearning4j"
                // "D:\\Projects.github\\other_projects\\elasticsearch"
                // "D:\\Projects.github\\other_projects\\javaparser"
                // "D:\\Projects.github\\other_projects\\spark"
                // "D:\\Projects.github\\other_projects\\orientdb-3.2.4"
                // "D:\\Projects.github\\apache_projects\\commons-lang"
                "D:\\SPLGroup\\spl-workspaces\\sample-projects\\cocome-maven-project"
        );

        Project project = loadProject(projectDir);
        saveProject(project);

        // ------------------------------------------------------------------

        Parallel.shutdown();
    }

    static Project loadProject(File projectDir ) throws IOException {

        MavenDownloader md = new MavenDownloader();
        LibraryFinder lf = new JavaLibraryFinder()
                .setDownloader(md)
                .setNamedLibraries(MapUtils.asMap(
                        "jdk8", new File("D:\\SPLGroup\\SPLDevelopment3.0\\extlibs\\java\\jdk\\jdk8"),
                        "jdk14", new File("D:\\SPLGroup\\SPLDevelopment3.0\\extlibs\\java\\jdk\\jdk14")
                ));

        Project project = Projects.newProject(projectDir, PropertiesUtils.empty());
        project.setLibraryFinder(lf);

        List<File> sources  = ProjectUtils.getSourceFiles(project);
        Set<File> libraries = ProjectUtils.getLibraryFiles(project);

        ProjectAnalyzer.analyzeProject(project, new File(project.getName().getName() + ".json"));

        return project;
    }

    static void saveProject(Project project) throws IOException {

        File projectDir = project.getProjectHome();
        List<File> sources  = ProjectUtils.getSourceFiles(project);
        Set<File> libraries = ProjectUtils.getLibraryFiles(project);

        File undDir = new File(projectDir, "scitools.und");
        UndDatabase udb = UndDatabase.database(undDir, "java", 8);

        udb.delete();
        udb.create();

        udb.addSources(sources);
        udb.addLibraries(libraries);
        udb.analyze(false);

        // analyze(undDir);

        udb.close();
    }
}
