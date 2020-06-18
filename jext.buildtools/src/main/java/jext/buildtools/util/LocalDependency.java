package jext.buildtools.util;

import jext.buildtools.Dependency;

public class LocalDependency implements Dependency {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Type getType() {
        return Type.LOCAL;
    }
}
