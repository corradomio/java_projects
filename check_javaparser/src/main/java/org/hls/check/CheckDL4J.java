package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.SolveSymbolsVisitor;
import jext.javaparser.symbolsolver.resolution.typesolvers.CachedTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.CompositeTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JarFilesTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.util.ClassPoolRegistry;
import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class CheckDL4J {

    static JavaParserPool pool;
    static ClassPoolRegistry cpr;

    public static void main(String[] args) {
        try {
            Parallel.setup();
            Logger.configure();
            CacheManager.configure();

            Project project = Projects.newProject(
                new File("D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7")
                , PropertiesUtils.empty());

            project.getLibraryDownloader().setDownload(new File("C:\\Users\\Corrado Mio\\.m2\\repository"));

            // ProjectDump.dump(project);

            pool = JavaParserPool.getPool().withCache();

            project.getModules().forEach(module -> {
                pool.addAll(module.getSourceRoots());
            });

            cpr = new ClassPoolRegistry()
                .addJdk(new File("D:\\Java\\Jdk8.0.x64"));
            project.getLibraries().forEach(library -> {
                cpr.addAll(library.getFiles());
            });

            project.getModules().forEach(module -> {
                Parallel.forEach(module.getSources(), source -> {
                    solve(source.getFile());
                });
                // module.getSources().forEach(source -> {
                //     solve(source.getFile());
                // });
            });

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("== DONE ==");
        Parallel.setup();
    }

    private static void solve(File source) {
        // System.out.printf("== %s ==\n", source.getName());

        CompositeTypeSolver ts = new CachedTypeSolver();
        ts.add(new JarFilesTypeSolver().withClassPoolRegistry(cpr));
        ts.add(new JavaParserPoolTypeSolver(pool));

        ParseResult<CompilationUnit> result = pool.parse(source);
        result.ifSuccessful(cu -> {

            SolveSymbolsVisitor ssv = new SolveSymbolsVisitor();
            ssv.analyze(cu, ts);
        });
    }
}
