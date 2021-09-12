package jext.sourcecode.project;

import jext.util.FileUtils;
import jext.util.JSONUtils;
import jext.util.StringUtils;

import java.io.File;
import java.io.IOException;

public class ProjectRevisions implements Revisions {

    private final String prefix;
    private final File splDirectory;
    private int srcRevision;
    private int dstRevision;
    private ProjectDifferences pdiff;

    private static final String SOURCE_INFO       = "source-info.json";
    private static final String SOURCE_INFO_REV   = "source-info-%04d.json";
    private static final String SOURCE_INFO_NAME  = "source-info";
    private static final String PROJECT_INFO      = "project-info.json";
    private static final String PROJECT_INFO_REV  = "project-info-%04d.json";
    private static final String PROJECT_INFO_NAME = "project-info";
    private static final String DIFFERENCES_NONE  = "differences-none-%04d.json";
    private static final String DIFFERENCES_INFO  = "differences-%04d-%04d.json";
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

    public File getProjectInfoFile() {
        return getProjectInfoFile(CURRENT_REVISION);
    }

    public File getSourceInfoFile() {
        return new File(splDirectory, prefix + SOURCE_INFO);
    }

    public File getProjectInfoFile(int rev) {
        if (rev == CURRENT_REVISION)
            return new File(splDirectory, prefix + PROJECT_INFO);

        String revProjectInfo = String.format(PROJECT_INFO_REV, rev);
        return new File(splDirectory, prefix + revProjectInfo);
    }

    public File getSourceInfoFile(int rev) {
        if (rev == CURRENT_REVISION)
            return new File(splDirectory, prefix + SOURCE_INFO);
        String revSourceInfo = String.format(SOURCE_INFO_REV, rev);
        return new File(splDirectory, prefix + revSourceInfo);
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
        File projectInfoFile = new File(splDirectory, prefix + PROJECT_INFO);
        if (!projectInfoFile.exists())
            return NO_REVISION;

        for (int rev=0; true; ++rev) {
            String revProjectInfo = String.format(PROJECT_INFO_REV, rev);
            projectInfoFile = new File(splDirectory, prefix + revProjectInfo);
            if (!projectInfoFile.exists())
                return rev;
        }
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
        File srcProjectInfo = getProjectInfoFile(rev);
        File dstProjectInfo = getProjectInfoFile();

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

        // save project info
        File curProjectInfo = getProjectInfoFile();
        File revProjectInfo = getProjectInfoFile(rev);
        FileUtils.copy(curProjectInfo, revProjectInfo);

        // save source info
        File curSourceInfo = getSourceInfoFile();
        File revSourceInfo  = getSourceInfoFile(rev);
        FileUtils.copy(curSourceInfo, revSourceInfo);
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
        int rev = getCurrentRevision() - 1;
        if (rev < 0) return;

        ProjectDifferences pdiff = new ProjectDifferences();

        File revProjectInfo = getProjectInfoFile(rev);
        File revSourceInfo  = getSourceInfoFile(rev);

        File curProjectInfo = getProjectInfoFile(CURRENT_REVISION);
        pdiff.compareProjects(curProjectInfo, revProjectInfo);

        if (pdiff.isEmpty()) {
            FileUtils.delete(revProjectInfo);
            FileUtils.delete(revSourceInfo);
        }
    }

    /**
     * Delete 'project-info.json' and all 'project-info-[revision].json'
     * files
     */
    public void deleteAll() {
        FileUtils.asList(splDirectory.listFiles((dir, name) -> (
                name.contains(prefix + PROJECT_INFO_NAME) ||
                name.contains(prefix + SOURCE_INFO_NAME)  ||
                name.contains(prefix + DIFFERENCES_INFO_NAME))))
            .forEach(FileUtils::delete);
        FileUtils.delete(getSourceInfoFile());
    }

    private ProjectDifferences getDifferences(int rev) {

        pdiff = new ProjectDifferences();
        if (rev == getCurrentRevision())
            return pdiff;

        File revProjectInfo = getProjectInfoFile(rev);
        File curProjectInfo = getProjectInfoFile(CURRENT_REVISION);
        pdiff.compareProjects(curProjectInfo, revProjectInfo);

        return pdiff;
    }

}
