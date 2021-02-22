package org.hls.check;

import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectDump;
import jext.util.Properties;
import jext.util.concurrent.Parallel;

import java.io.File;

public class Check {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Parallel.setup();

        Project p = Projects.newProject(
            // new File("D:\\SPLGroup\\Downloads\\BTProjects\\ForSalwa")
            new File("D:\\Projects.github\\ml_projects\\elasticsearch-7.11.0")
            , Properties.empty()
        ) ;

        ProjectDump.dump(p);


        Parallel.shutdown();
    }
}
