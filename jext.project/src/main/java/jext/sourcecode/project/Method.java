package jext.sourcecode.project;

import jext.name.IdNamed;
import jext.name.Name;

import java.util.List;
import java.util.Set;

public interface Method extends IdNamed {

    String getId();
    Name getName();

    String CLASS_INITIALIZER  = "$classInitializer";
    String STATIC_INITIALIZER = "$staticInitializer";

    MethodName getMethodName();

    Set<String> getModifiers();
    String getVisibility();
    Set<String> getStructure();

    RefType getType();

    List<Parameter> getParameters();

    RefType getOwnerType();

    String getSignature();
    String getDeclaration();

    String getDigest();
}
