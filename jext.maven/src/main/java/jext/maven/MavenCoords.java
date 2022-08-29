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

    public static MavenCoords of(MavenCoords coords, String version) {
        return of(coords.groupId, coords.artifactId, version);
    }

    public static MavenCoords of(String gid, String aid, String v) {
        return new MavenCoords(gid, aid, v);
    }

    public static MavenCoords of(String name, String version) {
        String[] parts = name.split(":");
        if (parts.length == 1)
            return of("", name, version);
        if (parts.length == 2) {
            if (name.endsWith(version))
                return of(parts[0], parts[1]);
            else
                return of(parts[0], parts[1], version);
        }
        else {
            if (name.endsWith(version))
                return of(parts[0], parts[1]);
            else
                return of(parts[0], parts[1], version);
        }
    }

    public static MavenCoords of(String pathOrCoords) {
        if (pathOrCoords.contains("/") || pathOrCoords.contains("\\"))
            return fromPath(pathOrCoords);
        else
            return fromCoords(pathOrCoords);
    }

    private static MavenCoords fromCoords(String coords) {
        // <artifactId>
        // <groupId>:<artifactId>
        // <groupId>:<artifactId>:<packaging>
        // <groupId>:<artifactId>:<version>
        // <groupId>:<artifactId>:<packaging>:<version>
        String[] parts = coords.split(":");
        if (parts.length == 1)
            return of("", parts[0], "");
        else if (parts.length == 2)
            return of(parts[1], parts[0], "");
        else if (parts.length == 3 && PACKAGING_TYPES.contains(parts[2]))
            return of(parts[0], parts[1], "");
        else if (parts.length == 3)
            return of(parts[0], parts[1], parts[2]);
        else
            return of(parts[0], parts[1], parts[3]);
    }

    private static MavenCoords fromPath(String path) {
        // <groupId>/<artifactId>/<version>/<artifactId>-<version>.jar

        int sep;
        // remove <artifactId>-<version>.jar
        sep = path.lastIndexOf('/');
        path = path.substring(0, sep);
        // extract <version>
        sep = path.lastIndexOf('/');
        String version = path.substring(sep+1);
        path = path.substring(0, sep);
        // extract <artifactId>
        sep = path.lastIndexOf('/');
        String artifactId = path.substring(sep+1);
        path = path.substring(0, sep);
        String groupId = path.replace('/', '.');

        return of(groupId, artifactId, version);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public String groupId;
    public String artifactId;
    public String version;
    // private String name;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private MavenCoords(String gid, String aid, String v) {
        this.groupId = gid;
        this.artifactId = aid;
        this.version = v;
        // init();
        if (version.endsWith(SNAPSHOT))
            version = version.substring(0, version.length()-SNAPSHOT.length());
    }

    // private MavenCoords(MavenCoords coords, String v) {
    //     this.groupId = coords.groupId;
    //     this.artifactId = coords.artifactId;
    //     this.version = v;
    //     init();
    // }

    // private void init() {
    //     if (version.endsWith(SNAPSHOT))
    //         version = version.substring(0, version.length()-SNAPSHOT.length());
    // }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public String getName() {
        if (groupId.isEmpty())
            return artifactId;
        else
            return String.format("%s:%s", groupId, artifactId);
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
            return of(this, v);
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
        if (groupId.isEmpty() && !hasVersion())
            return artifactId;
        if (groupId.isEmpty())
            return String.format("%s:%s", artifactId, version);
        else
            return String.format("%s:%s:%s", groupId, artifactId, version);
    }

    @Override
    public int compareTo(MavenCoords that) {
        int cmp = this.groupId.compareTo(that.groupId);
        if (cmp == 0)
            cmp = this.artifactId.compareTo(that.artifactId);
        if (cmp == 0)
            cmp = this.version.compareTo(that.version);
        return cmp;
    }

    @Override
    public int hashCode() {
        return (groupId.hashCode()*31 + artifactId.hashCode())*31 + version.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        MavenCoords that = (MavenCoords) obj;
        return this.groupId.equals(that.groupId)
            && this.artifactId.equals(that.artifactId)
            && this.version.equals(that.version);
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
