import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.nio.file.Path;

public class Issue11 extends Issues {

    public static void main(String[] args) {
        String code =
                "import java.text.SimpleDateFormat;\n" +
                    "import java.util.Date;\n" +
                    "\n" +
                    "public class Test {\n" +
                    "    static final SimpleDateFormat formatter = null;\n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "        return \"MaxTimeCondition\" + formatter.format(new Date());\n" +
                    "    }\n" +
                    "}\n";
        Path pathToSourceFile = adaptPath("src/test/resources/issue3100");
        TypeSolver typeSolver = new CombinedTypeSolver(
            new ReflectionTypeSolver(),
            new JavaParserTypeSolver(pathToSourceFile));
        ParserConfiguration config = new ParserConfiguration();
        StaticJavaParser.getConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
        CompilationUnit cu = StaticJavaParser.parse(code);
        System.out.println(cu.toString());

        (new VoidVisitorAdapter<Void>() {

            public void visit(MethodCallExpr n, Void arg) {
                try {
                    n.resolve();
                }
                catch (Exception e) {
                    System.out.printf("%s %s\n", n, e);
                }
            }

        }).visit(cu, null);

    }
}
