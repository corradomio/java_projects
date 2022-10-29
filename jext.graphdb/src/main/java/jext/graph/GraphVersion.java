package jext.graph;

public class GraphVersion {

    private String dbversion;
    private int majorVersion;

    public GraphVersion(String dbversion) {
        this.dbversion = dbversion;
        if (this.dbversion.startsWith("4."))
            this.majorVersion = 4;
        else
            this.majorVersion = 3;
    }

    public String getVersion() {
        return dbversion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }
}
