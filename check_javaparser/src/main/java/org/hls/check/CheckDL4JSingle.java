package org.hls.check;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.SolveSymbolsVisitor;
import jext.javaparser.symbolsolver.resolution.typesolvers.CachedTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistryTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.CompositeTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import jext.javaparser.util.ClassPoolRegistry;
import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheckDL4JSingle {

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

            solve(new File(
            // "D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7\\arbiter\\arbiter-deeplearning4j\\src\\test\\java\\org\\deeplearning4j\\arbiter\\multilayernetwork\\TestLayerSpace.java"
            // "D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7\\deeplearning4j\\deeplearning4j-nlp-parent\\deeplearning4j-nlp-japanese\\src\\test\\java\\org\\deeplearning4j\\text\\tokenization\\tokenizer\\JapaneseTokenizerTest.java"
                "D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7\\deeplearning4j\\deeplearning4j-core\\src\\test\\java\\org\\deeplearning4j\\nn\\updater\\TestUpdaters.java"
            ));

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("== DONE ==");
        Parallel.shutdown();
    }

    private static void solve(File source) throws FileNotFoundException {
        System.out.printf("== %s ==\n", source.getName());

        CompositeTypeSolver ts = new CachedTypeSolver();
        ts.add(new ClassPoolRegistryTypeSolver().withClassPoolRegistry(cpr));
        ts.add(new JavaParserPoolTypeSolver(pool));

        ParseResult<CompilationUnit> result = pool.parse(source);
        result.ifSuccessful(cu -> {

            SolveSymbolsVisitor ssv = new SolveSymbolsVisitor();
            ssv.analyze(cu, ts);
        });

    }
}
