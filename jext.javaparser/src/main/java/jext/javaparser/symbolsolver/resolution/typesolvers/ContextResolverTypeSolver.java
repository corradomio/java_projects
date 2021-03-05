package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.util.ContextResolver;
import jext.javaparser.util.UnsolvedSymbols;

public class ContextResolverTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected ContextResolver solvedSymbols;
    protected UnsolvedSymbols unsolvedSymbols;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public ContextResolverTypeSolver(ContextResolver solvedSymbols, UnsolvedSymbols unsolvedSymbols) {
        this(DEFAULT, solvedSymbols, unsolvedSymbols);
    }

    public ContextResolverTypeSolver(String name, ContextResolver solvedSymbols, UnsolvedSymbols unsolvedSymbols) {
        super(name);
        this.solvedSymbols = solvedSymbols;
        this.unsolvedSymbols = unsolvedSymbols;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public ContextResolver getSolvedSymbols() {
        return solvedSymbols;
    }

    public UnsolvedSymbols getUnsolvedSymbols() {
        return unsolvedSymbols;
    }

    // ----------------------------------------------------------------------
    // tryToSolveType
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String symbol) {
        return solvedSymbols.resolve(symbol);
    }

}
