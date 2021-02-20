package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

public class TypeSolverExtWrapper extends BaseTypeSolver {

    private TypeSolver ts;

    public TypeSolverExtWrapper(TypeSolver ts) {
        super(DEFAULT);
        this.ts = ts;
        this.ts.setParent(this);
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String s) {
        return ts.tryToSolveType(s);
    }

}
