package jext.buildtools.util;

import jext.buildtools.Library;
import jext.buildtools.maven.MavenCoords;

public class MavenLibrary implements Library {

    private MavenCoords coords;

    public MavenLibrary(MavenCoords coords) {
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
