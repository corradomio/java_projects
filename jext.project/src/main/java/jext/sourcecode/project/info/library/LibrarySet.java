package jext.sourcecode.project.info.library;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.Version;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.util.SetUtils;

import java.io.File;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LibrarySet extends AbstractSet<Library> implements jext.sourcecode.project.LibrarySet {

    private static Logger logger = Logger.getLogger(LibrarySet.class);

    private Set<Library> localLibraries = new TreeSet<>();

    // [groupId:artifactId] -> library
    private Map<String, Library> highestLibraries = new TreeMap<>();
    // [groupId:artifactId:version] -> library
    private Map<String, Library> mavenLibraries = new TreeMap<>();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public LibrarySet() {

    }

    // ----------------------------------------------------------------------
    //
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
        Set<Library> mlibs = new HashSet<>(mavenLibraries.values());
        Set<Library> hlibs = new HashSet<>(highestLibraries.values());

        return SetUtils.difference(mlibs, hlibs);
    }

    @Override
    public Set<File> getLibraryFiles() {
        Set<File> files = new jext.util.HashSet<>();
        getUsedLibraries().forEach(library -> {
            files.addAll(library.getFiles());
        });
        return files;
    }

    @Override
    public Set<Library> getLibraries(LibraryType libraryType) {
        if (libraryType == LibraryType.LOCAL)
            return localLibraries;
        if (libraryType == LibraryType.MAVEN)
            return SetUtils.asSet(mavenLibraries.values());
        else
            return Collections.emptySet();
    }

    @Override
    public int size() {
        return localLibraries.size() + highestLibraries.size();
    }

    @Override
    public boolean add(Library library) {
        if (library.getLibraryType() != LibraryType.MAVEN)
            return localLibraries.add(library);

        String gaName = library.getName().getName();        // groupId:artifactId
        String gavName = library.getName().getFullName();   // groupId:artifactId:version
        Version version = Version.of(library.getVersion());

        // register the library using [groupId:artifactId:version]
        if (!mavenLibraries.containsKey(gavName))
            mavenLibraries.put(gavName, library);

        // register the library using [groupId:artifactId] with the HIGHEST version
        if (!highestLibraries.containsKey(gaName)) {
            highestLibraries.put(gaName, library);
            return true;
        }

        Version currentVersion = Version.of(highestLibraries.get(gaName).getVersion());
        if (currentVersion.compareTo(version) < 0) {
            highestLibraries.put(gaName, library);
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
        if (library.getLibraryType() != LibraryType.MAVEN)
            return library;
        else
            return highestLibraries.get(library.getName().getName());
    }

    @Override
    public Set<Library> resolveAll(Set<Library> libraries) {
        return libraries.stream()
            .map(this::resolve)
            .sorted()
            .collect(Collectors.toSet());
    }

    @Override
        public Library get(String nameOrId) {
        if (mavenLibraries.containsKey(nameOrId))
            return mavenLibraries.get(nameOrId);

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

        for (Library library : mavenLibraries.values()) {
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
    public void checkArtifacts(MavenDownloader md, boolean parallel) {
        List<MavenCoords> artifacts = mavenLibraries
            .values()
            .stream()
            .filter(library -> library.getLibraryType() == LibraryType.MAVEN)
            // Library is a 'LibraryNode' NOT a 'MavenLibrary'
            .map(library -> MavenCoords.of(library.getName().getName(), library.getVersion()))
            .collect(Collectors.toList());

        if (parallel) {
            (new Thread(() -> md.checkArtifacts(artifacts, false, parallel))).start();
        }
        else {
            md.checkArtifacts(artifacts, true, parallel);
        }
    }

}
