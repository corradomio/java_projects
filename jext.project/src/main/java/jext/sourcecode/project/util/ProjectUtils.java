package jext.sourcecode.project.util;

import jext.util.logging.Logger;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryRepository;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.ModuleSource;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;
import jext.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProjectUtils {

    private static Logger logger = Logger.getLogger(ProjectUtils.class);

    public static List<Module> getModules(Project project, List<String> moduleNames) {
        if (moduleNames.isEmpty())
            return Collections.emptyList();

        return moduleNames.stream()
            .map(moduleName -> project.getModules().getModule(moduleName))
            .collect(Collectors.toList());
    }

    public static Optional<Source> getSource(Project project, ModuleSource ms, Predicate<String> isAccepted) {
        Source source = project.getModules().getModule(ms.module).getSources().getSource(ms.source);
        if (isAccepted.test(source.getPath()))
            return Optional.of(source);
        else
            return Optional.empty();
    }

    public static List<Source> getSources(Project project, List<ModuleSource> moduleSources, Predicate<String> isAccepted) {
        List<Source> sources = new ArrayList<>();

        for (ModuleSource ms : moduleSources) {

            Module module = project.getModules().getModule(ms.module);
            if (module == null) {
                logger.errorf("Module %s not found in project %s", ms.module, project.getName().getFullName());
                continue;
            }

            Source source = module.getSources().getSource(ms.source);
            if (source == null) {
                logger.errorf("Source %s not found in module %s of project %s", ms.source, ms.module, project.getName().getFullName());
                continue;
            }

            if (!isAccepted.test(source.getPath()))
                continue;

            sources.add(source);
        }

        return sources;
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
            Resource resource = module.getResources().getResource(nameOrId);
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

    // ----------------------------------------------------------------------

    public static Set<Library> getRuntimeLibraries(Project project) {
        Set<Library> rtLibraries = new HashSet<>();
        project.getModules().forEach(module -> {
            rtLibraries.add(module.getRuntimeLibrary());
        });
        return rtLibraries;
    }

    // ----------------------------------------------------------------------

    public static Set<String> getMavenRepositories(Project project) {
        Set<String> mavenRepos = new HashSet<>();
        project.getModules().forEach(module -> {
            mavenRepos.addAll(module.getMavenRepositories());
        });
        return mavenRepos;
    }

    public static Set<LibraryRepository> getLibraryRepositories(Project project) {
        Set<LibraryRepository> libRepos = new HashSet<>();
        project.getModules().forEach(module -> {
            libRepos.addAll(module.getLibraryRepositories());
        });
        return libRepos;
    }

    // ----------------------------------------------------------------------

    public static long countCodeLines(File projectHome, String extension) {
        long[] total = new long[1];
        try {
            Files.walkFileTree(projectHome.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    File file = dir.toFile();
                    if (file.getName().startsWith("."))
                        return FileVisitResult.SKIP_SUBTREE;
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    File file = path.toFile();
                    if (file.getName().endsWith(extension)) {
                        List<String> lines = FileUtils.toStrings(file);
                        long blankLines = lines
                            .stream()
                            .map(String::trim)
                            .filter(String::isEmpty)
                            .count();
                        total[0] += lines.size() - blankLines;
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {

        }
        return total[0];
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
