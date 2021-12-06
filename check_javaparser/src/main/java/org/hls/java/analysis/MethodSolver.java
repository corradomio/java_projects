package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;
import jext.javaparser.util.JPUtils;

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

            ResolvedType returnType = rmd.getReturnType();
            int nParams = rmd.getNumberOfParams();
            for (int i=0; i<nParams; ++i) {
                ResolvedParameterDeclaration rpd = rmd.getParam(i);
                ResolvedType parameterType = rpd.getType();
                parameterType = null;
            }
            return;
        }
        // catch (UnsolvedSymbolException | UnsupportedOperationException | IllegalArgumentException e) {
        //
        // }
        catch (StackOverflowError e) {
            throw e;
        }
        catch (Throwable t) {
            logger.errorf(">>> [%s] <%s::%s> %s\n  %s :: %s\n",
                t.getClass().getSimpleName(),
                JPUtils.getDeclaringType(n).describe(),  n.getNameAsString(),
                t.getMessage(),
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
            logger.errorf(">>> [%s] <%s::%s> %s\n  %s :: %s\n",
                t.getClass().getSimpleName(),
                JPUtils.getDeclaringType(n).describe(),  n.toString(),
                t.getMessage(),
                cu.getStorage().get().getPath(),
                n.getTokenRange().get().getBegin().getRange().toString());
        }
    }
}
