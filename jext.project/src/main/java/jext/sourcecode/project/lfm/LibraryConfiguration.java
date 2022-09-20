package jext.sourcecode.project.lfm;

import jext.configuration.Configuration;
import jext.configuration.HierarchicalConfiguration;
import jext.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LibraryConfiguration {

    private HierarchicalConfiguration configuration;
    private List<String> names;
    private String version;
    private String ref;
    private List<File> files = new ArrayList<>();

    public void configure(Configuration configuration) {
        this.configuration = (HierarchicalConfiguration) configuration;

        // <library name='...' .../>
        // <path    name='...' .../>
        String names = configuration.getString("@name");
        this.names = StringUtils.split(names, ",");

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
