package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.LogVoidVisitorAdapter;
import jext.javaparser.analysis.SolveSymbolsVisitor;
import jext.javaparser.symbolsolver.resolution.typesolvers.JarFilesTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserRootsTypeSolver;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class CheckLombok {

    public static void main(String[] args) {
        Parallel.setup();
        Logger.configure();
        CacheManager.configure();

        JavaParserPool pool = JavaParserPool.getPool();

        pool.add(new File("src")).add(new File("src_only"));


        FileUtils.listFiles(
            new File("D:\\Projects.github\\java_projects\\check_javaparser")
            ,f -> f.getName().endsWith(".java"))
            .forEach(f -> {

                CombinedTypeSolver ts = new CombinedTypeSolver();
                ts.add(new JavaParserRootsTypeSolver()
                    .add(new File("src"))
                    .add(new File("src_only"))
                    .add(new File("data\\bookstore\\src\\main\\java"))
                    .add(new File("D:\\Projects.github\\java_projects\\jext.javaparser\\src\\main\\java"))
                    .withCache());
                ts.add(new JarFilesTypeSolver()
                    .add(new File("C:\\Users\\Corrado Mio\\.m2\\repository\\org\\projectlombok\\lombok\\1.18.12\\lombok-1.18.12.jar"))
                    .add(new File("D:\\Projects.github\\java_projects\\jext.javaparser\\libs\\javaparser-core-3.19.1-SNAPSHOT.jar"))
                    .add(new File("D:\\Projects.github\\java_projects\\jext.javaparser\\libs\\javaparser-symbol-solver-core-3.19.1-SNAPSHOT.jar"))
                    .addJdk(new File("D:\\Java\\Jdk8.0.x64")));

                ParseResult<CompilationUnit> res = pool.parse(f);
                res.ifSuccessful(cu -> {
                    new SolveSymbolsVisitor()
                        .analyze(cu, ts);
                });
            });
    }

}
