package jext.sourcecode.project.lfm;

import jext.configuration.Configuration;
import jext.configuration.HierarchicalConfiguration;
import jext.util.logging.Logger;
import jext.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibraryConfiguration {

    // ----------------------------------------------------------------------
    // Factory method
    // ----------------------------------------------------------------------

    public static LibraryConfiguration ref(String name, String version, String ref) {
        return new LibraryConfiguration(name, version, ref);
    }

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    private HierarchicalConfiguration configuration;
    private List<String> names;
    private String version;
    private String platform;
    private String ref;
    private final List<File> files = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LibraryConfiguration() {

    }

    private LibraryConfiguration(String name, String version, String ref) {
        this.names = Collections.singletonList(name);
        this.ref = ref;
        this.version = version;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public List<String> getNames() {
        return names;
    }

    public String getVersion() {
        return version;
    }

    public String getPlatform() {
        return platform;
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

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void configure(Configuration configuration) {
        this.configuration = (HierarchicalConfiguration) configuration;

        // <library name='...' .../>
        // <path    name='...' .../>
        String names = configuration.getString("@name");
        this.names = StringUtils.split(names, ",");
        // this.name = this.names.get(0);

        // <library ... version='...' />
        // <path    ... version='...' />
        // Note: it will be responsibility to identify the correct library version
        // based on the library name
        this.version = configuration.getString("@version", "");

        // <library ... ref='...' />
        this.ref = configuration.getString("@ref", "");

        // <library ... platform='...' />
        // <library ... os='...' />
        this.platform = configuration.getString("@platform", "");
        if (this.platform.isEmpty())
            this.platform = configuration.getString("@os", "");

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
            Logger.getLogger("library." + this.names.get(0)).errorf("No library files configured");
    }

    private void addPath(String path) {
        if (!StringUtils.isEmpty(path))
            files.add(new File(path));
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
