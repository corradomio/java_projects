package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.SolveSymbolsVisitor;
import jext.javaparser.symbolsolver.resolution.typesolvers.CachedTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistryTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import jext.javaparser.util.ClassPoolRegistry;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheckMini {

    static void test1() {

        JavaParserPool pool = JavaParserPool.getPool().withCache()
            .add(new File("src_only"));
        ClassPoolRegistry cpr = new ClassPoolRegistry()
            .addJdk(new File("D:\\Java\\Jdk8.0.x64"));

        FileUtils.listFiles(new File("src_only"), f -> f.getName().endsWith(".java"))
            .forEach(source -> {
                System.out.println(source.getAbsolutePath());
                ContextTypeSolver ts = new ContextTypeSolver();
                ts.add(new ClassPoolRegistryTypeSolver().withClassPoolRegistry(cpr));
                ts.add(new JavaParserPoolTypeSolver(pool));

                ParseResult<CompilationUnit> result = pool.parse(source);
                result.ifSuccessful(cu -> {

                    SolveSymbolsVisitor ssv = new SolveSymbolsVisitor();
                    ssv.analyze(cu, ts);
                });
            });


    }

    static void test2() {

        String sourceCode = "import java.util.List;\n" +
            "\n" +
            "public class Test {\n" +
            "    public void test() {\n" +
            "        class Worker implements Runnable {\n" +
            "            private String jobs;\n" +
            "            List<String> tokens;\n" +
            "            public StringBuilder sb;\n" +
            "\n" +
            "            public void run() {\n" +
            "\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}\n";
        CombinedTypeSolver ts = new CombinedTypeSolver();
        // ts.add(new JavaParserTypeSolver(new File("src_only")));
        // ts.add(new JarTypeSolver().addJdk(new File("D:\\Java\\Jdk8.0.x64")));
        ts.add(new ReflectionTypeSolver());

        StaticJavaParser.getConfiguration()
            .setSymbolResolver(new JavaSymbolSolver(ts));
        CompilationUnit cu = StaticJavaParser.parse(sourceCode);
        List<VariableDeclarator> variables = cu.findAll(VariableDeclarator.class);
        assertEquals(3, variables.size());

        assertEquals("java.lang.String",                    variables.get(0).getType().resolve().describe());
        assertEquals("java.util.List<java.lang.String>",    variables.get(1).getType().resolve().describe());
        assertEquals("java.lang.StringBuilder",             variables.get(2).getType().resolve().describe());

    }
    static void testIssue3103() {
        StaticJavaParser.getConfiguration()
            .setSymbolResolver(new JavaSymbolSolver(new ReflectionTypeSolver()));

        String sourceCode = "import java.util.List;\n" +
            "\n" +
            "public class Test {\n" +
            "    public void test() {\n" +
            "        class Worker implements Runnable {\n" +
            "            private String jobs;\n" +
            "            List<String> tokens;\n" +
            "            public StringBuilder sb;\n" +
            "\n" +
            "            public void run() {\n" +
            "\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}\n";
        CompilationUnit cu = StaticJavaParser.parse(sourceCode);
        List<VariableDeclarator> variables = cu.findAll(VariableDeclarator.class);
        assertEquals(3, variables.size());

        assertEquals("java.lang.String",                    variables.get(0).getType().resolve().describe());
        assertEquals("java.util.List<java.lang.String>",    variables.get(1).getType().resolve().describe());
        assertEquals("java.lang.StringBuilder",             variables.get(2).getType().resolve().describe());
    }

    public static void main(String[] args) {
        Parallel.setup();
        Logger.configure();
        CacheManager.configure();

        test1();
        // test2();
        // testIssue3103();

        Parallel.shutdown();
    }
}
