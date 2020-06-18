package jext.buildtools;

public interface Dependency {

    enum Type {
        LOCAL,
        MAVEN,
        MODULE
    }

    String getName();

    Type getType();
}
