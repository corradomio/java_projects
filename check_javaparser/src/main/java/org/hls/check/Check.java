package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import jext.cache.CacheManager;
import jext.io.util.FileFilters;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.LogVoidVisitorAdapter;
import jext.javaparser.util.UniqueIdentifiers;
import jext.javaparser.symbolsolver.resolution.typesolvers.JarFilesTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.util.JPUtils;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class Check {

    private static CombinedTypeSolver cptss = new CombinedTypeSolver();

    static UniqueIdentifiers ui = new UniqueIdentifiers();

    public static void main(String[] args) throws Exception {
        Parallel.setup();
        Logger.configure();
        CacheManager.configure();

        // TypeSolver ts = new JDKTypeSolver(new File("D:\\Java\\MiniJdk\\Jdk8"));
        // System.out.println(ts.tryToSolveType("java.util.Collection"));

        // JavaParserPool pool = JavaParserPool.getPool();
        // pool.setCacheSizeLimit(1000);

        Parallel.forEach(FileUtils.listFiles(
            // new File("D:\\Projects.github\\other_projects\\hibernate-orm")
            // new File("D:\\Projects.github\\other_projects\\deeplearning4j")
            new File("D:\\Projects.github\\ml_projects\\elasticsearch-7.11.0")
            , FileFilters.IS_JAVA),
            Check::parse);

        // FileUtils.listFiles(
        //     new File("D:\\Projects.github\\other_projects\\hibernate-orm")
            // new File("D:\\Projects.github\\other_projects\\deeplearning4j")
            // , FileFilters.IS_JAVA)
            // .stream()
            // .forEach(Check::parse);

        // us.stats();
        ui.stats();

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
            // System.out.printf("== %s ==\n", file.getName());
            //return new JavaParser().parse(file);
            ParseResult<CompilationUnit> presult = JavaParserPool.getPool().parse(file);
            if (presult.isSuccessful() && presult.getResult().isPresent()) {
                // JSONUtils.save(new File(file.getParentFile(), file.getName() + ".json"), presult.getResult().get());

                // File serialized = new File(file.getParentFile(), file.getName() + ".kryo");
                CompilationUnit cu = presult.getResult().get();

                // UniqueSymbols us = new UniqueSymbols();
                // us.analyze(cu);
                ui.analyze(cu);

                // KryoSerializer.serialize(serialized, cu);
                // CompilationUnit desercu = KryoSerializer.deserialize(serialized, CompilationUnit.class);
                // File serialized = new File(file.getParentFile(), file.getName() + ".protostuff");
                // ProtostuffSerializer.serialize(serialized, presult.getResult().get());
                // CompilationUnit desercu = ProtostuffSerializer.deserialize(serialized, CompilationUnit.class);
                // File serialized = new File(file.getParentFile(), file.getName() + ".fst");
                // FstSerializer.serialize(serialized, presult.getResult().get());
                // CompilationUnit desercu = KryoSerializer.deserialize(serialized, CompilationUnit.class);
                // System.out.println(desercu);
            }
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static void analyze1(CompilationUnit cu) {
        new LogVoidVisitorAdapter<Void>().analyze(cu);
    }

    static void analyze(CompilationUnit cu) {
        try {
            CombinedTypeSolver ts = new CombinedTypeSolver();
            // ContextTypeSolver ts = new ContextTypeSolver();
            // ts.add(new JavaParserTypeSolver(new File("data\\bookstore\\src\\main\\java")));
            ts.add(new JavaParserPoolTypeSolver(JavaParserPool.getPool()));
            // ts.add(new JavaParserTypeSolver(new File("src_only")));
            ts.add(new JarFilesTypeSolver().add(new File("D:\\Java\\MiniJdk\\Jdk8")));
            // ts.add(new ReflectionTypeSolver());
            // ts.add(new JarTypeSolver(new File("D:\\Java\\MiniJdk\\jdk8\\rt.jar")));
            // ts.add(new JarTypeSolver(new File("D:\\Java\\MiniJdk\\jdk8\\alt-rt.jar")));
            // ts.add(cptss.getTypeSolver(new File("D:\\Java\\MiniJdk\\jdk8")));

            JPUtils.setSymbolSolver(cu, ts);

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
