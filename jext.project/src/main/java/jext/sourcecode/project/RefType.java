package jext.sourcecode.project;

import jext.java.TypeRole;
import jext.name.IdNamed;
import jext.name.Name;

import java.util.List;

/**
 * Referenced type
 */
public interface RefType extends IdNamed {

    String getId();
    Name getName();

    Project getProject();
    Library getLibrary();

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
    Type asType();

    boolean isCollection();
    List<RefType> getElements();

    int getTypeParametersCount();

    boolean isAnonymous();

}
