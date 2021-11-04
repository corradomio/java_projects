package jext.sourcecode.project;

import jext.name.IdNamed;
import jext.name.Name;

import java.util.List;
import java.util.Set;

public interface Method extends IdNamed {

    String CLASS_INITIALIZER  = "$classInitializer";
    String STATIC_INITIALIZER = "$staticInitializer";

    String getId();
    Name getName();

    MethodName getMethodName();

    String[] getModifiers();

    // Set<String> getModifiers();
    // String      getVisibility();
    // Set<String> getStructure();

    boolean isStatic();

    List<Parameter> getParameters();

    String getSignature();
    String getDeclaration();
    String getDigest();

    RefType getOwnerType();

    ReturnType getReturnType();

}
