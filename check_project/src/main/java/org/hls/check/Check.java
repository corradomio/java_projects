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

        Project p = Projects.newProject(new File(
            // "D:\\SPLGroup\\BTProjects\\ForSalwa"
            // "D:\\SPLGroup\\BTProjects\\cocome-maven-project"
            // "D:\\SPLGroup\\example_repo\\deeplearning4j"
            "D:\\SPLGroup\\example_repo\\hibernate-orm-master"
            ), Properties.empty()
        ) ;

        String name = p.getName().getName();

        ProjectDump.yaml(p, new File(name + ".yaml"));


        Parallel.shutdown();
    }
}
