package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.symbolsolver.resolution.typesolvers.CachedTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistry;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaRuntimeTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.LibrariesTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.UnsolvedSymbolsRegistry;
import jext.javaparser.symbolsolver.resolution.typesolvers.UnsolvedSymbolsRegistryTypeSolver;
import jext.util.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.util.ProjectUtils;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;
import jext.util.concurrent.Serial;
import org.hls.java.analysis.MethodSolver;
import org.hls.java.analysis.SymbolSolver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckProject {

    static Logger log = Logger.getLogger("CheckProject");
    static Project project;

    static JavaParserPool pool;
    static ClassPoolRegistry cprlib;
    static ClassPoolRegistry cpjdk;
    static UnsolvedSymbolsRegistry usr;
    static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws IOException {
        jext.util.logging.v2.Logger.configure(new File("config/log4jv2.xml"));
        // jext.util.logging.v1.Logger.configure(new File("config/log4jv1.xml"));
        Parallel.setup();
        CacheManager.configure();

        log.infof("Projects.newProject");
        project = Projects.newProject(
            new File(
                "D:\\Projects.github\\other_projects\\hibernate-orm"
                // "D:\\Projects.github\\java_projects\\check_typesolver"
            ), PropertiesUtils.properties(
                "module.exclude", "target,out,.*"
                // , "module.exclude.1", "test,asciidoc"
            ));

        log.infof("JavaParserPool");
        pool = JavaParserPool.getPool()
            .withSourceRoots(ProjectUtils.getSourceRoots(project));

        log.infof("ClassPoolRegistry/libraries");
        cprlib = new ClassPoolRegistry()
            .addAll(ProjectUtils.getLibraryFiles(project));

        log.infof("ClassPoolRegistry/JDK");
        cpjdk = new ClassPoolRegistry()
            .addJdk(new File("D:\\Java\\Jdk11.0"));

        usr = new UnsolvedSymbolsRegistry();

        // --
        log.infof("Sources");
        List<Source> sources = project.getSources();

        // --
        log.infof("Parallel.analyzeTypes");
        count = new AtomicInteger();
        Parallel.forEach(sources, CheckProject::analyzeTypes);

        // --
        log.infof("Parallel.analyzeMethods");
        count = new AtomicInteger();
        Parallel.forEach(sources, CheckProject::analyzeMethods);

        // --
        log.printf("processed %d files\n", sources.size());
        Parallel.shutdown();
        CacheManager.shutdown();
    }

    static void analyzeTypes(Source source) {
        int n = count.incrementAndGet();
        if (n%1000 == 0)
            System.out.printf("... processed %d files\n", n);

        CompilationUnit cu;
        ParseResult<CompilationUnit> parsed = pool.parse(source.getFile());
        if (!parsed.isSuccessful() || !parsed.getResult().isPresent()) {
            log.errorf("ERROR ... %s: %s", source.getFile(), parsed.getProblems());
            return;
        }
        else {
            cu = parsed.getResult().get();
        }

        SymbolSolver sym = new SymbolSolver();
        ContextTypeSolver ts = new ContextTypeSolver();
        ts.add(new JavaParserPoolTypeSolver(pool));
        ts.add(new LibrariesTypeSolver(cprlib));
        ts.add(new JavaRuntimeTypeSolver(cpjdk));
        ts.add(new UnsolvedSymbolsRegistryTypeSolver(usr));
        CachedTypeSolver cts = new CachedTypeSolver(ts);

        sym.analyze(cu, cts);
    }

    static void analyzeMethods(Source source) {
        int n = count.incrementAndGet();
        if (n%1000 == 0)
            System.out.printf("... processed %d files\n", n);

        CompilationUnit cu;
        ParseResult<CompilationUnit> parsed = pool.parse(source.getFile());
        if (!parsed.isSuccessful() || !parsed.getResult().isPresent()) {
            log.errorf("ERROR ... %s: %s", source.getFile(), parsed.getProblems());
            return;
        }
        else {
            cu = parsed.getResult().get();
        }

        MethodSolver sym = new MethodSolver();
        ContextTypeSolver ts = new ContextTypeSolver();
        ts.add(new JavaParserPoolTypeSolver(pool));
        ts.add(new LibrariesTypeSolver(cprlib));
        ts.add(new JavaRuntimeTypeSolver(cpjdk));
        ts.add(new UnsolvedSymbolsRegistryTypeSolver(usr));
        CachedTypeSolver cts = new CachedTypeSolver(ts);

        sym.analyze(cu, cts);
    }
}
