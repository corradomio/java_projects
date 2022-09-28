package jext.sourcecode.project.lfm;

import jext.configuration.Configuration;
import jext.configuration.HierarchicalConfiguration;
import jext.logging.Logger;
import jext.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LibraryConfiguration {

    private HierarchicalConfiguration configuration;
    private List<String> names;
    private String name;
    private String version;
    private String ref;
    private final List<File> files = new ArrayList<>();

    public void configure(Configuration configuration) {
        this.configuration = (HierarchicalConfiguration) configuration;

        // <library name='...' .../>
        // <path    name='...' .../>
        String names = configuration.getString("@name");
        this.names = StringUtils.split(names, ",");
        this.name = this.names.get(0);

        // <library ... version='...' />
        // <path    ... version='...' />
        this.version = configuration.getString("@version", "1.0");

        // <library ... ref='...' />
        this.ref = configuration.getString("@ref", "");

        // <path    ... value='...'/>
        // <library ... path="..."/>
        addPath(configuration.getString("@value"));
        addPath(configuration.getString("@path"));

        // <library ...>
        //      <path value='...'/>
        //      ...
        // </library>
        this.configuration.configurationsAt("path")
            .forEach(pathConfiguration -> {
                addPath(pathConfiguration.getString("@value"));
            });

        if (ref.isEmpty() && files.isEmpty())
            Logger.getLogger("library." + this.name).errorf("No library files configured");
    }

    public List<String> getNames() {
        return names;
    }

    public String getVersion() {
        return version;
    }

    public String getRef() {
        return ref;
    }

    public File getFile() {
        if (files.isEmpty())
            return new File("/nofiles");
        else
            return files.get(0);
    }

    public List<File> getFiles() {
        return files;
    }

    private void addPath(String path) {
        if (!StringUtils.isEmpty(path))
            files.add(new File(path));
    }

}
