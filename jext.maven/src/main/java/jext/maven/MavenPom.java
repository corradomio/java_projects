package jext.maven;

import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.PropertiesUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static jext.maven.MavenCoords.isPattern;
import static jext.maven.MavenCoords.isRange;
import static jext.maven.MavenCoords.isValid;

/*
    <project>
      <modelVersion>4.0.0</modelVersion>
      <groupId>com.bea.wlplatform</groupId>
      <artifactId>commonj-twm</artifactId>
      <version>1.1</version>
      <name>Timer and Work Manager for Application Servers</name>
      <url>http://dev2dev.bea.com/wlplatform/commonj/twm.html</url>
      <distributionManagement>
        <downloadUrl>http://ftpna2.bea.com/pub/downloads/commonj/commonj-twm.jar</downloadUrl>
      </distributionManagement>
    </project>

 */

public class MavenPom implements MavenConst {

    // ----------------------------------------------------------------------
    // Invalid Maven POM
    // ----------------------------------------------------------------------

    private static MavenPom INVALID = new MavenPom();

    public static MavenPom invalid() { return INVALID; }

    public static boolean isInvalid(MavenPom pom) {
        return pom == null || pom == INVALID;
    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final String EMPTY_POM =
        "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<project xmlns='http://maven.apache.org/POM/4.0.0'/>";

    private static final String POM = "pom.xml";

    private static final String PROJECT = "project";
    private static final String PARENT_POM = "../pom.xml";
    private static final String DM_RELOCATION = "distributionManagement/relocation";
    private static final String DM_DEPENDENCIES = "dependencyManagement/dependencies/dependency";
    private static final String DEPENDENCIES = "dependencies/dependency";
    private static final String REPOSITORIES = "repositories/repository";
    private static final String DOWNLOAD_URL = "distributionManagement/downloadUrl";

    private MavenDownloader md;

    private Logger logger = Logger.getLogger(MavenDownloader.class);
    private File pomFile;
    private Element project;

    // local caches
    private Properties mavenprops;
    private Properties localprops;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected MavenPom() {
        mavenprops = PropertiesUtils.empty();
        localprops = PropertiesUtils.empty();
        parseFile();
    }

    public MavenPom(File pomFile) {
        this(pomFile, null);
    }

    public MavenPom(File pomFile, MavenDownloader md) {
        this.md = md;

        if (pomFile.isDirectory())
            pomFile = new File(pomFile, POM);

        this.pomFile = pomFile;

        parseFile();
        readMavenProps();
        readLocalProps();
    }

    private void parseFile() {
        if (pomFile != null && pomFile.exists())
        try {
            this.project = XPathUtils.parse(pomFile).getDocumentElement();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            logger.errorf("Unable to parse %s: %s", pomFile, e);
        }

        if (this.project == null)
        try {
            this.project = XPathUtils.parse(EMPTY_POM).getDocumentElement();
        }
        catch (ParserConfigurationException | IOException | SAXException e) { }

    }

    private void readMavenProps() {
        String parentGroupId, parentArtifactId, parentVersion;
        String groupId, artifactId, version, packaging;
        String empty = "";

        this.mavenprops = new Properties();

        // 0) main properties
        {
            addProperty(mavenprops, "project.modelVersion", "modelVersion", "4.0.0");
            addProperty(mavenprops, "project.name", "name", empty);
            mavenprops.put("project.basedir", FileUtils.getAbsolutePath(pomFile.getParentFile()));
        }

        // 1) parent coords
        {
            parentGroupId = addProperty(mavenprops, "project.parent.groupId", "parent/groupId", empty);
            parentArtifactId = addProperty(mavenprops, "project.parent.artifactId", "parent/artifactId", empty);
            parentVersion = addProperty(mavenprops, "project.parent.version", "parent/version", empty);
            packaging = addProperty(mavenprops, "project.packaging", "packaging", "jar");
            mavenprops.setProperty("packaging.type", packaging);
        }

        // 2) current coords
        {
            groupId = addProperty(mavenprops, "project.groupId", "groupId", parentGroupId);
            artifactId = addProperty(mavenprops, "project.artifactId", "artifactId", parentArtifactId);
            version = addProperty(mavenprops, "project.version", "version", parentVersion);
        }

        // 3) POM properties: in some ".pom" files there are dependencies with "pom.*" instead then "project.*"
        {
            mavenprops.setProperty("pom.groupId", groupId);
            mavenprops.setProperty("pom.artifactId", artifactId);
            mavenprops.setProperty("pom.version", version);
        }
    }

    private void readLocalProps() {
        this.localprops = new Properties();

        XPathUtils.selectNodes(project, "properties/*")
            .forEach(eprop -> {
                String pname = eprop.getNodeName().trim();
                String value = eprop.getTextContent().trim();
                value = PropertiesUtils.resolveValue(value, mavenprops);
                localprops.put(pname, value);
            });
    }

    // ----------------------------------------------------------------------

    // public MavenPom setDownloader(MavenDownloader downloader) {
    //     this.downloader = downloader;
    //     return this;
    // }

    // ----------------------------------------------------------------------
    // Predicates
    // ----------------------------------------------------------------------

    /** Check if the file exists and it is a POM file */
    public boolean exists() {
        // file doesn't exist
        if (!pomFile.exists())
            return false;

        // root XML node is null
        if (project == null)
            return false;

        // root XML node is not <project>
        if (!PROJECT.equals(project.getNodeName()))
            return false;

        return true;
    }

    // /** Check if '.pom' file exists */
    // public boolean exists() {
    //     return pomFile.exists();
    // }

    /** Check if '[relocation]' exists */
    public boolean isRelocated() {
        return XPathUtils.selectNode(project, DM_RELOCATION) != null;
    }

    /** Check if '[parent]' exists */
    public boolean hasParent() {
        return XPathUtils.selectNode(project, PARENT) != null;
    }

    public boolean hasRepositories() {
        return XPathUtils.selectNode(project, REPOSITORIES) != null;
    }

    /** Check if '[packaging] == 'pom' */
    public boolean isPomPackaging() {
        return PACKAGING_POM.equals(getPackaging());
    }

    /** Check if [distributionManagement/downloadUrl] is present */
    public boolean hasDownloadUrl() {
        return !getDownloadUrl().isEmpty();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getPackaging() {
        String packaging = XPathUtils.getValue(project, PACKAGING, PACKAGING_JAR);
        if (PACKAGING_BUNDLE.equals(packaging))
            return PACKAGING_JAR;
        else if (packaging.contains("$"))
            return PACKAGING_JAR;
        else
            return packaging;
    }

    /*
        <project>
          <parent>
            <groupId>org.apache</groupId>
            <artifactId>apache</artifactId>
            <version>4</version>
          </parent>
          <groupId>activation</groupId>
          <artifactId>activation</artifactId>
          <version>1.0.2</version>
        </project>

        <project>
          <groupId>activation</groupId>
          <artifactId>activation</artifactId>
          <version>1.0.2</version>
        </project>
     */
    public MavenCoords getCoords() {
        String gid = NO_ID;
        String aid = NO_ID;
        String v = NONE;

        // defaults using parent
        Element parent = (Element) XPathUtils.selectNode(project, PARENT);
        if (parent != null) {
            gid = XPathUtils.getValue(parent, GROUP_ID, gid);
            aid = XPathUtils.getValue(parent, ARTIFACT_ID, aid);
            v = XPathUtils.getValue(parent, VERSION, v, mavenprops);
        }

        // resolve using parent
        gid = XPathUtils.getValue(project, GROUP_ID, gid);
        aid = XPathUtils.getValue(project, ARTIFACT_ID, aid);
        v = XPathUtils.getValue(project, VERSION, v, mavenprops);

        return new MavenCoords(gid, aid, v);
    }

    /*
        <project ...>
            <modelVersion>4.0.0</modelVersion>

            <parent>
                <groupId>com.microsoft.msr.malmo</groupId>
                <artifactId>MalmoJavaJar</artifactId>
                <version>0.30.0</version>
            </parent>

            <!-- NO [artifactId] -->
            ...
        </project>

     */
    public MavenCoords getParentCoords() {
        // check a strange case:
        //
        //      1) <parent> present
        //      2) <artifactId> not present
        //
        Element parent = (Element) XPathUtils.selectNode(project, PARENT);
        if (parent == null)
            return null;

        Element artifactId = (Element) XPathUtils.selectNode(project, ARTIFACT_ID);
        if (artifactId == null)
            return null;

        String gid = XPathUtils.getValue(parent, GROUP_ID);
        String aid = XPathUtils.getValue(parent, ARTIFACT_ID);
        String v = XPathUtils.getValue(parent, VERSION);

        if (!isValid(gid) || !isValid(aid) || !isValid(v)) {
            logger.debugf("Invalid parent coords in %s", pomFile);
            return null;
        }

        return new MavenCoords(gid, aid, v);
    }

    /**
     * Retrieve the LOCAL parent '.pom', if it exists, otherwise  null
     */
    public MavenPom getParentPom() {
        MavenCoords parentCoords = getParentCoords();
        if (parentCoords == null)
            return null;

        /*
          <parent>
            <groupId>org.apache</groupId>
            <artifactId>apache</artifactId>
            <version>4</version>
            <relativePath>...</relativePath>
          </parent>
         */

        // 2) retrieve the 'relativePath'
        //    sometime there is '<relativePath/>
        String relativePath = XPathUtils.getValue(project, "parent/relativePath", PARENT_POM);
        if (relativePath.isEmpty())
            relativePath = PARENT_POM;
        {
            // 3) check if there is a 'local pom'
            File parentPom = new File(pomFile.getParentFile(), relativePath);
            if (parentPom.exists() && parentPom.isDirectory())
                parentPom = new File(parentPom, POM);
            if (parentPom.exists())
                return new MavenPom(parentPom, md);
        }

        // 3) check for a 'remote pom'
        if (md != null)
            return md.getPom(parentCoords);

        logger.errorf("Unable to retrieve parent pom in %s", pomFile);
        return null;
    }

    /*
        <project>
          <groupId>activation</groupId>
          <artifactId>activation</artifactId>
          <version>1.0.2</version>
          <distributionManagement>
            <relocation>
              <groupId>javax.activation</groupId>
            </relocation>
          </distributionManagement>
        </project>
     */
    public MavenCoords getRelocated() {
        MavenCoords coords = getCoords();
        Element relocation = (Element) XPathUtils.selectNode(project, DM_RELOCATION);
        String gid = XPathUtils.getValue(relocation, GROUP_ID);
        String aid = XPathUtils.getValue(relocation, ARTIFACT_ID);
        String v = XPathUtils.getValue(relocation, VERSION);

        return coords.merge(gid, aid, v);
    }

    public String getDownloadUrl() {
        return XPathUtils.getValue(project, DOWNLOAD_URL, "");
    }

    public Properties getProperties() {
        Properties properties = new Properties();

        MavenPom parentPom = getParentPom();
        if (parentPom != null)
            properties.putAll(parentPom.getProperties());

        properties.putAll(mavenprops);
        properties.putAll(localprops);

        return properties;
    }

    private String addProperty(Properties properties, String name, String xpath, String defaultValue) {
        String value = XPathUtils.getValue(project, xpath, defaultValue, properties);
        if (isValid(value))
            properties.put(name, value);
        return value;
    }

    /*
        <project>
            ...
            <repositories>
                <repository>
                    <id>sonatype-nexus-snapshots</id>
                    <name>Sonatype Nexus Snapshots</name>
                    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
                    ...
                </repository>
                ...
            </repositories>
            ...
        </project>
     */
    public Set<String> getRepositories() {
        Set<String> repoUrls = new HashSet<>();
        for (Element elt : XPathUtils.selectElements(project, REPOSITORIES)) {
            String repoUrl = XPathUtils.getValue(elt, "url");
            if (isValid(repoUrl))
                repoUrls.add(repoUrl);
        }
        return repoUrls;
    }

    // ----------------------------------------------------------------------
    // Modules
    // ----------------------------------------------------------------------

    /*
        <project>
            ...
            <modules>
                <module>relativePath</module>
                ...
            </modules>
            ...
        </project>
     */
    public List<String> getModules() {
        List<String> modules = new ArrayList<>();
        for (Element elt : XPathUtils.selectElements(project, "modules/module")) {
            String relativePath = elt.getTextContent();
            if (isValid(relativePath)) {
                modules.add(relativePath);
            }
        }
        return modules;
    }

    // ----------------------------------------------------------------------
    // Dependencies
    // ----------------------------------------------------------------------
    /*
        <project>
          <groupId>ant</groupId>
          <artifactId>ant</artifactId>
          <version>1.6.2</version>

          <dependencies>
            <dependency>
              <groupId>xerces</groupId>
              <artifactId>xerces-impl</artifactId>
              <version>2.6.2</version>
              <scope>compile</scope>
            </dependency>
            ...
          </dependencies>
        </project>
     */
    public List<MavenCoords> getDependencyCoords() {
        // try {
            return getDependencies()
                .stream()
                .map(dep -> dep.coords)
                .collect(Collectors.toList());
        // }
        // catch (Throwable t) {
        //     logger.error(pomFile.toString() + ": " + t, t);
        //     return Collections.emptyList();
        // }
    }

    public List<MavenDependency> getDependencies() {
        return getDependencies(DEPENDENCIES);
    }

    /*
        <project>
          ...
          <dependencyManagement>
            <dependencies>

              <dependency>
                <artifactId>asm</artifactId>
                <groupId>${project.groupId}</groupId>
                <version>${project.version}</version>
              </dependency>
              ...
            </dependencies>
          </dependencyManagement>

        </project>
     */
    public List<MavenDependency> getComponents() {
        return getDependencies(DM_DEPENDENCIES);
    }

    private List<MavenDependency> getDependencies(String xpath) {

        List<MavenDependency> depList = new ArrayList<>();
        Properties props = getProperties();
        MavenCoords coords = getCoords();
        XPathUtils.selectElements(project, xpath)
            .forEach(dep -> {
                boolean optional = XPathUtils.getValue(dep, OPTIONAL, false, props);
                String scope = XPathUtils.getValue(dep, SCOPE, SCOPE_COMPILE);
                boolean scoped = !SCOPE_COMPILE.equals(scope) && !SCOPE_PROVIDED.equals(scope) && !SCOPE_TEST.equals(scope);

                if (optional || scoped)
                    return;

                String gid = XPathUtils.getValue(dep, GROUP_ID, coords.groupId, props);
                String aid = XPathUtils.getValue(dep, ARTIFACT_ID, coords.artifactId, props);
                String v = XPathUtils.getValue(dep, VERSION, NONE, props);

                String category = String.format("%s:%s", gid, aid);

                // IF v is not valid AND gid is equal to coords.groupId, it is possible to use 'coords.version'
                // NO!
                //if (!isValid(v) && coords.groupId.equals(gid))
                //    v = coords.version;

                //if (!isValid(v))
                //    logger.warnf("No version in %s:%s - %s", gid, aid, pomFile);
                if (isRange(v))
                    logger.warnc(category,"Range version in %s:%s:%s - %s", gid, aid, v, pomFile);

                if (isPattern(v))
                    logger.warnc(category,"Pattern version in %s:%s:%s - %s", gid, aid, v, pomFile);

                MavenCoords dcoords = new MavenCoords(gid, aid, v);
                if (md != null && !dcoords.hasVersion())
                    dcoords = md.getVersioned(dcoords);

                depList.add( new MavenDependency(dcoords, scope));
            });

        return depList;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
