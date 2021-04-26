package jext.sourcecode.project;

import jext.name.IdNamed;

import java.util.List;

public interface Method extends IdNamed {

    String CLASS_INITIALIZER  = "$classInitializer";
    String STATIC_INITIALIZER = "$staticInitializer";

    MethodName getMethodName();

    RefType getType();
    String  getTypeId();

    List<? extends Parameter> getParameters();

    RefType getOwnerType();
    String  getOwnerTypeId();

    String getSignature();
    String getDeclaration();
}
