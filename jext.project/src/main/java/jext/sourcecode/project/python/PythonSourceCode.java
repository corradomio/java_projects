package jext.sourcecode.project.python;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.ProjectException;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.util.SourceCode;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PythonSourceCode extends SourceCode {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static Source newSource(File sourceFile, Module module) {
        String name = sourceFile.getName();

        if (name.endsWith(PythonConstants.PYTHON_EXT))
            return new PythonSourceCode(sourceFile, module);

        throw new ProjectException("Unsupported source file " + sourceFile);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private PythonSourceCode(File file, Module module) {
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
