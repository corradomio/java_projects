package jext.buildtools.scan.rules;

import jext.buildtools.Name;
import jext.buildtools.scan.util.PatternSet;
import jext.logging.Logger;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ScanRules {

    private static Logger logger = Logger.getLogger(ScanRules.class);

    private File scanFile;
    private Element elt;

    private Properties properties = new Properties();
    private ModuleScan moduleScan = new ModuleScan();
    private PatternSet isRemoved = new PatternSet();
    private Templates templates = new Templates();

    public ScanRules() {

    }

    public void configure(File scanFile) throws Exception {
        try(InputStream stream = new FileInputStream(scanFile)) {
            configure(stream);
        }
    }

    public void configure(InputStream stream) throws Exception {
        if (elt != null) return;

        elt = XPathUtils.parse(stream).getDocumentElement();

        properties = XPathUtils.getProperties(elt, "properties");

        moduleScan.configure(elt, "modules/scan");
        isRemoved.configure(elt, "modules/remove");
        templates.configure(elt, "modules");
    }

    public List<File> getModuleDirs(File baseDir) {
        return moduleScan.getModuleDirs(baseDir);
    }

    public boolean isRemoved(Name name) {
        String flattenName = name.toString().replace("/", "_");
        return isRemoved.accept(flattenName);
    }

    public List<Template> getMissingModules(Set<String> moduleNames) {
        return templates.getMissingModules(moduleNames);
    }

    public Template getTemplate(Name name) {
        return templates.getTemplate(name.toString());
    }
}
