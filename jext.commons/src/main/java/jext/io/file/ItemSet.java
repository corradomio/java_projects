package jext.io.file;

import jext.logging.Logger;
import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
        <directory name="..."  includes="pat1,pat2 pat3" excludes="pat1 pat2 pat3">
             <include name="pattern"/>
             <exclude name="pattern"/>
        </directory>

        <filename name="pattern"/>
 */

public class ItemSet {

    private final Logger logger = Logger.getLogger(FileSet.class);

    private String relativeDir;
    private FilePatterns includes = new FilePatterns();
    private FilePatterns excludes = new FilePatterns();
    private boolean recursive;
    private boolean directory;
    protected boolean selectFiles;
    protected boolean selectDirs;

    public ItemSet() {
        this.relativeDir = "";
        this.selectFiles = true;
        this.selectDirs = true;
    }

    public void configure(Element elt) {
        directory = elt.getTagName().equals("directory");
        if (directory)
            configureDirectory(elt);
        else
            configureFilename(elt);
    }

    private void configureFilename(Element elt) {
        relativeDir = "";
        String pattern = XPathUtils.getValue(elt, "@name");
        add(pattern, false);
    }

    private void configureDirectory(Element elt) {
        String inline;
        String[] patterns;

        relativeDir = XPathUtils.getValue(elt, "@name", "");
        if (".".equals(relativeDir))
            relativeDir = "";

        inline = XPathUtils.getValue(elt, "@includes", "");
        patterns = inline.split("[, ]");
        for(String pattern : patterns)
            add(pattern, false);
        inline = XPathUtils.getValue(elt, "@excludes", "");
        patterns = inline.split("[, ]");
        for(String pattern : patterns)
            add(pattern, true);

        XPathUtils.selectNodes(elt, "include")
                .forEach(incl -> {
                    String pattern = XPathUtils.getValue(incl, "@name");
                    add(pattern, false);
                });

        XPathUtils.selectNodes(elt, "exclude")
                .forEach(incl -> {
                    String pattern = XPathUtils.getValue(incl, "@name");
                    add(pattern, true);
                });

        // add default
        //
        //      <include name="*"/>
        //      <include name="**"/>   (recursive)
        //
        // if it is specified only <exclude name="..."/>
        if (includes.isEmpty())
            if (directory)
                add("**", false);
            else
                add("*", false);
    }

    public void addPattern(String pattern, boolean exclude) {
        if (isExtension(pattern))
            add("**/*" + pattern, exclude);
        else if (isName(pattern))
            add("**/" + pattern + "/**", exclude);
        else
            add(pattern, exclude);
    }

    private static boolean isExtension(String pattern) {
        return pattern.startsWith(".");
    }

    private static boolean isName(String pattern) {
        return !(pattern.contains("/") ||
                 pattern.contains("?") ||
                 pattern.contains("*"));
    }

    private void add(String pattern, boolean exclude) {
        if (pattern.isEmpty())
            return;
        FilePattern fpat = new FilePattern(pattern);
        if (exclude)
            excludes.add(fpat);
        else
            includes.add(fpat);
        recursive |= fpat.recursive;
    }

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
        if (files == null || files.length == 0)
            return Collections.emptyList();

        return FileUtils.asList(files)
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
