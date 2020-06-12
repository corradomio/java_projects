package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

import java.util.function.Predicate;

public class CombinedTypeSolver extends com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver {

    public CombinedTypeSolver(TypeSolver... elements) {
        super(elements);
    }

    public CombinedTypeSolver(Predicate<Exception> exceptionHandler, TypeSolver... elements) {
        super(exceptionHandler, elements);
    }

    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        return super.tryToSolveType(name);
    }
}
