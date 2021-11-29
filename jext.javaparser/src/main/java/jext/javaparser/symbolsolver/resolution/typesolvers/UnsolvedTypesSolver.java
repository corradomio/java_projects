package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.util.UnsolvedTypesRegistry;

public class UnsolvedTypesSolver extends BaseTypeSolver implements TypeSolverExt {

    private UnsolvedTypesRegistry typeRegistry;

    public UnsolvedTypesSolver() {
        super(UnsolvedTypesSolver.class.getName());
        typeRegistry = new UnsolvedTypesRegistry();
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        ResolvedReferenceTypeDeclaration rrtd = typeRegistry.get(name);
        if (rrtd != null)
            return SymbolReference.solved(rrtd);
        else
            return UNSOLVED;
    }
}
