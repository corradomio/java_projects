package jext.io.file;

import jext.util.function.Wildcard;

/**
 * Match a directory against a pattern.
 *
 */
public class FilePattern {

    public final boolean recursive;
    private final Wildcard wcard;
    // private final String pattern;

    public FilePattern(String pattern) {
        this.recursive = pattern.contains("/") || pattern.contains("**");
        this.wcard = new Wildcard(pattern);
        // this.pattern = pattern;
    }

    public boolean accept(String text) {
        // String name = text.replace("\\", "/");
        // if (recursive) {
        //     int sep = name.lastIndexOf('/');
        //     name = name.substring(sep+1);
        // }
        // return wcard.accept(name);

        // if (pattern.startsWith("/") && !text.startsWith("/"))
        //     text = "/" + text;

        return wcard.accept(text);
    }
}
