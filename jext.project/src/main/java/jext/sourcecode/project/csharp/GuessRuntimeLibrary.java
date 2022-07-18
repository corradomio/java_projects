package jext.sourcecode.project.csharp;

import jext.sourcecode.project.Module;
import jext.util.FileUtils;
import jext.util.StringUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GuessRuntimeLibrary {

    public static final String NO_RUNTIME_LIBRARY = "";
    public static final String DEFAULT_RUNTIME_LIBRARY = ".NET 6.0";

    /*
        C# 1.0          .NET Framework 1.0
        C# 1.1          .NET Framework 1.1
        C# 1.2          .NET Framework 1.1
        C# 2.0          .NET Framework 2.0 / 3.0
        C# 3.0          .NET Framework 2.0 / 3.0 / 3.5
        C# 4.0          .NET Framework 4
        C# 5.0          .NET Framework 4.5
        C# 6.0          .NET Framework 4.6 /
                        .NET Core 1.0 / 1.1
        C# 7.0          .NET Framework 4.7
        C# 7.1          .NET Core 2.0
        C# 7.2          .NET Core 2.0
        C# 7.3          .NET Framework 4.8
                        .NET Core 2.1 / 2.2
        C# 8.0          .NET Core 3.0 / 3.1
        C# 9.0          .NET .NET 5.0
        C# 10.0         .NET .NET 6.0
    */

    public static final Map<String, String> LANGUAGE_RUNTIMES = new HashMap<String, String>(){{
        put( "3.0", ".NET Framework 3.5");
        put( "4.0", ".NET Framework 4");
        put( "5.0", ".NET Framework 4.5");
        put( "6.0", ".NET Framework 4.6");
        put( "7.0", ".NET Framework 4.7");
        put( "7.1", ".NET Core 2.0");
        put( "7.2", ".NET Core 2.0");
        put( "7.3", ".NET Core 2.2");
        put( "8.0", ".NET Core 3.1");
        put( "9.0", ".NET 5.0");
        put("10.0", ".NET 6.0");
    }};

    public static String guessRuntimeLibrary(Module module) {
        Optional<String> runtimeName;
        GuessRuntimeLibrary grtl = new GuessRuntimeLibrary(module);

        runtimeName = grtl.checkConfiguration();
        if (runtimeName.isPresent())
            return runtimeName.get();

        return DEFAULT_RUNTIME_LIBRARY;
    }



    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Module module;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private GuessRuntimeLibrary(Module module) {
        this.module = module;
    }

    // ----------------------------------------------------------------------
    // checkConfiguration
    // ----------------------------------------------------------------------

    /*
        <Project>
            <PropertyGroup>
                ...
                <TargetFrameworks>net472;net6.0-windows</TargetFrameworks>
                <LangVersion>8.0</LangVersion>
                ...
            </PropertyGroup>

            <PropertyGroup> ... </PropertyGroup>
            ...

        </Project>
     */

    private Optional<String> checkConfiguration() {
        // scan for the first ".csproj" file found
        List<File> csprojFiles = FileUtils.listFiles(module.getModuleHome(), ".csproj");

        for(File csproj : csprojFiles) {
            try {
                Element project = XPathUtils.parse(csproj).getDocumentElement();

                List<Element> propertyGroups = XPathUtils.selectElements(project, "PropertyGroup");
                for (Element propertyGroup : propertyGroups) {
                    String languageVersion = XPathUtils.getValue(propertyGroup, "LangVersion", null);
                    String targetFrameworks = XPathUtils.getValue(propertyGroup, "TargetFrameworks", null);

                    String runtimeName = selectRuntime(languageVersion, targetFrameworks);
                    if (runtimeName != null)
                        return Optional.of(runtimeName);
                }

            } catch (ParserConfigurationException | IOException | SAXException e) {

            }
        }

        return Optional.empty();
    }

    private static String selectRuntime(String languageVersion, String targetFrameworks) {
        String runtimeName = null;

        if (languageVersion == null && targetFrameworks == null)
            return runtimeName;

        // clean 'targetFrameworks' if contain '${...}'
        if (targetFrameworks != null)
        while (targetFrameworks.contains("${")) {
            int b = targetFrameworks.indexOf("${");
            int e = targetFrameworks.indexOf("}");
            targetFrameworks = targetFrameworks.substring(0, b) + targetFrameworks.substring(e+1);
        }

        runtimeName = selectRuntimeFromTargets(targetFrameworks);

        if (runtimeName == null)
            runtimeName = selectRuntimeFromLanguage(languageVersion);

        return runtimeName;
    }

    private static String selectRuntimeFromTargets(String targetFrameworks) {
        String selectedTarget = null;

        if (targetFrameworks == null)
            return selectedTarget;

        for(String target : StringUtils.split(targetFrameworks, ",")) {
            // remove spaces
            target = target.trim();
            // check for '<framework>-<platform>'
            int p = target.indexOf('-');
            if (p != -1)
                target = target.substring(0, p);

            if (selectedTarget == null || target.compareTo(selectedTarget) > 0)
                selectedTarget = target;
        }

        return selectedTarget;
    }

    private static String selectRuntimeFromLanguage(String languageVersion) {
        if (languageVersion == null)
            return null;

        // <LangVersion>3</LangVersion>
        // <LangVersion>8.0</LangVersion>
        // <LangVersion>default</LangVersion>
        if ("default".equals(languageVersion))
            languageVersion = "6.0";
        if (!languageVersion.contains("."))
            languageVersion += ".0";

        return LANGUAGE_RUNTIMES.getOrDefault(languageVersion, null);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
