package jext.sourcecode.project;

public enum TypeUse {
    NONE,       // no type use defined
    ALL,        // EXTENDS or IMPLEMENTS or DEPENDS_ON
    HIERARCHY,  // EXTENDS or IMPLEMENTS
    EXTENDS,
    IMPLEMENTS,
    DEPENDS_ON,
    TEMPLATE,   // used in type parameters
    IMPLICIT
}
