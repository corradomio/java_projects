package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.SolveSymbolsVisitor;
import jext.javaparser.symbolsolver.resolution.typesolvers.CachedTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JarFilesTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.util.ClassPoolRegistry;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class CheckMini {

    public static void main(String[] args) {
        Parallel.setup();
        Logger.configure();
        CacheManager.configure();

        JavaParserPool pool = JavaParserPool.getPool().withCache()
            .add(new File("src_only"));
        ClassPoolRegistry cpr = new ClassPoolRegistry()
            .addJdk(new File("D:\\Java\\Jdk8.0.x64"));

        FileUtils.listFiles(new File("src_only"), f -> f.getName().endsWith(".java"))
            .forEach(source -> {
                CachedTypeSolver ts = new CachedTypeSolver();
                ts.add(new JarFilesTypeSolver().withClassPoolRegistry(cpr));
                ts.add(new JavaParserPoolTypeSolver(pool));

                ParseResult<CompilationUnit> result = pool.parse(source);
                result.ifSuccessful(cu -> {

                    SolveSymbolsVisitor ssv = new SolveSymbolsVisitor();
                    ssv.analyze(cu, ts);
                });
            });

    }
}
