package jext.sourcecode.project.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryVersion {

    private static final Pattern MajorVersionPattern = Pattern.compile("([0-9]+\\.[0-9]+\\.[0-9]+)");
    private static final Pattern MinorVersionPattern = Pattern.compile("([0-9]+\\.[0-9]+)");
    private static final Pattern BuildNumberPattern = Pattern.compile("([0-9]+\\.[0-9]+\\.[0-9]+-[0-9]+)");
    private static final Pattern PatchNumberPattern = Pattern.compile("([0-9]+\\.[0-9]+\\.[0-9]+-[0-9]+-[0-9]+)");
    private static final Pattern MajorQualifierPattern = Pattern.compile("([0-9]+\\.[0-9]+\\.[0-9]+[-.][a-zA-Z0-9-.]+)");
    private static final Pattern QualifierPattern = Pattern.compile("([0-9]+\\.[0-9]+[-.][a-zA-Z0-9-.]+)");
    private static final String EMPTY = "";

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Try to retrieve the library version from the file name.
     * Following the Maven version specifications, the library must have a name like:
     *
     *      [name]-[version].jar
     *      [name]-[version].pom
     *
     * However, [name] com be composed by differen 'subnames', for example:
     *
     *
     *      commons-collection-3.2.2.jar
     *
     * @param name filename (without extension)
     * @return the library version or the empty version
     */
    public static String versionOf(String name) {
        // the filename is composed by <name>-...-<version>.jar
        Matcher matcher;

        // strip the extension (.jar, .zip, ...)
        String version = name; //.substring(0,sep);
        int sep = version.indexOf('-');

        while (sep != -1) {
            version = version.substring(sep+1);

            matcher = MajorVersionPattern.matcher(version);
            if (matcher.matches())
                return matcher.group(1);

            matcher = MinorVersionPattern.matcher(version);
            if (matcher.matches())
                return matcher.group(1);

            matcher = BuildNumberPattern.matcher(version);
            if (matcher.matches())
                return matcher.group(1);

            matcher = PatchNumberPattern.matcher(version);
            if (matcher.matches())
                return matcher.group(1);

            matcher = MajorQualifierPattern.matcher(version);
            if (matcher.matches())
                return matcher.group(1);

            matcher = QualifierPattern.matcher(version);
            if (matcher.matches())
                return matcher.group(1);

            sep = version.indexOf('-');
        }

        return EMPTY;
    }

}
