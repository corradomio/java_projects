package jext.sourcecode.project;

import jext.name.Name;

import java.util.List;
import java.util.Set;

/**
 * Type implementation.
 * In Java, a class|interface|enum definition
 */
public interface Type extends RefType {

    /** Related object ids */
    // String getSourceId();
    // String getModuleId();
    // String getModuleRefId();

    Name getNamespace();

    /** All Java modifiers */
    Set<String> getModifiers();
    /** Java modifier related to visibility (private, protected, public, package) */
    String getVisibility();
    /** Java modifiers related to structure (static, final, abstract, ...) */
    Set<String> getStructure();

    /**
     * Check if the type is a 'valid' type for analysis:
     * it has a specified role (role != UNKNOWN)
     */
    boolean isValid();
    boolean isInner();

    /**
     * List of
     * - all used types
     * - extended superclasses
     * - implemented interfaces
     * - used types in implementation
     */
    List<Type> getUseTypes(TypeUse useType, UseDirection direction, boolean recursive, boolean refTypes);

    // /**
    //  * List of fields defined in the type's implementation
    //  */
    // List<Field> getFields();

    // /**
    //  * List of defined methods
    //  */
    // List<Method> getMethods();

    // ----------------------------------------------------------------------
    // Navigate
    // ----------------------------------------------------------------------

    Source getSource();
    Module getModule();

}
