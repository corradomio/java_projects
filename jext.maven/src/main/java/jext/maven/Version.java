package jext.maven;

/*
    https://books.sonatype.com/mvnref-book/reference/pom-relationships-sect-pom-syntax.html
    https://www.mojohaus.org/versions-maven-plugin/version-rules.html
    https://docs.oracle.com/middleware/1212/core/MAVEN/maven_version.htm#MAVEN400
    https://semver.org/

    Version schemes

        MajorVersion:       1.2.1
        MinorVersion:       2.0
        IncrementalVersion: 1.2-SNAPSHOT
        BuildNumber:        1.4.2-12
        PatchNumber:        1.4.2-12-1
        Qualifier:          1.2-beta-2

        Extras:
        MajorDotQualifier:  5.3.5.RELEASE
        MajorQualifier:     3.27.0-GA
        DotQualifier:       1.2.LABEL

        SemVer:


    All versions with a qualifier are older than the same version without a qualifier (release version).

    For example:    1.2-beta-2 is older than 1.2.

    Identical versions with different qualifier fields are compared by using basic string comparison.

    For example:    1.2-beta-2 is newer than 1.2-alpha-6.

    If you do not follow Maven versioning standards in your project versioning scheme, then for version comparison,
    Maven interprets the entire version as a simple string. Maven and its core plug-ins use version comparison for a
    number of tasks, most importantly, the release process.

    If you use a nonstandard versioning scheme, Maven release and version plug-in goals might not yield the expected
    results. Because basic string comparison is performed on nonstandard versions, version comparison calculates the
    order of versions incorrectly in some cases.

    For example, Maven arranges the version list in the following manner:

        1.0.1.0
        1.0.10.1
        1.0.10.2
        1.0.9.3

    Version 1.0.9.3 should come before 1.0.10.1 and 1.0.10.2, but the unexpected fourth field (.3) forced Maven to
    evaluate the version as a string.

    The SNAPSHOT qualifier
    ----------------------
    todo


     Semantic Versioning
     -------------------
     todo

 */

