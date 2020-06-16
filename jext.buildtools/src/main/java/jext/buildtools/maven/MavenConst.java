package jext.buildtools.maven;

public interface MavenConst {
    String PARENT = "parent";

    String GROUP_ID = "groupId";
    String ARTIFACT_ID = "artifactId";
    String VERSION = "version";
        String NO_VERSION = "";
        String UNKNOWN = "unknown";

    String PACKAGING = "packaging";
        String PACKAGING_TYPES = "jar,pom,maven-plugin,ejb,war,ear,rar";
        String PACKAGING_POM = "pom";

    String SCOPE = "scope";
        String SCOPE_COMPILE = "compile";
}
