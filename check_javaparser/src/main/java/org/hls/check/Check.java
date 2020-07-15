package org.hls.check;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.LogVisitorAdapter;
import jext.javaparser.symbolsolver.resolution.typesolvers.CompositeTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JDKTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.util.JPUtils;
import jext.javassist.ClasspathsTypeSolver;
import jext.javassist.ClasspathsTypeSolvers;
import jext.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

public class Check {

    private static ClasspathsTypeSolvers cptss = new ClasspathsTypeSolvers();

    public static void main(String[] args) throws Exception {

        TypeSolver ts = new JDKTypeSolver(new File("D:\\Java\\MiniJdk\\Jdk8"));
        System.out.println(ts.tryToSolveType("java.util.Collection"));

        JavaParserPool pool = JavaParserPool.getPool();
        // pool.addSourceRoot(new File("data\\bookstore\\src\\main\\java"));
        // pool.addSourceRoot(new File("src\\main\\java"));

        FileUtils.listFiles(
            //new File("data\\bookstore\\src\\main\\java"),
            new File("src_only"),
            pathname -> true)
                .forEach(file -> {
                    System.out.printf("== %s ==\n", file.getName());
                    // ParseResult<CompilationUnit> result = pool.parse(file);
                    ParseResult<CompilationUnit> result = parse(file);
                    result.ifSuccessful(Check::analyze);
                });
    }

    private static ParseResult<CompilationUnit> parse(File file) {
        try {
            return new JavaParser().parse(file);
        } catch (FileNotFoundException e) {
            return new ParseResult<CompilationUnit>(null, Collections.emptyList(), null);
        }
    }


    static void analyze1(CompilationUnit cu) {
        new LogVisitorAdapter<Void>().analyze(cu);
    }

    static void analyze(CompilationUnit cu) {
        try {
            CombinedTypeSolver ts = new CombinedTypeSolver();
            // ContextTypeSolver ts = new ContextTypeSolver();
            // ts.add(new JavaParserTypeSolver(new File("data\\bookstore\\src\\main\\java")));
            ts.add(new JavaParserPoolTypeSolver(JavaParserPool.getPool()));
            // ts.add(new JavaParserTypeSolver(new File("src_only")));
            // ts.add(new JDKTypeSolver(new File("D:\\Java\\MiniJdk\\Jdk8")));
            // ts.add(new ReflectionTypeSolver());
            // ts.add(new JarTypeSolver(new File("D:\\Java\\MiniJdk\\jdk8\\rt.jar")));
            // ts.add(new JarTypeSolver(new File("D:\\Java\\MiniJdk\\jdk8\\alt-rt.jar")));
            ts.add(cptss.getTypeSolver(new File("D:\\Java\\MiniJdk\\jdk8")));

            JPUtils.setTypeSolver(cu, ts);

            cu.findAll(MethodCallExpr.class).forEach(Check::analyze);
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
    }

    static void analyze(MethodCallExpr mce) {
        try {
            ResolvedMethodDeclaration rmd = mce.resolve();
            System.out.println(mce.toString());
            // System.out.printf("    %s::%s\n", rmd.getQualifiedName(), rmd.getSignature());
            System.out.printf("    %s\n", rmd.getQualifiedSignature());
        }
        catch (Throwable t) {
            System.out.println(mce.toString());
            System.out.println("... " + t.getMessage());
        }
    }
}
