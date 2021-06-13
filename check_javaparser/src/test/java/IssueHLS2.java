import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import jext.javaparser.analysis.LogVoidVisitorAdapter;

public class IssueHLS2 {

    public static void main(String[] args) {
        String code =
            "package a.b.c.d;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "public class TestHLS2 {\n" +
                "\n" +
                "    static class C {\n" +
                "        void print(String s) { }\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        a.b.c.d.TestHLS2.C c = new a.b.c.d.TestHLS2.C();\n" +
                "        List<String> l = new ArrayList<>();\n" +
                "        l.forEach(c::print);\n" +
                "    }\n" +
                "}";
        TypeSolver typeSolver = new CombinedTypeSolver(
            new ReflectionTypeSolver()
        );
        StaticJavaParser.getConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
        CompilationUnit cu = StaticJavaParser.parse(code);

        (new VoidVisitorAdapter<Void>() {
            public void visit(MethodReferenceExpr n, Void arg) {
                try {
                    n.resolve();
                }
                catch (Exception e) {
                    System.out.printf("%s %s\n", n, e);
                }
                super.visit(n, arg);
            }
        }).visit(cu, null);

        (new LogVoidVisitorAdapter<>()).analyze(cu);
    }
}
