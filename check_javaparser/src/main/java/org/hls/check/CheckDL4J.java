package org.hls.check;

import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectDump;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class CheckDL4J {

    public static void main(String[] args) {
        Parallel.setup();
        Logger.configure();
        CacheManager.configure();

        Project project = Projects.newProject(
            new File("D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7")
        , PropertiesUtils.empty());

        project.getLibraryDownloader().setDownload(new File("C:\\Users\\Corrado Mio\\.m2\\repository"));

        ProjectDump.dump(project);

    }
}
