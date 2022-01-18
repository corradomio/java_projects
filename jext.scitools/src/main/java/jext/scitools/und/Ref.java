package jext.scitools.und;

public class Ref {

    public static Ref of(com.scitools.understand.Reference uref) {
        return new Ref(uref);
    }

    public static Ref[] of(com.scitools.understand.Reference[] urefs) {
        Ref[] refs = new Ref[urefs.length];
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

    private Ref(com.scitools.understand.Reference uref) {
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

    public Ent ent() {
        return Ent.of(uref.ent());
    }

    public Ent scope() {
        return Ent.of(uref.scope());
    }

    public InFile inFile() {
        return new InFile(uref.file(), uref.line(), uref.column());
    }
}
