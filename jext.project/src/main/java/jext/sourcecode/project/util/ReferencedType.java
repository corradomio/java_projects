package jext.sourcecode.project.util;

import jext.java.TypeRole;
import jext.lang.JavaConstants;
import jext.lang.JavaUtils;
import jext.logging.Logger;
import jext.name.Name;
import jext.name.NamedObject;
import jext.name.ObjectName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.TypeParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferencedType extends NamedObject implements RefType, JavaConstants {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static ReferencedType VOID;
    public static ReferencedType NULL;
    public static ReferencedType JAVA_LANG_VOID;
    public static ReferencedType JAVA_LANG_OBJECT;

    //private static List<TypeParam> NO_TYPE_PARAMS = Collections.emptyList();;
    //private static List<TypeParam> ONE_TYPE_PARAM = Arrays.asList(new TypeParamImpl("T"));
    //private static List<TypeParam> TWO_TYPE_PARAMS = Arrays.asList(new TypeParamImpl("T1"), new TypeParamImpl("T2"));

    // ----------------------------------------------------------------------
    // Static fields
    // ----------------------------------------------------------------------

    private static final Map<String, ReferencedType> map = new HashMap<>();

    static {
        //
        // DOESN't change the order!
        //

        // initialized AS FIRST!
        // NO_TYPE_PARAMS = Collections.emptyList();

        VOID   = new ReferencedType(JavaConstants.VOID);
        NULL   = new ReferencedType(JavaConstants.NULL);
        JAVA_LANG_OBJECT = new ReferencedType(JavaConstants.JAVA_LANG_OBJECT);
        JAVA_LANG_VOID   = new ReferencedType(JavaConstants.JAVA_LANG_VOID);

        // ONE_TYPE_PARAM  = Collections.singletonList(JAVA_LANG_OBJECT);
        // TWO_TYPE_PARAMS = Arrays.asList(JAVA_LANG_OBJECT, JAVA_LANG_OBJECT);

        for (String primitiveType : JavaConstants.PRIMITIVE_TYPES) {
            map.put(primitiveType, new ReferencedType(primitiveType));
        }

        map.put(VOID.getName().getFullName(), VOID);
        map.put(JAVA_LANG_OBJECT.getName().getFullName(), JAVA_LANG_OBJECT);
        map.put(JAVA_LANG_VOID.getName().getFullName(), JAVA_LANG_VOID);
    }

    // ----------------------------------------------------------------------
    // Static constructor
    // ----------------------------------------------------------------------

    public static ReferencedType of(String typeName) {
        ReferencedType rtype = map.get(typeName);
        if (rtype == null)
            rtype = new ReferencedType(typeName);
        return rtype;
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected static Logger logger = Logger.getLogger(ReferencedType.class);

    public Name namespace;
    public TypeRole role;
    public Library library;
    private List<RefType> elements = Collections.emptyList();
    private List<TypeParam> tparams;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ReferencedType(String qualifiedName) {
        this(qualifiedName, 0, null);
    }

    public ReferencedType(String qualifiedName, Library library) {
        this(qualifiedName, 0, library);
    }

    public ReferencedType(String qualifiedName, int nTypeParameters, Library library) {
        this(new ObjectName(qualifiedName), nTypeParameters, library);
    }

    public ReferencedType(Name name) {
        this(name, 0, null);
    }

    public ReferencedType(Name name, Library library) {
        this(name, 0, library);
    }

    public ReferencedType(Name name, int nTypeParams, Library library) {
        super(name);
        this.library = library;
        this.role = TypeRole.UNKNOWN;
        this.namespace = guessNamespace();

        // DEBUG
        String fullname = name.getFullName();
        if (fullname.contains("<") || fullname.contains("[") || fullname.contains("\\") || fullname.contains("?"))
            Logger.getLogger(ReferencedType.class).errorf("Invalid: %s", fullname);
        // if (JavaUtils.isPrimitive(fullname))
        //     setNameWithId(new ObjectName(JavaUtils.boxed(fullname)));

        // add the type parameters. Es: Map<Object,Object>
        if (nTypeParams == 0) {
            this.tparams = Collections.emptyList();
        }
        else {
            this.tparams = new ArrayList<>();
            for (int i=0; i<nTypeParams; ++i) {
                String tname = String.format("T%d", i + 1);
                this.tparams.add(new TypeParamImpl(name, tname, tname));
            }
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
        return tparams.size();
    }

    @Override
    public List<TypeParam> getTypeParameters() {
        return tparams;
    }

    @Override
    public TypeRole getRole() {
        return role;
    }

    @Override
    public Library getLibrary() {
        return library;
    }

    // @Override
    // public String getLibraryId() {
    //     if (library != null)
    //         return library.getId();
    //     else
    //         return null;
    // }

    @Override
    public Project getProject() {
        return library.getProject();
    }

    // @Override
    // public boolean isTemplateType() {
    //     return nTypeParams > 0;
    // }

    // @Override
    // public List<RefType> getTypeParameters() {
    //     switch (nTypeParams) {
    //         case 0:
    //             return NO_TYPE_PARAMS;
    //         case 1:
    //             return ONE_TYPE_PARAM;
    //         case 2:
    //             return TWO_TYPE_PARAMS;
    //         default:
    //             List<RefType> typeParams = new ArrayList<>();
    //             for (int i=0; i<nTypeParams; ++i)
    //                 typeParams.add(JAVA_LANG_OBJECT);
    //             return typeParams;
    //     }
    // }

    // --

    // @Override
    // public boolean isArrayType() {
    //     return false;
    // }

    // @Override
    // public int getRank() {
    //     return 0;
    // }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public void add(RefType refType) {
        if (elements == null || elements.size() == 0)
            elements = new ArrayList<>();
        elements.add(refType);
    }

    @Override
    public String toString() {
        return this.getName().getFullName();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
