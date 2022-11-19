package jext.io.filters;

import jext.util.FileUtils;
import jext.util.PathUtils;
import jext.util.function.Wildcard;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class WildcardFileFilter implements FileFilter, Predicate<String> {

    protected static class PrefixMatcher {
        private String prefix;

        private PrefixMatcher(String prefix) {
            this.prefix = prefix;
        }

        public boolean accept(String path) {
            return path.startsWith(prefix);
        }
    }

    protected static class WildcardMatcher {

        private final boolean recursive;
        private final Wildcard wildcard;

        /**
         * Creates a new matcher with the given pattern.
         * Supported patterns:
         *
         *      - .ext      file with the selected extension
         *      - .*        a name starting with a dot
         *      - if contains a '/', it is checked the file path
         *      - *         each directory name
         *      - **        each subdirectory
         *      - re:...    it is a regular expression
         *
         * @param pattern
         *            wildcard expressions
         */
        private WildcardMatcher(String pattern, boolean recursive) {
            this.recursive = recursive;
            this.wildcard = new Wildcard(pattern);
        }

        /**
         * Matches the given string against the expressions of this matcher.
         */
        public boolean accept(String path, String name) {
            if (recursive)
                return wildcard.accept(path);
            else
                return wildcard.accept(name);
        }

    }

    protected final List<PrefixMatcher> prefixes = new ArrayList<>();
    protected final List<WildcardMatcher> matchers = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public WildcardFileFilter() {

    }

    public WildcardFileFilter(String pattern) {
        add(pattern);
    }

    public WildcardFileFilter(List<String> patterns) {
        addAll(patterns);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public boolean isEmpty() {
        return matchers.isEmpty();
    }

    // ----------------------------------------------------------------------
    // Patterns
    // ----------------------------------------------------------------------

    public WildcardFileFilter addAll(Collection<String> patterns) {
        patterns.forEach(this::add);
        return this;
    }

    public WildcardFileFilter add(String pattern) {
        //
        boolean recursive = pattern.contains("/") || pattern.contains("**");

        matchers.add(new WildcardMatcher(pattern, recursive));
        prefixes.add(new PrefixMatcher(pattern));
        return this;
    }

    // ----------------------------------------------------------------------
    // Override
    // ----------------------------------------------------------------------

    @Override
    public boolean accept(File file) {
        String path = FileUtils.getAbsolutePath(file);
        String name = file.getName();
        return this.test(name) || this.test(path);
    }

    @Override
    public boolean test(String path) {
        // check for prefixes
        for (PrefixMatcher p : prefixes)
            if (p.accept(path))
                return true;

        // check for matchers
        String name = PathUtils.getName(path);
        for (WildcardMatcher m : matchers)
            if (m.accept(path, name))
                return true;

        // not matched
        return false;
    }

}
