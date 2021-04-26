package jext.sourcecode.resources.java;

import jext.java.FastJavaParser;
import jext.java.TypeRole;
import jext.name.Name;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Type;
import jext.sourcecode.resources.SourceCode;
import jext.sourcecode.resources.type.ImplementedType;
import jext.sourcecode.resources.type.ReferencedType;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JavaSourceCode extends SourceCode {

    private FastJavaParser parser;

    public JavaSourceCode(File file, Module module) {
        super(file, module);
        this.parser = new FastJavaParser(file);
    }

    @Override
    public Optional<File> getSourceRoot() {
        return parser.getSourceRoot();
    }

    @Override
    public List<Type> getTypes() {
        Name declaredType = parser.getType();
        if (declaredType == null)
            return Collections.emptyList();

        TypeRole role = parser.getRole();
        return Collections.singletonList(new ImplementedType(declaredType, role, this));
    }

    @Override
    public List<RefType> getUsedTypes() {
        List<Name> importedClasses = parser.getImportedClasses();
        if (importedClasses.isEmpty())
            return Collections.emptyList();

        return importedClasses.stream()
            .map(ReferencedType::new)
            .collect(Collectors.toList());
    }

    public FastJavaParser getParser() {
        return parser;
    }
}
