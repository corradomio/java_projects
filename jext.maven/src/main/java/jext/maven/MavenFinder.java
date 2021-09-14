package jext.maven;

public class MavenFinder {

    private static final MavenFinder instance = new MavenFinder();

    public static MavenFinder getInstance() {
        return instance;
    }

    private static final String SEARCH_MAVEN_ORG_URL = "https://search.maven.org/solrsearch/select";

    public Object find(String name, String version) {
        return find(MavenCoords.of(name, version));
    }

    public Object find(MavenCoords coords) {
        return null;
    }
}
