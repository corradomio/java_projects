package jext.sourcecode.project.csharp.util;

import java.util.regex.Pattern;

/**
 * Normalize the 'targetFramework' name
 */
public class DotNetNormalizer {

    private static final String NET = "net";
    private static final String NETCOREAPP = "netcoreapp";
    private static final String NETCORE = "netcore";
    private static final String NETSTANDARD = "netstandard";
    private static final String FRAMEWORK = "framework";
    private static final String DOT = ".";
    private static final String EMPTY = "";

    /*
            net40
            net45
            net451
            net461
            net472
            net5
            net5.0
            net6
            net6.0
            net6.0-android
            net6.0-ios
            net6.0-maccatalyst
            net6.0-tizen
            net6.0-windows
            net6.0-windows10.0.19041
            net6.0-windows7.0
            net7.0
            netcoreapp2.0
            netcoreapp2.1
            netcoreapp3.1
            netcoreapp6.0
            netstandard1.3
            netstandard2.0
            netstandard2.1
     */

    public static String versionOf(String dotnet) {
        int p;

        // normalize the name
        String version = normalize(dotnet);

        // netstandard2.1
        if (version.startsWith(NETSTANDARD))
            version = version.substring(NETSTANDARD.length());
        // netcoreapp6.0
        else if (version.startsWith(NETCOREAPP))
            version = version.substring(NETCOREAPP.length());
        // net6.0
        // net6.0-android
        // net472
        else if (version.startsWith(NET))
            version = version.substring(NET.length());

        // net6.0-maccatalyst
        p = version.indexOf('-');
        version = p > 0 ? version.substring(0, p) : version;

        // check for version containing '$' or '(',')'
        if (version.contains("$") || version.contains("("))
            return "";

        // remove extra spaces
        version = version.trim();

        // net6.0
        // net472
        if (version.contains(DOT) || version.length() == 1)
            return version;

        // 47
        // 471
        if (Pattern.matches("[1-4][0-9]*", version)) {
            if (version.length() == 2)
                // 47 -> 4.7
                return version.charAt(0) + DOT + version.charAt(1);
            else
                // 471 -> 4.7.1
                return version.charAt(0) + DOT + version.charAt(1) + DOT + version.charAt(2);
        }

        return version;
    }

    public static String normalize(String name) {

        // .NETFramework2.0
        // .NETFramework4.0-Client
        // .NETPortable0.0-Profile328
        // .NETStandard1.1
        // .NETStandard2.0
        // .NETFramework5.0
        // .NETFramework6.0
        // .NETCoreApp2.0

        // to lowercase
        name = name.toLowerCase();
        // remove spaces
        name = name.replaceAll("\\s+", "");
        // remove starting dot
        if (name.startsWith("."))
            name = name.substring(1);

        // "netframework..." -> "net..."
        if (name.contains(FRAMEWORK))
            name = name.replace(FRAMEWORK, "");

        // "netcore..." -> "netcoreapp..."
        if (Pattern.matches("netcore[1-9].*", name))
            name = name.replace(NETCORE, NETCOREAPP);

        // net4.8.1" -> "net481"
        if (Pattern.matches("net[1-4].*", name))
            name = name.replace(DOT, EMPTY);

        return name;
    }
}
