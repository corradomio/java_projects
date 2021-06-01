package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistryTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.util.ClassPoolRegistry;
import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectDump;
import jext.sourcecode.project.util.ProjectUtils;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;
import org.hls.java.analysis.MemberDeclarations;

import java.io.File;
import java.util.List;
import java.util.Set;

public class CheckDL4J {

    static JavaParserPool pool;
    static ClassPoolRegistry cpr;

    public static void main(String[] args) {
        try {
            Parallel.setup();
            Logger.configure();
            CacheManager.configure();

            Project project = Projects.newProject(
                new File(
                    "D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7"
                    // "D:\\Projects.github\\ml_projects\\elasticsearch-7.11.0"
                ), PropertiesUtils.empty());

            project.getLibraryDownloader().setDownloadDirectory(new File("C:\\Users\\Corrado Mio\\.m2\\repository"));

            ProjectDump.yaml(project, new File(project.getName().getName() + ".yaml"), 0);


            List<File> sourceRoots = ProjectUtils.getSourceRoots(project);
            Set<File> libraryFiles = ProjectUtils.getLibraryFiles(project);


            // JavaParserPool:
            //      multiple source roots
            pool = JavaParserPool.newPool("dl4j")
                .addAll(sourceRoots)
                .withCache();

            // ClassPoolRegistry
            //      multiple jar files
            cpr = new ClassPoolRegistry()
                // .addAll(libraryFiles)
                .addJdk(new File("D:\\Java\\Jdk8.0.x64"));

            // Resolve all symbols
            //

            project.getModules().forEach(module -> {
                Parallel.forEach(module.getSources(), source -> {
                    solve(source.getFile());
                });
            });

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("== DONE ==");
        Parallel.shutdown();
    }

    private static void solve(File source) {
        System.out.printf("== %s ==\n", source.getName());

        ContextTypeSolver ts = new ContextTypeSolver();
        ts.add(new ClassPoolRegistryTypeSolver(cpr));
        ts.add(new JavaParserPoolTypeSolver().withPool(pool));

        ParseResult<CompilationUnit> result = pool.parse(source);
        result.ifSuccessful(cu -> {

            // ts.withCu(cu);
            //SolveSymbolsVisitor visitor = new SolveSymbolsVisitor();
            MemberDeclarations visitor = new MemberDeclarations();
            visitor.analyze(cu, ts);
        });
    }
}
