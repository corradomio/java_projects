package org.hls.check;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import jext.io.util.FileFilters;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.LogVisitorAdapter;
import jext.javaparser.symbolsolver.resolution.typesolvers.JDKTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.util.JPUtils;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

public class Check {

    private static CombinedTypeSolver cptss = new CombinedTypeSolver();

    public static void main(String[] args) throws Exception {
        Parallel.setup();
        Logger.configure();



        // TypeSolver ts = new JDKTypeSolver(new File("D:\\Java\\MiniJdk\\Jdk8"));
        // System.out.println(ts.tryToSolveType("java.util.Collection"));

        JavaParserPool pool = JavaParserPool.getPool();
        pool.setCacheSizeLimit(1000);

        Parallel.forEach(FileUtils.listFiles(new File("D:\\Projects.github\\other_projects\\hibernate-orm"), FileFilters.IS_JAVA),
            pool::parse);

        // FileUtils.listFiles(
        //     //new File("data\\bookstore\\src\\main\\java"),
        //     new File("src_only"),
        //     pathname -> true)
        //         .forEach(file -> {
        //             System.out.printf("== %s ==\n", file.getName());
        //             // ParseResult<CompilationUnit> result = pool.parse(file);
        //             ParseResult<CompilationUnit> result = parse(file);
        //             result.ifSuccessful(Check::analyze);
        //         });
        Parallel.shutdown();
    }

    private static void parse(File file) {
        try {
            System.out.printf("== %s ==\n", file.getName());
            //return new JavaParser().parse(file);
            JavaParserPool.getPool().parse(file);
        } catch (Exception e) {
            Logger.getLogger("parse").error(e, e);
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
            ts.add(new JDKTypeSolver(new File("D:\\Java\\MiniJdk\\Jdk8")));
            // ts.add(new ReflectionTypeSolver());
            // ts.add(new JarTypeSolver(new File("D:\\Java\\MiniJdk\\jdk8\\rt.jar")));
            // ts.add(new JarTypeSolver(new File("D:\\Java\\MiniJdk\\jdk8\\alt-rt.jar")));
            // ts.add(cptss.getTypeSolver(new File("D:\\Java\\MiniJdk\\jdk8")));

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
            System.out.println("... " + t);
        }
    }
}
