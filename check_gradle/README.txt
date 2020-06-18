https://services.gradle.org/distributions/


Maven file structure
    https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html
    https://maven.apache.org/resolver/maven-resolver-api/scm.html

    <module>
        pom.xml
        README.txt
        ...
        src/main/<language>>
                /resources
                /filters
                /webapps
           /test/<language>>
                /resources
                /filters
           /it
           /site
           /assembly

        <submodule>

Gradle

    <module>
        build.gradle
        settings.gradle
        gradle.properties?
        gradle?
            ...
        buildSrc?
            build.gradle?
            src/main/<language>
            src/test/<language>
        src/main/<language>

----------------------------------------

Specify a file set:

    <fileset dir