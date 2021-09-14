package jext.maven;

public interface MavenConst {
    String PARENT = "parent";

    String GROUP_ID = "groupId";
    String ARTIFACT_ID = "artifactId";
    String VERSION = "version";
        String NONE = "";
        String NO_ID = "unk";

    String PACKAGING = "packaging";
        String PACKAGING_TYPES = "jar,pom,maven-plugin,ejb,war,ear,rar";
        String PACKAGING_POM = "pom";
        String PACKAGING_JAR = "jar";
        String PACKAGING_AAR = "aar";
        String PACKAGING_BUNDLE = "bundle";

    String SCOPE = "scope";
        String SCOPE_COMPILE = "compile";
        String SCOPE_PROVIDED = "provided";
        String SCOPE_TEST = "test";
    String OPTIONAL = "optional";

    String SNAPSHOT = "-SNAPSHOT";
}
