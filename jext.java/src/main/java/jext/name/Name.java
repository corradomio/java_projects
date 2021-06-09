package jext.name;

/**
 * The 'name' of a object, composed by the namespace and the simple name.
 *
 * For a class/interface:   namespace + typeName
 * For a file:              directory + fileName
 *
 */
public interface Name extends Comparable<Name> {

    boolean isRoot();

    Name getParent();

    String getName();

    String getParentName();

    String getFullName();

    int hashCode();
}
