package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.SolveSymbolsVisitor;
import jext.javaparser.symbolsolver.resolution.typesolvers.CachedTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistryTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.CompositeTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextResolverTypeSolver;
import jext.javaparser.util.ClassPoolRegistry;
import jext.javaparser.util.ContextResolver;
import jext.javaparser.util.UnsolvedSymbols;
import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectDump;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class Analyze {

    static JavaParserPool pool;
    static ClassPoolRegistry cpr;
    static ContextResolver css;
    static UnsolvedSymbols us;

    static Logger log = Logger.getLogger("Analyze");

    public static void main(String[] args) {
        try {
            Parallel.setup();
            Logger.configure();
            CacheManager.configure();

            log.infof("Load project");
            Project project = Projects.newProject(
                new File(
                    // "D:\\Projects.github\\ml_projects\\elasticsearch-5.6.16"
                    // "D:\\SPLGroup\\spl-workspaces\\ext-workspace\\BTProjects\\DEUM"
                    // "D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7"
                    "D:\\Projects.github\\ml_projects\\elasticsearch-7.11.0"
                ), PropertiesUtils.empty());

            project.getLibraryDownloader().setDownload(new File("C:\\Users\\Corrado Mio\\.m2\\repository"));

            ProjectDump.dump(project, ProjectDump.NO_LIBRARIES | ProjectDump.NO_TYPES | ProjectDump.NO_DEPENDENCIES);

            log.infof("JavaParserPool");

            pool = JavaParserPool.getPool().withCache();
            project.getModules().forEach(module -> {
                pool.addAll(module.getSourceRoots());
            });

            log.infof("ClassPoolRegistry");

            cpr = new ClassPoolRegistry()
                .addJdk(new File("D:\\Java\\Jdk8.0.x64"));
            project.getLibraries().forEach(library -> {
                cpr.addAll(library.getFiles());
            });

            css = new ContextResolver();
            us = new UnsolvedSymbols();

            log.infof("Solve");

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
        Parallel.shutdown();
    }

    private static void solve(File source) {
        System.out.printf("== %s ==\n", source.getName());

        CompositeTypeSolver ts = new CachedTypeSolver();
        ts.add(new ClassPoolRegistryTypeSolver().withClassPoolRegistry(cpr));
        ts.add(new JavaParserPoolTypeSolver().withPool(pool));
        ts.add(new ContextResolverTypeSolver(css, us));

        ParseResult<CompilationUnit> result = pool.parse(source);
        result.ifSuccessful(cu -> {

            SolveSymbolsVisitor ssv = new SolveSymbolsVisitor();
            ssv.analyze(cu, ts);
        });
    }
}
