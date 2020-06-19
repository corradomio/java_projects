package jext.buildtools.scan.rules;

import jext.buildtools.scan.util.FileSets;
import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.List;

public class Template {

    private String name;
    private String directory;

    private FileSets sources = new FileSets();
    private FileSets resources = new FileSets();
    private Dependencies dependencies = new Dependencies();
    private Repositories repositories = new Repositories();

    void configure(Element elt) {
        name = XPathUtils.getValue(elt, "@name");
        directory = XPathUtils.getValue(elt, "directory/@path", "");

        sources.configure(elt, "sources");
        resources.configure(elt, "resources");
        dependencies.configure(elt, "dependencies");
        repositories.configure(elt, "repositories");
    }

    public String getName() {
        return name;
    }

    public File getModuleDir(File baseDir) {
        if (FileUtils.isAbsolute(directory))
            return new File(directory);
        else if(!directory.isEmpty())
            return new File(baseDir, directory);
        else
            return baseDir;
    }

    public List<File> getSources(File baseDir) {
        return sources.getFiles(baseDir);
    }

    public List<File> getResources(File baseDir) {
        return resources.getFiles(baseDir);
    }

    public Dependencies getDependencies() {
        return dependencies;
    }

    public Template merge(Template that) {
        Template merged = new Template();

        merged.name = this.name;
        merged.directory = this.directory;

        merged.sources.addAll(this.sources);
        merged.sources.addAll(that.sources);

        merged.resources.addAll(this.resources);
        merged.resources.addAll(that.resources);
        return merged;
    }
}
