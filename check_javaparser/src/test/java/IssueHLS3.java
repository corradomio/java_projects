import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class IssueHLS3 {

    public static void main(String[] args) {
        String code =
            "package a.b.c.d;\n" +
                "\n" +
                "public class TestHLS3 {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        a.b.c.d.TestHLS2.C c = new a.b.c.d.TestHLS2.C();\n" +
                "    }\n" +
                "}";
        TypeSolver typeSolver = new CombinedTypeSolver(
            new ReflectionTypeSolver()
        );
        StaticJavaParser.getConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
        CompilationUnit cu = StaticJavaParser.parse(code);

        (new VoidVisitorAdapter<Void>() {
            public void visit(ClassOrInterfaceType n, Void arg) {
                System.out.printf("ClassOrInterfaceType %s\n", n.toString());
                super.visit(n, arg);
            }

        }).visit(cu, null);

        // (new LogVoidVisitorAdapter<>()).analyze(cu);
    }
}
