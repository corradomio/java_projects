package jext.nio.file;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class WildcardMatcher {

    private final Pattern pattern;

    /**
     * Creates a new matcher with the given expression.
     *
     * @param expression
     *            wildcard expressions
     */
    public WildcardMatcher(final String expression) {
        String regex;
        if (expression.startsWith("re:"))
            regex = expression.substring(3);
        else
            regex = asRegex(expression);
        pattern = Pattern.compile(regex);
    }

    private static String asRegex(String expression) {
        final String[] parts = expression.split("\\:|\\|");
        final StringBuilder regex = new StringBuilder(expression.length() * 2);
        boolean next = false;
        for (final String part : parts) {
            if (next) {
                regex.append('|');
            }
            regex.append('(').append(toRegex(part)).append(')');
            next = true;
        }
        return regex.toString();
    }

    private static CharSequence toRegex(final String expression) {
        final StringBuilder regex = new StringBuilder(expression.length() * 2);
        for (final char c : expression.toCharArray()) {
            switch (c) {
                case '?':
                    regex.append(".");
                    break;
                case '*':
                    regex.append(".*");
                    break;
                default:
                    // regex.append(Pattern.quote(String.valueOf(c)));
                    regex.append(c);
                    break;
            }
        }
        return regex;
    }

    /**
     * Matches the given string against the expressions of this matcher.
     *
     * @param s
     *            string to test
     * @return <code>true</code>, if the expression matches
     */
    public boolean matches(final String s) {
        return pattern.matcher(s).matches();
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
        for (WildcardMatcher m : matchers)
            if (m.matches(pathname))
                return true;
        return false;
    }
}
