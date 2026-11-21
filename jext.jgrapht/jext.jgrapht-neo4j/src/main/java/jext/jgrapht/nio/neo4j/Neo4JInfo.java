package jext.jgrapht.nio.neo4j;

public class Neo4JInfo {

    public static Neo4JInfo of(String url, String username, String password, String refId) {
        return new Neo4JInfo(url, username, password, refId);
    }

    private final String url;
    private final String username;
    private final String password;
    private final String refId;

    private Neo4JInfo(String url, String username, String password, String refId) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.refId = refId;
    }

    public String getUsername() {
        return username;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getRefId() {
        return refId;
    }
}
