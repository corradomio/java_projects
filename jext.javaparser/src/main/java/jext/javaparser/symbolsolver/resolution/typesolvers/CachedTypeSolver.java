package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.cache.Cache;
import jext.cache.CacheManager;

public class CachedTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String cacheName;
    private Cache<String, SymbolReference<ResolvedReferenceTypeDeclaration>> cache;
    private TypeSolverExt ts;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public CachedTypeSolver() {
        this(DEFAULT);
    }

    public CachedTypeSolver(TypeSolver ts) {
        this(DEFAULT, ts);
    }

    public CachedTypeSolver(String name) {
        super(name);
        this.cacheName = name;
    }

    public CachedTypeSolver(String name, TypeSolver ts) {
        super(name);
        this.cacheName = name;
        this.ts = (TypeSolverExt) ts;
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public CachedTypeSolver withCache(String cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    // ----------------------------------------------------------------------
    // tryToSolveType
    // ----------------------------------------------------------------------

    @Override
    public void setCu(CompilationUnit cu) {
        this.ts.setCu(cu);
    }

    @Override
    public boolean isNamespace(String name) {
        return this.ts.isNamespace(name);
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;

        // the cache can be deleted!
        cache = CacheManager.getCache(this.cacheName);

        solved = cache.get(name, () -> this.ts.tryToSolveType(name));
        return solved;
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(Type n) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;

        solved = this.ts.tryToSolveType(n);
        if (!solved.isSolved())
            return solved;

        // the cache can be deleted!
        cache = CacheManager.getCache(this.cacheName);

        String name = solved.getCorrespondingDeclaration().getQualifiedName();
        cache.put(name, solved);
        return solved;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
