package jext.buildtools.scan;

import jext.buildtools.Module;
import jext.buildtools.Project;
import jext.buildtools.scan.rules.ScanRules;
import jext.buildtools.scan.rules.Template;
import jext.logging.Logger;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScanProject implements Project {

    private Logger logger;
    private File projectDir;
    private ScanRules scanRules;

    private ScanModule rootModule;
    private List<ScanModule> modules;

    public ScanProject(File projectDir) {
        File scanFile;
        if (projectDir.isDirectory()) {
            this.projectDir = projectDir;
            scanFile = new File(projectDir, "project-scan.xml");
        }
        else {
            scanFile = projectDir;
            this.projectDir = projectDir.getParentFile();
        }

        this.scanRules = new ScanRules();

        if (scanFile.exists()) {
            try {
                this.scanRules.configure(scanFile);
            } catch (Exception e) {
                logger.errorf("Unable to configure using %s: %s", scanFile, e);
            }
        }
        else {
            try(InputStream stream = getClass().getResourceAsStream("/jext/buildtools/project-scan.xml")) {
                this.scanRules.configure(stream);
            } catch (Exception e) {
                logger.errorf("Unable to configure using embedded configuration: %s", e);
            }
        }

        this.rootModule = new ScanModule(this);
        this.logger = Logger.getLogger(ScanProject.class, getName());
    }

    public String getName() {
        return projectDir.getName();
    }

    public File getProjectDir() {
        return projectDir;
    }

    public void setConfiguration(File configFile) {
        try {
            this.scanRules.configure(configFile);
        }
        catch (Exception e) {
            logger.errorf("Unable to configure using %s: %s", configFile, e);
        }
    }

    @Override
    public List<ScanModule> getModules() {
        if (modules != null)
            return modules;

        modules = new ArrayList<>();

        // add root module
        modules.add(rootModule);

        // search modules by scan
        searchModules();

        // remove modules
        removeModules();

        // add missing modules
        addMissingModules();

        // configure modules
        configureModules();

        return modules;
    }

    private void searchModules() {
        modules = scanRules.getModuleDirs(projectDir)
                .stream()
                .map(moduleDir -> new ScanModule(moduleDir, this))
                .collect(Collectors.toList());
    }

    private void removeModules(){
        modules = modules.stream()
                .filter(module -> !scanRules.isRemoved(module.getName()))
                .collect(Collectors.toList());
    }

    private void addMissingModules() {
        Set<String> moduleNames = getModules()
                .stream()
                .map(module -> module.getName().toString())
                .collect(Collectors.toSet());
        scanRules.getMissingModules(moduleNames)
                .forEach(template -> {
                    String name = template.getName();
                    File moduleDir = template.getModuleDir(projectDir);
                    modules.add(new ScanModule(name, moduleDir, this));
                });
    }

    private void configureModules() {
        modules.forEach(module -> {
            Template template = scanRules.getTemplate(module.getName());
            module.setTemplate(template);
        });
    }

    @Override
    public Module getModule(String name) {
        for(ScanModule module : getModules()) {
            String mname = module.getName().toString();
            if (mname.equals(name) || mname.endsWith(name))
                return module;
        }
        return null;
    }

}
