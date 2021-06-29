package jext.sourcecode.project;

import jext.util.FileUtils;
import jext.util.JSONUtils;
import jext.util.StringUtils;

import java.io.File;
import java.io.IOException;

public class ProjectRevisions {

    public static int NO_REVISION = -1;
    public static int CURRENT_REVISION = -1;

    private final String prefix;
    private final File splDirectory;
    private int srcRevision;
    private int dstRevision;
    private ProjectDifferences pdiff;

    private static final String SOURCE_INFO = "source-info.json";
    private static final String PROJECT_INFO = "project-info.json";
    private static final String PROJECT_INFO_REV = "project-info-%04d.json";
    private static final String PROJECT_INF_NAME = "project-info";
    private static final String DIFFERENCES_NONE = "differences-none-%04d.json";
    private static final String DIFFERENCES_INFO = "differences-%04d-%04d.json";
    private static final String DIFFERENCES_INFO_NAME = "differences-";


    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ProjectRevisions(String refId, File splDirectory) {
        if (StringUtils.isEmpty(refId))
            this.prefix = StringUtils.empty();
        else
            this.prefix = refId + "-";
        this.splDirectory = splDirectory;
    }

    // ----------------------------------------------------------------------
    // Files
    // ----------------------------------------------------------------------

    public File getProjectInfo() {
        return getProjectInfo(CURRENT_REVISION);
    }

    public File getSourceInfo() {
        return new File(splDirectory, prefix + SOURCE_INFO);
    }

    public File getProjectInfo(int rev) {
        if (rev == -1)
            return new File(splDirectory, prefix + PROJECT_INFO);
        String revProjectInfo = String.format(PROJECT_INFO_REV, rev);
        return new File(splDirectory, prefix + revProjectInfo);
    }

    public File getDifferenceInfo(int srcRev, int dstRev) {
        String differenceInfo;
        if (srcRev == NO_REVISION)
            differenceInfo = String.format(DIFFERENCES_NONE, dstRev);
        else
            differenceInfo = String.format(DIFFERENCES_INFO, srcRev, dstRev);
        return new File(splDirectory, prefix + differenceInfo);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int getCurrentRevision() {
        for (int rev = 0; rev != -1; ++rev) {
            File projectInfo = getProjectInfo(rev);
            if (!projectInfo.exists())
                return rev;
        }
        return -1;
    }

    public int getLastRevision() {
        return getCurrentRevision() - 1;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    /**
     * Compare the current revision (the content of file 'project-info.json')
     * with the specified revision (the content of file 'project-info-[rev].json')
     *
     * The differences as considered in the following way:
     *
     *  - how to convert the specified revision 'rev' into the 'current' revision
     *
     * Note:
     *
     * @param rev revision to consider or -1 (N
     * @return list of differences
     */
    public ProjectDifferences compareWithRevision(int rev) {
        File srcProjectInfo = getProjectInfo(rev);
        File dstProjectInfo = getProjectInfo();

        this.srcRevision = rev;
        this.dstRevision = getCurrentRevision();

        pdiff = new ProjectDifferences();
        if (rev == NO_REVISION)
            pdiff.compareProjects(null, dstProjectInfo);
        else
            pdiff.compareProjects(srcProjectInfo, dstProjectInfo);

        saveDifferences();
        return pdiff;
    }

    /**
     * Save the current 'project-info.json' file as 'project-info-[revision].json'
     */
    public void saveRevision() {
        int rev = getCurrentRevision();

        File curProjectInfo = getProjectInfo();
        File revProjectInfo = getProjectInfo(rev);

        FileUtils.copy(curProjectInfo, revProjectInfo);
    }

    public void saveDifferences() {
        try {
            File differenceInfo =getDifferenceInfo(srcRevision, dstRevision);
            JSONUtils.save(differenceInfo, pdiff);
        } catch (IOException e) { }
    }

    /**
     * If the current content of 'project-info.json' is the SAME than
     * 'project-info-[revision].json', DELETE 'project-info-[revision].json'
     */
    public void deleteDuplicated() {
        int rev = getLastRevision();
        if (rev < 0) return;

        ProjectDifferences pdiff = new ProjectDifferences();

        File revProjectInfo = getProjectInfo(rev);
        File curProjectInfo = getProjectInfo(CURRENT_REVISION);
        pdiff.compareProjects(curProjectInfo, revProjectInfo);

        if (pdiff.isEmpty())
            FileUtils.delete(revProjectInfo);
    }

    /**
     * Delete 'project-info.json' and all 'project-info-[revision].json'
     * files
     */
    public void deleteAll() {
        FileUtils.asList(splDirectory.listFiles((dir, name) -> name.contains(prefix + PROJECT_INF_NAME)))
            .forEach(FileUtils::delete);
        FileUtils.asList(splDirectory.listFiles((dir, name) -> name.contains(prefix + DIFFERENCES_INFO_NAME)))
            .forEach(FileUtils::delete);
        FileUtils.delete(getSourceInfo());
    }

    private ProjectDifferences getDifferences(int rev) {

        pdiff = new ProjectDifferences();
        if (rev == getCurrentRevision())
            return pdiff;

        File revProjectInfo = getProjectInfo(rev);
        File curProjectInfo = getProjectInfo(CURRENT_REVISION);
        pdiff.compareProjects(curProjectInfo, revProjectInfo);

        return pdiff;
    }

}
