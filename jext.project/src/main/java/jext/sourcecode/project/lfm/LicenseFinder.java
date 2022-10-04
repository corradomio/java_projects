package jext.sourcecode.project.lfm;

import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LicenseFinder {

    public static String findLicense(File libraryDirectory) {
        return new LicenseFinder(libraryDirectory).find();
    }

    private File directory;

    private LicenseFinder(File directory) {
        if (directory.isFile())
            directory = directory.getParentFile();
        this.directory = directory;
    }

    private String find() {
        String license = null;
        if (license == null)
            license = findUsingMavenPom();
        if (license == null)
            license = findUsingLicenseFile();
        if (license == null)
            license = findUsingLastWord();
        if (license == null)
            return "Undefined";
        else
            return license;
    }

    private String findUsingMavenPom() {
        File pomFile = findPomFile();
        if (pomFile == null)
            return null;

        /*
            <licenses>
                <license>
                    <name>The Apache Software License, Version 2.0</name>
                    <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
                    <distribution>repo</distribution>
                </license>
            </licenses>
         */

        try {
            Element root = XPathUtils.parse(pomFile).getDocumentElement();
            return XPathUtils.getValue(root, "/licenses/license/name/#text");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            // none to do
        }
        return null;
    }

    private File findPomFile() {
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".pom"));
        if (files == null || files.length == 0)
            return null;
        else
            return files[0];
    }

    private static String[][] LICENSES = {
            {"Please see ",   "Delegate license"},
            {"Please refer ", "Delegate license"},
            {"BSD license", "BSD License"},
            {"ISC license", "ISC license"},
            {"SOFTWARE IS PROVIDED 'AS IS'", "BSD License"},
            {"Mozilla ", "Mozilla License"},
            {"GNU GENERAL PUBLIC LICENSE", "GNU GPL License"},
            {"GNU LESSER GENERAL PUBLIC LICENSE", "GNU LGPL License"},
            {"GNU GPL", "GNU GPL License"},
            {"GNU LGPL", "GNU LGPL License"},
            {"MIT License", "MIT License"},
            {"Terence Parr and Sam Harwell", "BSD License"},
            {"Version 2.0, January 2004", "Apache License"},
            {"Apache License", "Apache License"},
            {"Redistributions of source code must retain", "BSD License"},
            {"Creative Commons", "Creative Commons License"},
            {"creativecommons", "Creative Commons License"},
            {"Python Software Foundation License", "Python License"},
            {" MIT ", "MIT License"},
            {" Oracle ", "Oracle License"},
            {"Artistic License", "Artistic License"},
            {"HPND License", "HPND License"},
            {"LICENSE.APACHE", "Apache License"},
            {"Zope Public License", "Zope Public License"},
            {"www.apache.org/licenses", "Apache License"}
    };

    static {
        for(int i=0; i<LICENSES.length; ++i)
            LICENSES[i][0] = LICENSES[i][0].toLowerCase();
    }

    private static final int MAX_LINES = 100;

    private String findUsingLicenseFile() {
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().contains("license"));
        if (files == null || files.length == 0)
            return null;

        for(File file : files) {
            List<String> lines = FileUtils.toStrings(file);
            int iline =0;
            for (String line : lines) {
                if (iline >= MAX_LINES)
                    break;
                if (line.isEmpty())
                    continue;

                line = line.toLowerCase();
                while (line.contains("  "))
                    line = line.replace("  ", " ");
                if (line.contains("\""))
                    line = line.replace("\"", "'");
                if (line.contains("`"))
                    line = line.replace("`", "'");
                if (line.contains("''"))
                    line = line.replace("''", "'");
                if (line.contains("-"))
                    line = line.replace("-", " ");

                for (int i=0; i<LICENSES.length; ++i) {
                    String pattern = LICENSES[i][0];
                    if (line.contains(pattern))
                        return LICENSES[i][1];
                }

                iline += 1;
            }
        }

        return null;
    }

    private String findUsingLastWord() {
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().contains("license"));
        if (files == null || files.length == 0)
            return null;

        for(File file : files) {
            List<String> lines = FileUtils.toStrings(file);
            int iline =0;
            for (String line : lines) {
                if (iline >= MAX_LINES)
                    break;

                String lcline = line.trim().toLowerCase();
                if (lcline.endsWith("license"))
                    return line;

                iline += 1;
            }
        }

        return null;
    }
}
