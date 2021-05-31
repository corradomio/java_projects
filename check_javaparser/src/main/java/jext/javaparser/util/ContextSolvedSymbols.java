package jext.javaparser.util;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.resolution.ReferencedTypeDeclaration;

import java.util.HashMap;
import java.util.Map;

public class ContextSolvedSymbols {

    private Map<String, ResolvedReferenceTypeDeclaration>
        resolved = new HashMap<>();

    public synchronized void resolved(String qualifiedName) {
        resolved.put(qualifiedName, new ReferencedTypeDeclaration(qualifiedName));
    }

    public synchronized void resolved(ResolvedReferenceTypeDeclaration rdecl) {
        String qualifiedName = rdecl.getQualifiedName();
        resolved.put(qualifiedName, rdecl);
    }

    public synchronized SymbolReference<ResolvedReferenceTypeDeclaration>
    resolve(String symbol) {
        if (resolved.containsKey(symbol))
            return SymbolReference.solved(resolved.get(symbol));
        else
            return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }
}
