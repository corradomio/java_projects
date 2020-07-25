package jext.buildtools.maven;

import java.util.Map;
import java.util.TreeMap;

/**
 * This class collect a list of maven coordinates but keep ONLY the one
 * instance of the maven coordinate based on two strategies
 *
 *      1) the first coordinate inserted
 *      2) the coordinate inserted qith the highest version
 *
 */
public class MavenCoordsSet {

    public enum Mode {
        FIRST,
        LATEST
    }

    private Mode mode;
    private Map<String, MavenCoords> artifacts = new TreeMap<>();

    public MavenCoordsSet() {
        this.mode = Mode.FIRST;
    }

    public MavenCoordsSet(Mode mode) {
        this.mode = mode;
    }

    public void add(MavenCoords coords) {
        String artifact = coords.getArtifact();
        if (!artifacts.containsKey(artifact))
            artifacts.put(artifact, coords);
        else if (mode == Mode.FIRST)
            return;
        else if (artifacts.get(artifact).getVersion().compareTo(coords.getVersion()) < 0)
                artifacts.put(artifact, coords);
    }
}
