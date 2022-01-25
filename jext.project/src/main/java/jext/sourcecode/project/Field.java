package jext.sourcecode.project;

import jext.name.IdNamed;

import java.util.Set;

public interface Field extends IdNamed {

    FieldName getName();

    DeclType  getType();

    RefType   getOwnerType();

    Set<String> getModifiers();

    String    getDeclaration();
    long      getDigest();

}
