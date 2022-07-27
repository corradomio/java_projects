package jext.sourcecode.project.util;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.Version;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibrarySet;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.java.maven.MavenLibrary;
import jext.util.HashSet;
import jext.util.SetUtils;

import java.io.File;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Class used to collect all libraries but to keep ONLY the latest version
 */
public class LibrarySetImpl extends AbstractSet<Library> implements LibrarySet {

    private static Logger logger = Logger.getLogger(LibrarySetImpl.class);

    private Set<Library> localLibraries = new TreeSet<>();

    // [groupId:artifactId] -> library
    private Map<String, Library> highestLibraries = new TreeMap<>();

    // [groupId:artifactId:version] -> library
    private Map<String, Library> remoteLibraries = new TreeMap<>();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public LibrarySetImpl() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Library getLibrary(String nameOrId) {
        return get(nameOrId);
    }

    @Override
    public Library getLibrary(Library library) {
        return resolve(library);
    }

    @Override
    public Iterator<Library> iterator() {
        return getUsedLibraries().iterator();
    }

    @Override
    public Set<Library> getUsedLibraries() {
        Set<Library> libraries = new TreeSet<>();
        libraries.addAll(localLibraries);
        libraries.addAll(highestLibraries.values());
        return libraries;
    }

    @Override
    public Set<Library> getUnusedLibraries() {
        Set<Library> mlibs = new HashSet<>(remoteLibraries.values());
        Set<Library> hlibs = new HashSet<>(highestLibraries.values());
        return SetUtils.difference(mlibs, hlibs);
    }

    @Override
    public Set<File> getLibraryFiles() {
        Set<File> files = new HashSet<>();
        getUsedLibraries().forEach(library -> {
            files.addAll(library.getFiles());
        });
        return files;
    }

    @Override
    public Set<Library> getLibraries(LibraryType libraryType) {
        if (libraryType == LibraryType.LOCAL)
            return localLibraries;
        if (libraryType == LibraryType.REMOTE)
            return SetUtils.asSet(remoteLibraries.values());
        else
            return Collections.emptySet();
    }

    @Override
    public int size() {
        return localLibraries.size() + remoteLibraries.size();
    }

    @Override
    public boolean add(Library library) {
        if (library.getLibraryType() != LibraryType.REMOTE)
            return localLibraries.add(library);

        String gaName = library.getName().getName();        // groupId:artifactId
        String gavName = library.getName().getFullName();   // groupId:artifactId:version
        Version version = Version.of(library.getVersion());

        Library mavenLib = library;
        String name = mavenLib.getName().getName();
        if (!highestLibraries.containsKey(name)) {
            highestLibraries.put(name, mavenLib);
            return true;
        }

        // register the library using [groupId:artifactId:version]
        if (!remoteLibraries.containsKey(gavName))
            remoteLibraries.put(gavName, library);

        // register the library using [groupId:artifactId] with the HIGHEST version
        if (!highestLibraries.containsKey(gaName)) {
            highestLibraries.put(gaName, mavenLib);
            return true;
        }

        Version currentVersion = Version.of(highestLibraries.get(name).getVersion());
        if (currentVersion.compareTo(version) < 0) {
            highestLibraries.put(name, mavenLib);
            return true;
        }
        else if (currentVersion.compareTo(version) > 0) {
            return false;
        }
        else {
            return false;
        }
    }

    /**
     * If the library is a Maven library, it return the latest version
     */
    @Override
    public Library resolve(Library library) {
        if (library.getLibraryType() != LibraryType.REMOTE)
            return library;
        else
            return highestLibraries.get(library.getName().getName());
    }

    @Override
    public Set<Library> resolveAll(Set<Library> libraries) {
        return libraries.stream()
            .map(this::resolve)
            .collect(Collectors.toSet());
    }

    @Override
    public Library get(String nameOrId) {
        if (remoteLibraries.containsKey(nameOrId))
            return remoteLibraries.get(nameOrId);

        for (Library library : localLibraries) {
            if (library.getName().getFullName().equals(nameOrId))
                return library;
            if (library.getId().equals(nameOrId))
                return library;
            if (library.getName().getName().equals(nameOrId))
                return library;
            if (library.getPath().equals(nameOrId))
                return library;
        }

        for (Library library : remoteLibraries.values()) {
            if (library.getName().getFullName().equals(nameOrId))
                return library;
            if (library.getId().equals(nameOrId))
                return library;
            if (library.getName().getName().equals(nameOrId))
                return library;
            if (library.getPath().equals(nameOrId))
                return library;
        }

        return null;
    }

    @Override
    public void checkArtifacts(LibraryDownloader md, boolean parallel) {
        List<MavenCoords> artifacts = highestLibraries
            .values()
            .stream()
            .map(library -> MavenCoords.of(library.getName().getFullName()))
            .collect(Collectors.toList());

        if (parallel) {
            (new Thread(() -> md.checkArtifacts(artifacts, false, parallel))).start();
        }
        else {
            md.checkArtifacts(artifacts, true, parallel);
        }
    }

}
