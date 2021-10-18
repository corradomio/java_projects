package jext.sourcecode.project;

import jext.name.IdNamed;

public interface Field extends IdNamed {

    RefType getType();

    RefType getOwnerType();

}
