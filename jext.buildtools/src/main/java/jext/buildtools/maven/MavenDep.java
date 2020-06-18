package jext.buildtools.maven;

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

public class MavenDep implements Comparable<MavenDep>, MavenConst {

    public MavenCoords coords;
    public final String scope;
    public final String noVersion;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public MavenDep(MavenCoords coords) {
        this.coords = coords;
        this.scope = SCOPE_COMPILE;
        this.noVersion = String.format("%s:%s", coords.groupId, coords.artifactId);
    }

    public MavenDep(String gid, String aid, String v, String scope) {
        this.coords = new MavenCoords(gid, aid, v);
        this.scope = scope;
        this.noVersion = String.format("%s:%s", gid, aid);
    }

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
        return noVersion;
    }

    @Override
    public int compareTo(MavenDep that) {
        return noVersion.compareTo(that.noVersion);
    }

    @Override
    public int hashCode() {
        return noVersion.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        MavenDep that = (MavenDep) obj;
        return noVersion.equals(that.noVersion);
    }
}
