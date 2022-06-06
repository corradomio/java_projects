package jext.sourcecode.project.java;

import jext.sourcecode.project.java.util.FastJavaParser;
import jext.java.TypeRole;
import jext.name.Name;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.ProjectException;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.java.types.ImplementedType;
import jext.sourcecode.project.java.types.ReferencedType;
import jext.sourcecode.project.util.SourceCode;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaSourceCode extends SourceCode {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static Source newSource(File sourceFile, Module module) {
        String name = sourceFile.getName();

        if (name.endsWith(JavaConstants.JAVA_EXT))
            return new JavaSourceCode(sourceFile, module);

        throw new ProjectException("Unsupported source file " + sourceFile);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private FastJavaParser parser;

    private JavaSourceCode(File file, Module module) {
        super(file, module);
        this.parser = new FastJavaParser(file);
    }

    @Override
    public Optional<String> getSourceRoot() {
        Optional<File> sr = parser.getSourceRoot();
        return sr.map(value -> FileUtils.relativePath(module.getModuleHome(), value));
    }

    @Override
    public Set<RefType> getTypes() {
        Name declaredType = parser.getType();
        if (declaredType == null)
            return Collections.emptySet();

        TypeRole role = parser.getRole();
        return Collections.singleton(new ImplementedType(declaredType, role, this));
    }

    @Override
    public Set<RefType> getUsedTypes() {
        List<Name> importedClasses = parser.getImportedClasses();
        if (importedClasses.isEmpty())
            return Collections.emptySet();

        return importedClasses.stream()
            .map(ReferencedType::new)
            .collect(Collectors.toSet());
    }

    public FastJavaParser getParser() {
        return parser;
    }
}
