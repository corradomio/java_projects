package jext.sourcecode.project.gradle.util;

import jext.maven.MavenCoords;
import jext.util.PathUtils;
import jext.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
        Inside the file '.gradle' search the following strings:

            '<groupId>:<artifactId>:<version>'
            group:<groupId>, name:<artifactId>, version:<version>
 */

public class BuildGradleFile extends GradleFile {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private Map<String, Object> properties;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public BuildGradleFile(File configurationFile) {
        super(configurationFile);
    }

    // ----------------------------------------------------------------------
    // Dependencies
    // ----------------------------------------------------------------------


    // "... url "..." ..."
    // " -> '
    private static Pattern MAVEN_URL = Pattern.compile(
        ".*url\\s*['\"]([^'\"]+)['\"].*"
    );


    /**
     * Scan the file to retrieve the list of 'Maven' repositories.
     *
     * Warn: it is not considered the 'Groovy syntax' of the file, but only
     *       its textual representation.
     */
    public List<String> getRepositories() {
        Set<String> urlRepos = new HashSet<>();

        for (String line : content) {
            // mavenCentral     https://repo.maven.apache.org/maven2/
            // mavenLocal
            // jcenter          https://jcenter.bintray.com/
            // url "..."
            // maven { url " ..." }
            // gradlePluginPortal

            if (line.contains("mavenCentral()")) {
                urlRepos.add("https://repo.maven.apache.org/maven2/");
                continue;
            }
            if (line.contains("jcenter()")) {
                urlRepos.add("https://jcenter.bintray.com/");
                continue;
            }
            if (line.contains("google()")) {
                urlRepos.add("https://dl.google.com/dl/android/maven2/");
                urlRepos.add("https://maven.google.com/");
                continue;
            }
            Matcher matcher = MAVEN_URL.matcher(line);
            if (matcher.matches()) {
                urlRepos.add(matcher.group(1));
            }
        }

        return new ArrayList<>(urlRepos);
    }

    // ----------------------------------------------------------------------
    // Dependencies
    // ----------------------------------------------------------------------

    // (prefix) ":<moduleName>"
    // " -> '
    private static Pattern MOD_COORDS = Pattern.compile(
        "(.*)['\"]:([a-zA-Z][^'\"]+)['\"].*"
    );

    public Set<String> getModuleDependencies() {
        Matcher matcher;
        Set<String> dependencies = new HashSet<>();

        for (String line : content) {

            //matcher = MOD_COORDS_NO_PARENS.matcher(line);
            matcher = MOD_COORDS.matcher(line);
            if (matcher.matches()) {
                addDependency(dependencies, matcher);
            }
        }

        return dependencies;
    }

