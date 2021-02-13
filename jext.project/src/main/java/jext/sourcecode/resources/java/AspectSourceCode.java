package jext.sourcecode.resources.java;

import jext.sourcecode.resources.SourceCode;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Type;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AspectSourceCode extends SourceCode {

    public AspectSourceCode(File file, Module module) {
        super(file, module);
    }

    @Override
    public Optional<File> getSourceRoot() {
        return Optional.empty();
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