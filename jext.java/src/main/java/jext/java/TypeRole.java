package jext.java;

/**
 * Role of a type.
 *
 * A defined type can be a class, an interface, an enumeration or a primitive type.
 *
 * It is not always possible to decide the role of a declared type.
 * For example, in Java, it is possible to understand the role of a reference type
 * used in the definition of a class or an interface, based un the usage of the keywords
 * 'extends' and 'implements'.
 *
 * But, in the definition of a field, or a method in a class, it is not possible to
 * decide if the type specified is a class, an interface or an enumeration. It is
 * only possible to decide if the type is a primitive type, because the list of
 * primitive types is predefined.
 *
 */
public enum TypeRole {
    UNKNOWN,        // unknown role | reference type

    //PRIMITIVE,    // is a primitive (short|int|long|...|void)
    //VOID,         // as PRIMITIVE

    INTERFACE,      // is an interface
    CLASS,          // is a class
    ENUM,           // is an enumeration
    ANNOTATION,     // is an annotation

    //TYPE_PARAM,   // is a type parameter (used in class|interface|enum definition)

    // JavaParser types not supported (yet)
    //INTERSECTION,
    //UNION,
    //WILDCARD,
    //VARIABLE,
}
