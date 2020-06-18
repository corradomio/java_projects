package jext.buildtools.util;

import jext.buildtools.Dependency;
import jext.buildtools.maven.MavenCoords;

public class MavenDependency implements Dependency {

    private MavenCoords coords;

    public MavenDependency(MavenCoords coords) {
        this.coords = coords;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }
}
