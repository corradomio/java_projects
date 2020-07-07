package jext.name;

/*
    'a/b/c'
    '/a/b/c' -> 'a/b/c'
    '/'
    ''
 */

public class PathName implements Name {
    private String path;

    public PathName(String path) {
        this.path = path;
        this.normalize();
    }

    public PathName(String parent, String name) {
        this.path = String.format("%s/%s", parent, name);
        this.normalize();
    }

    public PathName(Name parent, String name) {
        this.path = String.format("%s/%s", parent, name);
        this.normalize();
    }

    private void normalize(){
        path = path.replace('\\', '/');
        if (path.endsWith("/"))
            path = path.substring(0, path.length()-1);
        if (path.startsWith("/"))
            path = path.substring(1);
        while (path.contains("//"))
            path= path.replace("//", "/");
    }

    @Override
    public boolean isRoot() {
        return path.isEmpty();
    }

    @Override
    public String getName() {
        int sep = path.lastIndexOf('/');
        return sep > 0 ? path.substring(sep+1) : path;
    }

    @Override
    public Name getParent() {
        int sep = path.lastIndexOf('/');
        return sep > 0 ? new PathName(path.substring(0, sep)) : null;
    }

    @Override
    public String getParentName() {
        int sep = path.lastIndexOf('/');
        return sep > 0 ? path.substring(0, sep) : null;
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Name that = (Name) obj;
        return path.equals(that.toString());
    }

    @Override
    public String toString() {
        return path;
    }
}
