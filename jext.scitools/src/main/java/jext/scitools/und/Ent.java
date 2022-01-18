package jext.scitools.und;

import com.scitools.understand.Lexer;
import com.scitools.understand.UnderstandException;

public class Ent {

    public static Ent of(com.scitools.understand.Entity uent) {
        return new Ent(uent);
    }

    public static Ent[] of(com.scitools.understand.Entity[] uents) {
        Ent[] ents = new Ent[uents.length];
        for(int i=0; i<uents.length; ++i)
            ents[i] = Ent.of(uents[i]);
        return ents;
    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private com.scitools.understand.Entity uent;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private Ent(com.scitools.understand.Entity uent) {
        this.uent = uent;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int id() {
        return uent.id();
    }

    public String name() {
        return uent.name();
    }

    public String uniquename() {
        return uent.uniquename();
    }

    public String longname() {
        return uent.longname();
    }

    public Ref ref(String refkindstring , String entkindstring) {
        Ref[] refs = Ref.of(uent.refs(refkindstring, entkindstring , true));
        return refs == null || refs.length == 0 ? null : refs[0];
    }

    public Ref[] refs(String refkindstring , String entkindstring ) {
        return Ref.of(uent.refs(refkindstring, entkindstring , false));
    }

    public Kind kind() {
        return Kind.of(uent.kind());
    }

    public String[] ib(String s) {
        return uent.ib(s);
    }

    public String type() {
        return uent.type();
    }

    public String library() {
        return uent.library();
    }

    public String value() {
        return uent.value();
    }

    public String language() {
        return uent.language();
    }

    public String parameters() {
        return uent.parameters();
    }

    public String freetext(String s) {
        return uent.freetext(s);
    }

    public String[] comments(boolean b, boolean b1, String s) {
        return uent.comments(b, b1, s);
    }

    public Lexer lexer(boolean b, int i, boolean b1, boolean b2) throws UnderstandException {
        return uent.lexer(b, i, b1, b2);
    }

    public String contents() {
        return uent.contents();
    }
}
