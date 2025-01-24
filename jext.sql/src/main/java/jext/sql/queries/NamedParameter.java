package jext.sql.queries;

public class NamedParameter {

    public final String name;
    public final int index;
    public final String type;
    public final boolean nullable;
    public final boolean scalar;

    public NamedParameter(String name, int index, String type, boolean nullable, boolean scalar) {
        this.name = name;
        this.index = index;
        this.type = type;
        this.nullable = nullable;
        this.scalar = scalar;
    }
}
