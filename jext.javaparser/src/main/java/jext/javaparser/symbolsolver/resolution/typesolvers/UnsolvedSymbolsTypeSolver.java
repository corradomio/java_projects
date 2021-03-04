package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.util.UnresolvedSymbols;

public class UnresolvedSymbolsTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected UnresolvedSymbols unresolvedSymbols;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public UnresolvedSymbolsTypeSolver(UnresolvedSymbols unresolvedSymbols) {
        super(DEFAULT);
        this.unresolvedSymbols = unresolvedSymbols;
    }

    public UnresolvedSymbolsTypeSolver(String name, UnresolvedSymbols unresolvedSymbols) {
        super(name);
        this.unresolvedSymbols = unresolvedSymbols;
    }

    // ----------------------------------------------------------------------
    // tryToSolveType
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String symbol) {
        return unresolvedSymbols.resolve(symbol);
    }

}
