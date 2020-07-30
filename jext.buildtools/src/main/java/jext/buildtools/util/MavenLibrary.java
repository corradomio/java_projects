package jext.buildtools.util;

import jext.buildtools.Library;
import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.maven.MavenDownloader;

public class MavenLibrary implements Library {

    private MavenCoords coords;
    private MavenDownloader downloader;
    private Module module;
    private Name name;

    public MavenLibrary(MavenCoords coords, Module module) {
        this.coords = coords;
        this.module = module;
        this.name = new PathName(coords.getArtifact().replace(':', '/'));
    }

    @Override
    public String getId() {
        return coords.toString();
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Module getModule() {
        return module;
    }

    public void setDownloader(MavenDownloader downloader) {
        this.downloader = downloader;
    }

}
