package jext.sourcecode.resources.python;

import jext.python.PythonConstants;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Type;
import jext.sourcecode.resources.SourceCode;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PythonSourceCode extends SourceCode {

    public PythonSourceCode(File file, Module module) {
        super(file, module);
        this.language = PythonConstants.PYTHON;
    }

    @Override
    public Optional<String> getSourceRoot() {
        // return Optional.of(FileUtils.getAbsolutePath(module.getModuleHome()));
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
