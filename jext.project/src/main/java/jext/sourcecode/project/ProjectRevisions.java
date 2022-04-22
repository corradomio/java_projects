package jext.sourcecode.project;

import jext.logging.Logger;
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
    private int srcRevision, dstRevision;

    private static final String PROJECT_INFO_REV  = "%s-source-project-r%02d.json";
    private static final String PROJECT_INFO_NAME = "%s-source-project";
    private static final String DIFFERENCES_INFO  = "%s-source-diff-r%02d-r%02d.json";
    private static final String DIFFERENCES_INFO_NAME = "%s-source-diff-";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ProjectRevisions(String refId, File splDirectory) {
        if (StringUtils.isEmpty(refId))
            this.refId = "none";
        else
            this.refId = refId;
        this.splDirectory = splDirectory;
        this.projectHome = splDirectory.getParentFile();
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
     *         false othwrwise
     */
    public ProjectComparator compareRevisions(int srcRevision, int dstRevision) {
        this.srcRevision = srcRevision;
        this.dstRevision = dstRevision;

        File differencesInfo = getDifferenceInfoFile();
        if (differencesInfo.exists()) {
            try {
                return JSONUtils.load(differencesInfo, ProjectComparator.class);
            } catch (IOException e) {
                Logger.getLogger(getClass()).error(e, e);
            }
        }

        Project psrc = null, pdst = null;
        File srcRevisionInfo = getProjectInfoFile(srcRevision);
        File dstRevisionInfo = getProjectInfoFile(dstRevision);

        if (srcRevisionInfo.exists())
            psrc = Projects.newProject(srcRevisionInfo, PropertiesUtils.empty());
        if (dstRevisionInfo.exists())
            pdst = Projects.newProject(dstRevisionInfo, PropertiesUtils.empty());

        ProjectComparator pcomp = ProjectComparator.compare(psrc, pdst, dstRevision);

        // check if there are no differences
        if (pcomp.isEmpty()) {
            FileUtils.delete(dstRevisionInfo);
        }

        try {
            pcomp.save(differencesInfo);
        } catch (IOException e) {
            Logger.getLogger(getClass()).error(e, e);
        }

        return pcomp;
    }

    /**
     * Delete 'project-info.json' and all 'project-info-[revision].json'
     * files
     */
    public void deleteAll() {
        String projectInfoName = String.format(PROJECT_INFO_NAME, refId);
        String differencesInfoName = String.format(DIFFERENCES_INFO_NAME, refId);

        FileUtils.asList(splDirectory.listFiles((dir, name) -> (
                name.contains(projectInfoName) ||
                name.contains(differencesInfoName))))
            .forEach(FileUtils::delete);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
