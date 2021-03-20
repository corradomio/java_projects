package jext.sourcecode.project;

import jext.name.IdNamed;

public interface Parameter extends IdNamed {

    RefType getType();
    String getTypeId();

    Method getOwnerMethod();
    String getOwnerMethodId();

    RefType getOwnerType();
    String getOwnerTypeId();
}
