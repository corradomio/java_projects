import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.nio.file.Path;

public class Issue3100 extends Issues{

    public static void main(String[] args) {
        String code =
            "public class NodeTest {\n" +
                "\n" +
                "    public void testNode() {\n" +
                "        Trie trie = new Trie();\n" +
                "        Trie.Node node = trie.new Node();\n" +
                "    }\n" +
                "}";
        Path pathToSourceFile = adaptPath("src/test/resources/issue3100");
        TypeSolver typeSolver = new CombinedTypeSolver(
            new ReflectionTypeSolver(),
            new JavaParserTypeSolver(pathToSourceFile));
        ParserConfiguration config = new ParserConfiguration();
        StaticJavaParser.getConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
        CompilationUnit cu = StaticJavaParser.parse(code);
        System.out.println(cu.toString());

        (new VoidVisitorAdapter<Void>() {

            public void visit(ClassOrInterfaceType n, Void arg) {
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
