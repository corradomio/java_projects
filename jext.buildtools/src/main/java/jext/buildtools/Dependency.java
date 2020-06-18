package jext.buildtools;

public interface Dependency {

    enum Type {
        FILE,
        MAVEN,
        MODULE
    }

    String getName();

    Type getType();
}
