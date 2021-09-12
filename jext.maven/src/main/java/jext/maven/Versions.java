package jext.maven;

import java.util.TreeSet;

public class Versions {

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

    public Version getLatestVersion() {
        if (isEmpty())
            return Version.NO_VERSION;
        else
            return versions.last();
    }

}
