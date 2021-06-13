import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class IssueHLS5 {

    public static void main(String[] args) {
        String code =
            "package a.b.c.d;\n" +
                "\n" +
                "import java.lang.reflect.Type;\n" +
                "\n" +
                "public class TestHLS5 {\n" +
                "    private static void appendRecursiveTypes(final StringBuilder builder, final int[] recursiveTypeIndexes,\n" +
                "                                             final Type[] argumentTypes) {\n" +
                "        for (int i = 0; i < recursiveTypeIndexes.length; i++) {\n" +
                "            appendAllTo(builder.append('<'), \", \", argumentTypes[i].toString()).append('>');\n" +
                "        }\n" +
                "    }\n" +
                "    private static <T> StringBuilder appendAllTo(final StringBuilder builder, final String sep,\n" +
                "                                                 @SuppressWarnings(\"unchecked\") final T... types) {\n" +
                "        return builder;\n" +
                "    }\n" +
                "\n" +
                "}";
        TypeSolver typeSolver = new CombinedTypeSolver(
            new ReflectionTypeSolver()
        );
        StaticJavaParser.getConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
        CompilationUnit cu = StaticJavaParser.parse(code);

        (new VoidVisitorAdapter<Void>() {
            public void visit(MethodCallExpr n, Void arg) {
                try {
                    n.resolve();
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                super.visit(n, arg);
            }

        }).visit(cu, null);

        // (new LogVoidVisitorAdapter<>()).analyze(cu);
    }
}
