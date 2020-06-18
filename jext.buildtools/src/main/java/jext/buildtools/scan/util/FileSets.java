package jext.buildtools.scan.util;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSets {

    private List<FileSet> fileSets = new ArrayList<>();

    public void configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        XPathUtils.selectNodes(selected, "directory")
                .forEach(this::configureFileSet);

        XPathUtils.selectNodes(selected, "filename")
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

    public List<File> getFiles(File baseDir) {

        List<File> sources = new ArrayList<>();

        fileSets.forEach(fileSet -> {
            sources.addAll(fileSet.getFiles(baseDir));
        });

        return sources;
    }

}
