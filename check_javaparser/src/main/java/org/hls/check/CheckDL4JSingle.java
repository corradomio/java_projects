package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.SolveSymbolsVisitor;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistry;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistryTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.io.FileNotFoundException;

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

            project.getLibraryDownloader().setDownloadDirectory(new File("C:\\Users\\Corrado Mio\\.m2\\repository"));

            // ProjectDump.dump(project);

            pool = JavaParserPool.getPool().withCache();

            project.getModules().forEach(module -> {
                pool.addAll(module.getSources().getSourceRootDirectories());
            });

            cpr = new ClassPoolRegistry()
                .addJdk(new File("D:\\Java\\Jdk8.0.x64"));
            project.getLibraries().forEach(library -> {
                cpr.addAll(library.getFiles(), "jdk");
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

        ContextTypeSolver ts = new ContextTypeSolver();
        ts.add(new ClassPoolRegistryTypeSolver(cpr));
        ts.add(new JavaParserPoolTypeSolver(pool));

        ParseResult<CompilationUnit> result = pool.parse(source);
        result.ifSuccessful(cu -> {

            SolveSymbolsVisitor ssv = new SolveSymbolsVisitor();
            ssv.analyze(cu, ts);
        });

    }
}
