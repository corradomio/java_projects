package jext.buildtools.util;

import jext.buildtools.Dependency;

public class ModuleDependency implements Dependency {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Type getType() {
        return Type.MODULE;
    }
}
