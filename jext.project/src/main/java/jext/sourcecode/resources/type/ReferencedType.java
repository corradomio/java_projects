package jext.sourcecode.resources.type;

import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.util.NamedObject;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Type;
import jext.java.TypeRole;
import jext.sourcecode.project.util.ObjectName;
import jext.java.JavaUtils;
import jext.logging.Logger;

public class ReferencedType extends NamedObject implements RefType {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final ReferencedType JAVA_LANG_VOID = new ReferencedType(JavaUtils.JAVA_LANG_VOID);
    public static final ReferencedType JAVA_LANG_OBJECT = new ReferencedType(JavaUtils.JAVA_LANG_OBJECT);
    public static final ReferencedType JAVA_LANG_CLASS = new ReferencedType(JavaUtils.JAVA_LANG_CLASS);
    public static final ReferencedType ARRAY = new ReferencedType(JavaUtils.ARRAY);

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected static Logger logger = Logger.getLogger(ReferencedType.class);

    public int nTypeParams;
    public TypeRole role = TypeRole.UNKNOWN;
    public Library library;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ReferencedType(Name name, Library library) {
        super(name);
        this.library = library;
        this.role = TypeRole.UNKNOWN;
    }

    public ReferencedType(String fullName, Library library) {
        super(new ObjectName(fullName));
        this.library = library;
        this.role = TypeRole.UNKNOWN;
    }

    public ReferencedType(String fullName) {
        super(new ObjectName(fullName));
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isType() { return !role.equals(TypeRole.UNKNOWN); }

    @Override
    public Type asType() {
        throw new ClassCastException();
    }

    // @Override
    // public int getTypeParametersCount() {
    //     return nTypeParams;
    // }

    @Override
    public TypeRole getRole() {
        return role;
    }

    @Override
    public Library getLibrary() {
        return library;
    }

    @Override
    public String getLibraryId() {
        if (library != null)
            return library.getId();
        else
            return null;
    }

    @Override
    public Project getProject() {
        return library.getProject();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return this.getName().getFullName();
    }

}
