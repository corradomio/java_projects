package jext.name;

public interface Name {

    boolean isRoot();
    String getName();

    Name getParent();
    String getParentName();
}
