package jext.scitools.und;

public class Reference {

    public static Reference of(com.scitools.understand.Reference uref) {
        return new Reference(uref);
    }

    public static Reference[] of(com.scitools.understand.Reference[] urefs) {
        Reference[] refs = new Reference[urefs.length];
        for(int i=0; i<urefs.length; ++i)
            refs[i] = of(urefs[i]);
        return refs;
    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private com.scitools.understand.Reference uref;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private Reference(com.scitools.understand.Reference uref) {
        this.uref = uref;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // MISSING
    // public Kind kind() {
    //     return Kind.of(uref.kind());
    // }

    // MISSING
    // public boolean isforward() {
    //     return uref.isforward()
    // }

    public Entity ent() {
        return Entity.of(uref.ent());
    }

    public Entity scope() {
        return Entity.of(uref.scope());
    }

    public InFile inFile() {
        return new InFile(uref.file(), uref.line(), uref.column());
    }
}
