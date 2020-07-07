package jext.name;

public class ObjectName implements Name {
    private String namespace;

    public ObjectName(String namespace) {
        this.namespace = namespace;
        this.normalize();
    }

    public ObjectName(String parent, String name) {
        this.namespace = String.format("%s.%s", parent, name);
        this.normalize();
    }

    public ObjectName(Name parent, String name) {
        this.namespace = String.format("%s.%s", parent, name);
        this.normalize();
    }

    private void normalize(){
        if (namespace.endsWith("."))
            namespace = namespace.substring(0, namespace.length()-1);
        if (namespace.startsWith("."))
            namespace = namespace.substring(1);
        while (namespace.contains(".."))
            namespace = namespace.replace("..", ".");
    }

    @Override
    public boolean isRoot() {
        return namespace.isEmpty();
    }

    @Override
    public String getName() {
        int sep = namespace.lastIndexOf('.');
        return sep > 0 ? namespace.substring(sep+1) : namespace;
    }

    @Override
    public Name getParent() {
        int sep = namespace.lastIndexOf('.');
        return sep > 0 ? new PathName(namespace.substring(0, sep)) : null;
    }

    @Override
    public String getParentName() {
        int sep = namespace.lastIndexOf('.');
        return sep > 0 ? namespace.substring(0, sep) : null;
    }

    @Override
    public int hashCode() {
        return namespace.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Name that = (Name) obj;
        return namespace.equals(that.toString());
    }

    @Override
    public String toString() {
        return namespace;
    }
}
