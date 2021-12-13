package jext.sourcecode.project.util;

import jext.name.Name;
import jext.name.ObjectName;
import jext.sourcecode.project.FieldName;

public class FieldNameObject extends ObjectName implements FieldName {

    private long hash;

    public FieldNameObject(String name) {
        super(name);
    }

    public FieldNameObject(String namespace, String name) {
        super(namespace, name);
    }

    public FieldNameObject(Name parent, String name) {
        super(parent, name);
    }

    @Override
    public long getHash() {
        return this.hash;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }

}
