package jext.sourcecode.project.util;

import jext.sourcecode.resources.libraries.MavenLibrary;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.maven.Version;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class LibrarySet extends AbstractSet<Library> {

    private Set<Library> localLibs = new TreeSet<>();
    private Map<String, MavenLibrary> mavenLibs = new TreeMap<>();


    @Override
    public Iterator<Library> iterator() {
        List<Library> libraries = new ArrayList<>();
        libraries.addAll(localLibs);
        libraries.addAll(mavenLibs.values());
        return libraries.iterator();
    }

    @Override
    public int size() {
        return localLibs.size() + mavenLibs.size();
    }

    public boolean add(Library library) {
        if (library.getLibraryType() != LibraryType.MAVEN)
            return localLibs.add(library);

        boolean modified = false;
        MavenLibrary mavenLib = (MavenLibrary) library;
        String name = mavenLib.getMavenName();
        Version version = mavenLib.getMavenVersion();
        if (!mavenLibs.containsKey(name)) {
            mavenLibs.put(name, mavenLib);
            modified = true;
        } else if (mavenLibs.get(name).getMavenVersion().compareTo(version) < 0) {
            mavenLibs.put(name, mavenLib);
            modified = true;
        }
        return modified;
    }

}
