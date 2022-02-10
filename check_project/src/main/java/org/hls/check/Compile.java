package org.hls.check;

import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.logging.Logger;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectDump;
import jext.util.Parameters;

import java.io.File;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public class Compile {

    public static void main(String[] args) {
        CacheManager.configure();
        Logger.configure();

        Name name = PathName.of("test/dl4j");
        Parameters params = Parameters.params();

        Project dl4j = Projects.newProject(name,
            new File("D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7"),
            params
        );

        System.out.println(dl4j.getModules().getModule().getTypes());

        // System.out.println(dl4j.getProjectType());
        // dl4j.getLibraries().forEach(library -> {
        //     System.out.println(library.getName());
        // });

        ProjectDump.dump(dl4j, ProjectDump.NO_TYPES | ProjectDump.NO_LIBRARIES);
        JavaParserPool pool = JavaParserPool.newPool("global");

        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger();
        dl4j.getModules().parallelStream()
            // .flatMap(module -> module.getSourceRoots().stream())
            // .flatMap(sourceRoot -> sourceRoot.getSources().stream())
            .flatMap(module -> module.getSources().stream())
            .forEach(source -> {
                pool.parse(source.getFile());
                if(count.incrementAndGet()% 100 == 0)
                    System.out.printf("... %d \n", count.get());
            });
        System.out.printf("done %d in %d ms\n", count.get(), System.currentTimeMillis() - start);

    }
}
