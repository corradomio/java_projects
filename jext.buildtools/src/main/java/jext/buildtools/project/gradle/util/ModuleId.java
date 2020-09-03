package jext.buildtools.project.gradle.util;

public class ModuleId implements Comparable<ModuleId> {

    /*
        'id' depends on the building system
        - maven  -> Maven coordinates
        - gradle -> relative path respect the project root
     */
    public String id;

    // relative path respect the project root
    public String name;

    public ModuleId(String name) {
        this.id = name;
        this.name = name;
    }

    public ModuleId(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(ModuleId that) {
        return name.compareTo(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        ModuleId that = (ModuleId) obj;
        return name.equals(that.name);
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", name, id);
    }

}
