package jext.sourcecode.project;

import jext.name.IdNamed;
import jext.name.Name;

import java.util.List;
import java.util.Set;

public interface Method extends IdNamed {

    String CLASS_INITIALIZER  = "$classInitializer";
    String STATIC_INITIALIZER = "$staticInitializer";

    // it returns a 'MethodName' a specialized version of 'Name' containing information
    // on method signature
    MethodName getName();

    String getSignature();
    String getDeclaration();
    String getDigest();

    String[] getModifiers();
    boolean isStatic();

    int getNumOfParams();
    List<Parameter> getParameters();

    RefType  getOwnerType();
    DeclType getReturnType();

}
