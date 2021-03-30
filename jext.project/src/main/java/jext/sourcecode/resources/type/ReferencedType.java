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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReferencedType extends NamedObject implements RefType {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final ReferencedType JAVA_LANG_NULL = new ReferencedType(JavaUtils.JAVA_LANG_NULL);
    public static final ReferencedType JAVA_LANG_VOID = new ReferencedType(JavaUtils.JAVA_LANG_VOID);
    public static final ReferencedType JAVA_LANG_OBJECT = new ReferencedType(JavaUtils.JAVA_LANG_OBJECT);
    public static final ReferencedType JAVA_LANG_CLASS = new ReferencedType(JavaUtils.JAVA_LANG_CLASS);
    // public static final ReferencedType ARRAY = new ReferencedType(JavaUtils.ARRAY);

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected static Logger logger = Logger.getLogger(ReferencedType.class);

    public Name namespace;
    public int nTypeParams;
    public TypeRole role;
    public Library library;
    private List<RefType> elements = new ArrayList<>();

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
        String fullname = name.getFullName();
        if (fullname.contains("<") || fullname.contains("[") || fullname.contains("\\") || fullname.contains("?"))
            Logger.getLogger(ReferencedType.class).errorf("Invalid: %s", fullname);
        if (JavaUtils.isPrimitive(fullname)) {
            setName(JavaUtils.boxed(fullname));
        }
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
    public boolean isCollection() {
        return !elements.isEmpty();
    }

    @Override
    public List<RefType> getElements() {
        return elements;
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

    public void add(RefType refType) {
        elements.add(refType);
    }

    @Override
    public String toString() {
        return this.getName().getFullName();
    }

}
