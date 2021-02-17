package jext.sourcecode.project.maven;

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

public class LibrarySet extends AbstractSet<Library> {

    private Set<Library> localLibraries = new TreeSet<>();
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

    public void checkArtifacts() {
        List<MavenCoords> artifacts = mavenLibraries
            .values()
            .stream()
            .map(MavenLibrary::getCoords)
            .collect(Collectors.toList());

        Parallel.forEach(mavenLibraries.values(), mavenLibrary -> {
            MavenCoords coords = mavenLibrary.getCoords();
            MavenDownloader md = mavenLibrary.getMavenDownloader();
            md.getPom(coords);
            md.getArtifact(coords);
        });
    }

}
