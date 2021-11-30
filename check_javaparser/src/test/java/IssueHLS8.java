import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class IssueHLS8 {
    public static void main(String[] args) {
        String code =
            "package a.b.c.d;\n" +
                "\n" +
                "import java.util.List;\n" +
                "import not.existent.pkg.Unsolved;\n" +
                "\n" +
                "public class TestHLS8 {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        List<Unsolved> l;\n" +
                "    }\n" +
                "}\n";
        TypeSolver typeSolver = new CombinedTypeSolver(
            new ReflectionTypeSolver()
        );
        StaticJavaParser.getConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
        CompilationUnit cu = StaticJavaParser.parse(code);

        (new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceType n, Void arg) {
                try {
                    n.getTokenRange().ifPresent(tok -> {
                        System.out.printf("... analyzing %s\n", tok);
                    });
                    n.resolve();
                }
                catch (Throwable t) {
                    System.out.println(t);
                }
            }

        }).visit(cu, null);

        // (new LogVoidVisitorAdapter<>()).analyze(cu);
    }
}
