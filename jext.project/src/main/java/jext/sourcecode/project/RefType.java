package jext.sourcecode.project;

import jext.java.TypeRole;
import jext.name.IdNamed;
import jext.name.Name;

import java.util.List;

/**
 * Referenced type
 */
public interface RefType extends IdNamed {

    Project getProject();
    Library getLibrary();
    String  getLibraryId();

    Name getNamespace();

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

    boolean isCollection();
    List<RefType> getElements();

    /**
     * Type conversion
     */
    Type asType();

    /**
     *
     */
    int getTypeParametersCount();

    boolean isAnonymous();

}
