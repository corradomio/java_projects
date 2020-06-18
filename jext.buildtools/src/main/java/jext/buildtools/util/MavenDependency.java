package jext.buildtools.util;

import jext.buildtools.Dependency;
import jext.buildtools.maven.MavenCoords;

public class MavenDependency implements Dependency {

    private MavenCoords coord;

    public MavenDependency(MavenCoords coords) {
        this.coord = coords;
    }

    @Override
    public String getName() {
        return coord.toString();
    }

    @Override
    public Type getType() {
        return Type.MAVEN;
    }
}
