package jext.buildtools.simple;

import jext.buildtools.Libraries;
import jext.buildtools.Resources;
import jext.buildtools.Sources;
import jext.buildtools.util.BaseModule;

import java.io.File;

public class SimpleModule extends BaseModule {

    public SimpleModule(File moduleDir, SimpleProject project) {
        super(moduleDir, project);

    }

    @Override
    public Libraries getLibraries() {
        return null;
    }

    @Override
    public Resources getResources() {
        return null;
    }
}
