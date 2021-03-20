package jext.sourcecode.resources.type;

import jext.java.TypeRole;
import jext.lang.JavaUtils;
import jext.logging.Logger;
import jext.name.Name;
import jext.name.ObjectName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.util.NamedObject;

public class ReferencedType extends NamedObject implements RefType {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final ReferencedType VOID = new ReferencedType(JavaUtils.VOID);
    public static final ReferencedType JAVA_LANG_VOID = new ReferencedType(JavaUtils.JAVA_LANG_VOID);
    public static final ReferencedType JAVA_LANG_OBJECT = new ReferencedType(JavaUtils.JAVA_LANG_OBJECT);
    public static final ReferencedType JAVA_LANG_CLASS = new ReferencedType(JavaUtils.JAVA_LANG_CLASS);
    public static final ReferencedType ARRAY = new ReferencedType(JavaUtils.ARRAY);

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected static Logger logger = Logger.getLogger(ReferencedType.class);

    public Name namespace;
    public int nTypeParams;
    public TypeRole role = TypeRole.UNKNOWN;
    public Library library;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ReferencedType(String fullName) {
        this(new ObjectName(fullName));
    }

    public ReferencedType(String fullName, Library library) {
        this(new ObjectName(fullName), library);
    }

    public ReferencedType(Name name) {
        this(name, null);
    }

    public ReferencedType(Name name, Library library) {
        super(name);
        this.library = library;
        this.role = TypeRole.UNKNOWN;
        this.namespace = guessNamespace();

        // DEBUG
        String qualifiedName = name.getFullName();
        if (qualifiedName.contains("<") || qualifiedName.contains("[") || qualifiedName.contains("\\") || qualifiedName.contains("?"))
            System.out.println("Invalid: " + qualifiedName);
        // if (qualifiedName.length() < 3)
        //     System.out.println("Invalid: " + qualifiedName);
    }

    private Name guessNamespace() {
        String fullname = getName().getFullName();
        int lst = fullname.length();
        int dot = fullname.lastIndexOf('.');
        while (dot != -1 && Character.isUpperCase(fullname.charAt(dot+1))) {
            lst = dot;
            dot = fullname.lastIndexOf('.', dot-1);
        }
        if (dot != -1) {
            String namespace = fullname.substring(0, lst);
            return new ObjectName(namespace);
        }
        else {
            return new ObjectName(JavaUtils.ROOT);
        }
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Name getNamespace() {
        return this.namespace;
    }

    @Override
    public boolean isType() { return !role.equals(TypeRole.UNKNOWN); }

    @Override
    public boolean isAnonymous() {
        return this.getName().getName().startsWith("Anonymous");
    }

    @Override
    public Type asType() {
        throw new ClassCastException();
    }

    @Override
    public int getTypeParametersCount() {
        return nTypeParams;
    }

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
