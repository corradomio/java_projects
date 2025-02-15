package org.hls.check;

import jext.cache.CacheManager;
import jext.util.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectDump;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class Check {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Parallel.setup();

        System.out.println(String.format("1-%s-2", "ciccio"));
    }
}
