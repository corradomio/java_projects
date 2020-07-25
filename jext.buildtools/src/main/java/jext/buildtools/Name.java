package jext.buildtools;

public interface Name extends Comparable<Name> {
    String getName();

    Name getParent();
    String getParentName();

    String getFullname();
}
