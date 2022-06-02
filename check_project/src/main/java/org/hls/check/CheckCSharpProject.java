package org.hls.check;

import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectDump;
import jext.sourcecode.project.csharp.CSharpLibraryFinder;
import jext.util.PropertiesUtils;

import java.io.File;

public class CheckCSharpProject {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        LibraryFinder lfinder = new CSharpLibraryFinder()
            ;

        Project project = Projects.newProject(new File(
            "D:\\Projects\\CSharp\\Apache-Lucene.Net-4.8.0-beta00016.src"
            ),
            PropertiesUtils.properties(

            ));

        project.setLibraryFinder(lfinder);

        project.getModules().forEach(m -> {
            System.out.printf("  module %s: %s\n", m.getName().getName(), m.getModuleHome());
        });


        ProjectDump.dump(project, 0);

    }
}
