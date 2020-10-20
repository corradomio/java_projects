package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;

public class MethodCallsVisitor extends BaseVisitorAdapter {

    // ----------------------------------------------------------------------
    // Analyze
    // ----------------------------------------------------------------------

    public MethodCallsVisitor analyze(CompilationUnit cu) {
        this.cu = cu;

        try {
            visit(cu, null);
        } catch (Throwable e) {
            logger.error(e);
        }
        return this;
    }

    // ----------------------------------------------------------------------
    // Visit
    // ----------------------------------------------------------------------

    @Override
    public void visit(ConstructorDeclaration n, Void arg) {
        System.out.printf("[[ Constructor %s\n", n.getNameAsString());
        super.visit(n, arg);
        System.out.printf("]] Constructor %s\n", n.getNameAsString());
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        System.out.printf("[[ Method %s\n", n.getNameAsString());
        super.visit(n, arg);
        System.out.printf("]] Method %s\n", n.getNameAsString());
    }

    // @Override
    // public void visit(BlockStmt n, Void arg) {
    //     System.out.printf("  [[ Body\n");
    //     super.visit(n, arg);
    //     System.out.printf("  ]] Body\n");
    // }

    @Override
    public void visit(MethodCallExpr n, Void arg) {
        System.out.printf("    call %s\n", n.toString());
        super.visit(n, arg);
    }

}
