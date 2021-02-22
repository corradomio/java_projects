package jext.sourcecode.project.gradle.collectors;

import jext.io.LineOutputStream;
import jext.util.LogDigester;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;

/*
    ------------------------------------------------------------
    Root project
    ------------------------------------------------------------

    [10:04 | d:\projects.github\other_projects\hibernate-orm]                                                                                                                                                                                                       No dependencies

    default - Configuration for default artifacts. (n)
    No dependencies

    derby - The JDBC dependency configuration for the [derby] profile
    \--- org.apache.derby:derby:10.11.1.1

    hana - The JDBC dependency configuration for the [hana] profile
    \--- com.sap.cloud.db.jdbc:ngdbc:2.4.59

    mariadb - The JDBC dependency configuration for the [mariadb] profile
    \--- org.mariadb.jdbc:mariadb-java-client:2.2.4

    mssqlserver - The JDBC dependency configuration for the [mssqlserver] profile
    \--- com.microsoft.sqlserver:mssql-jdbc:6.4.0.jre8

    oracle - The JDBC dependency configuration for the [oracle] profile
    \--- :ojdbc8 FAILED

    pgsql - The JDBC dependency configuration for the [pgsql] profile
    \--- org.postgresql:postgresql:42.2.2

    A web-based, searchable dependency report is available by adding the --scan option.

 */

public class DependenciesCollector extends LineOutputStream /*implements Iterable<String>*/ {

    //
    // Some configuration names
    //
    // compile
    // compileClasspath
    // compileOnly
    // implementation
    // default
    // testCompile
    // testCompileClasspath
    // testCompileOnly
    // testImplementation
    // testRuntime
    // testRuntimeClasspath
    // testRuntimeOnly
    // .


    // private static final String COMPILE_CLASSPATH = "compileClasspath";
    // private static final String COMPILE_ONLY = "compileOnly";
    // private static final String TEST_COMPILE_CLASSPATH = "testCompileClasspath";
    // private static final String TEST_COMPILE = "testCompileClasspath";
    // private static final String DEFAULT = "default";

    public static class Dependencies {
        public final Set<String> libraries = new TreeSet<>();
        public final Set<String> projects = new TreeSet<>();

        public Dependencies addAll(Dependencies that) {
            libraries.addAll(that.libraries);
            projects.addAll(that.projects);
            return this;
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final int STATE_CONFIGURATIONS = 1;
    private static final int STATE_DEPENDENCIES = 2;

    private final LogDigester digester;

    private final Map<String, Dependencies> configurations = new HashMap<>();
    private String currentConfiguration = "";

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     *
     */
    public DependenciesCollector() {
        digester = new LogDigester();
        digester.addRule("[-]+", STATE_CONFIGURATIONS);

        // <configurationName> - <description>
        digester.addRule(STATE_CONFIGURATIONS, "([A-Za-z0-9_$]+)\\s-\\s.*", this::addConfiguration);

        //
        // +--- org.jboss.logging:jboss-logging-processor:2.1.0.Final
        digester.addRule(STATE_DEPENDENCIES, "[\\s\\|\\\\+-]+project\\s*:\\s*([A-Za-z0-9_$:-]+).*", this::addProject);
        digester.addRule(STATE_DEPENDENCIES, "[\\s\\|\\\\+-]+([A-Za-z0-9_$:.-]+).*", this::addLibrary);

        digester.addRule(STATE_DEPENDENCIES, "", STATE_CONFIGURATIONS);

        digester.addRule(STATE_CONFIGURATIONS, "A web-based.*", LogDigester.STATE_DONE);
        digester.addRule(STATE_DEPENDENCIES, "A web-based.*", LogDigester.STATE_DONE);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void consume(String line) {
        digester.consume(line);
    }

    private static final String FAILED = "FAILED";
    private static final String NOT_RESOLVED = "(n)";
    private static final String PROJECT = "project";

    private int addConfiguration(int state, Matcher matcher, String line)  {
        String configurationName = matcher.group(1);

        if (!configurations.containsKey(configurationName))
            configurations.put(configurationName, new Dependencies());

        currentConfiguration = configurationName;
        return STATE_DEPENDENCIES;
    }

    private int addProject(int state, Matcher matcher, String line) {
        // (n): not resolved
        if (line.contains(NOT_RESOLVED))
            return state;

        String name = matcher.group(1);
        configurations.get(currentConfiguration).projects.add(name);
        return state;
    }

    private int addLibrary(int state, Matcher matcher, String line) {
        // (n): not resolved
        if (line.contains(NOT_RESOLVED))
            return state;
        if (line.contains(FAILED))
            return state;
        if (line.contains(PROJECT))
            return state;

        String coords = matcher.group(1);
        configurations.get(currentConfiguration).libraries.add(coords);

        // String coords = matcher.group(1);
        // if (isValidCoords(coords))
        //     libraries.add(coords);
        return state;
    }

    // private static boolean isValidCoords(String coords) {
    //     String[] parts = coords.split(":");
    //     if (parts.length < 2)
    //         return false;
    //     for (int i=0; i<parts.length; ++i)
    //         if (parts[i].isEmpty())
    //             return false;
    //     return true;
    // }

    public Set<String> getConfigurationNames() {
        return this.configurations.keySet();
    }

    public Set<String> getProjects(String configuration) {
        if (this.configurations.containsKey(configuration))
            return configurations.get(configuration).projects;

        Dependencies alldeps = new Dependencies();
        for (String configurationName : configurations.keySet())
            alldeps.addAll(configurations.get(configurationName));
        return alldeps.projects;
    }

    public Set<String> getLibraries(String configuration) {
        if (this.configurations.containsKey(configuration))
            return configurations.get(configuration).libraries;

        Dependencies alldeps = new Dependencies();
        for (String configurationName : configurations.keySet())
            alldeps.addAll(configurations.get(configurationName));
        return alldeps.libraries;
    }

}
