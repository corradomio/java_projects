package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;

import java.io.File;

public class InvalidTypeSolver extends BaseTypeSolver {

    public InvalidTypeSolver(String name) {
        super(name);
    }

    public InvalidTypeSolver(File file) {
        super(file);
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }
}
