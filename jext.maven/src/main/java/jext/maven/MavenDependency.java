package jext.maven;

/*
    https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html

        Scope               Transitive
        compile             y
        provided            no
        runtime             no
        test                no
        system              no
        import              no
 */

public class MavenDependency implements Comparable<MavenDependency>, MavenConst {

    public MavenCoords coords;
    public final String scope;
    public final String noVersion;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public MavenDependency(MavenCoords coords) {
        this(coords, SCOPE_COMPILE);
    }

    public MavenDependency(MavenCoords coords, String scope) {
        this.coords = coords;
        this.scope = scope;
        this.noVersion = String.format("%s:%s", coords.groupId, coords.artifactId);
    }

    // public MavenDependency(String gid, String aid, String v, String scope) {
    //     this.coords = new MavenCoords(gid, aid, v);
    //     this.scope = scope;
    //     this.noVersion = String.format("%s:%s", gid, aid);
    // }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /** Check if the scope is 'compile' */
    public boolean scopeCompile() {
        return SCOPE_COMPILE.equals(scope);
    }

    // ----------------------------------------------------------------------
    // Comparison of two MavenDependency
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return coords.toString();
    }

    @Override
    public int compareTo(MavenDependency that) {
        return noVersion.compareTo(that.noVersion);
    }

    @Override
    public int hashCode() {
        return noVersion.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        MavenDependency that = (MavenDependency) obj;
        return noVersion.equals(that.noVersion);
    }
}
