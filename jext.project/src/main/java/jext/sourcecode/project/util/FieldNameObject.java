package jext.sourcecode.project.util;

import jext.java.JavaUtils;
import jext.name.Name;
import jext.name.ObjectName;
import jext.sourcecode.project.FieldName;

public class FieldNameObject extends ObjectName implements FieldName {

    public static FieldNameObject of(String name) {
        return new FieldNameObject(name);
    }

    public static FieldNameObject of(Name parent, String name) {
        return of(JavaUtils.qualifiedName(parent.getFullName(), name));
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private FieldNameObject(String name) {
        super(name);
    }

    // public FieldNameObject(Name parent, String name) {
    //     super(parent, name);
    // }

}
