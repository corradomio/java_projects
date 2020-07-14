package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

/**
 * Use to add support to "parent".
 * In this way, the inner type solver can be used multiple times and in parallel
 */
public class ParentTypeSolver extends BaseTypeSolver {

    private TypeSolver typeSolver;

    public ParentTypeSolver(TypeSolver typeSolver) {
        super("Default");
        this.typeSolver = typeSolver;
    }

    protected ParentTypeSolver(String name, TypeSolver typeSolver) {
        super(name);
        this.typeSolver = typeSolver;
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        return typeSolver.tryToSolveType(name);
    }
}
