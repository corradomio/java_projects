package jext.sourcecode.project.util;

import jext.name.Name;
import jext.name.NamedObject;
import jext.sourcecode.project.DeclType;
import jext.sourcecode.project.RefType;
import jext.util.Assert;
import jext.util.LongHash;

import java.util.Collections;
import java.util.List;

public class DeclaredType extends NamedObject implements DeclType {

    public static DeclaredType VOID = of(ReferencedType.VOID);

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    public static DeclaredType of(RefType refType) {
        return of(refType, 0);
    }

    public static DeclaredType of(RefType refType, int rank) {
        return new DeclaredType(refType, rank);
    }

    public static DeclaredType of(RefType refType, int rank, String name) {
        return new DeclaredType(refType, rank, name);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String token;
    private final RefType refType;
    private final int rank;
    private List<DeclType> typeParams = Collections.emptyList();
    // private long hash;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private DeclaredType(RefType refType, int rank) {
        super(refType.getName());
        this.refType = refType;
        this.rank = rank;
        this.token = refType.getName().getName();
        for (int i=0; i<rank; ++i)
            this.token += "[]";
    }

    private DeclaredType(RefType refType, int rank, String token) {
        this(refType, rank);
        this.token = token;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Name getName() {
        return refType.getName();
    }

    @Override
    public String getSignature() {
        return token;
    }

    @Override
    public RefType getType() {
        return refType;
    }

    @Override
    public boolean isArray() {
        return rank > 0;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public boolean isTemplate() {
        return !typeParams.isEmpty();
    }

    @Override
    public int getTypeParamsCount() {
        return typeParams.size();
    }

    @Override
    public List<DeclType> getTypeParameters() {
        return typeParams;
    }

    // @Override
    // public long getHash() {
    //     if (hash != 0)
    //         return hash;
    //
    //     hash = LongHash.hash(refType.getName().getFullName());
    //     hash = LongHash.concat(rank, hash);
    //     for (DeclType typeParam : typeParams)
    //         hash = LongHash.concat(hash, typeParam.getHash());
    //     return hash;
    // }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void addAll(List<DeclType> typeParams) {
        if (!typeParams.isEmpty())
            this.typeParams = typeParams;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
