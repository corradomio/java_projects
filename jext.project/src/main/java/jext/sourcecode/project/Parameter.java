package jext.sourcecode.project;

import jext.name.IdNamed;
import jext.name.Name;

public interface Parameter extends IdNamed {

    String getId();
    Name getName();

    RefType getType();

    Method getOwnerMethod();

    RefType getOwnerType();

    int getRank();
}
