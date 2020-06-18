package jext.buildtools.util;

import jext.buildtools.Dependency;
import jext.buildtools.Module;

public class ModuleDependency implements Dependency {

    private Module module;

    private ModuleDependency(Module module) {
        this.module = module;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }
}
