package jext.sourcecode.project.java.maven;

import jext.sourcecode.project.LibraryRepository;

public class MavenRepository implements LibraryRepository {

    private String id;
    private String url;

    public MavenRepository(String url) {
        this.id = Integer.toHexString(url.hashCode());
        this.url = url;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return id;
    }

    @Override
    public String getRepositoryType() {
        return "MAVEN";
    }

    @Override
    public String getUrl() {
        return url;
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return url;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        MavenRepository that = (MavenRepository) obj;
        return this.id.equals(that.id);
    }
}
