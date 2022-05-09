package jext.sourcecode.project.java.gradle.collectors;

import java.util.Set;
import java.util.TreeSet;

public class GradleDeps {
    public final Set<String> libraries = new TreeSet<>();
    public final Set<String> projects = new TreeSet<>();

    public GradleDeps addAll(GradleDeps that) {
        libraries.addAll(that.libraries);
        projects.addAll(that.projects);
        return this;
    }
}
