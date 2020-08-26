package jext.buildtools;

public interface Name extends Comparable<Name> {

    Name getParent();

    String getName();

    String getParentName();

    String getFullname();
}
