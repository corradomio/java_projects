package jext.buildtools.library;

import jext.buildtools.Library;
import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.buildtools.util.NamedObject;
import jext.buildtools.util.PathName;

public class MavenLibrary extends NamedObject implements Library {

    private MavenCoords coords;
    private MavenDownloader downloader;
    private Module module;

    public MavenLibrary(MavenCoords coords, Module module) {
        super(null);
        this.coords = coords;
        this.module = module;
        setName(new PathName(coords.getName()));
    }

    @Override
    public Module getModule() {
        return module;
    }

    public void setDownloader(MavenDownloader downloader) {
        this.downloader = downloader;
    }

    @Override
    public String toString() {
        return coords.toString();
    }

}
