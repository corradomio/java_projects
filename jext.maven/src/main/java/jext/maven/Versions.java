package jext.maven;

import java.util.TreeSet;

public class Versions {

    public enum Selector {
        EQUALS,         // = ==
        LESS_EQUAL,     // <=
        LESS_THAN,      // <
        GREATER_EQUAL,  // >=
        GREATER_THAN,   // >
        LATEST,         //
        NEAREST         // ~
    }

    public static Versions noVersions = new Versions();

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private TreeSet<Version> versions = new TreeSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Versions() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public boolean isEmpty() {
        return versions.isEmpty();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void add(String version) {
        versions.add(Version.of(version));
    }

    public Version select(String version, String sel) {
        if ("=".equals(sel) || "==".equals(sel))
            return select(version, Selector.EQUALS);
        if ("<".equals(sel))
            return select(version, Selector.LESS_THAN);
        if ("<=".equals(sel))
            return select(version, Selector.LESS_EQUAL);
        if (">".equals(sel))
            return select(version, Selector.GREATER_THAN);
        if (">=".equals(sel))
            return select(version, Selector.GREATER_EQUAL);
        if ("~".equals(sel))
            return select(version, Selector.NEAREST);
        else // ""
            return select(version, Selector.LATEST);
    }

    public Version select(String version, Selector sel) {
        if (isEmpty())
            return Version.NO_VERSION;

        Version ver = Version.of(version);
        if (sel == Selector.LATEST)
            return versions.last();

        // if ==ver or >=ver or <=ver and ver is present, return ver
        if ((sel == Selector.EQUALS || sel == Selector.GREATER_EQUAL || sel == Selector.LESS_EQUAL) && versions.contains(ver))
            return ver;

        // if ==ver but ver is not present, return nover
        if (sel == Selector.EQUALS)
            return Version.NO_VERSION;

        if (sel == Selector.GREATER_THAN || sel == Selector.GREATER_EQUAL) {
            Version selected = versions.ceiling(ver);
            return selected != null ? selected : Version.NO_VERSION;
        }
        else if (sel == Selector.LESS_THAN || sel == Selector.LESS_EQUAL) {
            Version selected = versions.floor(ver);
            return selected != null ? selected : Version.NO_VERSION;
        }
        else {
            Version selected = versions.ceiling(ver);
            if (selected.isEmpty())
                selected = versions.floor(ver);
            return selected;
        }
    }

    public Version getLatestVersion() {
        if (isEmpty())
            return Version.NO_VERSION;
        else
            return versions.last();
    }

}
