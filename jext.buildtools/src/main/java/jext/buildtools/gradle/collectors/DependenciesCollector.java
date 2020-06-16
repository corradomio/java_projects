package jext.buildtools.gradle.collectors;

import jext.io.LineOutputStream;
import jext.buildtools.util.LogDigester;
import jext.logging.Logger;

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

    private static Logger logger = Logger.getLogger(DependenciesCollector.class);

    private static final String STATE_CONFIGURATIONS = "CONFIGURATIONS";
    private static final String STATE_DEPENDENCIES = "DEPENDENCIES";

    private final LogDigester digester;
    private final Set<String> libraries = new TreeSet<>();
    private final Set<String> projects = new TreeSet<>();

    /**
     *
     */
    public DependenciesCollector() {
        digester = new LogDigester();
        digester.addRule("[-]+", STATE_CONFIGURATIONS);

        // <projectName> - <description>
        digester.addRule(STATE_CONFIGURATIONS, "[A-Za-z0-9_$:-]+\\s-\\s.*", STATE_DEPENDENCIES);

        // +--- org.jboss.logging:jboss-logging-processor:2.1.0.Final
        digester.addRule(STATE_DEPENDENCIES, "[\\s\\|\\\\+-]+project :([A-Za-z0-9_$:-]+).*", this::addProject);
        digester.addRule(STATE_DEPENDENCIES, "[\\s\\|\\\\+-]+([A-Za-z0-9_$:.-]+).*", this::addLibrary);

        digester.addRule(STATE_DEPENDENCIES, "", STATE_CONFIGURATIONS);

        digester.addRule(STATE_CONFIGURATIONS, "A web-based.*", LogDigester.STATE_DONE);
        digester.addRule(STATE_DEPENDENCIES, "A web-based.*", LogDigester.STATE_DONE);
    }

    public void consume(String line) {
        digester.consume(line);
    }

    private String addProject(String state, Matcher matcher, String line) {
        String name = matcher.group(1);
        projects.add(name);
        return null;
    }

    private String addLibrary(String state, Matcher matcher, String line) {
        String coords = matcher.group(1);
        if (isValidCoords(coords))
            libraries.add(coords);
        return null;
    }

    private static boolean isValidCoords(String coords) {
        String[] parts = coords.split(":");
        if (parts.length < 2)
            return false;
        for (int i=0; i<parts.length; ++i)
            if (parts[i].isEmpty())
                return false;
        return true;
    }

    public Set<String> getProjects() {
        return projects;
    }

    public Set<String> getLibraries() {
        return libraries;
    }


}
