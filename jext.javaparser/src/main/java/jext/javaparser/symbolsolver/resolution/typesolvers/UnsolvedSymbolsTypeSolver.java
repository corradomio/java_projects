package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.util.UnsolvedSymbols;

public class UnsolvedSymbolsTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected UnsolvedSymbols unresolvedSymbols;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public UnsolvedSymbolsTypeSolver(UnsolvedSymbols unresolvedSymbols) {
        super(DEFAULT);
        this.unresolvedSymbols = unresolvedSymbols;
    }

    public UnsolvedSymbolsTypeSolver(String name, UnsolvedSymbols unresolvedSymbols) {
        super(name);
        this.unresolvedSymbols = unresolvedSymbols;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public UnsolvedSymbols getUnsolvedSymbols() {
        return unresolvedSymbols;
    }

    // ----------------------------------------------------------------------
    // tryToSolveType
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String symbol) {
        return unresolvedSymbols.resolve(symbol);
    }

}
