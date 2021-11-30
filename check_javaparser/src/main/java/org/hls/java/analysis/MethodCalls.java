package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;

public class MethodCalls extends ContextVisitorAdapter {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MethodCalls() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public BaseVoidVisitorAdapter analyze(CompilationUnit cu, TypeSolver ts) {
        return super.analyze(cu, ts);
    }

    @Override
    public void visit(NameExpr n, Void arg) {
        if (n.toString().contains("resolveClassLoadingStrategy"))
            System.out.println("ok");
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodCallExpr n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(MethodCallExpr n) {
        String call = n.toString();
        if (call.contains("resolveClassLoadingStrategy"))
            System.out.println("ok");

        try {
            ResolvedMethodDeclaration rmd = n.resolve();
            System.out.println(rmd.toString());
        }
        catch (Throwable t) {
            System.err.println(t);
        }
    }
}
