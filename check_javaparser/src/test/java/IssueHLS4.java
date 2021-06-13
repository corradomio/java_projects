import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class IssueHLS4 {

    public static void main(String[] args) {
        String code =
            "package a.b.c.d;\n" +
                "\n" +
                "import java.util.Collection;\n" +
                "import java.util.Collections;\n" +
                "import java.util.Iterator;\n" +
                "import java.util.NoSuchElementException;\n" +
                "\n" +
                "public class TestHLS4 {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        Collection<Character> emptySet = Collections.emptySet();\n" +
                "        final Iterator<Character> emptySetIt = emptySet.iterator();\n" +
                "        assertThrows(NoSuchElementException.class, emptySetIt::next);\n" +
                "    }\n" +
                "\n" +
                "    interface Executable {\n" +
                "        void execute() throws Throwable;\n" +
                "    }\n" +
                "\n" +
                "    private static void assertThrows(Class<?> clazz, Executable executable) {\n" +
                "\n" +
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
