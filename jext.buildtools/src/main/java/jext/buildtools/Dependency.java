package jext.buildtools;

public interface Library {

    enum Type {
        FILE,
        MAVEN
    }

    String getName();

    Type getType();
}
