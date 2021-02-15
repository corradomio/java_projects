package jext.sourcecode.project;

public enum ProjectType {
    AUTO("auto"),
    ANT("ant"),
    MAVEN("maven"),
    GRADLE("gradle"),
    ECLIPSE("eclipse"),
    SIMPLE("simple");

    private String name;

    ProjectType(String name) {
        this.name = name;
    }

    public static ProjectType toProjectType(String name) {
        if (name == null)
            return SIMPLE;
        if(name.equals(ANT.name))
            return ANT;
        if(name.equals(MAVEN.name))
            return MAVEN;
        if(name.equals(GRADLE.name))
            return GRADLE;
        if(name.equals(ECLIPSE.name))
            return ECLIPSE;
        else
            return SIMPLE;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
