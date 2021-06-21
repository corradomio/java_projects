package jext.sourcecode.project;

import jext.util.FileUtils;

import java.io.File;

public class ProjectRevisions {

    public static final int NO_REVISION = -1;
    public static final int CURRENT_REVISION = -1;

    private final File splDirectory;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ProjectRevisions(File splDirectory) {
        this.splDirectory = splDirectory;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * File for the current 'project-info.json'
     */
    public File getProjectInfo() {
        return getProjectInfo(CURRENT_REVISION);
    }

    /**
     * File for the current 'project-info-[rev].json'
     * Use -1 as 'rev' to retrieve the 'project-info' for the current
     * revision ('project-info.json'.
     */
    public File getProjectInfo(int rev) {
        if (rev == -1)
            return new File(splDirectory, "project-info.json");
        String revProjectInfo = String.format("project-info-%04d.json", rev);
        return new File(splDirectory, revProjectInfo);
    }

    /**
     * Compute the differences between the specified revision and the current
     * revision
     * @param rev the specified revision
     * @return the revision differences
     */
    public ProjectDifferences getDifferences(int rev) {

        ProjectDifferences pdiff = new ProjectDifferences();
        if (rev == getCurrentRevision())
            return pdiff;

        File revProjectInfoFile = getProjectInfo(rev);
        File curProjectInfoFile = getProjectInfo(CURRENT_REVISION);
        pdiff.compareRevisions(curProjectInfoFile, revProjectInfoFile);

        return pdiff;
    }

    /**
     * Current revision index (starting from 0)
     */
    public int getCurrentRevision() {
        for (int rev = 0; rev != -1; ++rev) {
            String revProjectInfo = String.format("project-info-%04d.json", rev);
            File projectInfo = new File(splDirectory, revProjectInfo);
            if (!projectInfo.exists())
                return rev;
        }
        return -1;
    }

    /**
     * Latest revision, that is, current revision minus 1
     */
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
     * The differences are considered in the following way:
     *
     *  - how to convert the specified revision 'rev' into the 'current' revision
     *
     * @param rev revision to consider or -1 (N
     * @return list of differences
     */
    public ProjectDifferences compareWithRevision(int rev) {
        File curProjectInfoFile = getProjectInfo(CURRENT_REVISION);
        File revProjectInfoFile = getProjectInfo(rev);

        ProjectDifferences pdiff = new ProjectDifferences();
        if (rev == NO_REVISION)
            pdiff.compareRevisions(null, curProjectInfoFile);
        else
            pdiff.compareRevisions(revProjectInfoFile, curProjectInfoFile);

        return pdiff;
    }

    /**
     * Save the current 'project-info.json' file as 'project-info-[current_revision].json'
     */
    public void save() {
        int rev = getCurrentRevision();
        File revProjectInfoFile = getProjectInfo(rev);
        File curProjectInfoFile = getProjectInfo();

        FileUtils.copy(curProjectInfoFile, revProjectInfoFile);
    }

    /**
     * If the current content of 'project-info.json' is the SAME than
     * 'project-info-[last_revision].json', DELETE 'project-info-[last_revision].json'
     */
    public void deleteDuplicated() {
        int rev = getLastRevision();
        if (rev < 0) return;

        ProjectDifferences pdiff = getDifferences(rev);
        if (pdiff.isEmpty()) delete(rev);
    }

    private void delete(int rev) {
        if (rev < 0) return;
        File revProjectInfoFile = getProjectInfo(rev);
        FileUtils.delete(revProjectInfoFile);
    }

}
