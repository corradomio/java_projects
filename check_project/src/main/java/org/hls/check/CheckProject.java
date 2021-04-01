package org.hls.check;

import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectInfo;
import jext.util.JSONUtils;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CheckProject {

    public static void main(String[] args) {
        try {
            Logger.configure();
            CacheManager.configure();

            Project project = Projects.newProject(new File(
                    //"D:\\SPLGroup\\spl-workspaces\\dev-workspace\\workspace\\example_repo\\elasticsearch"
                    // "D:\\SPLGroup\\example_repo\\cocome-maven-project"
                "D:\\SPLGroup\\example_repo\\commons_lang"
                ),
                PropertiesUtils.empty());

            Map<String, ?> pinfo = ProjectInfo.analyze(project);
            JSONUtils.save(new File("project-info.json"), pinfo);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Parallel.shutdown();
        }
    }
}
