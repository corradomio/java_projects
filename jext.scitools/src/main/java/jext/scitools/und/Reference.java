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

    private com.scitools.understand.Reference ref;

    private Reference(com.scitools.understand.Reference ref) {
        this.ref = ref;
    }

    public Entity ent() {
        return Entity.of(ref.ent());
    }

    public Entity scope() {
        return Entity.of(ref.scope());
    }

    public InFile inFile() {
        return new InFile(ref.file(), ref.line(), ref.column());
    }
}
