package jext.sourcecode.project.util;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProjectUtils {

    public static List<Source> getSources(Project project) {
        List<Source> sources = new ArrayList<>();
        for (Module module : project.getModules())
                sources.addAll(module.getSources());

        // sort the files in decreasing order of totalLines
        // in this way, the most large files are analysed as soon
        // as possible
        sources.sort((s1, s2) -> {
            int tl1 = (int)s1.getSourceInfo().totalLines;
            int tl2 = (int)s2.getSourceInfo().totalLines;
            return tl2-tl1;
        });

        return sources;
    }

    // public static List<Source> getSources(Project project, Predicate<String> select) {
    //     return getSources(project).stream()
    //         .filter(source -> select.test(source.getPath()))
    //         .collect(Collectors.toList());
    // }

    public static Source getSource(Project project, String nameOrId) {
        for (Module module : project.getModules()) {
            Source source = module.getSource(nameOrId);
            if (source != null)
                return source;
        }
        return null;
    }

    // ----------------------------------------------------------------------

    public static List<Resource> getResources(Project project) {
        List<Resource> resources = new ArrayList<>();

        for (Module module : project.getModules())
            resources.addAll(module.getResources());

        return resources;
    }

    public static Resource getResource(Project project, String nameOrId) {
        for (Module module : project.getModules()) {
            Resource resource = module.getResource(nameOrId);
            if (resource != null)
                return resource;
        }
        return null;
    }

    // ----------------------------------------------------------------------

    public static List<Type> getTypes(Project project) {
        List<Type> definedTypes = new ArrayList<>();
        project.getModules().forEach(module -> {
            module.getTypes().forEach(refType -> {
                definedTypes.add(refType.asType());
            });
        });

        return definedTypes;
    }

    public static RefType getType(Project project, String typeId) {
        for(Module module : project.getModules()) {
            for(RefType type : module.getTypes()) {
                if (type.getId().equals(typeId))
                    return type;
                if (type.getName().getFullName().equals(typeId))
                    return type;
            }
        }

        return null;
    }

    public static Set<RefType> getDefinedTypes(Project project) {
        Set<RefType> definedTypes = new HashSet<>();

        project.getModules().forEach(module -> {
            definedTypes.addAll(module.getTypes());
        });

        return definedTypes;
    }

    public static Set<RefType> getUsedTypes(Project project) {
        Set<RefType> usedTypes = new HashSet<>();

        project.getModules().forEach(module -> {
            usedTypes.addAll(module.getUsedTypes());
        });

        return usedTypes;
    }

    public static Optional<RefType> findType(Project project, String typeName) {
        for (Module module : project.getModules()) {
            for (RefType refType : module.getTypes())
                if (refType.getName().getFullName().equals(typeName) ||
                    refType.getName().getName().equals(typeName) ||
                    refType.getId().equals(typeName))
                    return Optional.of(refType);
        }
        return Optional.empty();
    }

    // ----------------------------------------------------------------------

    public static Set<Library> getRuntimeLibraries(Project project) {
        Set<Library> rtLibraries = new HashSet<>();
        project.getModules().forEach(module -> {
            rtLibraries.add(module.getRuntimeLibrary());
        });
        return rtLibraries;
    }

}
