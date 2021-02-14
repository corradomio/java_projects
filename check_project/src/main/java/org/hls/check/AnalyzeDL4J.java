package org.hls.check;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.SolveSymbolsVisitor;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JarFilesTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserRootsTypeSolver;
import jext.javaparser.util.ClassPoolRegistry;
import jext.logging.Logger;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.Source;
import jext.util.Parameters;
import jext.util.concurrent.Parallel;

import java.io.File;

public class AnalyzeDL4J {

    static JavaParserPool pool;
    static ClassPoolRegistry classPoolRegistry;
    static ClassPoolRegistry jdkPoolRegistry;

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        Name name = new PathName("test/dl4j");
        Parameters params = Parameters.params();

        Project dl4j = Projects.newProject(name,
            new File("D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7")
            // new File("D:\\Projects.github\\ml_projects\\elasticsearch-7.11.0")
            , params
        );

        pool = JavaParserPool.getPool();
        dl4j.getModules().forEach(module -> {
            pool.addAll(module.getSourceRoots());
        });

        File JDK = new File("D:\\Java\\Jdk1.8.0.x64");

        classPoolRegistry = new ClassPoolRegistry();
        dl4j.getLibraries().forEach(library -> {
            classPoolRegistry.addAll(library.getFiles());
        });
        classPoolRegistry.addJdk(JDK);

        jdkPoolRegistry = new ClassPoolRegistry().addJdk(JDK);

        dl4j.getModules().parallelStream()
            .flatMap(module -> module.getSources().stream())
            .forEach(AnalyzeDL4J::analyze);

        System.out.println("=== END ===");

        CacheManager.shutdown();
        Parallel.shutdown();
    }

    static void analyze(Source source) {
        Module module = source.getModule();
        Project project = module.getProject();
        File srcFile = source.getFile();

        System.out.println("analyze " + srcFile);

        ContextTypeSolver ctx = new ContextTypeSolver();

        // jdk
        JarFilesTypeSolver jdkts = new JarFilesTypeSolver(jdkPoolRegistry);

        // libraries
        JarFilesTypeSolver libsts = new JarFilesTypeSolver(classPoolRegistry);

        // current module
        // JavaParserPoolTypeSolver mts = new JavaParserPoolTypeSolver(pool);
        JavaParserRootsTypeSolver mts = new JavaParserRootsTypeSolver();
        mts.addAll(module.getSourceRoots());
        module.getDependencies(false).forEach(dmodule -> {
            mts.addAll(dmodule.getSourceRoots());
        });

        // runtime library
        // JDKTypeSolver rtts = new JDKTypeSolver(new File("D:\\Java\\Jdk1.8.0.x64"));

        // compose type solver
        ctx.add(jdkts);         // jdk
        ctx.add(mts);           // modules
        ctx.add(libsts);        // libraries

        // compile the code

        TypeSolver ts = ctx;

        CompilationUnit cu = JavaParserPool.getPool().parse(srcFile).getResult().get();

        SolveSymbolsVisitor ss = new SolveSymbolsVisitor();
        ss.analyze(cu, ts);
    }
}
