package jext.javaparser.util;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import jext.javaparser.resolution.ReferencedTypeDeclaration;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeRegistry;
import jext.lang.JavaUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UnsolvedTypesRegistry implements TypeRegistry {

    private Map<String, ResolvedReferenceTypeDeclaration> symbols = new TreeMap<>();
    private Set<String> namespaces = new HashSet<>();

    public ResolvedReferenceTypeDeclaration get(String name) {
        return symbols.get(name);
    }

    @Override
    public boolean isNamespace(String name) {
        return namespaces.contains(name);
    }

    @Override
    public synchronized boolean put(String qualifiedName, int nTypeParameters) {
        ResolvedReferenceTypeDeclaration rrtd = symbols.get(qualifiedName);

        if (rrtd != null && (nTypeParameters == 0 || rrtd.getTypeParameters().size() == nTypeParameters))
            return false;

        symbols.put(qualifiedName, new ReferencedTypeDeclaration(qualifiedName, nTypeParameters));

        String namespace = JavaUtils.namespaceOf(qualifiedName);
        while(!namespace.isEmpty()) {
            if (namespaces.contains(namespace))
                break;

            namespaces.add(namespace);
            namespace = JavaUtils.namespaceOf(namespace);
        }
        return true;
    }
}
