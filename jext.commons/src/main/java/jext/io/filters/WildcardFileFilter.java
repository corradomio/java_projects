package jext.io.filters;

import jext.util.FileUtils;
import jext.util.PathUtils;
import jext.util.Wildcard;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class WildcardFileFilter implements FileFilter {

    private static class WildcardMatcher {

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

    private List<WildcardMatcher> matchers = new ArrayList<>();

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
    // Patterns
    // ----------------------------------------------------------------------

    public WildcardFileFilter addAll(List<String> patterns) {
        patterns.forEach(this::add);
        return this;
    }

    public WildcardFileFilter add(String pattern) {
        //
        boolean recursive = pattern.contains("/") || pattern.contains("**");

        WildcardMatcher m = new WildcardMatcher(pattern, recursive);
        matchers.add(m);
        return this;
    }

    // ----------------------------------------------------------------------
    // Override
    // ----------------------------------------------------------------------

    @Override
    public boolean accept(File file) {
        String path = FileUtils.getAbsolutePath(file);
        String name = file.getName();
        for (WildcardMatcher m : matchers)
            if (m.accept(path, name))
                return true;
        return false;
    }

    public boolean accept(String path) {
        String name = PathUtils.getName(path);
        for (WildcardMatcher m : matchers)
            if (m.accept(path, name))
                return true;
        return false;
    }

}
