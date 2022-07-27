package jext.sourcecode.project.info;

import jext.logging.Logger;
import jext.name.Name;
import jext.name.Named;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryRepository;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.info.library.InfoInvalidLibrary;
import jext.sourcecode.project.info.library.InfoLocalLibrary;
import jext.sourcecode.project.info.library.InfoRemoteLibrary;
import jext.sourcecode.project.info.library.InfoRuntimeLibrary;
import jext.sourcecode.project.info.library.LibrarySet;
import jext.sourcecode.project.info.util.InfoSourcesImpl;
import jext.sourcecode.project.java.JavaConstants;
import jext.sourcecode.project.java.maven.MavenRepository;
import jext.sourcecode.project.python.GuessRuntimeLibrary;
import jext.sourcecode.project.util.ModulesImpl;
import jext.sourcecode.project.util.SourceInfo;
import jext.sourcecode.project.util.SourcesImpl;
import jext.util.FileUtils;
import jext.util.JSONUtils;
import jext.util.MapUtils;
import jext.util.StringUtils;

import java.io.File;
import java.io.IOException;
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
    private LibrarySet libraries;

    private ModulesImpl modules;
    private SourcesImpl sources;

    private Logger logger;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public InfoProject(String projectName, File infoFileOrProjectHome, Properties properties) {
        this.name = PathName.of(projectName);
        this.properties = properties;
        this.logger = Logger.getLogger(InfoProject.class, projectName);

        loadInfo(infoFileOrProjectHome);
        setProjectHome();
    }

    private void loadInfo(File infoFileOrProjectHome) {
        if (infoFileOrProjectHome.getName().endsWith(".json"))
            this.infoFile = infoFileOrProjectHome;
        else {
            String refId = Integer.toHexString(this.name.hashCode());
            this.infoFile = new File(infoFileOrProjectHome, String.format(".spl/%s-project-info.json", refId));
        }
        // if (infoFileOrProjectHome.isFile()) {
        //     this.infoFile = infoFileOrProjectHome;
        // }
        // else {
        //     this.infoFile = new File(infoFileOrProjectHome,"project-info.json");
        //     if (!this.infoFile.exists())
        //         this.infoFile = new File(infoFileOrProjectHome,".spl/project-info.json");
        // }
        this.selector = (p) -> true;

        try {
            this.info = JSONUtils.load(this.infoFile, HashMap.class);
        } catch (IOException e) {
            logger.errorf("Unable lo load JSON file %s: %s", FileUtils.getAbsolutePath(infoFile), e);
            this.info = Collections.emptyMap();
        }

    }

    private void setProjectHome() {
        // try {
        //     info = JSONUtils.load(infoFile, HashMap.class);
        // } catch (IOException e) {
        //     info = Collections.emptyMap();
        // }

        String projectHome = MapUtils.get(info, "projectHome");
        if (projectHome != null) {
            this.projectHome = new File(projectHome);
        }
        else {
            this.projectHome = infoFile.getParentFile();
            if (this.projectHome.getName().startsWith("."))
                this.projectHome = this.projectHome.getParentFile();
        }
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
        return MapUtils.get(info, "properties", Project.PROJECT_TYPE);
    }

    @Override
    public String getLanguage() {
        String language = MapUtils.get(info, "properties", Project.PROJECT_LANGUAGE);
        if (language == null)
            language = JavaConstants.JAVA;
        return language;
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
    public String getRuntimeLibrary() {
        String rtLibrary = properties.getProperty(RUNTIME_LIBRARY);
        String projectLanguage = properties.getProperty(PROJECT_LANGUAGE);
        if (!StringUtils.isEmpty(rtLibrary))
            return rtLibrary;

        if (Project.JAVA_PROJECT.equals(projectLanguage))
            return jext.sourcecode.project.java.GuessRuntimeLibrary.DEFAULT_JAVA_RUNTIME_LIBRARY;
        if (Project.PYTHON_PROJECT.equals(projectLanguage))
            return GuessRuntimeLibrary.DEFAULT_RUNTIME_LIBRARY;

        return rtLibrary;
    }

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        if (sources != null)
            return sources;

        sources = new InfoSourcesImpl(getProjectHome());
        for (Module module : getModules())
            sources.addAll(module.getSources());

        return sources;
    }

    // @Override
    // public Source getSource(String sourceId) {
    //     if (sourceMap == null) {
    //         sourceMap = new HashMap<>();
    //         for (Source source : getSources())
    //             sourceMap.put(source.getId(), source);
    //     }
    //     return sourceMap.get(sourceId);
    // }

    // ----------------------------------------------------------------------
    // Modules
    // ----------------------------------------------------------------------

    @Override
    public Modules getModules() {
        if (modules != null)
            return modules;

        modules = new ModulesImpl();
        Map<String, Object> mminfos = MapUtils.get(info, "modules");
        for (String mname: mminfos.keySet()) {
            Map<String, Object> mminfo = MapUtils.get(mminfos, mname);
            modules.add(new InfoModule(this, mminfo));
        }

        return modules;
    }

    // @Override
    // public Module getModule(String nameOrId) {
    //     if (moduleMap == null) {
    //         moduleMap = new java.util.TreeMap<>();
    //         getModules().forEach(m -> {
    //             moduleMap.put(m.getId(), m);
    //             moduleMap.put(m.getName().getFullName(), m);
    //         });
    //     }
    //
    //     if (moduleMap.containsKey(nameOrId))
    //         return moduleMap.get(nameOrId);
    //
    //     // List<Module> modules = getModules();
    //     // for (Module m : modules) {
    //     //     if (m.getName().getFullName().equals(nameOrId))
    //     //         return m;
    //     //     if (m.getId().equals(nameOrId))
    //     //         return m;
    //     //     if (m.getName().getName().equals(nameOrId))
    //     //         return m;
    //     // }
    //
    //     return null;
    // }

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
    public LibraryDownloader getLibraryDownloader() {
        LibraryDownloader md = lfinder.getDownloader();
        getLibraryRepositories().forEach(librepo -> {
            md.addRepository(librepo.getName(), librepo.getUrl());
        });
        return md;
    }

    @Override
    public LibrarySet getLibraries() {
        if (libraries != null)
            return libraries;

        libraries = new LibrarySet();
        Map<String, Map<String, Object>> libraries = MapUtils.get(info, "libraries");
        for(Map<String, Object> linfo : libraries.values()) {
            Library l = composeLibrary(linfo);
            this.libraries.add(l);
        }
        List<Map<String, Object>> uinfos = MapUtils.get(info, "unusedLibraries");
        for(Map<String, Object> uinfo : uinfos) {
            Library l = composeLibrary(uinfo);
            this.libraries.add(l);
        }
        List<Map<String, Object>> rtinfos = MapUtils.get(info, "runtimeLibraries");
        for(Map<String, Object> rtinfo : rtinfos) {
            Library l = composeLibrary(rtinfo);
            this.libraries.add(l);
        }

        return this.libraries;
    }

    @Override
    public Set<Library> getLibraries(Module module) {
        LibrarySet libraries = getLibraries();
        return libraries.resolveAll(module.getDeclaredLibraries());
    }

    private Library composeLibrary(Map<String, Object> linfo) {
        LibraryType libraryType = LibraryType.valueOf(MapUtils.get(linfo, "libraryType"));
        if (LibraryType.REMOTE.equals(libraryType))
            return new InfoRemoteLibrary(this, linfo);
        if (LibraryType.LOCAL.equals(libraryType))
            return new InfoLocalLibrary(this, linfo);
        if (LibraryType.RUNTIME.equals(libraryType))
            return new InfoRuntimeLibrary(this, linfo);
        else
            return new InfoInvalidLibrary(this, linfo);
    }

    // @Override
    // public Library getLibrary(String nameOrId) {
    //     LibrarySet libraries = getLibraries();
    //     return libraries.get(nameOrId);
    // }

    // @Override
    // public Library getLibrary(Library library) {
    //     LibrarySet projectLibraries = (LibrarySet) getLibraries();
    //     return projectLibraries.resolve(library);
    // }

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
    // Support for sourceInfo
    // ----------------------------------------------------------------------

    public void updateSourceInfo() {
        SourceInfo sourceInfo = new SourceInfo();
        if (!infoFile.exists())
            return;

        getModules().forEach(module -> {
            sourceInfo.modules += 1;
            module.getSources().forEach(source -> {
                SourceInfo sinfo = source.getSourceInfo();
                sourceInfo.add(sinfo);
            });
        });

        File baseDirectory = this.infoFile.getParentFile();
        String sourceInfoName = String.format("%s-source-source-info.json", getId());
        File sourceInfoFile = new File(baseDirectory, sourceInfoName);

        JSONUtils.save(sourceInfoFile, sourceInfo);
    }

    public SourceInfo getSourceInfo() {
        if (!infoFile.exists())
            return new SourceInfo();

        File baseDirectory = this.infoFile.getParentFile();
        String sourceInfoName = String.format("%s-source-source-info.json", getId());
        File sourceInfoFile = new File(baseDirectory, sourceInfoName);

        if (!sourceInfoFile.exists())
            updateSourceInfo();

        try {
            return JSONUtils.load(sourceInfoFile, SourceInfo.class);
        } catch (IOException e) {
            Logger.getLogger(InfoProject.class).errorf("Unable to load info file %s: %s", sourceInfoFile, e);
            return new SourceInfo();
        }
    }


    // ----------------------------------------------------------------------
    // Extras
    // ----------------------------------------------------------------------

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
