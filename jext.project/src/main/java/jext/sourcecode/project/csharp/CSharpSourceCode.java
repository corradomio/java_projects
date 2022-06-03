package jext.sourcecode.project.csharp;

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
import java.util.Set;

public class CSharpSourceCode extends SourceCode {

    private Set<Type> definedTypes;
    private Set<Type> usedTypes;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static Source newSource(File sourceFile, Module module) {
        String name = sourceFile.getName();

        if (name.endsWith(CSharpConstants.CSHARP_EXT))
            return new CSharpSourceCode(sourceFile, module);

        throw new ProjectException("Unsupported source file " + sourceFile);
    }

    private CSharpSourceCode(File file, Module module) {
        super(file, module);
    }

    @Override
    public Optional<String> getSourceRoot() {
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
