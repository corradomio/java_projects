package jext.sourcecode.project.gradle.collectors;

import jext.io.LineOutputStream;
import jext.util.LogDigester;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

import static jext.util.LogDigester.STATE_BEGIN;

public class AllDepsCollector extends LineOutputStream {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final int STATE_PROJECT = 1;
    private static final int STATE_CONFIGURATIONS = 2;
    private static final int STATE_DEPENDENCIES = 3;

    private LogDigester digester;

    public Set<String> mavenRepos = new HashSet<>();

    // gradle project -> configuration -> set of libraries
    public Map<String, Map<String, GradleDeps>> pdeps = new HashMap<>();

    private String currentProject = "";
    private Map<String, GradleDeps> configurations;
    private String currentConfiguration;

    private static final String FAILED = "FAILED";
    private static final String NOT_RESOLVED = "(n)";
    private static final String PROJECT = "project";

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public AllDepsCollector() {
        digester = new LogDigester();
        digester.addRule(STATE_BEGIN, "[-]+", STATE_PROJECT);
        digester.addRule(STATE_PROJECT, "[-]+", STATE_CONFIGURATIONS);
        digester.addRule(STATE_CONFIGURATIONS, "[-]+", STATE_PROJECT);

        // Root project
        // Project :plugins:store-smb
        digester.addRule(STATE_PROJECT,"Root project", this::addRootProject);
        digester.addRule(STATE_PROJECT, "Project ([0-9A-Za-z_$:\\-]+).*", this::addProject);

        // MavenRepository 'url'
        digester.addRule(STATE_PROJECT, "MavenRepository '([0-9A-Za-z_$%:/\\-]+)'", this::addMavenRepository);

        // <configurationName>
        digester.addRule(STATE_CONFIGURATIONS, "([0-9A-Za-z_$]+).*", this::addConfiguration);

        // +--- org.jboss.logging:jboss-logging-processor:2.1.0.Final
        digester.addRule(STATE_DEPENDENCIES, "[\\s\\|\\\\+-]+([0-9A-Za-z_$:.-]+).*", this::addLibrary);
        digester.addRule(STATE_DEPENDENCIES, "", STATE_CONFIGURATIONS);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public void consume(String line) {
        digester.consume(line);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private int addRootProject(int state, Matcher matcher, String line) {
        currentProject = "";
        configurations = new HashMap<>();
        pdeps.put(currentProject, configurations);

        return state;
    }

    private int addMavenRepository(int state, Matcher matcher, String line) {
        String mavenUrl = matcher.group(1);
        this.mavenRepos.add(mavenUrl);
        return state;
    }

    private int addProject(int state, Matcher matcher, String line) {
        // gradleProjectName -> "" | ":<name>:<name>:..."
        String moduleName = matcher.group(1);
        if (moduleName.startsWith(":"))
            moduleName = moduleName.substring(1);
        moduleName = moduleName.replace(":", "/");

        currentProject = moduleName; //matcher.group(1);
        configurations = new HashMap<>();
        pdeps.put(currentProject, configurations);

        return state;
    }

    private int addConfiguration(int state, Matcher matcher, String line)  {
        currentConfiguration = matcher.group(1);

        if (line.contains("No configurations"))
            return state;

        if (!configurations.containsKey(currentConfiguration))
            configurations.put(currentConfiguration, new GradleDeps());

        return STATE_DEPENDENCIES;
    }

    private int addLibrary(int state, Matcher matcher, String line) {

        if (line.contains("No dependencies"))
            return STATE_PROJECT;

        // (n): not resolved
        if (line.contains(NOT_RESOLVED))
            return state;
        if (line.contains(FAILED))
            return state;
        if (line.contains(PROJECT))
            return state;

        String coords = matcher.group(1);
        configurations.get(currentConfiguration).libraries.add(coords);
        return state;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
