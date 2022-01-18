package jext.sourcecode.project.util;

import jext.name.Name;
import jext.name.NamedObject;
import jext.name.ObjectName;
import jext.sourcecode.project.DeclType;
import jext.sourcecode.project.TypeParam;
import jext.util.LongHash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TypeParamImpl extends NamedObject implements TypeParam {

    private static List<DeclType> OBJECT_BOUND = Collections.singletonList(DeclaredType.OBJECT);
    private List<DeclType> bounds = new ArrayList<>();
    private String signature;

    public TypeParamImpl(Name typeName, String name, String signature) {
        super(new ObjectName(typeName, name));
        this.signature = String.format("%s::%s", getName().getFullName(), signature);
    }

    @Override
    public Name getName() {
        return super.getName();
    }

    @Override
    public List<DeclType> getBounds() {
        if (bounds.isEmpty())
            return OBJECT_BOUND;
        else
            return bounds;
    }

    @Override
    public String getSignature() {
        return signature;
    }

    // @Override
    // public String getDigest() {
    //     return LongHash.asString(name.getFullName(), signature);
    // }

    public void add(DeclType bound) {
        bounds.add(bound);
    }

}
