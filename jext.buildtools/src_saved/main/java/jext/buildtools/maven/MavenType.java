package jext.buildtools.maven;

public enum MavenType {
    METADATA,       // maven-metadata.xml
    POM,            // <...>.pom
    JAR,            // <...>.jar

    JAVADOC,        // <...>-javadoc.jar
    SOURCES,        // <...>-sources.jar
    ANNOTATIONS,    // <...>-annotations.jar

    VERSIONS,       // <...>.html
    //INVALID         // invalid  (used when it is not possible to find a library)
}
