package jext.buildtools.maven;

import jext.logging.Logger;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static jext.buildtools.maven.MavenCoords.isValid;
import static jext.buildtools.maven.MavenCoords.isRange;
import static jext.buildtools.maven.MavenCoords.isPattern;

public class MavenPom implements MavenConst {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    static final String EMPTY_POM =
        "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<project xmlns='http://maven.apache.org/POM/4.0.0'/>";

    public static final String POM = "pom.xml";

    private static final String PARENT_POM = "../pom.xml";
    private static final String DM_RELOCATION = "distributionManagement/relocation";
    private static final String DM_DEPENDENCIES = "dependencyManagement/dependencies/dependency";
    private static final String DEPENDENCIES = "dependencies/dependency";
    private static final String REPOSITORIES = "repositories/repository";

    private MavenDownloader downloader;

    private Logger logger = MavenDownloader.logger;
    private File pomFile;
    private Element project;

    // local caches
    private Properties properties;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MavenPom(File pomFile) {
        this(pomFile, null);
    }

    public MavenPom(File pomFile, MavenDownloader downloader) {
        this.downloader = downloader;

        if (pomFile.isDirectory())
            pomFile = new File(pomFile, POM);

        this.pomFile = pomFile;
        try {
            this.project = XPathUtils.parse(pomFile).getDocumentElement();
        } catch (Exception e) {
            logger.errorf("Unable to parse %s: %s", pomFile, e);
        }

        if (this.project == null)
            try {
                this.project = XPathUtils.parse(EMPTY_POM).getDocumentElement();
            } catch (Exception e) {
                logger.error(e, e);
            }
    }

    // ----------------------------------------------------------------------
    // Predicates
    // ----------------------------------------------------------------------

    /** Check if '.pom' file exists */
    public boolean exists() {
        return pomFile.exists();
    }

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
        String packaging = XPathUtils.getValue(project, PACKAGING);
        return PACKAGING_POM.equals(packaging);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

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
        String gid = UNKNOWN;
        String aid = UNKNOWN;
        String v = NO_VERSION;

        // defaults using parent
        MavenCoords parentCoords = getParentCoords();
        if (parentCoords != null) {
            gid = parentCoords.groupId;
            aid = parentCoords.artifactId;
            v = parentCoords.version;
        }

        // resolve using parent
        gid = XPathUtils.getValue(project, GROUP_ID, gid);
        aid = XPathUtils.getValue(project, ARTIFACT_ID, aid);
        v = XPathUtils.getValue(project, VERSION, v);

        return new MavenCoords(gid, aid, v);
    }

    public MavenCoords getParentCoords() {
        Element parent = (Element) XPathUtils.selectNode(project, PARENT);
        if (parent == null)
            return null;

        String gid = XPathUtils.getValue(parent, GROUP_ID);
        String aid = XPathUtils.getValue(parent, ARTIFACT_ID);
        String v   = XPathUtils.getValue(parent, VERSION);

        if (!isValid(gid) || !isValid(aid) || !isValid(v)) {
            logger.errorf("Invalid parent coords in %s", pomFile);
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
        if (isValid(relativePath)) {
            // 3) check if there is a 'local pom'
            File parentPom = new File(pomFile.getParentFile(), relativePath);
            if (parentPom.exists() && parentPom.isDirectory())
                parentPom = new File(parentPom, POM);
            if (parentPom.exists())
                return new MavenPom(parentPom, downloader);
        }

        // 3) check for a 'remote pom'
        if (downloader != null)
            return downloader.getPom(parentCoords);

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

    /**
     * Retrieve the list of defined properties
     */
    public Properties getProperties() {
        if (properties != null)
            return properties;

        properties = new Properties();

        // 1) System Java properties
        //properties.putAll(System.getProperties());

        // 2) parent properties
        {
            MavenPom parentPom = getParentPom();
            if (parentPom != null)
                properties.putAll(parentPom.getProperties());
        }

        // 3) project properties
        {
            String parentGroupId, parentArtifactId, parentVersion;

            // 3.1) project info
            {
                addProperty(properties,"project.modelVersion", "modelVersion", "4.0.0");
                addProperty(properties,"project.name", "name", "");
                properties.put("project.basedir", pomFile.getParentFile().getAbsolutePath());
            }

            // 3.2) parent coords
            {
                parentGroupId    = addProperty(properties,"project.parent.groupId", "parent/groupId", null);
                parentArtifactId = addProperty(properties,"project.parent.artifactId", "parent/artifactId", null);
                parentVersion    = addProperty(properties,"project.parent.version", "parent/version", null);
            }

            // 3.3) current coords
            {
                addProperty(properties,"project.groupId", "groupId", parentGroupId);
                addProperty(properties,"project.artifactId", "artifactId", parentArtifactId);
                addProperty(properties,"project.version", "version", parentVersion);

                addProperty(properties,"project.packaging", "packaging", "jar");
            }
        }

        // 4) add all 'current properties'
        XPathUtils.selectNodes(project, "properties/*")
            .forEach(eprop -> {
                String pname = eprop.getNodeName().trim();
                String value = eprop.getTextContent().trim();

                properties.put(pname, value);
            });

        return properties;
    }

    private String addProperty(Properties properties, String name, String xpath, String defaultValue) {
        String value = XPathUtils.getValue(project, xpath, defaultValue);
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
    public List<String> getRepositories() {
        List<String> repoUrls = new ArrayList<>();
        for(Element elt : XPathUtils.selectNodes(project, REPOSITORIES)) {
            String repoUrl = XPathUtils.getValue(elt, "url");
            if (isValid(repoUrl))
                repoUrls.add(repoUrl);
        }
        return repoUrls;
    }

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
        File currentDir = pomFile.getParentFile();
        List<String> modules = new ArrayList<>();
        for(Element elt : XPathUtils.selectNodes(project, "modules/module")) {
            String relativePath = elt.getTextContent();
            if (isValid(relativePath)) {
                modules.add(relativePath);
            }
        }
        return modules;
    }

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
        return getDependencies()
            .stream()
            .map(dep -> dep.coords)
            .collect(Collectors.toList());
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
        if (hasRepositories()) {
            // logger.warnf("With repositories: %s", pomFile);
            List<String> repoUrls = getRepositories();

            // create a local downloader
            downloader = downloader.newDownloader();
            downloader.addRepositories(repoUrls);
        }

        List<MavenDependency> depList = new ArrayList<>();
        Properties props = getProperties();
        MavenCoords coords = getCoords();
        XPathUtils.selectNodes(project, xpath)
            .forEach(dep -> {
                String gid = XPathUtils.getValue(dep, GROUP_ID, coords.groupId, props);
                String aid = XPathUtils.getValue(dep, ARTIFACT_ID, coords.artifactId, props);
                String v = XPathUtils.getValue(dep, VERSION, NO_VERSION, props);
                String s = XPathUtils.getValue(dep, SCOPE, SCOPE_COMPILE);

                // IF v is not valid AND gid is equal to coords.groupId, it is possible to use 'coords.version'
                //
                if (!isValid(v) && coords.groupId.equals(gid))
                    v = coords.version;

                //if (!isValid(v))
                //    logger.warnf("No version in %s:%s - %s", gid, aid, pomFile);
                if (isRange(v))
                    logger.warnf("Range version in %s:%s:%s - %s", gid, aid, v, pomFile);

                if (isPattern(v))
                    logger.warnf("Pattern version in %s:%s:%s - %s", gid, aid, v, pomFile);

                MavenDependency dcoords = new MavenDependency(gid, aid, v, s);
                depList.add(dcoords);
            });

        return depList;
    }

}