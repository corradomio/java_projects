package jext.sourcecode.project;

import jext.name.IdNamed;

public interface Field extends IdNamed {

    FieldName getName();

    // RefType getType();
    // int getRank();
    DeclType getType();

    RefType getOwnerType();
}
