package org.hls.check;

import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectDump;
import jext.sourcecode.project.util.ProjectInfo;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.util.Map;

public class CheckElasticsearch {

    public static void main(String[] args) {
        try {
            CacheManager.configure();
            Logger.configure();

            Project project = Projects.newProject(new File("D:\\Projects.github\\ml_projects\\elasticsearch-5.6.16"),
                PropertiesUtils.empty());

            Map<String, ?> pinfo = ProjectInfo.analyze(project);
        }
        finally {
            Parallel.shutdown();
        }
    }
}
