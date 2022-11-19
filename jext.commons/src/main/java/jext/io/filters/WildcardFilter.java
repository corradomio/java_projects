package jext.io.filters;

import jext.util.PathUtils;

import java.io.File;

public class WildcardFilter extends WildcardFileFilter {

    public WildcardFilter(String pattern) {
        add(pattern);
    }

    @Override
    public boolean accept(File file) {
        String name = file.getName();
        return this.test(name);
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
