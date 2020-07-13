package jext.buildtools.maven;

/*
    https://docs.oracle.com/middleware/1212/core/MAVEN/maven_version.htm#MAVEN400

    Version schemes

        MajorVersion: 1.2.1
        MinorVersion: 2.0
        IncrementalVersion: 1.2-SNAPSHOT
        BuildNumber: 1.4.2-12
        Qualifier: 1.2-beta-2

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


 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Version implements Comparable<Version> {

    public static Version NO_VERSION = new Version();
    public static Version empty() { return NO_VERSION; }

    public static Version of(String version) {
        return (version != null && version.length() > 0) ? new Version(version) : NO_VERSION;
    }

    private enum Scheme {
        Generic,
        NumericVersion,
        QualifiedVersion
        // MajorVersion,           // 1.2.1
        // MinorVersion,           // 1.2
        // IncrementalVersion,     // 1.2-SNAPSHOT
        // BuildNumber,            // 1.4.2-12
        // PatchNumber,            // 1.4.2-12-3
        // Qualifier               // 1.2-beta-2
    }

    private static Pattern MajorVersionPattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)");
    private static Pattern MinorVersionPattern = Pattern.compile("([0-9]+)\\.([0-9]+)");
    private static Pattern IncrementalVersionPattern = Pattern.compile("([0-9]+)\\.([0-9]+)-SNAPSHOT");
    private static Pattern BuildNumberPattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)-([0-9]+)");
    private static Pattern PatchNumberPattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)-([0-9]+)-([0-9]+)");
    private static Pattern QualifierPattern = Pattern.compile("([0-9]+)\\.([0-9]+)-([a-zA-Z0-9-]+)");

    private String version;
    private Scheme scheme;
    private int major;
    private int minor;
    private int subver;
    private int patch;
    private int build;
    private String qualifier;

    private Version() {
        this.version = "";
        this.scheme = Scheme.Generic;
    }

    public Version(String version) {
        this.version = version;
        parse();
    }

    private void parse() {
        Matcher matcher;

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

        matcher = QualifierPattern.matcher(version);
        if (matcher.matches()) {
            this.scheme = Scheme.QualifiedVersion;
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            qualifier = matcher.group(3);
        }
        else
        {
            this.scheme = Scheme.Generic;
        }
    }

    @Override
    public int compareTo(Version that) {
        if (version.equals(that.version))
            return 0;

        if (this.scheme == Scheme.NumericVersion && that.scheme == Scheme.NumericVersion) {
            int cmp;
            cmp = this.major - that.major; if (cmp != 0) return cmp;
            cmp = this.minor - that.minor; if (cmp != 0) return cmp;
            cmp = this.subver- that.subver;if (cmp != 0) return cmp;
            cmp = this.build - that.build; if (cmp != 0) return cmp;
            cmp = this.patch - that.patch; if (cmp != 0) return cmp;
            return 0;
        }
        if (this.scheme == Scheme.QualifiedVersion && that.scheme == Scheme.QualifiedVersion) {
            int cmp;
            cmp = this.major - that.major; if (cmp != 0) return cmp;
            cmp = this.minor - that.minor; if (cmp != 0) return cmp;
            return this.qualifier.compareTo(that.qualifier);
        }
        if (this.scheme == Scheme.QualifiedVersion || that.scheme == Scheme.QualifiedVersion) {
            int cmp;
            cmp = this.major - that.major; if (cmp != 0) return cmp;
            cmp = this.minor - that.minor; if (cmp != 0) return cmp;
            if (this.scheme == Scheme.QualifiedVersion)
                return -1;
            else
                return +1;
        }
        if (this.scheme == Scheme.Generic || that.scheme == Scheme.Generic)
            return version.compareTo(that.version);
        else
            return this.version.compareTo(that.version);
    }

    @Override
    public String toString() {
        return String.format("%s::%s", version, scheme);
    }
}
