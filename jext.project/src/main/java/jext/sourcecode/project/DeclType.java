package jext.sourcecode.project;

import java.util.List;

public interface DeclType {

    String  getName();
    RefType getType();

    boolean isArray();
    int     getRank();

    boolean isTemplate();
    int            getTypeParamsCount();
    List<DeclType> getTypeParameters();

    long getHash();

}
