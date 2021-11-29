package jext.javaparser.symbolsolver.resolution.typesolvers;

public interface TypeRegistry {
    boolean isNamespace(String name);
    boolean put(String qualifiedName, int nTypeParameters);
}
