package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;

import java.util.NoSuchElementException;

public class SolveSymbolsVisitor extends BaseVoidVisitorAdapter {

    public void analyze(CompilationUnit cu, TypeSolver ts) {
        if (ts instanceof ContextTypeSolver)
            this.ts = (ContextTypeSolver) ts;
        else
            this.ts = new ContextTypeSolver().add(ts);

        super.analyze(cu);
    }

    @Override
    public void visit(PackageDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceType n, Void arg) {
        try {
            ResolvedReferenceType rrt = n.resolve();
            // System.out.println(rrt);
        }
        catch (UnsolvedSymbolException e) {
            logger.error(e.toString());
            // System.err.println(e);
        }
        catch (UnsupportedOperationException e) {
            logger.error(e.toString() + " " + n.getNameAsString());
            // System.err.println(e);
        }
        catch (NoSuchElementException e) {
            logger.error(e.toString() + " " + n.getNameAsString());
            // System.err.println(e);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(Parameter n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarationExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VarType n, Void arg) {
        super.visit(n, arg);
    }
}