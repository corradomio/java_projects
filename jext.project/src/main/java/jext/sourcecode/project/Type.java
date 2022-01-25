package jext.sourcecode.project;

import jext.name.Name;

import java.util.Set;

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

    long getDigest();

    Set<String> getModifiers();

}
