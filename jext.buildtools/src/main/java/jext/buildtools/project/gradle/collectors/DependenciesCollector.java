package jext.buildtools.project.gradle.collectors;

import jext.io.LineOutputStream;
import jext.logging.Logger;
import jext.util.LogDigester;

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

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final int STATE_CONFIGURATIONS = 1;
    private static final int  STATE_DEPENDENCIES = 2;

    private final LogDigester digester;
    private final Set<String> libraries = new TreeSet<>();
    private final Set<String> projects = new TreeSet<>();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     *
     */
    public DependenciesCollector() {
        digester = new LogDigester();
        digester.addRule("[-]+", STATE_CONFIGURATIONS);

        // <projectName> - <description>
        digester.addRule(STATE_CONFIGURATIONS, "[A-Za-z0-9_$:-]+\\s-\\s.*", STATE_DEPENDENCIES);

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

    private int addProject(int state, Matcher matcher, String line) {
        // (n): not resolved
        if (line.contains(NOT_RESOLVED))
            return state;

        String name = matcher.group(1);
        projects.add(name);
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
        libraries.add(coords);

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

    public Set<String> getProjects() {
        return projects;
    }

    public Set<String> getLibraries() {
        return libraries;
    }

}
