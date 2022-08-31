package jext.sourcecode.project.csharp.util;

import java.io.File;
import java.util.TreeSet;

/**
 * Class used to select the correct library's .NET implementation.
 * The directory [library]/libs contains a list of subdirectories as
 *
 *         lib/
 *             net20
 *             net35
 *             net45
 *             net472
 *             netstandard1.0
 *             netstandard1.3
 *             netstandard2.0
 *             netcoreapp3.0
 *             netcoreapp3.1
 *             net6.0
 *
 *  the problem is to select the correct implementation based on the
 *  .NET version used by the project/module
 *
 */
public class DotNetImplementations {

    private static final String DEFAULT = "net6.0";

    private File libDirectory;
    // net20 ... net472
    private TreeSet<String> frameworks = new TreeSet<>();
    // netstandard2.0
    private TreeSet<String> standards = new TreeSet<>();
    // netcoreapp
    private TreeSet<String> coreapps = new TreeSet<>();
    // net6.0...
    private TreeSet<String> cores = new TreeSet<>();

    public DotNetImplementations(File libDirectory) {
        this.libDirectory = libDirectory;
        populate();
    }

    private void populate() {
        File[] versions = libDirectory.listFiles();
        if (versions == null)
            return;
        for(File version : versions) {
            String name = version.getName();
            if (name.startsWith("netcoreapp"))
                coreapps.add(name);
            else if (name.startsWith("netstandard"))
                standards.add(name);
            else if (name.startsWith("net") && name.contains("."))
                cores.add(name);
            else
                frameworks.add(name);
        }
    }

    /**
     * Select the latest version
     * @return the selected version
     */
    public String select() {
        if (!cores.isEmpty())
            return cores.last();
        if (!coreapps.isEmpty())
            return coreapps.last();
        if (!standards.isEmpty())
            return standards.last();
        if (!frameworks.isEmpty())
            return frameworks.last();
        else
            return DEFAULT;
    }
}
