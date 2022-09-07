package jext.sourcecode.project.python.util;

import jext.maven.MavenCoords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

/*
    https://pip.pypa.io/en/stable/reference/requirements-file-format/
    https://pip.pypa.io/en/stable/cli/pip_install/#pip-install-examples
    https://pip.pypa.io/en/stable/reference/requirement-specifiers/#requirement-specifiers
    https://peps.python.org/pep-0508/

    pip freeze  > requirements.txt
    pip install -r requirements.txt


    <library>
    <library> == <version>
    <library> >= <version>, < <version>
    <library> ~= <version>

    <library>[<label>,...]

    <library> ; <platform> == '...'

    Some extensions
    ---------------

    -r other-requirements.txt       (import)
    library @ url                   (skipped)


    ~= meaning
    ----------

    mock-django~=0.6.10
    It means it will select the latest version of the package, greater than or equal to 0.6.10,
    but still in the 0.6.* version, so it won't download 0.7.0 for example. It ensures you will
    get security fixes but keep backward-compatibility, if the package maintainer respects the
    semantic versioning (which states that breaking changes should occur only in major versions).

 */

public class Requirements {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final int MAX_DEPTH = 8;

    private File requirementsFile;
    private List<MavenCoords> libraries;
    private int depth;  // used to avoid infinite recursions

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Requirements(File requirementsFile) {
        if (requirementsFile.isDirectory())
            requirementsFile = new File(requirementsFile, "requirements.txt");
        this.requirementsFile = requirementsFile;
        this.depth = 0;
    }

    private Requirements(File requirementsFile, int depth) {
        if (requirementsFile.isDirectory())
            requirementsFile = new File(requirementsFile, "requirements.txt");
        this.requirementsFile = requirementsFile;
        this.depth = depth;
    }


    public MavenCoords getLibrary(String name) {
        for(MavenCoords coords : libraries)
            if (coords.artifactId.equals(name))
                return coords;
        return MavenCoords.of(MavenCoords.NONE, name, MavenCoords.NONE);
    }

    public List<MavenCoords> getLibraries() {
        if (libraries != null)
            return libraries;

        if (!requirementsFile.exists() || depth > MAX_DEPTH)
            return Collections.emptyList();

        List<String> lines = parseFile();
        libraries = new ArrayList<>();

        for (String line : lines) {
            if (line.startsWith("-r"))
                parseImport(line);
            else
                parseLibrary(line);
        }

        return libraries;
    }

    private void parseLibrary(String line) {
        MavenCoords coords = parseLine(line);
        if (coords != null)
            libraries.add(coords);
    }

    private void parseImport(String line) {
        String importedPath = line.substring(2).trim();
        File currentDirectory = this.requirementsFile.getParentFile();
        File importedFile = new File(currentDirectory, importedPath);

        // depth used to avoid infinite recursion
        Requirements importedRequirements = new Requirements(importedFile, depth+1);
        libraries.addAll(importedRequirements.getLibraries());
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private List<String> parseFile() {
        List<String> lines = new ArrayList<>();

        String line;
        String previous = "";
        try(BufferedReader rdr = new BufferedReader(new FileReader(requirementsFile))) {
            while ((line = rdr.readLine()) != null) {
                line = line.trim();

                // skip empty lines or '.'
                if (line.length() <= 1)
                    continue;

                // comments: A line that begins with # is treated as a comment and ignored
                if (line.startsWith("#"))
                    continue;

                // line continuation: A line ending in an unescaped \
                // is treated as a line continuation and the newline following it is effectively ignored
                if (line.endsWith("\\")) {
                    int len = line.length();
                    previous = previous + " " + line.substring(0, len-1).trim();
                    continue;
                }

                if (previous.length() > 0) {
                    line = previous + " " + line;
                    previous = "";
                }

                // support      -r filepath
                // unsupported  -r http://...

                if (line.startsWith("-r ") && !lines.contains("://")) {
                    lines.add(line);
                    continue;
                }

                // supported options: to handle (in the future!)
                //
                //      [-e] <local project path>
                //      [-e] <vcs project url>
                //
                // not processed
                //
                //      -i -c -e -f
                //      --index-url --no-index --contraint --requirements --editable --find-links --no-binary ..
                if (line.startsWith("-e ") || line.startsWith("--editable "))
                    continue;

                if (line.startsWith("--") || line.startsWith("-"))
                    continue;

                // for now we skip 'http://', 'https://', 'ftp://' or in general 'protocol://...'
                if (line.contains("://"))
                    continue;

                lines.add(line);
            }
        }
        catch (IOException e) { }

        return lines;
    }

    /*
        version ::= <major>
                    <major>.<minor>
                    <major>.<minor>.<patch>
                    <major>.<minor>.*
                    <major>.*
                    *

        op ::=  == > >= <= < ~= !=

        requirement ::=
            library [; system]
            library [@ url]

        library
            name support?

        system ::=

        name
        name op version
        name op version, op version

        Note: PipLibrary can be null!
     */
    private static MavenCoords parseLine(String line) {
        int pos, end;
        String name;
        String version = "";

        // line :  name ((op version) (, (op version))*)? (; other)?

        // remove '; ...' if present
        pos = line.indexOf(';');
        if (pos != -1)
            line = line.substring(0, pos);

        // remove '@ ...' if present
        pos = line.indexOf('@');
        if (pos != -1)
            line = line.substring(0, pos);

        // remove '[...]' if present
        pos = line.indexOf('[');
        if (pos != -1) {
            end = line.indexOf(']');
            line = line.substring(0, pos) + line.substring(end+1);
        }

        // extract name from 'name ((op version) (, (op version))*)?'
        pos = wordEnd(line);
        if (pos != -1) {
            name = line.substring(0, pos);
            line = line.substring(pos).trim();
        }
        else {
            name = line;
            line = "";
        }

        // just to be sore that the library has a valid name
        if (name.isEmpty())
            return null;

        if (line.isEmpty())
            return MavenCoords.of(name, version);

        // extract version from '((op version) (, (op version))*)?'
        String[] versions = line.split(",");
        if (versions.length == 0)
            return MavenCoords.of(name, version);

        // in theory there are multiple rules to specify the version.
        // we select just the first one
        version = vstrip(versions[0]);

        return MavenCoords.of(name, version);
    }

    private static int wordEnd(String s) {
        // '<' | '<=' | '!=' | '==' | '>=' | '>' | '~=' | '==='
        // name op ...
        // name=...
        // name<...
        // name>...
        int p,e = s.length();
        p = s.indexOf(' ');
        if (p != -1) e = min(e, p);
        p = s.indexOf('<');
        if (p != -1) e = min(e, p);
        p = s.indexOf('>');
        if (p != -1) e = min(e, p);
        p = s.indexOf('=');
        if (p != -1) e = min(e, p);
        p = s.indexOf('!');
        if (p != -1) e = min(e, p);
        p = s.indexOf('~');
        if (p != -1) e = min(e, p);
        return e;
    }

    private static String vstrip(String s) {
        while(startsWithOneOf(s, " <>=~!"))
            s = s.substring(1);
        return s;
    }

    private static boolean startsWithOneOf(String s, String pset) {
        if (s.length() == 0)
            return false;

        char c = s.charAt(0);
        int n = pset.length();
        for (int i=0; i<n; ++i) {
            char p = pset.charAt(i);
            if (c == p)
                return true;
        }
        return false;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
