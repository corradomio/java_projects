package jext.sourcecode.project;

import jext.name.IdNamed;

public interface Field extends IdNamed {

    FieldName getName();

    DeclType  getType();

    RefType   getOwnerType();

    String[]  getModifiers();

    String    getDeclaration();
    long      getDigest();

}
