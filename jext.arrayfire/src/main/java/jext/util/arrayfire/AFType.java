package jext.util.arrayfire;

public enum AFType {
    f32,    // float32 == float
    c32,
    f64,    // float64 == double
    c64,
    b8,     // boolean == byte
    s32,    // int
    u32,    // int
    u8,     // byte == boolean
    s64,    // long
    s16,    // short
    u16,    // short
    f16     // float16
    ;

    public static int toType(Class clazz) {
        if (clazz == boolean.class)
            return b8.ordinal();
        if (clazz == byte.class)
            return u8.ordinal();
        if (clazz == int.class)
            return s32.ordinal();
        if (clazz == long.class)
            return s64.ordinal();
        if (clazz == float.class)
            return f32.ordinal();
        if (clazz == double.class)
            return f64.ordinal();
        else
            throw new AFException("Unsupported data type " + clazz);
    }

    public static Class fromType(int type) {
        switch(AFType.values()[type]) {
            case b8: return boolean.class;

            case u8: return byte.class;
            case s32: return int.class;
            case u32: return int.class;
            case s64: return long.class;

            case f32: return float.class;
            case f64: return double.class;

            default: throw new AFException("Unsupported data type " + type);
        }
    }
}