    private void addDependency(Set<String> dependencies, Matcher matcher) {
        String text = matcher.group();
        String prefix = matcher.group(1);
        if (skipPrefix(prefix))
            return;
        String module = matcher.group(2);
        if (module.startsWith(":"))
            module = module.substring(1);
        module = module.replace(':', '/').trim();
        if (module.length() != 0)
            dependencies.add(module);
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    public Set<MavenCoords> getMavenDependencies() {
        Set<MavenCoords> libraries = new HashSet<>();

        addProperties();
        addDependencies(libraries);
        addDependencySets(libraries);
        addSpecialLibraries(libraries);

        return libraries;
    }

    private static Pattern JAR_FILE = Pattern.compile(
        ".*['\"]([A-Za-z0-9./_\\\\/-]+\\.jar)['\"].*"
    );

    /** Scan the file to extract "...[name].jar" */
    public Set<String> getJarLibraries() {
        Set<String> localLibraries = new HashSet<>();

        Matcher matcher;
        for (String line : content) {

            //matcher = MOD_COORDS_NO_PARENS.matcher(line);
            matcher = JAR_FILE.matcher(line);
            if (matcher.matches()) {
                String rpath = PathUtils.normalize(matcher.group(1));
                String libraryName = PathUtils.getNameWithoutExt(rpath);
                localLibraries.add(libraryName);
            }
        }

        return localLibraries;
    }

    // ----------------------------------------------------------------------
    // def <identifier> = "<value>"

    private static Pattern DEF_PROPERTY = Pattern.compile(
        ".*def\\s+([A-Za-z]+)\\s*=\\s*['\"]([^'\"]+)['\"].*"
    );

    private void addProperties() {
        if (properties != null)
            return;

        Matcher matcher;
        properties = new HashMap<>();

        int iline = 0;
        for (String line : content) {
            iline += 1;

            matcher = DEF_PROPERTY.matcher(line);
            if (matcher.matches()) {
                String name = matcher.group(1);
                String value = matcher.group(2);
                properties.put(name, value);
            }
        }
    }

    // ----------------------------------------------------------------------
    // [^:'\"]  [^:'\"!=;*]

    // (prefix)"<groupId>:<artifactId>:<version>"
    // " -> '
    private static Pattern LIB_COORDS = Pattern.compile(
        //".*[^'\"]*['\"]([^:'\"!=;*/,]+):([^:'\"!=;*/,]+):([^:'\"!=;*/,]+)['\"].*"
        "(.*)[\\s(]['\"]([A-Za-z0-9.-]+):([A-Za-z0-9.-]+):([A-Za-z0-9.-]+)['\"].*"
    );

    // (prefix)group : "<groupId>" , name : "<artifactId>" , version : "<version>"
    // " -> '
    private static Pattern QUOT_COORDS = Pattern.compile(
        //".*group\\s*:\\s*['\"]([^'\"]+)['\"]\\s*,\\s*name\\s*:\\s*['\"]([^'\"]+)['\"]\\s*,\\s*version\\s*:\\s*['\"]([^'\"]+)['\"].*"
        "(.*)group\\s*:\\s*([A-Za-z0-9.-]+)\\s*,\\s*name\\s*:\\s*([A-Za-z0-9.-]+)\\s*,\\s*version\\s*:\\s*([A-Za-z0-9.-]+).*"
    );

    // (prefix)"<groupId>:<artifactId>"
    // " -> '
    private static Pattern LIB_COORDS_NO_VERSION = Pattern.compile(
        // ".*[^'\"]*['\"]([^:'\"!=;*/,]+):([^:'\"!=;*/,]+)['\"].*"
        "(.*)[\\s(]['\"]([A-Za-z0-9.-]+):([A-Za-z0-9.-]+)['\"].*"
    );

    // (prefix)group : "<groupId>" , name : "<artifactId>"
    // " -> '
    private static Pattern QUOT_COORDS_NO_VERSION = Pattern.compile(
        //".*group\\s*:\\s*['\"]([^'\"]+)['\"]\\s*,\\s*name\\s*:\\s*['\"]([^'\"]+)['\"].*"
        "(.*)group\\s*:\\s*([A-Za-z0-9.-]+)\\s*,\\s*name\\s*:\\s*([A-Za-z0-9.-]+).*"
    );

    private void addDependencies(Set<MavenCoords> libraries) {
        Matcher matcher;

        int iline = 0;
        for (String line : content) {
            iline += 1;

            // minimum 'a:b'
            if (line.length() < 5)
                continue;

            matcher = QUOT_COORDS.matcher(line);
            if (matcher.matches()) {
                addLibrary(libraries, matcher);
                continue;
            }
            matcher = LIB_COORDS.matcher(line);
            if (matcher.matches()) {
                addLibrary(libraries, matcher);
                continue;
            }
            matcher = QUOT_COORDS_NO_VERSION.matcher(line);
            if (matcher.matches()) {
                addLibraryNoVersion(libraries, matcher);
                continue;
            }
            matcher = LIB_COORDS_NO_VERSION.matcher(line);
            if (matcher.matches()) {
                addLibraryNoVersion(libraries, matcher);
                continue;
            }
            else {
                continue;
            }
        }
    }

    // ----------------------------------------------------------------------
    // dependencySet(group: 'org.apache.logging.log4j', version: '2.13.2') {
    //      entry 'name'
    //      ...
    // }
    //
    // " -> '
    //

    private static Pattern DSET_HEADER = Pattern.compile(
        ".*dependencySet\\(group\\s*:\\s*['\"]([^:'\"]+)['\"]\\s*,\\s*version\\s*:\\s*['\"]([^:'\"!=;*]+)['\"]\\s*\\).*"
    );

    // entry 'log4j-api'
    // " -> '
    private static Pattern DSET_ENTRY = Pattern.compile(
        "\\s*entry\\s+['\"]([^'\"]+)['\"].*"
    );

    private void addDependencySets(Set<MavenCoords> libraries) {
        Matcher matcher;

        String groupId, artifactId, version;

        boolean dset = false;
        int iline = 0;
        groupId = version = null;

        for (String line : content) {
            ++iline;

            line = line.trim();
            matcher = DSET_HEADER.matcher(line);
            if (matcher.matches()) {
                dset = true;
                groupId = matcher.group(1);
                version = matcher.group(2);
                continue;
            }

            matcher = DSET_ENTRY.matcher(line);
            if (matcher.matches() && dset) {
                artifactId = matcher.group(1);

                if (groupId != null && version != null)
                    libraries.add(new MavenCoords(groupId, artifactId, version));

                continue;
            }

            {
                dset = false;
                groupId = version = null;
            }
        }
    }

    // ----------------------------------------------------------------------
    // Special libraries, based on manual analysis of the file
    //

    private void addSpecialLibraries(Set<MavenCoords> libraries) {
        for (String line : content) {
            if(line.contains("gradlePlugin"))
                libraries.add(new MavenCoords("org.gradle:gradle-core-api"));
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void addLibrary(Set<MavenCoords> libraries, Matcher matcher) {
        String text = matcher.group();
        String prefix = matcher.group(1);
        if (skipPrefix(prefix))
            return;
        String groupId = StringUtils.replace(matcher.group(2), properties);
        String artifactId = StringUtils.replace(matcher.group(3), properties);
        String version = StringUtils.replace(matcher.group(4), properties);
        if (artifactId.length() == 0 || "none".equals(artifactId)) // tric
            return;
        libraries.add(new MavenCoords(groupId, artifactId, version));
    }

    private void addLibraryNoVersion(Set<MavenCoords> libraries, Matcher matcher) {
        String text = matcher.group();
        String prefix = matcher.group(1);
        if (skipPrefix(prefix))
            return;
        String groupId = StringUtils.replace(matcher.group(2), properties);
        String artifactId = StringUtils.replace(matcher.group(3), properties);
        String version = StringUtils.empty();
        if (artifactId.length() == 0 || "none".equals(artifactId))
            return;
        libraries.add(new MavenCoords(groupId, artifactId, version));
    }

    private static List<String> SKIP_PREFIXES = new ArrayList<String>() {{
        add("classpath");
        add("annotation");
        add("dependson");
        add("expand");
    }};

    private static boolean skipPrefix(String s) {
        s = s.toLowerCase();
        for(String prefix : SKIP_PREFIXES)
            if (s.contains(prefix))
                return true;
        return false;
    }

}
