package jext.sourcecode.project.maven;

import jext.maven.MavenCoords;
import jext.name.Name;

public class MavenName implements Name {

    private MavenCoords coords;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MavenName(String coords) {
        this(MavenCoords.of(coords));
    }

    public MavenName(MavenCoords coords) {
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
