package jext.buildtools;

public interface Dependency {

    enum Type {
        LOCAL,
        MAVEN,
        MODULE
    }

    default boolean isType(Type type) {
        return type == getType();
    }

    String getName();

    Type getType();
}
