import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class IssueHLS6 {

    public static void main(String[] args) {
        String code =
            "package a.b.c.d;\n" +
                "\n" +
                "import java.util.Calendar;\n" +
                "import java.util.Date;\n" +
                "\n" +
                "public class TestHLS6 {\n" +
                "    String format(final Object obj) {\n" +
                "        if (obj instanceof Date) {\n" +
                "            return format((Date) obj);\n" +
                "        } else if (obj instanceof Calendar) {\n" +
                "            return format((Calendar) obj);\n" +
                "        } else if (obj instanceof Long) {\n" +
                "            return format(((Long) obj).longValue());\n" +
                "        } else {\n" +
                "            throw new IllegalArgumentException(\"Unknown class: \" +\n" +
                "                (obj == null ? \"<null>\" : obj.getClass().getName()));\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}";
        TypeSolver typeSolver = new CombinedTypeSolver(
            new ReflectionTypeSolver()
        );
        StaticJavaParser.getConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
        CompilationUnit cu = StaticJavaParser.parse(code);

        (new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ObjectCreationExpr n, Void arg) {
                try {
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
