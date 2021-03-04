package jext.javaparser.util;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;

public class UnsolvedSymbols {

    public SymbolReference<ResolvedReferenceTypeDeclaration>
        resolve(String symbol) {
        return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }
}
