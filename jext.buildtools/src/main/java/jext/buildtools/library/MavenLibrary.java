package jext.buildtools.util;

import jext.buildtools.Library;
import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.maven.MavenDownloader;

public class MavenLibrary extends NamedObject implements Library {

    private MavenCoords coords;
    private MavenDownloader downloader;
    private Module module;

    public MavenLibrary(MavenCoords coords, Module module) {
        super(null);
        this.coords = coords;
        this.module = module;
        setName(new PathName(coords.getArtifact().replace(':', '/')));
    }

    @Override
    public Module getModule() {
        return module;
    }

    public void setDownloader(MavenDownloader downloader) {
        this.downloader = downloader;
    }

}