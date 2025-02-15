package org.hls.check;


import jext.buildtools.SourceProject;
import jext.buildtools.gradle.GradleProject;
import jext.util.logging.Logger;

import java.io.File;
import java.util.Properties;

public class MainGradle {

    public static void main(String[] args) throws Exception {
        Logger.configure();

        GradleProject p = new GradleProject(new File("D:\\Projects.github\\other_projects\\hibernate-orm"))
                .properties(new Properties(){{
            put(GradleProject.GRADLE_VERSION, "6.4");
        }});

        SourceProject.dump(p);
    }
}
