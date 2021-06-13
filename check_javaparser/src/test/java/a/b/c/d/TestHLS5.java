package a.b.c.d;

import java.lang.reflect.Type;

public class TestHLS5 {
    private static void appendRecursiveTypes(final StringBuilder builder, final int[] recursiveTypeIndexes,
                                             final Type[] argumentTypes) {
        for (int i = 0; i < recursiveTypeIndexes.length; i++) {
            appendAllTo(builder.append('<'), ", ", argumentTypes[i].toString()).append('>');
        }
    }
    private static <T> StringBuilder appendAllTo(final StringBuilder builder, final String sep,
                                                 @SuppressWarnings("unchecked") final T... types) {
        return builder;
    }

}
