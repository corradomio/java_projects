package jext.sourcecode.project;

import jext.name.Name;

public interface MethodName extends Name {

    boolean hasSignature();

    String getSignature();

    int getNumOfParams();

}
