package jext.scitools.und;

public class Kind {

    public static Kind of(com.scitools.understand.Kind ukind) {
        return new Kind(ukind);
    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private com.scitools.understand.Kind ukind;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private Kind(com.scitools.understand.Kind ukind) {
        this.ukind = ukind;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public boolean check(String var1) {
        return ukind.check(var1);
    }

    public String name() {
        return ukind.name();
    }

}
