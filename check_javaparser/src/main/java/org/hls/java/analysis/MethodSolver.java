package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;

public class MethodSolver extends ContextVisitorAdapter {

    public BaseVoidVisitorAdapter analyze(CompilationUnit cu, TypeSolver ts) {
        return super.analyze(cu, ts);
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        visit(n);
        super.visit(n, arg);
    }

    private void visit(MethodDeclaration n) {
        try {
            ResolvedMethodDeclaration rmd = n.resolve();
            return;
        }
        // catch (UnsolvedSymbolException | UnsupportedOperationException | IllegalArgumentException e) {
        //
        // }
        catch (StackOverflowError e) {
            throw e;
        }
        catch (Throwable t) {
            System.err.printf(">>> [%s] %s\n  %s :: %s\n", t.getClass(), t.getMessage(),
                cu.getStorage().get().getPath(),
                n.getTokenRange().get().getBegin().getRange().toString());
        }
    }

    @Override
    public void visit(FieldDeclaration n, Void arg) {
        visit(n);
        super.visit(n, arg);
    }

    private void visit(FieldDeclaration n) {
        try {
            ResolvedFieldDeclaration rfd = n.resolve();
            return;
        }
        // catch (UnsolvedSymbolException | UnsupportedOperationException | IllegalArgumentException e) {
        //
        // }
        catch (StackOverflowError e) {
            throw e;
        }
        catch (Throwable t) {
            System.err.printf(">>> [%s] %s\n  %s :: %s\n", t.getClass(), t.getMessage(),
                cu.getStorage().get().getPath(),
                n.getTokenRange().get().getBegin().getRange().toString());
        }
    }
}
