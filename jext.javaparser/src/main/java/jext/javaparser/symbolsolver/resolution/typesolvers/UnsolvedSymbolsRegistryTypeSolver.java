package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;

public class UnsolvedSymbolsRegistryTypeSolver extends BaseTypeSolver implements TypeRegistry {

    private UnsolvedSymbolsRegistry usr;

    public UnsolvedSymbolsRegistryTypeSolver() {
        this(new UnsolvedSymbolsRegistry());
    }

    public UnsolvedSymbolsRegistryTypeSolver(UnsolvedSymbolsRegistry usr) {
        super(UnsolvedSymbolsRegistryTypeSolver.class.getName());
        this.usr = usr;
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        ResolvedReferenceTypeDeclaration rrtd = usr.get(name);
        if (rrtd != null)
            return SymbolReference.solved(rrtd);
        else
            return UNSOLVED;
    }

    @Override
    public boolean isNamespace(String name) {
        return usr.isNamespace(name);
    }

    @Override
    public boolean put(String qualifiedName, int nTypeParameters) {
        return usr.put(qualifiedName, nTypeParameters);
    }
}
