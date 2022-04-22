import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.RecordDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class IssueHLS9 {
    public static void main(String[] args) {
        String code =
            "package a.b.c.d;\n" +
                "\n" +
                "class AClass {}\n" +
                "record ARecord (int fi) { }\n" +
                "";

        TypeSolver typeSolver = new CombinedTypeSolver(
            new ReflectionTypeSolver()
        );
        StaticJavaParser.getConfiguration().setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_17);
        StaticJavaParser.getConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
        CompilationUnit cu = StaticJavaParser.parse(code);

        (new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                ResolvedReferenceTypeDeclaration rrtd;
                try {
                    rrtd = n.resolve();
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }

            @Override
            public void visit(RecordDeclaration n, Void arg) {
                ResolvedReferenceTypeDeclaration rrtd;
                try {
                    rrtd = n.resolve();
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }

        }).visit(cu, null);
    }
}
