package jext.maven;

/*
    <groupId>:<artifactId>
    <groupId>:<artifactId>:<version>
    <groupId>:<artifactId>:<packagingType>:<version>

    Sometimes:

    <groupId>:<artifactId>:<packagingType>

    <packagingType> ::= jar (default), pom, maven-plugin, ejb, war, ear, rar
 */

public class MavenCoords implements Comparable<MavenCoords>, MavenConst {

    public static MavenCoords of(String name, String version) {
        return new MavenCoords(name, version);
    }

    public static MavenCoords of(String coords) {
        return new MavenCoords(coords);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public  String groupId;
    public  String artifactId;
    public  String version;
    private String name;
    private String toString;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private MavenCoords(String coords) {
        if (coords.contains(":"))
            parseCoords(coords);
        else
            parsePath(coords);
        init();
    }

    private void parseCoords(String coords) {
        // <groupId>:<artifactId>
        // <groupId>:<artifactId>:<packaging>
        // <groupId>:<artifactId>:<version>
        // <groupId>:<artifactId>:<packaging>:<version>
        String[] parts = coords.split(":");
        if (parts.length == 2) {
            groupId = parts[0];
            artifactId = parts[1];
            version = NONE;
        }
        else if (parts.length == 3) {
            groupId = parts[0];
            artifactId = parts[1];
            if (PACKAGING_TYPES.contains(parts[2]))
                version = NONE;
            else
                version = parts[2];
        }
        else {
            groupId = parts[0];
            artifactId = parts[1];
            // packaging = parts[2]
            version = parts[3];
        }
    }

    private void parsePath(String path) {
        // <groupId>/<artifactId>/<version>/<artifactId>-<version>.jar
        int sep;
        // remove <artifactId>-<version>.jar
        sep = path.lastIndexOf('/');
        path = path.substring(0, sep);
        // extract <version>
        sep = path.lastIndexOf('/');
        version = path.substring(sep+1);
        path = path.substring(0, sep);
        // extract <artifactId>
        sep = path.lastIndexOf('/');
        artifactId = path.substring(sep+1);
        path = path.substring(0, sep);
        groupId = path.replace('/', '.');
    }

    public MavenCoords(String gid, String aid) {
        this(gid, aid, null);
        init();
    }

    public MavenCoords(String gid, String aid, String v) {
        if (gid.contains(":") && v == null) {
            int pos = gid.indexOf(":");
            v = aid;
            aid = gid.substring(pos+1);
            gid = gid.substring(0, pos);
        }
        else if (v == null) {
            gid = NONE;
            aid = gid;
            v = aid;
        }

        this.groupId = gid;
        this.artifactId = aid;
        if (v == null || PACKAGING_TYPES.contains(v))
            this.version = NONE;
        else
            this.version = v;
        init();
    }

    public MavenCoords(MavenCoords coords, String v) {
        this.groupId = coords.groupId;
        this.artifactId = coords.artifactId;
        this.version = v;
        init();
    }

    private void init() {
        if (version.endsWith(SNAPSHOT))
            this.version = version.substring(0, version.length()-SNAPSHOT.length());
        this.name = String.format("%s:%s", groupId, artifactId);
        if(hasVersion())
            toString = String.format("%s:%s:%s", groupId, artifactId, version);
        else
            toString = String.format("%s:%s", groupId, artifactId);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public String getName() {
        return this.name;
    }

    public Version getVersion() {
        return hasVersion() ? Version.of(version) : Version.NO_VERSION;
    }

    public boolean hasVersion() {
        return !version.isEmpty() && !isRange(version) && !isPattern(version);
    }

    public boolean isRange() {
        return isRange(version);
    }

    public MavenCoords merge(String v) {
        if (!isValid(v))
            return this;
        else
            return new MavenCoords(this, v);
    }

    public MavenCoords merge(String gid, String aid, String v) {
        if (!isValid(gid)) gid = groupId;
        if (!isValid(aid)) aid = artifactId;
        if (!isValid(v)) v = version;
        return new MavenCoords(gid, aid, v);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return toString;
    }

    @Override
    public int compareTo(MavenCoords o) {
        return toString.compareTo(o.toString);
    }

    @Override
    public int hashCode() {
        return toString.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        MavenCoords that = (MavenCoords) obj;
        return toString.equals(that.toString);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public boolean isValid() {
        return isValid(groupId) && isValid(artifactId);
    }

    public static boolean isValid(String s){
        return s != null && !s.isEmpty() && !s.contains("${");
    }

    public static boolean isRange(String s){
        if (s == null) return false;
        return s.contains("[") || s.contains("(") || s.contains(",");
    }

    public static boolean isPattern(String s) {
        return s != null && s.contains("${");
    }

}
