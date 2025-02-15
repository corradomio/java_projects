package org.hls.check;

import jext.util.logging.Logger;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinderManager;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.csharp.CSharpLibraryFinder;
import jext.sourcecode.project.lfm.DefaultLibraryFinderManager;
import jext.sourcecode.project.python.PythonLibraryFinder;
import jext.sourcecode.project.util.ProjectDump;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.util.Collection;

public class CheckPythonProject {

    public static void main(String[] args) {
        Logger.configure();
        // CacheManager.configure();
        Parallel.setup();

        LibraryFinderManager lfm = DefaultLibraryFinderManager.instance();
        PythonLibraryFinder lfinder = (PythonLibraryFinder) lfm.getLibraryFinder("python");
        lfinder.setNamedLibrary("3.8,py38", "3.8", new File("D:\\Python\\Anaconda3-2022.05"));

        Library rt = lfinder.getRuntimeLibrary("py38");
        System.out.println(rt.getFiles());

        Project project = Projects.newProject(new File(
                // "D:\\Projects\\Python\\flask-2.1.2"
                "D:\\Projects\\Python\\pypiserver-1.5.0"
                ),
                PropertiesUtils.properties(

                ), lfm);

        Collection<Library> libs = project.getLibraries();
        libs.forEach(l -> {
            System.out.printf("%s:%s", l.getName(), l.getVersion());
        });

        // project.getModules().forEach(m -> {
        //     System.out.printf("  module %s: %s\n", m.getName().getName(), m.getModuleHome());
        // });
        //
        // project.getModules().forEach(m -> {
        //     System.out.printf("  module %s: %s\n", m.getName().getName(), m.getModuleHome());
        //     System.out.println(m.getDeclaredLibraries());
        // });

        ProjectDump.dump(project, 0);

        Parallel.shutdown();

    }
}
