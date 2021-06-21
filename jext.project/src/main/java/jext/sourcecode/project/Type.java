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
    String getSourceId();
    String getModuleId();
    String getModuleRefId();

    Name getNamespace();

    Set<String> getModifiers();
    String getVisibility();
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
    List<? extends Type> getUseTypes(TypeUse useType, UseDirection direction, boolean recursive, boolean refTypes);

    /**
     * List of fields defined in the type's implementation
     */
    List<? extends Field> getFields();

    /**
     * List of defined methods
     */
    List<? extends Method> getMethods();

    // ----------------------------------------------------------------------
    // Navigate
    // ----------------------------------------------------------------------

    Source getSource();
    Module getModule();

}
