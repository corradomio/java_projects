package jext.io.file;

import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static jext.util.ArrayUtils.asList;

/*
        <directory name="..."  includes="pat1,pat2 pat3" excludes="pat1 pat2 pat3">
             <include name="pattern"/>
             <exclude name="pattern"/>
        </directory>

        <filename name="pattern"/>
 */

public class ItemSet {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    public static final String NAME = "@name";

    private String relativeDir;
    private FilePatterns includes = new FilePatterns();
    private FilePatterns excludes = new FilePatterns();
    private boolean recursive;
    private boolean directory;
    protected boolean selectFiles;
    protected boolean selectDirs;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ItemSet() {
        this.relativeDir = "";
        this.selectFiles = true;
        this.selectDirs = true;
    }

    // ----------------------------------------------------------------------
    // Configure using XML
    // ----------------------------------------------------------------------

    public void configure(Element elt) {
        directory = elt.getTagName().equals("directory");
        if (directory)
            configureDirectory(elt);
        else
            configureFilename(elt);
    }

    private void configureFilename(Element elt) {
        relativeDir = "";
        String pattern = XPathUtils.getValue(elt, NAME);
        addPattern(pattern, false);
    }

    private void configureDirectory(Element elt) {
        String inline;
        String[] patterns;

        relativeDir = XPathUtils.getValue(elt, NAME, "");
        if (".".equals(relativeDir))
            relativeDir = "";

        inline = XPathUtils.getValue(elt, "@includes", "");
        patterns = inline.split("[, ]");
        for(String pattern : patterns)
            addPattern(pattern, false);
        inline = XPathUtils.getValue(elt, "@excludes", "");
        patterns = inline.split("[, ]");
        for(String pattern : patterns)
            addPattern(pattern, true);

        XPathUtils.selectElements(elt, "include")
                .forEach(incl -> {
                    String pattern = XPathUtils.getValue(incl, NAME);
                    addPattern(pattern, false);
                });

        XPathUtils.selectElements(elt, "exclude")
                .forEach(incl -> {
                    String pattern = XPathUtils.getValue(incl, NAME);
                    addPattern(pattern, true);
                });

        // add default
        //
        //      <include name="*"/>
        //      <include name="**"/>   (recursive)
        //
        // if it is specified only <exclude name="..."/>
        if (includes.isEmpty())
            if (directory)
                addPattern("**", false);
            else
                addPattern("*", false);
    }

    // ----------------------------------------------------------------------
    // Configure by code
    // ----------------------------------------------------------------------

    public void addAll(Collection<String> paterns, boolean exclude) {
        for (String pattern : paterns)
            add(pattern, exclude);
    }

    public void add(String pattern) {
        add(pattern, false);
    }

    public void add(String pattern, boolean exclude) {
        if (isExtension(pattern))
            addPattern("**/*" + pattern, exclude);
        else if (isName(pattern))
            addPattern("**/" + pattern + "/**", exclude);
        else
            addPattern(pattern, exclude);
    }

    // ----------------------------------------------------------------------
    // Configure by code
    // ----------------------------------------------------------------------

    private static boolean isExtension(String pattern) {
        return pattern.startsWith(".") &&
                !pattern.contains("*") &&
                !pattern.contains("?");
    }

    private static boolean isName(String pattern) {
        return !(pattern.contains("/") ||
                 pattern.contains("?") ||
                 pattern.contains("*"));
    }

    private void addPattern(String pattern, boolean exclude) {
        if (pattern.isEmpty())
            return;
        FilePattern fpat = new FilePattern(pattern);
        if (exclude)
            excludes.add(fpat);
        else
            includes.add(fpat);
        recursive |= fpat.recursive;
    }

    // ----------------------------------------------------------------------
    // Get files
    // ----------------------------------------------------------------------

    public String getDir() {
        return relativeDir;
    }

    public List<File> getFiles(File baseDir) {
        if (!relativeDir.isEmpty())
            baseDir = new File(baseDir, relativeDir);

        if (!baseDir.exists() || !baseDir.isDirectory())
            return Collections.emptyList();
        if (recursive)
            return getRecursiveFiles(baseDir);
        else
            return getCurrentFiles(baseDir);
    }

    private List<File> getCurrentFiles(File baseDir) {
        File[] files = baseDir.listFiles(File::isFile);
        return asList(files)
                .stream()
                .filter(file -> accept(baseDir, file))
                .collect(Collectors.toList());
    }

    private List<File> getRecursiveFiles(File baseDir) {
        List<File> selected = new ArrayList<>();

        try {
            Files.walkFileTree(baseDir.toPath(), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    if (selectDirs) {
                        File file = dir.toFile();
                        if (accept(baseDir, file))
                            selected.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (selectFiles) {
                        File file = path.toFile();
                        if (accept(baseDir, file))
                            selected.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) { }

        return selected;
    }

    private boolean accept(File baseDir, File file) {
        return includes.accept(baseDir, file)
                && !excludes.accept(baseDir, file);
    }

}
