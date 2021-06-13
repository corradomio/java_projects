package a.b.c.d;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestHLS4 {

    public static void main(String[] args) {
        Collection<Character> emptySet = Collections.emptySet();
        final Iterator<Character> emptySetIt = emptySet.iterator();
        assertThrows(NoSuchElementException.class, emptySetIt::next);
    }

    interface Executable {
        void execute() throws Throwable;
    }

    private static void assertThrows(Class<?> clazz, Executable executable) {

    }
}
