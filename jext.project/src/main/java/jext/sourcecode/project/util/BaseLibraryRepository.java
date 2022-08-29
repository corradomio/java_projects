package jext.sourcecode.project.util;

import jext.sourcecode.project.LibraryRepository;
import jext.sourcecode.project.LibraryType;

import java.io.File;

public abstract class BaseLibraryRepository implements LibraryRepository {

    protected String url;
    protected String id;
    protected String name;
    protected File download;

    protected BaseLibraryRepository() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getRepositoryType() {
        return LibraryType.REMOTE.name();
    }

    public void setInfo(String name, String url) {
        this.name = name;
        this.url = url;
        this.id = Integer.toHexString(url.hashCode());
    }

    public void setDownloadDirectory(File downloadDirectory) {
        this.download = downloadDirectory;
    }

}
