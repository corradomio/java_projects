package jext.sourcecode.project;

import jext.util.logging.Logger;
import jext.util.Assert;
import jext.util.FileUtils;
import jext.util.JSONUtils;
import jext.util.PropertiesUtils;
import jext.util.StringUtils;

import java.io.File;
import java.io.IOException;


public class ProjectRevisions implements Revisions {

    private final File splDirectory;
    private final File projectHome;
    private final String refId;
    private final LibraryFinderManager libraryFinderManager;
    private int srcRevision;
    private int dstRevision;

    private static final String PROJECT_INFO_REV  = "%s-source-project-r%02d.json";
    private static final String DIFFERENCES_INFO  = "%s-source-diff-r%02d-r%02d.json";
    private static final String SOURCE_MODEL_PREFIX = "%s-source-";

    // private static final String PROJECT_INFO_NAME = "%s-source-project";
    // private static final String DIFFERENCES_INFO_NAME = "%s-source-diff-";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ProjectRevisions(String refId, File splDirectory, LibraryFinderManager lfm) {
        if (StringUtils.isEmpty(refId))
            this.refId = "none";
        else
            this.refId = refId;
        this.splDirectory = splDirectory;
        this.projectHome = splDirectory.getParentFile();
        this.libraryFinderManager = lfm;
        Assert.verify(lfm != null, "LibraryFinderManager is null");
    }

    // ----------------------------------------------------------------------
    // Files
    // ----------------------------------------------------------------------

    public File getSrcProjectInfoFile() {
        return getProjectInfoFile(srcRevision);
    }

    public File getDstProjectInfoFile() {
        return getProjectInfoFile(dstRevision);
    }

    public File getProjectInfoFile(int revision) {
        String revProjectInfo = String.format(PROJECT_INFO_REV, refId, revision);
        return new File(splDirectory, revProjectInfo);
    }

    public File getDifferenceInfoFile() {
        String differenceInfo = String.format(DIFFERENCES_INFO, refId, srcRevision, dstRevision);
        return new File(splDirectory, differenceInfo);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    /**
     * If the current content of 'project-info.json' is the SAME than
     * 'project-info-[revision].json', DELETE 'project-info-[revision].json'
     *
     * @return true if the new revision is equals to the previous one
     *         false otherwise
     */
    public ProjectComparator compareRevisions(int srcRevision, int dstRevision) {
        this.srcRevision = srcRevision;
        this.dstRevision = dstRevision;

        File srcRevisionInfo = getProjectInfoFile(srcRevision);
        File dstRevisionInfo = getProjectInfoFile(dstRevision);
        File differencesInfo = getDifferenceInfoFile();

        // ERROR: it is NOT enough to check if the file already exists,
        // BUT it is necessary to check IF the timestamp follow the timestamp
        // of dstRevisionInfo
        // OR, better, this test has no sense
        // if (differencesInfo.exists()) {
        //     try {
        //         return JSONUtils.load(differencesInfo, ProjectComparator.class);
        //     } catch (IOException e) {
        //         Logger.getLogger(getClass()).error(e, e);
        //     }
        // }

        Project psrc = null, pdst = null;
        LibraryFinderManager lfm = this.libraryFinderManager;

        if (srcRevisionInfo.exists())
            psrc = Projects.newProject(srcRevisionInfo, PropertiesUtils.empty(), lfm);
        if (dstRevisionInfo.exists())
            pdst = Projects.newProject(dstRevisionInfo, PropertiesUtils.empty(), lfm);

        ProjectComparator pcomp = ProjectComparator.compare(psrc, pdst, dstRevision);

        // check if there are no differences
        if (pcomp.isEmpty()) {
            FileUtils.delete(dstRevisionInfo);
        }

        pcomp.save(differencesInfo);

        return pcomp;
    }

    /**
     * Delete 'project-info.json' and all 'project-info-[revision].json'
     * files
     */
    public void deleteAll() {
        String namePrefix = String.format(SOURCE_MODEL_PREFIX, refId);

        FileUtils.asList(splDirectory.listFiles((dir, name) -> (
                name.startsWith(namePrefix))))
            .forEach(FileUtils::delete);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
