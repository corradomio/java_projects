package jext.scitools.und;

import com.scitools.understand.Kind;
import com.scitools.understand.Lexer;
import com.scitools.understand.UnderstandException;

public class Entity {

    public static Entity of(com.scitools.understand.Entity uent) {
        return new Entity(uent);
    }

    public static Entity[] of(com.scitools.understand.Entity[] uents) {
        Entity[] ents = new Entity[uents.length];
        for(int i=0; i<uents.length; ++i)
            ents[i] = Entity.of(uents[i]);
        return ents;
    }

    private com.scitools.understand.Entity ent;

    private Entity(com.scitools.understand.Entity ent) {
        this.ent = ent;
    }

    public int id() {
        return ent.id();
    }

    public String name() {
        return ent.name();
    }

    public String uniquename() {
        return ent.uniquename();
    }

    public String longname() {
        return ent.longname();
    }

    public Reference[] refs(String s, String s1, boolean b) {
        return Reference.of(ent.refs(s, s1, b));
    }

    public Kind kind() {
        return ent.kind();
    }

    public String[] ib(String s) {
        return ent.ib(s);
    }

    public String type() {
        return ent.type();
    }

    public String library() {
        return ent.library();
    }

    public String value() {
        return ent.value();
    }

    public String language() {
        return ent.language();
    }

    public String parameters() {
        return ent.parameters();
    }

    public String freetext(String s) {
        return ent.freetext(s);
    }

    public String[] comments(boolean b, boolean b1, String s) {
        return ent.comments(b, b1, s);
    }

    public Lexer lexer(boolean b, int i, boolean b1, boolean b2) throws UnderstandException {
        return ent.lexer(b, i, b1, b2);
    }

    public String contents() {
        return ent.contents();
    }
}
