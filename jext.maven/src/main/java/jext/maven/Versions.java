package jext.maven;

import java.util.TreeSet;

public class Versions {

    public static Versions noVersions = new Versions();

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private TreeSet<Version> versions = new TreeSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Versions() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public boolean isEmpty() {
        return versions.isEmpty();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void add(String version) {
        versions.add(Version.of(version));
    }

    public String getLatestVersion() {
        if (isEmpty())
            return Version.NO_VERSION.get();
        else
            return versions.last().get();
    }

}


// class VersionsOld {
//
//     /** Date -> Version */
//     private Map<String, String> dateVersion = new HashMap<>();
//     /** Version -> Date */
//     private Map<String, String> versionDate = new HashMap<>();
//
//     public VersionsOld() {
//
//     }
//
//     /** Check if there exists versions */
//     public boolean isEmpty() {
//         return versionDate.isEmpty();
//     }
//
//     /** Add a pair [version, date] */
//     public Versions add(String version, String date) {
//         dateVersion.put(date, version);
//         versionDate.put(version, date);
//         return this;
//     }
//
//     /** List of versions ordered by date */
//     public List<String> getVersions() {
//         if (isEmpty())
//             return Collections.emptyList();
//         List<String> dateList = new ArrayList<>(dateVersion.keySet());
//         dateList.sort(String::compareTo);
//         return dateList.stream()
//                 .map(date -> dateVersion.get(date))
//                 .collect(Collectors.toList());
//     }
//
//     /** Retrieve the latest version, or NO_VERSION */
//     public String getLatestVersion() {
//         if (isEmpty())
//             return NO_VERSION;
//         List<String> versions = getVersions();
//         return versions.get(versions.size()-1);
//     }
//
//     /** Get the publishing date of the version */
//     public String getDate(String version) {
//         if (!versionDate.containsKey(version))
//             throw new InvalidValueException("version", version);
//         return versionDate.getOrDefault(version, NO_VERSION);
//     }
//
//     public void dump() {
//         List<String> versions = getVersions();
//         versions.forEach(v -> {
//             System.out.printf("%s: %s\n", v, versionDate.get(v));
//         });
//     }
// }
