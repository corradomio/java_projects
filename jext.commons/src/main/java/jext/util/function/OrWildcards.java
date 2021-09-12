package jext.util.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OrWildcards implements Predicate<String> {

    private final List<Wildcard> wildcards = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public OrWildcards() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public boolean isEmpty() {
        return wildcards.isEmpty();
    }

    @Override
    public boolean test(String text) {
        for (Wildcard wc : wildcards)
            if (wc.test(text))
                return true;
        return false;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void add(String pattern) {
        add(new Wildcard(pattern));
    }

    public void add(Wildcard wc) {
        wildcards.add(wc);
    }
}
