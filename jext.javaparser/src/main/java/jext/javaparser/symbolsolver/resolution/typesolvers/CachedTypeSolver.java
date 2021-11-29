package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.debug.Debug;

public class CachedTypeSolver extends CompositeTypeSolver {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String cacheName;
    private Cache<String, SymbolReference<ResolvedReferenceTypeDeclaration>> cache;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public CachedTypeSolver() {
        this(DEFAULT);
    }

    public CachedTypeSolver(String name) {
        super(name);
        this.cacheName = name;
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public CachedTypeSolver withCache() {
        withCache(DEFAULT);
        return this;
    }

    public CachedTypeSolver withCache(String cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    public CachedTypeSolver add(TypeSolver ts) {
        super.add(ts);
        return this;
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        // Cache<String, SymbolReference<ResolvedReferenceTypeDeclaration>> cache =
        //     CacheManager.getCache(this.cacheName);

        if (name.contains(".Collection") || name.equals("Collection"))
            Debug.nop();

        // the cache can be deleted!
        cache = CacheManager.getCache(this.cacheName);

        SymbolReference<ResolvedReferenceTypeDeclaration> ref = cache.get(name, () -> super.tryToSolveType(name));
        return ref;
    }
}
