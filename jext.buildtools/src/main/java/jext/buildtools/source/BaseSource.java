package jext.buildtools.source;

import jext.buildtools.Module;
import jext.buildtools.Source;
import jext.buildtools.resource.BaseResource;

import java.io.File;

public abstract class BaseSource extends BaseResource implements Source {

    protected BaseSource(File file, Module module) {
        super(file, module);
    }

    @Override
    public String getLanguage() {
        String name = file.getName();
        int pos = name.lastIndexOf('.');
        return name.substring(pos+1);
    }
}
