package jext.sourcecode.project.maven;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.Version;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.util.concurrent.Parallel;

import java.util.AbstractSet;
import java.util.ArrayList;
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
public class LibrarySet extends AbstractSet<Library> {

    private static Logger logger = Logger.getLogger(LibrarySet.class);

    private Set<Library> localLibraries = new TreeSet<>();

    // [groupId:artifactId] -> library
    private Map<String, MavenLibrary> mavenLibraries = new TreeMap<>();

    public LibrarySet() {

    }

    @Override
    public Iterator<Library> iterator() {
        List<Library> libraries = new ArrayList<>();
        libraries.addAll(localLibraries);
        libraries.addAll(mavenLibraries.values());
        return libraries.iterator();
    }

    @Override
    public int size() {
        return localLibraries.size() + mavenLibraries.size();
    }

    public boolean add(Library library) {
        if (library.getLibraryType() != LibraryType.MAVEN)
            return localLibraries.add(library);

        MavenLibrary mavenLib = (MavenLibrary) library;
        String name = mavenLib.getMavenName();
        Version version = mavenLib.getMavenVersion();
        if (!mavenLibraries.containsKey(name)) {
            mavenLibraries.put(name, mavenLib);
            return true;
        }

        Version currentVersion = mavenLibraries.get(name).getMavenVersion();
        if (currentVersion.compareTo(version) < 0) {
            mavenLibraries.put(name, mavenLib);
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
    public Library resolve(Library library) {
        if (library.getLibraryType() != LibraryType.MAVEN)
            return library;
        else
            return mavenLibraries.get(library.getName().getName());
    }

    public List<Library> resolveAll(List<Library> libraries) {
        return libraries.stream()
            .map(this::resolve)
            .sorted()
            .collect(Collectors.toList());
    }

    public void checkArtifacts() {
        List<MavenCoords> artifacts = mavenLibraries
            .values()
            .stream()
            .map(MavenLibrary::getCoords)
            .collect(Collectors.toList());

        Parallel.forEach(mavenLibraries.values(), mavenLibrary -> {
            try {
            MavenCoords coords = mavenLibrary.getCoords();
            MavenDownloader md = mavenLibrary.getMavenDownloader();
            md.getPom(coords);
            md.getArtifact(coords);
            }
            catch (RuntimeException e) {
                logger.errorf("Unable to check %s: %s", mavenLibrary.getName(), e.getMessage());
            }
        });
    }

}
