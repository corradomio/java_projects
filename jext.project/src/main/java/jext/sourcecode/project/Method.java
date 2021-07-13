package jext.sourcecode.project;

import jext.name.IdNamed;

import java.util.List;
import java.util.Set;

public interface Method extends IdNamed {

    String CLASS_INITIALIZER  = "$classInitializer";
    String STATIC_INITIALIZER = "$staticInitializer";

    MethodName getMethodName();

    Set<String> getModifiers();
    String getVisibility();
    Set<String> getStructure();

    RefType getType();
    String  getTypeId();

    List<Parameter> getParameters();

    RefType getOwnerType();
    String  getOwnerTypeId();

    String getSignature();
    String getDeclaration();

    long getDigest();
}
