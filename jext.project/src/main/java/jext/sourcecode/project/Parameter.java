package jext.sourcecode.project;

import jext.name.IdNamed;
import jext.name.Name;

public interface Parameter extends IdNamed {

    DeclType getType();

    Method  getOwnerMethod();
    RefType getOwnerType();

}
