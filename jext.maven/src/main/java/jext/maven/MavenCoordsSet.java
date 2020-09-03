package jext.maven;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

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
        String artifact = coords.getName();
        if (!artifacts.containsKey(artifact))
            artifacts.put(artifact, coords);
        else if (mode == Mode.FIRST)
            return;
        else if (artifacts.get(artifact).getVersion().compareTo(coords.getVersion()) < 0)
            artifacts.put(artifact, coords);
    }

    public boolean contains(MavenCoords coords) {
        String artifact = coords.getName();
        if (!artifacts.containsKey(artifact))
            return false;
        if (mode == Mode.FIRST)
            return true;

        MavenCoords stored = artifacts.get(artifact);
        if (stored.getVersion().compareTo(coords.getVersion()) < 0)
            return false;
        else
            return true;
    }

    public Stream<MavenCoords> stream() {
        return artifacts.values().stream();
    }
}
