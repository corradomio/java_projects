package jext.util.function;

import jext.debug.Debug;

import java.util.ArrayList;
import java.util.Collection;
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
            if (wc.test(text)) {
                return true;
            }
        return false;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public OrWildcards add(String pattern) {
        return add(new Wildcard(pattern));
    }

    public OrWildcards addAll(Collection<String> patterns) {
        patterns.forEach(this::add);
        return this;
    }

    public OrWildcards add(Wildcard wc) {
        wildcards.add(wc);
        return this;
    }
}
