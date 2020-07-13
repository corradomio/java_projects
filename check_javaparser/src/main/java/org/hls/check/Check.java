package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.LogVisitorAdapter;
import jext.javaparser.symbolsolver.resolution.typesolvers.CompositeTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JDKTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.util.JPUtils;
import jext.util.FileUtils;

import java.io.File;

public class Check {

    public static void main(String[] args) {

        JavaParserPool pool = JavaParserPool.getPool();
        pool.addSourceRoot(new File("data\\bookstore\\src\\main\\java"));

        FileUtils.listFiles(new File("data\\bookstore\\src\\main\\java"), pathname -> true)
                .forEach(file -> {
                    System.out.printf("== %s ==\n", file.getName());
                    ParseResult<CompilationUnit> result = pool.parse(file);
                    result.ifSuccessful(Check::analyze);
                });
    }

    static void analyze1(CompilationUnit cu) {
        new LogVisitorAdapter<Void>().analyze(cu);
    }

    static void analyze(CompilationUnit cu) {
        //CompositeTypeSolver ts = new CompositeTypeSolver("default");
        ContextTypeSolver ts = new ContextTypeSolver();
        // ts.add(new JavaParserTypeSolver(new File("data\\bookstore\\src\\main\\java")));
        ts.add(new JavaParserPoolTypeSolver(JavaParserPool.getPool()));
        // ts.add(new ReflectionTypeSolver());
        ts.add(new JDKTypeSolver(new File("D:\\Java\\MiniJdk\\Jdk8")));

        JPUtils.setTypeSolver(cu, ts);

        cu.findAll(MethodCallExpr.class).forEach(Check::analyze);
    }

    static void analyze(MethodCallExpr mce) {
        try {
            ResolvedMethodDeclaration rmd = mce.resolve();
            System.out.println(mce.toString());
            // System.out.printf("    %s::%s\n", rmd.getQualifiedName(), rmd.getSignature());
            System.out.printf("    %s\n", rmd.getQualifiedSignature());
        }
        catch (Throwable t) {
            // System.out.println(mce.toString());
            // System.out.println("... " + t.getMessage());
        }
    }
}
