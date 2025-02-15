package org.hls.check;

import com.scitools.understand.UnderstandException;
import jext.cache.CacheManager;
import jext.util.logging.Logger;
import jext.scitools.UndDatabase;
import jext.sourcecode.project.ProjectComparator;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.io.IOException;

public class CheckUndDb {

    // private static void analyze(File undDir) throws UnderstandException {
    //     Set<String> kinds = new HashSet<>();
    //
    //     try(UndDatabase db  = UndDatabase.database(undDir, "java", 8).open()) {
    //
    //         Ent[] ents = db.ents("");
    //         for (Ent ent : ents) {
    //             System.out.println(ent.longname());
    //
    //             Ref[] refs = ent.refs("", "");
    //             for(Ref ref : refs) {
    //                 System.out.print("... ");
    //                 System.out.printf("%s: %s\n", ref.scope().name(), ref.ent().name());
    //             }
    //
    //             String kind = ent.kind().name();
    //             String[] labels = kind.split(" ");
    //             for (String label : labels)
    //                 kinds.add(label);
    //         }
    //
    //         System.out.println(ents.length);
    //         System.out.println(kinds);
    //     }
    // }

    public static void main(String[] args) throws UnderstandException, IOException {
        Logger.configure();
        CacheManager.configure();
        Parallel.setup();

        File projectFile = new File(
            // "D:\\Projects.github\\java_projects\\check_java_syntax\\.spl\\b9ee0f37-source-diff-r-1-r00.json"
            "D:\\Projects.github\\java_projects\\check_java_syntax\\.spl\\b9ee0f37-source-diff-r00-r01.json"
        );

        ProjectComparator pdiff = ProjectComparator.load(projectFile);

        analyze(pdiff);

        // Project project = loadProject(projectFile);
        // saveProject(project);

        // ------------------------------------------------------------------

        Parallel.shutdown();
    }

    static void analyze(ProjectComparator pdiff) throws IOException {

        File undDir = new File(pdiff.getProjectHome(), "scitools.und");
        UndDatabase udb = UndDatabase.database(undDir);

        if (!pdiff.isDifference()) {
            udb.delete();
            udb.create("java", 8);
        }
        else {

        }

        udb.addSources(pdiff.getAddedSourceFiles());
        udb.removeSources(pdiff.getDeletedSourceFiles());
        udb.addLibraries(pdiff.getAddedLibraryFiles());

        udb.analyze(pdiff.isDifference());
    }

    // static Project loadProject(File projectFile ) throws IOException {
    //
    //     MavenDownloader md = new MavenDownloader();
    //     LibraryFinder lf = new JavaLibraryFinder()
    //             .setDownloader(md)
    //             .setNamedLibraries(MapUtils.asMap(
    //                     "jdk8", new File("D:\\SPLGroup\\SPLDevelopment3.0\\extlibs\\java\\jdk\\jdk8"),
    //                     "jdk14", new File("D:\\SPLGroup\\SPLDevelopment3.0\\extlibs\\java\\jdk\\jdk14")
    //             ));
    //
    //     Project project = Projects.newProject(projectFile, PropertiesUtils.empty());
    //     project.setLibraryFinder(lf);
    //
    //     // List<File> sources  = ProjectUtils.getSourceFiles(project);
    //     // Set<File> libraries = ProjectUtils.getLibraryFiles(project);
    //
    //     ProjectAnalyzer.analyzeProject(project, new File(project.getName().getName() + ".json"));
    //
    //     return project;
    // }

    // static void saveProject(Project project) throws IOException {
    //
    //     File projectDir = project.getProjectHome();
    //     List<File> sources  = ProjectUtils.getSourceFiles(project);
    //     Set<File> libraries = ProjectUtils.getLibraryFiles(project);
    //
    //     File undDir = new File(projectDir, "scitools.und");
    //     UndDatabase udb = UndDatabase.database(undDir, "java", 8);
    //
    //     udb.delete();
    //     udb.create();
    //
    //     udb.addSources(sources);
    //     udb.addLibraries(libraries);
    //
    //     udb.analyze(false);
    //
    //     // analyze(undDir);
    //
    //     udb.close();
    // }
}
