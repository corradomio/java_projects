package jext.sourcecode.project.java.maven;

import jext.maven.MavenCoords;
import jext.name.Name;

public class MavenName implements Name {

    public static MavenName of(String aid, String gid, String v) {
        return new MavenName(MavenCoords.of(gid, aid, v));
    }

    public static MavenName of(String name, String version) {
        return new MavenName(MavenCoords.of(name, version));
    }

    public static MavenName of(String coords) {
        return new MavenName(MavenCoords.of(coords));
    }

    public static MavenName of(MavenCoords coords) {
        return new MavenName(coords);
    }

    // ----------------------------------------------------------------------
    // Private data
    // ----------------------------------------------------------------------

    private MavenCoords coords;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    // private MavenName(String coords) {
    //     this(MavenCoords.of(coords));
    // }

    private MavenName(MavenCoords coords) {
        this.coords = coords;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isRoot() {
        return true;
    }

    @Override
    public String getName() {
        return coords.getName();
    }

    @Override
    public String getFullName() {
        return coords.toString();
    }

    @Override
    public Name getParent() {
        return null;
    }

    @Override
    public String getParentName() {
        return null;
    }

    public String getVersion() {
        return coords.version;
    }

    // ----------------------------------------------------------------------
    // Override
    // ----------------------------------------------------------------------

    @Override
    public int compareTo(Name that) {
        return getFullName().compareTo(that.getFullName());
    }

    @Override
    public boolean equals(Object obj) {
        Name that = (Name) obj;
        return getFullName().equals(that.getFullName());
    }

    @Override
    public int hashCode() {
        return getFullName().hashCode();
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
