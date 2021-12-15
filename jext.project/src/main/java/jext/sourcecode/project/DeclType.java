package jext.sourcecode.project;

import jext.name.IdNamed;

import java.util.List;

public interface DeclType extends IdNamed {

    String getSignature();

    RefType getType();

    boolean isArray();
    int     getRank();

    boolean isTemplate();
    int            getTypeParamsCount();
    List<DeclType> getTypeParameters();

    // long getHash();

}
