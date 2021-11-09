package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;

public class ContextVisitorAdapter extends BaseVoidVisitorAdapter {

    protected BaseVoidVisitorAdapter analyze(CompilationUnit cu, TypeSolver ts) {
        ContextTypeSolver ctxts = (ContextTypeSolver) ts;
        ctxts.setCu(cu);

        SymbolResolver symbolResolver = new JavaSymbolSolver(ts);
        cu.setData(Node.SYMBOL_RESOLVER_KEY, symbolResolver);

        visit(cu, null);

        return this;
    }
}
