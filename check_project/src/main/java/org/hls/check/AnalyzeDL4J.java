package org.hls.check;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.SolveSymbolsVisitor;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JarFilesTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserRootsTypeSolver;
import jext.logging.Logger;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.Source;
import jext.util.Parameters;

import java.io.File;

public class AnalyzeDL4J {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        Name name = new PathName("test/dl4j");
        Parameters params = Parameters.params();

        Project dl4j = Projects.newProject(name,
            new File("D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7"),
            params
        );

        dl4j.getModules().stream()
            .flatMap(module -> module.getSources().stream())
            .forEach(AnalyzeDL4J::analyze);

    }

    static void analyze(Source source) {
        Module module = source.getModule();
        Project project = module.getProject();
        File srcFile = source.getFile();

        System.out.println("analyze " + srcFile);

        JavaParserPool pool = JavaParserPool.getPool();

        ContextTypeSolver ctx = new ContextTypeSolver();

        File JDK = new File("D:\\Java\\Jdk1.8.0.x64");

        // jdk
        JarFilesTypeSolver jdkts = new JarFilesTypeSolver().addJdk(JDK);

        // libraries
        JarFilesTypeSolver libsts = new JarFilesTypeSolver().addJdk(JDK);
        project.getLibraries().forEach(library -> {
            libsts.addAll(library.getFiles());
        });

        // add rt libraries
        project.getRuntimeLibraries().forEach(jdk -> {
            jdkts.addJdk(JDK);
            libsts.addJdk(JDK);
        });

        // current module
        JavaParserRootsTypeSolver mts = new JavaParserRootsTypeSolver();
        mts.addAll(module.getSourceRoots());

        // dependent modules
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
