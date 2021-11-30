package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;
import jext.javaparser.util.JPUtils;
import jext.lang.JavaUtils;

public class SymbolSolver extends ContextVisitorAdapter  {

    public BaseVoidVisitorAdapter analyze(CompilationUnit cu, TypeSolver ts) {
        return super.analyze(cu, ts);
    }

    @Override
    public void visit(ClassOrInterfaceType n, Void arg) {
        super.visitArgs(n, arg);
        visit(n);
        super.visit(n, arg);
    }

    private void visit(ClassOrInterfaceType n) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;
        String name = n.getNameWithScope();

        // skip errors in AST
        if (tsx().isNamespace(name) || JPUtils.isTypeParameter(name, n))
            return;

        try {
            ResolvedReferenceType rrt = n.resolve();
            return;
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | IllegalArgumentException e) {

        }
        catch (StackOverflowError e) {
            throw e;
        }
        catch (Throwable t) {
            System.err.printf(">>> [%s] %s\n  %s :: %s\n", t.getClass(), t.getMessage(),
                cu.getStorage().get().getPath(),
                n.getTokenRange().get().getBegin().getRange().toString());
        }

        // skip errors in AST
        if (JPUtils.isMethodReferenceExpr(n) || JavaUtils.isIdentifier(name))
            return;

        try {
            solved = tsx().tryToSolveType(n);
            if (!solved.isSolved()) {
                int nTypeParams = 0;
                if (n.getTypeArguments().isPresent()) {
                    nTypeParams = n.getTypeArguments().get().size();
                }
                System.err.printf("Unsolved %s/%d\n  %s :: %s\n", n.getNameAsString(), nTypeParams,
                    cu.getStorage().get().getPath(),
                    n.getTokenRange().get().getBegin().getRange().toString());
            }
        }
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
