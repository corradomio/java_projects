package jext.sourcecode.project;

import jext.java.TypeRole;
import jext.name.IdNamed;

/**
 * Referenced type
 */
public interface RefType extends IdNamed {

    /**
     * Role of the type
     *
     * Note: it is possible that the rule is unknown, for example because
     * the type is inside a library
     */
    TypeRole getRole();

    /**
     * Check if the type is a 'reftype', a type defined inside an
     * external library
     */
    boolean isType();

    /**
     * Type conversion
     */
    Type asType();

    /**
     *
     */
    // int getTypeParametersCount();

    /**
     * Retrieve the library that contains the implementation of the
     * reference type
     */
    Library getLibrary();

    String getLibraryId();

    Project getProject();
}
