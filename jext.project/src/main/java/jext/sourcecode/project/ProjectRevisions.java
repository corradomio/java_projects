package jext.sourcecode.project;

import jext.util.FileUtils;

import java.io.File;

public class ProjectRevisions {

    public static int NO_REVISION = -1;
    public static int CURRENT_REVISION = -1;

    private final File splDirectory;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ProjectRevisions(File splDirectory) {
        this.splDirectory = splDirectory;
    }


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
        File curProjectInfo = getProjectInfo();
        File revProjectInfo = getProjectInfo(rev);

        ProjectDifferences pdiff = new ProjectDifferences();
        if (rev == NO_REVISION)
            pdiff.compareRevisions(null, curProjectInfo);
        else
            pdiff.compareRevisions(curProjectInfo, revProjectInfo);

        return pdiff;
    }

    /**
     * Save the current 'project-info.json' file as 'project-info-[revision].json'
     */
    public void save() {
        int rev = getCurrentRevision();
        File revProjectInfo = getProjectInfo(rev);
        File curProjectInfo = getProjectInfo();

        FileUtils.copy(curProjectInfo, revProjectInfo);
    }

    /**
     * If the current content of 'project-info.json' is the SAME than
     * 'project-info-[revision].json', DELETE 'project-info-[revision].json'
     */
    public void deleteDuplicated() {
        int rev = getLastRevision();
        if (rev < 0) return;

        ProjectDifferences pdiff = getDifferences(rev);
        if (pdiff.isEmpty()) delete(rev);
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

    // @Override
    public ProjectDifferences getDifferences(int rev) {

        ProjectDifferences pdiff = new ProjectDifferences();
        if (rev == getCurrentRevision())
            return pdiff;

        File revProjectInfo = getProjectInfo(rev);
        File curProjectInfo = getProjectInfo(CURRENT_REVISION);
        pdiff.compareRevisions(curProjectInfo, revProjectInfo);

        return pdiff;
    }

}
