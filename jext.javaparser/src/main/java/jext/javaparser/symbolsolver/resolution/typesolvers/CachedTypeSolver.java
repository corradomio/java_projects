package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.cache.Cache;
import jext.cache.CacheManager;

public class CachedTypeSolver extends CompositeTypeSolver {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String cacheName;

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
        withCache(this.cacheName);
        return this;
    }

    public CachedTypeSolver withCache(String cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    // ----------------------------------------------------------------------
    // tryToSolveType
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        Cache<String, SymbolReference<ResolvedReferenceTypeDeclaration>> cache =
            CacheManager.getCache(this.cacheName);
        return cache.get(name, () -> super.tryToSolveType(name));
    }
}
