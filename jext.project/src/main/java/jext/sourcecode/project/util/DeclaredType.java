package jext.sourcecode.project.util;

import jext.sourcecode.project.DeclType;
import jext.sourcecode.project.RefType;
import jext.util.Assert;
import jext.util.LongHash;

import java.util.Collections;
import java.util.List;

public class DeclaredType implements DeclType {

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

    private String name;
    private final RefType refType;
    private final int rank;
    private List<DeclType> typeParams = Collections.emptyList();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private DeclaredType(RefType refType, int rank) {
        this.refType = refType;
        this.rank = rank;
        this.name = refType.getName().getName();
        if (this.name == null)
            Assert.nop();
        for (int i=0; i<rank; ++i)
            this.name += "[]";
    }

    private DeclaredType(RefType refType, int rank, String name) {
        this.refType = refType;
        this.rank = rank;
        this.name = name;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return name;
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

    @Override
    public long getHash() {
        long hash = LongHash.hash(refType.getName().getFullName());
        hash = LongHash.concat(rank, hash);
        for (DeclType typeParam : typeParams)
            hash = LongHash.concat(hash, typeParam.getHash());
        return hash;
    }

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
