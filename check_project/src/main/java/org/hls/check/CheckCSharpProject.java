package org.hls.check;

import jext.logging.Logger;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinderManager;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.csharp.libraries.CSharpRuntimeLibrary;
import jext.sourcecode.project.lfm.DefaultLibraryFinderManager;
import jext.sourcecode.project.util.ProjectDump;
import jext.sourcecode.project.csharp.CSharpLibraryFinder;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class CheckCSharpProject {

    public static void main(String[] args) {
        Logger.configure();
        // CacheManager.configure();
        Parallel.setup();

        LibraryFinderManager lfm = DefaultLibraryFinderManager.instance();
        CSharpLibraryFinder lfinder = (CSharpLibraryFinder) lfm.getLibraryFinder("csharp");
        lfinder.setNamedLibrary(".NET Core 6", new File("D:\\C#\\.NET Core\\6.0.300"));

        Library rt = lfinder.getRuntimeLibrary(".NET Core 6");
        rt.getFiles();

        Project project = Projects.newProject(new File(
            "D:\\Projects\\CSharp\\Apache-Lucene.Net-4.8.0"
            ),
            PropertiesUtils.properties(

            ), lfm);

        project.getModules().forEach(m -> {
            System.out.printf("  module %s: %s\n", m.getName().getName(), m.getModuleHome());
        });

        ProjectDump.dump(project, 0);

        Parallel.shutdown();

    }
}
