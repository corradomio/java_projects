package jext.sourcecode.project.lfm;

import jext.compress.Archives;
import jext.util.logging.Logger;
import jext.net.RemoteFileDownloader;
import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.annotation.Nullable;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LicenseFinder {

    // ----------------------------------------------------------------------
    // Find a license
    // ----------------------------------------------------------------------

    public static LibraryLicense findLicense(File file) {
        LibraryLicense license = new LicenseFinder(file).find();
        if (!license.isValid())
            logger.warnf("Invalid license: %s", license.getUrl());

        return license;
    }

    public static LibraryLicense findLicense(String licenseText, String url) {
        LibraryLicense license = new LicenseFinder().findUsingText(licenseText, url);
        if (!license.isValid())
            logger.warnf("Invalid license: %s", license.getUrl());

        return license;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger =Logger.getLogger(LicenseFinder.class);

    private final File file;
    private final File directory;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private LicenseFinder() {
        file = null;
        directory = null;
    }

    private LicenseFinder(File fileOrDirectory) {
        this.file = fileOrDirectory;
        this.directory = fileOrDirectory.isDirectory()
                ? fileOrDirectory
                : fileOrDirectory.getParentFile();
    }

    // ----------------------------------------------------------------------
    // Find a license
    // ----------------------------------------------------------------------

    private LibraryLicense find() {
        LibraryLicense license = null;

        if (license == null)
            license = findFromCompressedFile();
        if (license == null)
            license = findUsingFile();
        if (license == null)
            license = findUsingLicenseFile();
        if (license == null)
            license = findUsingMavenPom();
        if (license == null)
            license = findUsingNugetNuspec();
        if (license == null)
            license = findUsingLastWord();
        if (license == null)
            return LibraryLicense.none();
        else
            return license;
    }

    // -----------------------------------------------------------------------------------

    @Nullable
    private LibraryLicense findFromCompressedFile() {
        if (!Archives.isCompressed(file))
            return null;

        // check for META-INF/MANIFEST.MF
        try {
            String line;
            BufferedReader rdr = Archives.openText(file,"META-INF/MANIFEST.MF");
            for(line=rdr.readLine(); line != null; line=rdr.readLine()) {
                if (line.contains("Bundle-License"))
                    break;
            }

            if (line == null)
                return null;

            int p = line.indexOf(':');
            String url = line.substring(p+1).trim();
            String license = findLicenseUsingUrl(url);

            return LibraryLicense.of(license, url);

        } catch (IOException e) {
            ;
        }
        return null;
    }

    @Nullable
    private LibraryLicense findUsingFile() {
        String license = findLicenseFromFile(file);
        if (license == null)
            return null;
        else
            return LibraryLicense.of(license, file);
    }

    @Nullable
    private LibraryLicense findUsingText(String licenseText, String url) {
        List<String> lines = Arrays.asList(licenseText.split("\n"));
        String type = findLicenseFromText(lines);
        if (type == null)
            return LibraryLicense.NO_LICENSE;
        else
            return LibraryLicense.of(type, url);
    }

    // -----------------------------------------------------------------------------------

    @Nullable
    private LibraryLicense findUsingNugetNuspec() {
        File nuspecFile = FileUtils.findFile(directory, ".nuspec");
        if (nuspecFile == null)
            return null;

        /*
            <package>
                <metadata>
                    <license >...</>
                    <licenseUrl>...</>
                </>
            </>
         */
        try {
            Element root = XPathUtils.parse(nuspecFile).getDocumentElement();
            String license = XPathUtils.getValue(root, "metadata/license/#text", null);
            String url  = XPathUtils.getValue(root, "metadata/licenseUrl/#text");

            if (license == null)
                license = findLicenseTypeFromUrl(url);
            if (license == null)
                license = findLicenseUsingUrl(url);

            return LibraryLicense.of(license, url);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            // none to do
        }
        return null;
    }

    // -----------------------------------------------------------------------------------

    @Nullable
    private LibraryLicense findUsingMavenPom() {
        String license = null;
        File pomFile = FileUtils.findFile(directory, ".pom");
        if (pomFile == null)
            return null;

        // if (license == null)
        license = findFromMavenXML(pomFile);

        // not necessary: already tested
        // if (license == null)
        //     license = findLicenseFromFile(pomFile);

        return license != null ? LibraryLicense.of(license, pomFile) : null;
    }

    private String findFromMavenXML(File pomFile) {
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
            String license = XPathUtils.getValue(root, "licenses/license/name/#text", null);
            String url  = XPathUtils.getValue(root, "licenses/license/url/#text");
            if (license == null)
                license = findLicenseTypeFromUrl(url);
            if (license == null)
                return null;
            else
                return license;

        } catch (ParserConfigurationException | IOException | SAXException e) {
            // none to do
        }

        return null;
    }

    // -----------------------------------------------------------------------------------

    @Nullable
    private LibraryLicense findUsingLicenseFile() {
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().contains("license"));
        if (files == null || files.length == 0)
            return null;

        for(File file : files) {
            String license = findLicenseFromFile(file);
            if (license != null) {
                String url = String.format("file:///%s", FileUtils.getAbsolutePath(file));
                return LibraryLicense.of(license, url);
            }
        }

        return null;
    }

    // ----------------------------------------------------------------------

    private static String[][] URL_LICENSES = {
            {"MPL-1.1.txt ",   "Mozilla License"},
    };

    static {
        for(int i=0; i<URL_LICENSES.length; ++i)
            URL_LICENSES[i][0] = URL_LICENSES[i][0].toLowerCase();
    }

    @Nullable
    private String findLicenseUsingUrl(String url) {
        url = url.toLowerCase();

        for (int i = 0; i < URL_LICENSES.length; ++i) {
            String pattern = URL_LICENSES[i][0];
            if (url.endsWith(pattern)) {
                return URL_LICENSES[i][1];
            }
        }

        return null;
    }

    // ----------------------------------------------------------------------

    private static String[][] FILE_LICENSES = {
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
            {"www.apache.org/licenses", "Apache License"},
            {"MICROSOFT SOFTWARE LICENSE TERMS", "Microsoft License"}
    };

    static {
        for(int i = 0; i< FILE_LICENSES.length; ++i)
            FILE_LICENSES[i][0] = FILE_LICENSES[i][0].toLowerCase();
    }

    private static final int MAX_LINES = 100;

    @Nullable
    private String findLicenseFromFile(File file) {
        if (!file.exists() || file.isDirectory())
            return null;

        List<String> lines = FileUtils.toStrings(file);
        return findLicenseFromText(lines);
    }

    @Nullable
    private String findLicenseFromText(List<String> lines) {
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

            for (int i = 0; i < FILE_LICENSES.length; ++i) {
                String pattern = FILE_LICENSES[i][0];
                if (line.contains(pattern)) {
                    return FILE_LICENSES[i][1];
                }
            }

            iline += 1;
        }

        return null;
    }

    // -----------------------------------------------------------------------------------

    @Nullable
    private LibraryLicense findUsingLastWord() {
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
                if (lcline.endsWith("license")) {
                    String url = String.format("file:///%s", FileUtils.getAbsolutePath(file));
                    return LibraryLicense.of(line, url);
                }

                iline += 1;
            }
        }

        return null;
    }

    // -----------------------------------------------------------------------------------

    @Nullable
    private String findLicenseTypeFromUrl(String licenseUrl) {
        File licenseFile = new File(directory, "LICENSE.txt");

        if (!licenseFile.exists()) {
            try {
                RemoteFileDownloader.downloadFile(licenseFile, licenseUrl);
            } catch (IOException e) {
                // logger.error(e.getMessage());
            }
        }

        if (!isValid(licenseFile))
            FileUtils.delete(licenseFile);

        return findLicenseFromFile(licenseFile);
    }

    private static boolean isValid(File licenseFile) {
        if (!licenseFile.exists())
            return false;

        if (licenseFile.length() < 5)
            return false;

        // In general, a license file is NOT an HTML file
        String content = FileUtils.toString(licenseFile).trim();
        if (content.length() < 5 || content.substring(0, 5).toLowerCase().startsWith("<html"))
            return false;
        else
            return true;
    }

    // -----------------------------------------------------------------------------------
    // End
    // -----------------------------------------------------------------------------------

}
