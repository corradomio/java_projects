package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverExt;

public class ContextVisitorAdapter extends BaseVoidVisitorAdapter {

    protected BaseVoidVisitorAdapter analyze(CompilationUnit cu, TypeSolver ts) {
        if (ts instanceof TypeSolverExt)
            ((TypeSolverExt) ts).setCu(cu);
        return super.analyze(cu, ts);
    }
}
