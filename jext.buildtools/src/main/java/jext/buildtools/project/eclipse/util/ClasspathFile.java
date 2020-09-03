package jext.buildtools.project.eclipse.util;

//
//         <?xml version="1.0" encoding="UTF-8"?>
//         <classpath>
//             <classpathentry kind="src" path="src/main/java"/>
//             <classpathentry kind="src" path="src/main/java" including="**/*.java"/>
//             <classpathentry kind="src" path="src/main/resources" excluding="**/*.java"/>
//             <classpathentry combineaccessrules="false" kind="src" path="/project1"/>
//              <classpathentry kind="var" path="M2_REPO/javax/servlet/servlet-api/2.5/servlet-api-2.5.jar"
//                      sourcepath="M2_REPO/javax/servlet/servlet-api/2.5/servlet-api-2.5-sources.jar"/>
//         </classpath>
//

import jext.logging.Logger;
import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClasspathFile {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(ClasspathFile.class);

    private static final String EMPTY_CLASSPATH = "" +
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<classpath/>";

    private File moduleDir;
    private File classpath;
    private Element elt;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ClasspathFile(File classpath) {
        if (classpath.isDirectory())
            classpath = new File(classpath, ".classpath");
        this.classpath = classpath;
        this.moduleDir = classpath.getParentFile();

        if (classpath.exists())
        try {
            this.elt = XPathUtils.parse(classpath).getDocumentElement();
        } catch (Exception e) {
            logger.errorf("Unable to parse %s: %s", classpath.getAbsolutePath(), e);
        }

        if (this.elt == null)
        try {
            this.elt = XPathUtils.parse(EMPTY_CLASSPATH).getDocumentElement();
        } catch (Exception e) {
            logger.errorf("Unable to parse embedded classpath: %s", e);
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public boolean exists() {
        return classpath.exists();
    }

    public List<File> getSourceDirs() {
        List<File> dirs = new ArrayList<>();
        XPathUtils.selectNodes(elt, "classpathentry[@kind='src']")
                .forEach(esrc -> {
                    String path = XPathUtils.getValue(esrc, "@path", "");
                    String combineaccessrules = XPathUtils.getValue(esrc, "@combineaccessrules", "");
                    if (!path.isEmpty() && combineaccessrules.isEmpty())
                        dirs.add(new File(moduleDir, path));
                });
        return dirs;
    }

    public List<File> getResourceDirs() {
        return Collections.emptyList();
    }

    public List<String> getModuleDependencies() {
        List<String> dmodules = new ArrayList<>();
        XPathUtils.selectNodes(elt, "classpathentry[@kind='src']")
                .forEach(esrc -> {
                    String path = XPathUtils.getValue(esrc, "@path", "");
                    String combineaccessrules = XPathUtils.getValue(esrc, "@combineaccessrules", "");
                    if (path.startsWith("/"))
                        path = path.substring(1);
                    if (!path.isEmpty() && !combineaccessrules.isEmpty())
                        dmodules.add(path);
                });
        return dmodules;
    }

    public List<String> getMavenLibraries() {
        List<String> mavenLibs = new ArrayList<>();
        XPathUtils.selectNodes(elt, "classpathentry[@kind='var']")
                .forEach(elib -> {
                    String path = XPathUtils.getValue(elib, "@path", "");
                    if (!path.startsWith("M2_REPO/")) return;
                    mavenLibs.add(path.substring(8));
                });
        return mavenLibs;
    }

    public List<File> getLocalLibraries() {
        List<File> localLibs = new ArrayList<>();
        XPathUtils.selectNodes(elt, "classpathentry[@kind='lib']")
                .forEach(esrc -> {
                    String path = XPathUtils.getValue(esrc, "@path", "");
                    if (!path.isEmpty())
                        localLibs.add(FileUtils.toFile(moduleDir, path));
                });
        return localLibs;
    }
}
