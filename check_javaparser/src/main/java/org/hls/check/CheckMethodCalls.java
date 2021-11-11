package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.LogVoidVisitorAdapter;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistryTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.CompositeTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistry;
import jext.javaparser.util.JPUtils;
import jext.logging.Logger;
import jext.util.FileUtils;

import java.io.File;

public class CheckMethodCalls {

    static JavaParserPool pool;

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        // ts = new JDKTypeSolver(new File("D:\\Java\\MiniJdk\\Jdk8"));
        pool = JavaParserPool.getPool();

        FileUtils.listFiles(new File(
            // "data\\bookstore\\src\\main\\java"
            // "D:\\Projects.github\\java_projects\\jext.cache"
            // "src_only"
            "D:\\Temp\\etisala-project-ebtic"
            )
            ,
            pathname -> true)
            .stream()
            .filter(file -> file.getName().endsWith(".java"))
            .forEach(file -> {
                System.out.printf("==\n== %s ==\n==\n", file.getName());
                // ParseResult<CompilationUnit> result = pool.parse(file);
                ParseResult<CompilationUnit> result = parse(file);
                result.ifSuccessful(CheckMethodCalls::analyze);
            });
    }

    private static ParseResult<CompilationUnit> parse(File file) {
        return pool.parse(file);
    }

    static void analyze(CompilationUnit cu) {
        ClassPoolRegistry cpr = new ClassPoolRegistry().addJdk(new File("D:\\Java\\Jdk8.0.x64"));
        CompositeTypeSolver ts = new CompositeTypeSolver();
        ts.add(new JavaParserPoolTypeSolver(JavaParserPool.getPool()));
        ts.add(new ClassPoolRegistryTypeSolver(cpr));

        JPUtils.setSymbolSolver(cu, ts);

        LogVoidVisitorAdapter<Void> lva = new LogVoidVisitorAdapter<Void>()
            .include(MethodCallExpr.class, ObjectCreationExpr.class);
        lva.analyze(cu);
    }
}
