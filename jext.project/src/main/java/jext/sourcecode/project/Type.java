package jext.sourcecode.project;

import jext.name.Name;

/**
 * Type implementation.
 * In Java, a class|interface|enum definition
 */
public interface Type extends RefType {

    Name getNamespace();

    // ----------------------------------------------------------------------
    // Navigate
    // ----------------------------------------------------------------------

    Source getSource();

    Module getModule();

    String getDigest();

    String[] getModifiers();

}
