import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class IssueHLS7 {

    public static void main(String[] args) {
        String code =
            "package a.b.c.d;\n" +
                "\n" +
                "class Processor {\n" +
                "    public enum Arch {\n" +
                "        BIT_32(\"32-bit\");\n" +
                "        private final String label;\n" +
                "        Arch(final String label) {\n" +
                "            this.label = label;\n" +
                "        }\n" +
                "        public String getLabel() { return label; }\n" +
                "    }\n" +
                "    enum Type {\n" +
                "        X86,\n" +
                "        UNKNOWN\n" +
                "    }\n" +
                "    public Processor(final Processor.Arch arch, final Processor.Type type) {}\n" +
                "}\n" +
                "\n" +
                "public class TestHLS7 {\n" +
                "\n" +
                "    private static void init_X86_32Bit() {\n" +
                "        final Processor processor = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "\n" +
                "    }\n" +
                "}";
        TypeSolver typeSolver = new CombinedTypeSolver(
            new ReflectionTypeSolver()
        );
        StaticJavaParser.getConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
        CompilationUnit cu = StaticJavaParser.parse(code);

        (new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(FieldAccessExpr n, Void arg) {
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
