package ae.ac.ebtic.sql.bt.btproxy;

import java.util.ArrayList;

public abstract class ArrayUtils<T> extends ArrayList<T> {

    public static <T> void set(ArrayList<T> array, int index, T value) {
        checkIndex(array, index);
        array.set(index, value);
    }

    private static <T> void checkIndex(ArrayList<T> array, int index) {
        while (index >= array.size())
            array.add(null);
    }
}
