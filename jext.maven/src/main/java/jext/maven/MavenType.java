package jext.maven;

public enum MavenType {
    METADATA,       // maven-metadata.xml
    POM,            // <...>.pom
    ARTIFACT,       // <...>.jar | <...>.aar | <...>.<packaging>

    JAVADOC,        // <...>-javadoc.jar
    SOURCES,        // <...>-sources.jar
    ANNOTATIONS,    // <...>-annotations.jar

    VERSIONS,       // <...>.html
    //INVALID         // invalid  (used when it is not possible to find a library)
}
