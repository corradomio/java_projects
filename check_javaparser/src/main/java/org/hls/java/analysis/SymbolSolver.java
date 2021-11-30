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
        visit(n);
        super.visit(n, arg);
    }

    private void visit(ClassOrInterfaceType n) {
        String name = n.getNameWithScope();

        if (tsx().isNamespace(name))
            return;
        if (JPUtils.isTypeParameter(name, n))
            return;

        int nTypeParams = 0;
        if (n.getTypeArguments().isPresent()) {
            nTypeParams = n.getTypeArguments().get().size();
        }

        try {
            ResolvedReferenceType rrt = n.resolve();
            return;
        }
        catch (StackOverflowError e) {
            System.out.println("StackOverflow");
        }
        catch (UnsolvedSymbolException e) {
            // System.err.printf(">>> Unsolved %s/%d\n  %s :: %s\n", n.getNameAsString(), nTypeParams,
            //     cu.getStorage().get().getPath(),
            //     n.getTokenRange().get().getBegin().getRange().toString());
        }
        catch (java.lang.UnsupportedOperationException e) {
            // System.err.printf(">>> Unsolved %s/%d\n  %s :: %s\n", n.getNameAsString(), nTypeParams,
            //     cu.getStorage().get().getPath(),
            //     n.getTokenRange().get().getBegin().getRange().toString());
        }
        catch (Throwable t) {
            System.err.printf(">>> [%s] %s\n  %s :: %s\n", t.getClass(), t.getMessage(),
                cu.getStorage().get().getPath(),
                n.getTokenRange().get().getBegin().getRange().toString());
        }

        if (JavaUtils.isIdentifier(name))
            return;
        if (JPUtils.isMethodReferenceExpr(n))
            return;

        try {
            SymbolReference<ResolvedReferenceTypeDeclaration>
            solved = tsx().tryToSolveType(name);
            if (!solved.isSolved()) {
                System.err.printf("Unsolved %s/%d\n  %s :: %s\n", n.getNameAsString(), nTypeParams,
                    cu.getStorage().get().getPath(),
                    n.getTokenRange().get().getBegin().getRange().toString());
            }
        }
        catch (StackOverflowError e) {
            System.out.println("StackOverflow");
        }
        catch (UnsolvedSymbolException e) {
            System.err.printf(">>> Unsolved %s/%d\n  %s :: %s\n", n.getNameAsString(), nTypeParams,
                cu.getStorage().get().getPath(),
                n.getTokenRange().get().getBegin().getRange().toString());
        }
        catch (Throwable t) {
            System.err.printf(">>> [%s] %s\n  %s :: %s\n", t.getClass(), t.getMessage(),
                cu.getStorage().get().getPath(),
                n.getTokenRange().get().getBegin().getRange().toString());
        }
    }

}
