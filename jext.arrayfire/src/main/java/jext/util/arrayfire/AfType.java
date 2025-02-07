package jext.util.arrayfire;

public enum AfType {
    f32,
    c32,
    f64,
    c64,
    b8,
    s32,
    u32,
    u8,
    s64,
    s16,
    u16,
    f16
    ;

    public static int toType(Class clazz) {
        if (clazz == byte.class)
            return b8.ordinal();
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

    public static AfType fromType(int type) {
        return AfType.values()[type];
    }
}
