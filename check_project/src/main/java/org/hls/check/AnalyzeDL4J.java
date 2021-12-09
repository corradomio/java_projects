package org.hls.check;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistry;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistryTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserRootsTypeSolver;
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
        Parameters params = Parameters.params("module.exclude", "*test*");

        Project dl4j = Projects.newProject(name,
            new File("D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7")
            // new File("D:\\Projects.github\\ml_projects\\elasticsearch-7.11.0")
            , params
        );

        pool = JavaParserPool.getPool().withCache();
        dl4j.getModules().forEach(module -> {
            pool.addAll(module.getSourceRootDirectories());
        });

        File JDK = new File("D:\\Java\\Jdk1.8.0.x64");

        classPoolRegistry = new ClassPoolRegistry();
        dl4j.getLibraries().forEach(library -> {
            classPoolRegistry.addAll(library.getFiles(), library.getName().getFullName());
        });
        classPoolRegistry.addJdk(JDK);

        jdkPoolRegistry = new ClassPoolRegistry().addJdk(JDK);

        dl4j.getModules().forEach(module -> {
            Parallel.forEach(module.getSources(), AnalyzeDL4J::analyze);
        });

        Logger.getLogger("main").println("=== END ===");

        CacheManager.shutdown();
        Parallel.shutdown();
    }

    static void analyze(Source source) {
        try {
            Module module = source.getModule();
            Project project = module.getProject();
            File srcFile = source.getFile();

            System.out.println("analyze " + srcFile);

            ContextTypeSolver ctx = new ContextTypeSolver();

            // jdk
            ClassPoolRegistryTypeSolver jdkts = new ClassPoolRegistryTypeSolver(jdkPoolRegistry);

            // libraries
            ClassPoolRegistryTypeSolver libsts = new ClassPoolRegistryTypeSolver(classPoolRegistry);

            // current module
            // JavaParserPoolTypeSolver mts = new JavaParserPoolTypeSolver(pool);
            JavaParserRootsTypeSolver mts = new JavaParserRootsTypeSolver().withCache();
            mts.addAll(module.getSourceRootDirectories());
            module.getDependencies().forEach(dmodule -> {
                mts.addAll(dmodule.getSourceRootDirectories());
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

        }
        catch(Throwable t){
            System.err.println(t.getClass());
        }
    }
}
