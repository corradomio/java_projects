package jext.nio.file;

import jext.util.Wildcard;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

class WildcardMatcher {

    private final Wildcard pattern;

    /**
     * Creates a new matcher with the given expression.
     *
     * @param expression
     *            wildcard expressions
     */
    public WildcardMatcher(String expression) {
        expression = expression.replace("\\", "/");
        pattern = new Wildcard(expression);
    }

    /**
     * Matches the given string against the expressions of this matcher.
     *
     * @param path  path to test
     * @return <code>true</code>, if the expression matches
     */
    public boolean matches(final String path) {
        return pattern.accept(path);
    }

}

public class WildcardFileFilter implements FileFilter {

    private List<WildcardMatcher> matchers = new ArrayList<>();

    public WildcardFileFilter addPattern(String pattern) {
        WildcardMatcher m = new WildcardMatcher(pattern);
        matchers.add(m);
        return this;
    }

    @Override
    public boolean accept(File file) {
        return accept(file.getAbsolutePath());
    }

    public boolean accept(String pathname) {
        pathname = pathname.replace("\\", "/");
        for (WildcardMatcher m : matchers)
            if (m.matches(pathname))
                return true;
        return false;
    }
}
