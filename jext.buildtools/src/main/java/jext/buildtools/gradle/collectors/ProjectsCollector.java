package jext.buildtools.gradle.collectors;

import jext.io.LineOutputStream;
import jext.buildtools.util.LogDigester;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;

/*
    Root project 'hibernate-orm'
    +--- Project ':documentation' - The Hibernate ORM documentation module
    +--- Project ':hibernate-agroal' - Integration for Agroal as a ConnectionProvider for Hibernate ORM
    +--- Project ':hibernate-c3p0' - Integration for c3p0 Connection pooling into Hibernate ORM
    +--- Project ':hibernate-core' - Hibernate's core ORM functionality
    +--- Project ':hibernate-ehcache' - Integration for using Ehcache 2.x as a Hibernate second-level-cache provider
    +--- Project ':hibernate-enhance-maven-plugin' - Enhance Plugin of the Hibernate project for use with Maven build system.
    +--- Project ':hibernate-entitymanager' - (deprecated - use hibernate-core instead) Hibernate O/RM implementation of the JPA specification
    +--- Project ':hibernate-envers' - Hibernate's entity version (audit/history) support
    +--- Project ':hibernate-graalvm' - Experimental extension to make it easier to compile applications into a GraalVM native image
    +--- Project ':hibernate-gradle-plugin' - Gradle plugin for integrating Hibernate functionality into your build
    +--- Project ':hibernate-hikaricp' - Integration for HikariCP into Hibernate O/RM
    +--- Project ':hibernate-infinispan' - (deprecated - use org.infinispan:infinispan-hibernate-cache-v53 instead)
    +--- Project ':hibernate-integrationtest-java-modules' - Integration tests for running Hibernate ORM in the Java module path
    +--- Project ':hibernate-java8' - (deprecated - use hibernate-core instead) Support for Java8-specific features - mainly Java8 Date/Time (JSR 310)
    +--- Project ':hibernate-jcache' - Integration for javax.cache into Hibernate as a second-level caching service
    +--- Project ':hibernate-jpamodelgen' - Annotation Processor to generate JPA 2 static metamodel classes
    +--- Project ':hibernate-osgi' - Support for running Hibernate O/RM in OSGi environments
    +--- Project ':hibernate-proxool' - Integration for Proxool Connection pooling into Hibernate O/RM
    +--- Project ':hibernate-spatial' - Integrate support for Spatial/GIS data into Hibernate O/RM
    +--- Project ':hibernate-testing' - Support for testing Hibernate ORM functionality
    +--- Project ':hibernate-vibur' - Integration for Vibur Connection pooling as a Hibernate ORM ConnectionProvider
    \--- Project ':release'
 */

public class ProjectsCollector extends LineOutputStream implements Iterable<String> {

    private String rootProject;
    private List<String> projects = new ArrayList<>();
    private LogDigester digester;

    private static final int ANALYZING = 1;

    public ProjectsCollector() {
        digester = new LogDigester();
        digester.addRule("Root project '([^']+)'", this::rootProject);
        digester.addRule(ANALYZING, "[\\\\ +-]+Project ':([^']+).*", this::retrieveProject);
        digester.addRule(ANALYZING, "", LogDigester.STATE_DONE);
    }

    private int rootProject(int status, Matcher matcher, String line) {
        rootProject = matcher.group(1);
        return ANALYZING;
    }

    private int retrieveProject(int status, Matcher matcher, String line) {
        String project = matcher.group(1);
        projects.add(project);
        return 0;
    }

    @Override
    public void consume(String line) {
        digester.consume(line);
    }

    @Override
    public Iterator<String> iterator() {
        return projects.iterator();
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        projects.forEach(action);
    }

}