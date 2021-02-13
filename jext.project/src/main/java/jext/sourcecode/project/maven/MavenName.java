package jext.sourcecode.project.maven;

import jext.name.Name;
import jext.maven.MavenCoords;

public class MavenName implements Name {

    private MavenCoords coords;


    public MavenName(MavenCoords coords) {
        this.coords = coords;
    }

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

    @Override
    public String[] getParts() {
        return coords.toString().split(":");
    }

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
        return coords.toString();
    }
}
