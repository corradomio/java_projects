package jext.sourcecode.project.info;

import jext.maven.MavenDownloader;
import jext.name.Name;
import jext.name.Named;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.util.HashMap;
import jext.util.JSONUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Predicate;

public class InfoProject implements Project {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String TYPE = "info";
    public static final String INFO_FILE = "project-info.json";

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Name name;
    private String id;
    private File infoFile;
    private File projectHome;
    private Properties properties;
    private LibraryFinder lfinder;

    private Map<String, Object> info;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public InfoProject(String projectName, File projectHome, Properties properties) {
        this.name = new PathName(projectName);
        this.properties = properties;
        if (projectHome.isFile()) {
            this.infoFile = projectHome;
            this.projectHome = this.infoFile.getAbsoluteFile().getParentFile();
            if (this.projectHome.getName().startsWith("."))
                this.projectHome = this.projectHome.getParentFile();
        }
        else {
            this.projectHome = projectHome;
            this.infoFile = new File(projectHome,"project-info.json");
            if (!this.infoFile.exists())
                this.infoFile = new File(projectHome,".spl/project-info.json");
        }
        this.id = Integer.toString(name.getFullName().hashCode(), 16);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public void setResourceFilter(Predicate<String> selector) {

    }

    @Override
    public String getProjectType() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public File getProjectHome() {
        return projectHome;
    }

    @Override
    public Project setLibraryFinder(LibraryFinder lfinder) {
        this.lfinder= lfinder;
        return this;
    }

    @Override
    public LibraryFinder getLibraryFinder() {
        return lfinder;
    }

    @Override
    public MavenDownloader getLibraryDownloader() {
        return lfinder.getDownloader();
    }

    @Override
    public List<Module> getModules() {
        return null;
    }

    @Override
    public Module getModule(String nameOrId) {
        return null;
    }

    @Override
    public Set<Library> getLibraries() {
        return null;
    }

    @Override
    public Library getLibrary(String nameOrId) {
        return null;
    }

    @Override
    public double getComplexity(double threshold) {
        return 0;
    }

    @Override
    public void abort() {

    }

    @Override
    public boolean isAborted() {
        return false;
    }

    @Override
    public int compareTo(@NotNull Named o) {
        return name.compareTo(o.getName());
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void check() {
        if (info != null)
            return;

        try {
            info = JSONUtils.load(infoFile, HashMap.class);
        } catch (IOException e) {
            info = Collections.emptyMap();
        }
    }
}
