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

    public final String groupId;
    public final String artifactId;
    public final String version;
    private String toString;

    public MavenCoords(String gid, String aid) {
        this.groupId = gid;
        this.artifactId = aid;
        this.version = NO_VERSION;
        init();
    }

    public MavenCoords(String gid, String aid, String v) {
        this.groupId = gid;
        this.artifactId = aid;
        if (v == null || PACKAGING_TYPES.contains(v))
            this.version = NO_VERSION;
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

    public MavenCoords(String coords) {
        String[] parts = coords.split(":");
        if (parts.length == 2) {
            groupId = parts[0];
            artifactId = parts[1];
            version = NO_VERSION;
        }
        else if (parts.length == 3) {
            groupId = parts[0];
            artifactId = parts[1];
            if (PACKAGING_TYPES.contains(parts[2]))
                version = NO_VERSION;
            else
                version = parts[2];
        }
        else {
            groupId = parts[0];
            artifactId = parts[1];
            version = parts[3];
        }
        init();
    }

    private void init() {
        if(hasVersion())
            toString = String.format("%s:%s:%s", groupId, artifactId, version);
        else
            toString = String.format("%s:%s", groupId, artifactId);
    }

    public String getName() {
        if (hasVersion())
            return String.format("%s-%s", artifactId, version).replace('.', '_');
        else
            return artifactId;
    }

    public boolean hasVersion() {
        return !version.isEmpty() && !isRange(version);
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