import jext.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Version implements Comparable<Version> {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static final Version NO_VERSION = new Version();

    public static Version empty() { return NO_VERSION; }

    public static Version of(String version) {
        if (version == null || version.isEmpty())
            return NO_VERSION;
        Version v = new Version(version);
        v.parse();
        return v;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final String EMPTY = "";

    private enum Scheme {
        StringVersion,
        NumericVersion,
        QualifiedVersion

        // MajorVersion,           // 1.2.1             NumericVersion
        // MinorVersion,           // 1.2               NumericVersion
        // BuildNumber,            // 1.4.2-12          NumericVersion
        // PatchNumber,            // 1.4.2-12-3        NumericVersion
        // IncrementalVersion,     // 1.2-SNAPSHOT      QualifiedVersion
        // Qualifier               // 1.2-beta-2        QualifiedVersion
        // MajorDotQualifier,      // 5.3.5.RELEASE     QualifiedVersion
        // MajorQualifier:         // 3.27.0-GA         QualifiedVersion
        // DotQualifier:           // 1.2.LABEL         QualifiedVersion

        // PythonQualifier:        // 1.2a1   1.2.1a1
                                   // 1.2b1   1.2.1b1
                                   // 1.2rc1  1.2.1rc1

        // MainVersionPattern      // 1                 NumericVersion
        // Version4Number          // 1.2.3.4           NumericVersion
    }

    private static final Pattern MajorVersionPattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)");
    private static final Pattern MinorVersionPattern = Pattern.compile("([0-9]+)\\.([0-9]+)");
    private static final Pattern MainVersionPattern  = Pattern.compile("([0-9]+)");
    // private static final Pattern IncrementalVersionPattern = Pattern.compile("([0-9]+)\\.([0-9]+)-SNAPSHOT");
    private static final Pattern BuildNumberPattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)-([0-9]+)");
    private static final Pattern PatchNumberPattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)-([0-9]+)-([0-9]+)");
    private static final Pattern MajorQualifierPattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)[-.]([a-zA-Z0-9-.]+)");
    private static final Pattern QualifierPattern = Pattern.compile("([0-9]+)\\.([0-9]+)[-.]([a-zA-Z0-9-.]+)");

    private static final Pattern PythonMajorVersionPattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)((a|b|rc)([0-9]+))");
    private static final Pattern PythonMinorVersionPattern = Pattern.compile("([0-9]+)\\.([0-9]+)((a|b|rc)([0-9]+))");

    private static final Pattern Version4Pattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)");
    private static final Pattern SemVerPattern = Pattern.compile("(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?");

    private String version;
    private Scheme scheme = Scheme.StringVersion;

    private int major = 0;
    private int minor = 0;
    private int subver = 0;
    private int patch = 0;
    private int build = 0;
    private String qualifier = EMPTY;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private Version() {
        this.version = EMPTY;
    }

    private Version(String version) {
        this.version = version;
    }

    private void parse() {
        Matcher matcher;

        matcher = Version4Pattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.NumericVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            subver= Integer.parseInt(matcher.group(3));
            patch = Integer.parseInt(matcher.group(4));
            return;
        }

        matcher = PythonMajorVersionPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.QualifiedVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            subver= Integer.parseInt(matcher.group(3));
            qualifier = matcher.group(4);
            return;
        }

        matcher = MainVersionPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.NumericVersion;
            major = Integer.parseInt(matcher.group(1));
            return;
        }

        matcher = PythonMinorVersionPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.QualifiedVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            qualifier = matcher.group(3);
            return;
        }

        matcher = MajorVersionPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.NumericVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            subver= Integer.parseInt(matcher.group(3));
            return;
        }

        matcher = MinorVersionPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.NumericVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            return;
        }

        matcher = BuildNumberPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.NumericVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            subver= Integer.parseInt(matcher.group(3));
            build = Integer.parseInt(matcher.group(4));
            return;
        }

        matcher = PatchNumberPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.NumericVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            subver= Integer.parseInt(matcher.group(3));
            build = Integer.parseInt(matcher.group(4));
            patch = Integer.parseInt(matcher.group(5));
            return;
        }

        matcher = MajorQualifierPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.QualifiedVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            subver = Integer.parseInt(matcher.group(3));
            qualifier = matcher.group(4);
            return;
        }

        matcher = QualifierPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.QualifiedVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            qualifier = matcher.group(3);
            return;
        }

        matcher = SemVerPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.QualifiedVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            subver = Integer.parseInt(matcher.group(3));
            qualifier = matcher.group(4);
            return;
        }

        {
            this.scheme = Scheme.StringVersion;
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public boolean isEmpty() {
        return version.isEmpty();
    }

    public String get() {
        return version;
    }

    /**
     * Return the index where there is the first difference
     * -1 -> equals
     *  0 -> major
     *  1 -> minor
     *  2 -> subver
     *  3 -> patch
     *  4 -> build
     *  5 -> qualifier
     *
     * @return
     */
    public int differOn(Version that) {
        if (scheme == Scheme.StringVersion)
            return 0;
        if (this.major != that.major)
            return 0;
        if (this.minor != that.minor)
            return 1;
        if (this.subver != that.subver)
            return 2;
        if (this.patch != that.patch)
            return 3;
        if (this.build != that.build)
            return 4;
        if (!this.qualifier.equals(that.qualifier))
            return 5;

        return -1;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static boolean isValid(String version) {
        Matcher m;

        m = MajorVersionPattern.matcher(version);
        if (m.matches()) return true;
        m = MinorVersionPattern.matcher(version);
        if (m.matches()) return true;
        m = BuildNumberPattern.matcher(version);
        if (m.matches()) return true;
        m = PatchNumberPattern.matcher(version);
        if (m.matches()) return true;
        m = MajorQualifierPattern.matcher(version);
        if (m.matches()) return true;
        m = QualifierPattern.matcher(version);
        if (m.matches()) return true;
        return false;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return ((((this.major*31 + this.minor)*31 + this.subver)*31 + this.patch)*31 + this.build)
                *31 + this.qualifier.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Version that = (Version) obj;

        // private int major = 0;
        // private int minor = 0;
        // private int subver = 0;
        // private int patch = 0;
        // private int build = 0;
        // private String qualifier = EMPTY;

        return this.major == that.major
            && this.minor == that.minor
            && this.subver == that.subver
            && this.patch == that.patch
            && this.build == that.build
            && this.qualifier.equals(that.qualifier);
    }

    @Override
    public int compareTo(Version that) {
        // one of the schemes is StringVersion, compare in lexicographic order
        if (this.scheme == Scheme.StringVersion || that.scheme == Scheme.StringVersion)
            return this.version.compareTo(that.version);

        // check for empty versions
        if (this.isEmpty() && that.isEmpty()) return 0;
        if (this.isEmpty()) return +1;
        if (that.isEmpty()) return -1;

        // compare by major/minor/subver/build/patch/qualifier (in lexicographic order)
        int cmp;

        cmp = this.major - that.major; if (cmp != 0) return cmp;
        cmp = this.minor - that.minor; if (cmp != 0) return cmp;
        cmp = this.subver- that.subver;if (cmp != 0) return cmp;
        cmp = this.build - that.build; if (cmp != 0) return cmp;
        cmp = this.patch - that.patch; if (cmp != 0) return cmp;

        // qualifier "" is BETTER that "label"
        if (this.qualifier.isEmpty() && !that.qualifier.isEmpty())
            cmp = +1;
        else if (!this.qualifier.isEmpty() && that.qualifier.isEmpty())
            cmp = -1;
        else
            cmp = this.qualifier.compareTo(that.qualifier);

        return cmp;
    }

    @Override
    public String toString() {
        return String.format("%s::%s", version, scheme);
    }

    public static void dump(Version v) {
        System.out.printf("version: %s\n", v.version);
        System.out.printf("   scheme: %s\n", v.scheme);
        System.out.printf("    major: %d\n", v.major);
        System.out.printf("    minor: %d\n", v.minor);
        System.out.printf("   subver: %d\n", v.subver);
        System.out.printf("    build: %d\n", v.build);
        System.out.printf("    patch: %d\n", v.patch);
        System.out.printf("  qualify: %s\n", v.qualifier);
        System.out.println("-----------------");
    }

}
