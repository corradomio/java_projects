package org.hls.primitives;

public class Modifiers {
    int packagedInt;
    private int privateInt;
    protected int protectedInt;
    public int publicInt;

    final int finalPackagedInt = 0;
    final private int finalPrivateInt = 0;
    final protected int finalProtectedInt = 0;
    final public int finalPublicInt = 0;

    static int staticPackagedInt;
    static private int staticPrivateInt;
    static protected int staticProtectedInt;
    static public int staticPublicInt;

    final static int finalStaticPackagedInt = 0;
    final static private int finalStaticPrivateInt = 0;
    final static protected int finalStaticProtectedInt = 0;
    final static public int finalStaticPublicInt = 0;

    int packagedInt(){ return 0; }
    private int privateInt(){ return 0; }
    protected int protectedInt(){ return 0; }
    public int publicInt(){ return 0; }

    final int finalPackagedInt(){ return 0; }
    final private int finalPrivateInt(){ return 0; }
    final protected int finalProtectedInt(){ return 0; }
    final public int finalPublicInt(){ return 0; }

    static int staticPackagedInt(){ return 0; }
    static private int staticPrivateInt(){ return 0; }
    static protected int staticProtectedInt(){ return 0; }
    static public int staticPublicInt(){ return 0; }

    final static int finalStaticPackagedInt(){ return 0; }
    final static private int finalStaticPrivateInt(){ return 0; }
    final static protected int finalStaticProtectedInt(){ return 0; }
    final static public int finalStaticPublicInt(){ return 0; }
}
