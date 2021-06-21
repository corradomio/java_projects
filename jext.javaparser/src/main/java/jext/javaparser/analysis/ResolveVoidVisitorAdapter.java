package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.CompactConstructorDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.RecordDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.IntersectionType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

public class ResolveVoidVisitorAdapter extends BaseVoidVisitorAdapter {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public BaseVoidVisitorAdapter analyze(CompilationUnit cu, TypeSolver ts) {
        return super.analyze(cu, ts);
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    @Override
    public void visit(ClassOrInterfaceType n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ArrayType n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(VarType n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(UnionType n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(UnknownType n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(WildcardType n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(PrimitiveType n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(VoidType n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(IntersectionType n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    // ----------------------------------------------------------------------
    // Declarations
    // ----------------------------------------------------------------------

    @Override
    public void visit(CompactConstructorDeclaration n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(FieldDeclaration n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(EnumConstantDeclaration n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(EnumDeclaration n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(AnnotationDeclaration n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(RecordDeclaration n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ConstructorDeclaration n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(AnnotationMemberDeclaration n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarator n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    // ----------------------------------------------------------------------
    // Expr
    // ----------------------------------------------------------------------

    @Override
    public void visit(MethodCallExpr n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(MarkerAnnotationExpr n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(SingleMemberAnnotationExpr n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(NormalAnnotationExpr n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ThisExpr n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(NameExpr n, Void arg) {
        // try {
        //     n.resolve();
        // }
        // catch (Throwable t) {
        //     logException(n, t);
        // }
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodReferenceExpr n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ObjectCreationExpr n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(FieldAccessExpr n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    // ----------------------------------------------------------------------
    // Other
    // ----------------------------------------------------------------------

    @Override
    public void visit(Parameter n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ExplicitConstructorInvocationStmt n, Void arg) {
        try {
            n.resolve();
        }
        catch (Throwable t) {
            logException(n, t);
        }
        super.visit(n, arg);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static int skip = "com.github.javaparser.".length();

    private void logException(Resolvable n, Throwable t) {
        String cname = n.getClass().getName().substring(skip);
        System.out.printf("ERROR [%s] \"%s\":  %s\n", cname, n, t.getMessage());
    }

}
