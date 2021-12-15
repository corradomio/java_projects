package jext.sourcecode.project.util;

import jext.name.Name;
import jext.name.ObjectName;
import jext.sourcecode.project.FieldName;

public class FieldNameObject extends ObjectName implements FieldName {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public FieldNameObject(String name) {
        super(name);
    }

    public FieldNameObject(Name parent, String name) {
        super(parent, name);
    }

}
