// package jext.javaparser.symbolsolver.resolution.typesolvers;
//
// import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
// import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
// import jext.javaparser.util.ContextSolvedSymbols;
// import jext.javaparser.util.UnsolvedSymbols;
//
// public class ContextSolvedSymbolsTypeSolver extends BaseTypeSolver {
//
//     // ----------------------------------------------------------------------
//     // Protected fields
//     // ----------------------------------------------------------------------
//
//     protected ContextSolvedSymbols solvedSymbols;
//     protected UnsolvedSymbols unsolvedSymbols;
//
//     // ----------------------------------------------------------------------
//     // Constructors
//     // ----------------------------------------------------------------------
//
//     public ContextSolvedSymbolsTypeSolver(ContextSolvedSymbols solvedSymbols, UnsolvedSymbols unsolvedSymbols) {
//         this(DEFAULT, solvedSymbols, unsolvedSymbols);
//     }
//
//     public ContextSolvedSymbolsTypeSolver(String name, ContextSolvedSymbols solvedSymbols, UnsolvedSymbols unsolvedSymbols) {
//         super(name);
//         this.solvedSymbols = solvedSymbols;
//         this.unsolvedSymbols = unsolvedSymbols;
//     }
//
//     // ----------------------------------------------------------------------
//     // Properties
//     // ----------------------------------------------------------------------
//
//     public ContextSolvedSymbols getSolvedSymbols() {
//         return solvedSymbols;
//     }
//
//     public UnsolvedSymbols getUnsolvedSymbols() {
//         return unsolvedSymbols;
//     }
//
//     // ----------------------------------------------------------------------
//     // tryToSolveType
//     // ----------------------------------------------------------------------
//
//     @Override
//     public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String symbol) {
//         return solvedSymbols.resolve(symbol);
//     }
//
// }
