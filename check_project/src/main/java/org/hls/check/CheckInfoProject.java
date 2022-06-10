package org.hls.check;

import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.sourcecode.project.DefaultLibraryFinderManager;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.csharp.CSharpLibraryFinder;
import jext.sourcecode.project.util.ProjectDump;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class CheckInfoProject {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Parallel.setup();

        DefaultLibraryFinderManager lfm = DefaultLibraryFinderManager.instance();

        // Register a runtimeLibrary
        ((CSharpLibraryFinder)lfm.getLibraryFinder("csharp"))
            .setNamedLibrary("net60", new File("D:\\C#\\.NET Core\\6.0.300"));

        Project project = Projects.newProject(new File(
                "D:\\Projects\\CSharp\\Apache-Lucene.Net-4.8.0"
            ),
            PropertiesUtils.empty(), lfm);

        ProjectDump.dump(project, 0);

    }
}
