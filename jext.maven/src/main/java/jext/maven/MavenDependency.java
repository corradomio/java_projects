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

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public MavenDependency(MavenCoords coords) {
        this(coords, SCOPE_COMPILE);
    }

    public MavenDependency(MavenCoords coords, String scope) {
        this.coords = coords;
        this.scope = scope;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /** Check if the scope is 'compile' */
    public boolean isCompile() {
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
        int cmp = this.coords.groupId.compareTo(that.coords.groupId);
        if (cmp == 0)
            cmp = this.coords.artifactId.compareTo(that.coords.artifactId);
        return cmp;
    }

    @Override
    public int hashCode() {
        return this.coords.groupId.hashCode()*31 + this.coords.artifactId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        MavenDependency that = (MavenDependency) obj;
        return this.coords.groupId.equals(that.coords.groupId)
            && this.coords.artifactId.equals(that.coords.artifactId);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
