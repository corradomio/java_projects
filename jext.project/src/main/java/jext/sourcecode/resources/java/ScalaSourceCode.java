package jext.sourcecode.resources.java;

import jext.sourcecode.resources.SourceCode;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Type;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class ScalaSourceCode extends SourceCode {

    public ScalaSourceCode(File file, Module module) {
        super(file, module);
    }

    @Override
    public List<Type> getTypes() {
        return Collections.emptyList();
    }

    @Override
    public List<RefType> getUsedTypes() {
        return Collections.emptyList();
    }
}
