package jext.javaparser.symbolsolver.resolution.typesolvers;

public interface TypeRegistry {
    boolean register(String qualifiedName, int nTypeParameters);
}
