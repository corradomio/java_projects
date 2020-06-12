package jext.javaparser.symbolsolver.namespacemodel;

import com.github.javaparser.resolution.types.ResolvedType;

public class NamespaceDeclaration implements ResolvedType {

    private String namespace;

    public NamespaceDeclaration(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String describe() {
        return namespace;
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isVoid() {
        return true;
    }
}

