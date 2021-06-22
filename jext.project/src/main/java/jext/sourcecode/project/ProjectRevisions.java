package jext.sourcecode.project;

import jext.util.FileUtils;
import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;

public class ProjectRevisions {

    public static int NO_REVISION = -1;
    public static int CURRENT_REVISION = -1;

    private final File splDirectory;
    private int srcRevision;
    private int dstRevision;
    private ProjectDifferences pdiff;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ProjectRevisions(File splDirectory) {
        this.splDirectory = splDirectory;
    }

    // ----------------------------------------------------------------------
    // Files
    // ----------------------------------------------------------------------

    public File getProjectInfo() {
        return getProjectInfo(CURRENT_REVISION);
    }

    public File getSourceInfo() {
        return new File(splDirectory, "source-info.json");
    }

    public File getProjectInfo(int rev) {
        if (rev == -1)
            return new File(splDirectory, "project-info.json");
        String revProjectInfo = String.format("project-info-%04d.json", rev);
        return new File(splDirectory, revProjectInfo);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int getCurrentRevision() {
        for (int rev = 0; rev != -1; ++rev) {
            String revProjectInfo = String.format("project-info-%04d.json", rev);
            File projectInfo = new File(splDirectory, revProjectInfo);
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
        String diffName;
        if (srcRevision == NO_REVISION)
            diffName = String.format("differences-none-%04d.json", dstRevision);
        else
            diffName = String.format("differences-%04d-%04d.json", srcRevision, dstRevision);

        try {
            JSONUtils.save(new File(splDirectory, diffName), pdiff);
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
            delete(rev);
    }

    /**
     * Delete 'project-info.json' and all 'project-info-[revision].json'
     * files
     */
    public void delete() {
        int currentRevision = getCurrentRevision();
        for (int rev=0; rev<currentRevision; ++rev) {
            File revProjectInfo = getProjectInfo(rev);
            FileUtils.delete(revProjectInfo);
        }
        FileUtils.delete(getProjectInfo());
    }

    // @Override
    private void delete(int rev) {
        if (rev < 0) return;
        File revProjectInfo = getProjectInfo(rev);
        FileUtils.delete(revProjectInfo);
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
