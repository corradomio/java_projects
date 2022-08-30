package jext.sourcecode.project.lfm.java;

import jext.logging.Logger;
import jext.sourcecode.project.util.BaseLibraryRepository;
import jext.util.FileUtils;

import java.io.File;

public class MavenRepository extends BaseLibraryRepository {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(MavenRepository.class);

    private static final long MAX_VERSIONS_AGE = 24L*60*60*1000L;   // 1 day in milliseconds
    private static final int BUFFER_SIZE = 1024;

    private boolean initialized = false;
    private File m2Directory;

    private enum ArtifactType {
        METADATA,       // maven-metadata.xml
        POM,            // <...>.pom
        ARTIFACT,       // <...>.jar | <...>.aar | <...>.<packaging>
        JAVADOC,        // <...>-javadoc.jar
        SOURCES,        // <...>-sources.jar
        ANNOTATIONS,    // <...>-annotations.jar
        VERSIONS,       // <...>.html
        //INVALID         // invalid  (used when it is not possible to find a library)
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MavenRepository() {
        setInfo("MVNrepository", "https://mvnrepository.com/");
        setDownloadDirectory(new File("."));
    }

    public void initialize() {
        if (initialized)
            return;
        else
            initialized = true;

        createDownloadDirectory();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------




    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void createDownloadDirectory() {
        m2Directory = new File(download, ".m2/repository");
        if (!m2Directory.exists())
            FileUtils.mkdirs(m2Directory);
    }
}
