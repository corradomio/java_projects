package jext.sourcecode.project.info;

import jext.maven.MavenDownloader;
import jext.name.Name;
import jext.name.Named;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryRepository;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.info.library.InfoInvalidLibrary;
import jext.sourcecode.project.info.library.InfoLocalLibrary;
import jext.sourcecode.project.info.library.InfoMavenLibrary;
import jext.sourcecode.project.info.library.InfoRTLibrary;
import jext.sourcecode.project.info.library.LibrarySet;
import jext.sourcecode.project.maven.MavenRepository;
import jext.util.JSONUtils;
import jext.util.MapUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    private Predicate<String> selector;
    private LibraryFinder lfinder;

    private Map<String, Object> info;
    private List<Module> modules;
    private Map<String, Module> moduleMap;
    //private Set<Library> libraries;
    private LibrarySet libraries;
    // private Map<String, Library> libraryMap;
    private List<Source> sources;
    private Map<String, Source> sourceMap;

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
        this.selector = (p) -> true;

        load();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void setResourceFilter(Predicate<String> selector) {
        this.selector = selector;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getId() {
        return MapUtils.get(info, "id");
    }

    @Override
    public Name getName() {
        return name;
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

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    @Override
    public List<Source> getSources() {
        if (sources != null)
            return sources;

        sources = new ArrayList<>();
        for (Module module : getModules())
            sources.addAll(module.getSources());

        return sources;
    }

    @Override
    public Source getSource(String sourceId) {
        if (sourceMap == null) {
            sourceMap = new HashMap<>();
            for (Source source : getSources())
                sourceMap.put(source.getId(), source);
        }
        return sourceMap.get(sourceId);
    }

    // ----------------------------------------------------------------------
    // Modules
    // ----------------------------------------------------------------------

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

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public Project setLibraryFinder(LibraryFinder lfinder) {
        this.lfinder= lfinder;
        this.lfinder.setProject(this);
        return this;
    }

    @Override
    public LibraryFinder getLibraryFinder() {
        return lfinder;
    }

    @Override
    public MavenDownloader getLibraryDownloader() {
        MavenDownloader md = lfinder.getLibraryDownloader();
        getLibraryRepositories().forEach(librepo -> {
            md.addRepository(librepo.getUrl());
        });
        return md;
    }

    @Override
    public LibrarySet getLibraries() {
        if (libraries != null)
            return libraries;

        libraries = new LibrarySet();
        List<Map<String, Object>> linfos = MapUtils.get(info, "libraries");
        for(Map<String, Object> linfo : linfos) {
            Library l = composeLibrary(linfo);
            libraries.add(l);
        }
        List<Map<String, Object>> uinfos = MapUtils.get(info, "unusedLibraries");
        for(Map<String, Object> uinfo : uinfos) {
            Library l = composeLibrary(uinfo);
            libraries.add(l);
        }
        List<Map<String, Object>> rtinfos = MapUtils.get(info, "runtimeLibraries");
        for(Map<String, Object> rtinfo : rtinfos) {
            Library l = composeLibrary(rtinfo);
            libraries.add(l);
        }

        return libraries;
    }

    @Override
    public Set<Library> getLibraries(Module module) {
        LibrarySet libraries = getLibraries();
        return libraries.resolveAll(module.getDeclaredLibraries());
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
        LibrarySet libraries = getLibraries();
        return libraries.get(nameOrId);
    }

    @Override
    public Library getLibrary(Library library) {
        LibrarySet projectLibraries = (LibrarySet) getLibraries();
        return projectLibraries.resolve(library);
    }

    @Override
    public Set<LibraryRepository> getLibraryRepositories() {
        List<String> urls = MapUtils.get(info, "mavenRepositories");
        return urls.stream()
            .map(MavenRepository::new)
            .collect(Collectors.toSet());
    }

    @Override
    public LibraryRepository getLibraryRepository(String librepoId) {
        for (LibraryRepository librepo : getLibraryRepositories()) {
            if (librepo.getId().equals(librepoId))
                return librepo;
        }

        return null;
    }

    // ----------------------------------------------------------------------
    // Extras
    // ----------------------------------------------------------------------

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

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    boolean isAccepted(String path) {
        return this.selector.test(path);
    }

    private void load() {
        if (info != null)
            return;

        try {
            info = JSONUtils.load(infoFile, HashMap.class);
        } catch (IOException e) {
            info = Collections.emptyMap();
        }
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Project that = (Project) obj;
        return name.equals(that.getName());
    }

    @Override
    public int compareTo(Named o) {
        return name.compareTo(o.getName());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
