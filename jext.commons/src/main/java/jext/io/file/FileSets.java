package jext.io.file;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
    <parent>
        <directory ...>...</directory>
        <filename  ...>...</filename>
    </parent>
 */

public class FileSets {

    private List<FileSet> fileSets = new ArrayList<>();

    public void configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        XPathUtils.selectElements(selected, "directory")
                .forEach(this::configureFileSet);

        XPathUtils.selectElements(selected, "filename")
                .forEach(this::configureFileSet);
    }

    public void configureFileSet(Element elt) {
        FileSet fileSet = new FileSet();
        fileSet.configure(elt);
        this.fileSets.add(fileSet);
    }

    public FileSets addAll(FileSets that) {
        fileSets.addAll(that.fileSets);
        return this;
    }

    public List<String> getDirs() {
        return fileSets.stream().map(FileSet::getDir).collect(Collectors.toList());
    }

    public List<File> getFiles(File baseDir) {

        List<String> dirs = new ArrayList<>();
        List<File> files = new ArrayList<>();

        // on the directory list
        //
        //      src/main/java
        //      src/main
        //      src
        //
        // uses ONLY the MORE SPECIFIC directory
        //

        fileSets.forEach(fileSet -> {
            String dir = fileSet.getDir();
            if (isParentOf(dir, dirs))
                return;
            files.addAll(fileSet.getFiles(baseDir));
            dirs.add(dir);
        });

        return files;
    }

    private boolean isParentOf(String dir, List<String> dirs) {
        for (String adir : dirs)
            if (adir.startsWith(dir))
                return true;
        return false;
    }

}
