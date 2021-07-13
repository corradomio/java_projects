package jext.sourcecode.project;

import java.util.List;

public interface Types {

    int getTypesCount();
    List<Type> getTypes();
    List<Type> getTypes(String moduleId);
    List<Type> getInterfaces(String typeId);

    Type   getType(String typeId);
    // Method getMethod(String methodId);
    // Field  getField(String fieldId);

    double getComplexity(double threshold);
}
