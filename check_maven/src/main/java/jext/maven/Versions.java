package jext.maven;

import jext.exception.InvalidValueException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static jext.maven.MavenConst.NO_VERSION;

public class Versions {

    /** Date -> Version */
    private Map<String, String> dateVersion = new HashMap<>();
    /** Version -> Date */
    private Map<String, String> versionDate = new HashMap<>();

    public Versions() {

    }

    /** Check if there exists versions */
    public boolean isEmpty() {
        return versionDate.isEmpty();
    }

    /** Add a pair [version, date] */
    public Versions add(String version, String date) {
        dateVersion.put(date, version);
        versionDate.put(version, date);
        return this;
    }

    /** List of versions ordered by date */
    public List<String> getVersions() {
        if (isEmpty())
            return Collections.emptyList();
        List<String> dateList = new ArrayList<>(dateVersion.keySet());
        dateList.sort(String::compareTo);
        return dateList.stream()
            .map(date -> dateVersion.get(date))
            .collect(Collectors.toList());
    }

    /** Retrieve the latest version, or NO_VERSION */
    public String getLatestVersion() {
        if (isEmpty())
            return NO_VERSION;
        List<String> versions = getVersions();
        return versions.get(versions.size()-1);
    }

    /** Get the publishing date of the version */
    public String getDate(String version) {
        if (!versionDate.containsKey(version))
            throw new InvalidValueException("version", version);
        return versionDate.getOrDefault(version, NO_VERSION);
    }

    public void dump() {
        List<String> versions = getVersions();
        versions.forEach(v -> {
            System.out.printf("%s: %s\n", v, versionDate.get(v));
        });
    }
}
