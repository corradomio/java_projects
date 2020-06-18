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
                .forEach(incl -> {
                    FileSet fileSet = new FileSet();
                    fileSet.configure(incl);
                    this.fileSets.add(fileSet);
                });

        XPathUtils.selectNodes(selected, "filename")
                .forEach(incl -> {
                    FileSet fileSet = new FileSet();
                    fileSet.configure(incl);
                    this.fileSets.add(fileSet);
                });

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
