package jext.sourcecode.project;

// import ae.ebtic.spl.analysis.common.EdgeDirection;
// import ae.ebtic.spl.analysis.components.Component;
// import ae.ebtic.spl.analysis.features.Feature;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Type implementation.
 * In Java, a class|interface|enum definition
 */
public interface Type extends RefType {

    // -- Source defining this type

    String getSourceId();
    Source getSource();

    // -- Module containing the source

    String getModuleId();
    Module getModule();

    /**
     * Check if the type is a 'valid' type for analysis:
     * it has a specified role (role != UNKNOWN)
     */
    boolean isValid();

    /**
     * List of
     * - all used types
     * - extended superclasses
     * - implemented interfaces
     * - used types in implementation
     */
    // List<Type> getUseTypes(TypeUse useType, EdgeDirection direction, boolean recursive, boolean refTypes);

    /**
     * List of fields defined in the type's implementation
     */
    // List<Field> getFields();

    /**
     * List of defined methods
     */
    // List<Method> getMethods();

    // ----------------------------------------------------------------------
    // Score
    // ----------------------------------------------------------------------

    /**
     * If present:
     *  score[0]: features-core
     *  score[1]: k-core
     */
    // @Nullable
    // List<Double> getScore();

    // ----------------------------------------------------------------------
    // Entrypoint
    // ----------------------------------------------------------------------

    /**
     * Check is this type is an "entryPoint" after the runtime analysis
     */
    // boolean isEntryPoint();

    // long[] getCountMethods();

    // /**
    //  * Set this type as an "entryPoint": during the "runtime analysis" there was
    //  * a thread containing this class as FIRST class in the thread dump
    //  */
    // void setEntryPoint();

    // ----------------------------------------------------------------------
    // Dependencies with other models
    // ----------------------------------------------------------------------

    /**
     * Components containing this type
     */
    // List<Component> getComponents();

    /**
     * Features containing this type (based on components contained in the features)
     */
    // List<Feature> getFeatures();

}
