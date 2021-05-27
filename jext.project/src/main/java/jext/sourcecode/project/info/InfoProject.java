package jext.sourcecode.project.info;

import jext.maven.MavenDownloader;
import jext.name.Name;
import jext.name.Named;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.info.library.InfoInvalidLibrary;
import jext.sourcecode.project.info.library.InfoLocalLibrary;
import jext.sourcecode.project.info.library.InfoMavenLibrary;
import jext.sourcecode.project.info.library.InfoRTLibrary;
import jext.util.HashMap;
import jext.util.JSONUtils;
import jext.util.MapUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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

    private final Name name;
    private File infoFile;
    private File projectHome;
    private Properties properties;
    private LibraryFinder lfinder;

    private Map<String, Object> info;
    private List<Module> modules;
    private Map<String, Module> moduleMap;
    private Set<Library> libraries;
    private Map<String, Library> libraryMap;

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

        load();
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public String getId() {
        return MapUtils.get(info, "id");
    }

    @Override
    public void setResourceFilter(Predicate<String> selector) {

    }

    @Override
    public String getProjectType() {
        return MapUtils.get(info, "type");
    }

    @Override
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.putAll(MapUtils.get(info, "properties"));
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
        if (modules != null)
            return modules;

        modules = new ArrayList<>();
        Map<String, Object> mminfos = MapUtils.get(info, "modules");
        for (String mname: mminfos.keySet()) {
            Map<String, Object> mminfo = MapUtils.get(mminfos, mname);
            modules.add(new InfoModule(this, mminfo));
        }

        return modules;
    }

    @Override
    public Module getModule(String nameOrId) {
        if (moduleMap == null) {
            moduleMap = new java.util.TreeMap<>();
            getModules().forEach(m -> {
                moduleMap.put(m.getId(), m);
                moduleMap.put(m.getName().getFullName(), m);
            });
        }

        if (moduleMap.containsKey(nameOrId))
            return moduleMap.get(nameOrId);

        // List<Module> modules = getModules();
        // for (Module m : modules) {
        //     if (m.getName().getFullName().equals(nameOrId))
        //         return m;
        //     if (m.getId().equals(nameOrId))
        //         return m;
        //     if (m.getName().getName().equals(nameOrId))
        //         return m;
        // }

        return null;
    }

    @Override
    public Set<Library> getLibraries() {
        if (libraries != null)
            return libraries;

        libraries = new HashSet<>();
        List<Map<String, Object>> linfos = MapUtils.get(info, "libraries");
        for(Map<String, Object> linfo : linfos) {
            Library l = composeLibrary(linfo);
            libraries.add(l);
        }

        return libraries;
    }

    private Library composeLibrary(Map<String, Object> linfo) {
        LibraryType libraryType = LibraryType.valueOf(MapUtils.get(linfo, "libraryType"));
        if (LibraryType.MAVEN.equals(libraryType))
            return new InfoMavenLibrary(this, linfo);
        if (LibraryType.LOCAL.equals(libraryType))
            return new InfoLocalLibrary(this, linfo);
        if (LibraryType.RUNTIME.equals(libraryType))
            return new InfoRTLibrary(this, linfo);
        else
            return new InfoInvalidLibrary(this, linfo);
    }

    @Override
    public Library getLibrary(String nameOrId) {
        if (libraryMap == null) {
            libraryMap = new java.util.TreeMap<>();
            getLibraries().forEach(l -> {
                libraryMap.put(l.getId(), l);
                libraryMap.put(l.getName().getFullName(), l);
                libraryMap.put(l.getName().getName(), l);
            });
        }

        if (libraryMap.containsKey(nameOrId))
            return libraryMap.get(nameOrId);

        // Set<Library> libraries = getLibraries();
        // for (Library library : libraries) {
        //     if (library.getName().getFullName().equals(nameOrId))
        //         return library;
        //     if (library.getId().equals(nameOrId))
        //         return library;
        // }

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
    public int compareTo(Named o) {
        return name.compareTo(o.getName());
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void load() {
        if (info != null)
            return;

        try {
            info = JSONUtils.load(infoFile, HashMap.class);
        } catch (IOException e) {
            info = Collections.emptyMap();
        }
    }
}
