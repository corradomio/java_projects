package jext.buildtools.project.gradle.util;

import jext.logging.Logger;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SettingsGradleFile extends GradleFile {

    public static final String SETTINGS = "settings.gradle";

    private static Logger logger = Logger.getLogger(SettingsGradleFile.class);

    private List<ModuleId> modules;

    public SettingsGradleFile(File settingFile) {
        super(settingFile.isFile() ? settingFile : new File(settingFile, SETTINGS));
    }

    public List<ModuleId> getModules() {
        if (modules != null)
            return modules;

        Set<ModuleId> uniqueModules = new TreeSet<>();
        analyzeIncludes(uniqueModules);

        //if (modules.isEmpty())
        checkRecursive(uniqueModules);

        modules = new ArrayList<>(uniqueModules);
        return modules;
    }

    private void analyzeIncludes(Set<ModuleId> modules) {
        for (String line : content) {
            if (!line.contains("include")) continue;

            String[] parts = line.split("\\s|,");
            for(int i=1; i<parts.length; ++i) {
                String module = parts[i];
                module = module.substring(1, module.length()-1).replace(":", "/");
                modules.add(new ModuleId(module));
            }
        }
    }

    private void checkRecursive(Set<ModuleId> modules) {
        File projectDir = gradleFile.getParentFile();
        FileUtils.listFiles(projectDir, file -> {
            File directory = file.getParentFile();
            String fileName = file.getName();
            String dirName = directory.getName();
            return fileName.equals("build.gradle") || fileName.equals(dirName + ".gradle");
        }).forEach(gradleFile -> {
            File moduleDir = gradleFile.getParentFile();
            String module = FileUtils.relativePath(projectDir, moduleDir);
            if (module.length() > 0)
                modules.add(new ModuleId(module));
        });
    }

}
