package org.hls.check;

import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectDump;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class CheckInfoProject {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Parallel.setup();

        Project project = Projects.newProject(new File(
                "project-info.json"
            ),
            PropertiesUtils.empty());

        ProjectDump.dump(project, 0);

    }
}
