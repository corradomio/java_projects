package jext.sourcecode.project.java.libraries;

import jext.name.Name;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.java.AndroidConstants;
import jext.sourcecode.project.java.types.ReferencedType;

import java.io.File;
import java.util.Set;

import static jext.sourcecode.project.java.JavaConstants.PRIMITIVE_TYPES;

public class JDKLibrary extends DirectoryLibrary {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JDKLibrary(String libraryName, String version, File libraryFile) {
        super(libraryName, version, libraryFile);
    }

    // public JDKLibrary(String libraryName, File libraryFile, Project project) {
    //     super(libraryName, libraryFile, project);
    // }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean contains(Name typeName) {
        if (PRIMITIVE_TYPES.contains(typeName.getFullName()))
            return true;
        else
            return super.contains(typeName);
    }

    @Override
    public String getVersion() {
        String name = getName().getName().toLowerCase();
        return getVersion(name);
    }

    public static String getVersion(String name) {
        if (name.startsWith("jdk"))
            return name.substring(3) + ".0.0";
        if (name.startsWith("adk"))
            return name.substring(3) + ".0.0";
        else
            return AndroidConstants.ANDROID_VERSIONS.getOrDefault(name, "");
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    protected Set<RefType> collectTypes() {
        Set<RefType> types = super.collectTypes();

        for(String primitiveType : PRIMITIVE_TYPES)
            types.add(new ReferencedType(primitiveType, JDKLibrary.this));

        types.add(ReferencedType.JAVA_LANG_OBJECT);
        types.add(ReferencedType.VOID);

        return types;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
