package org.hls.check;

import org.apache.log4j.BasicConfigurator;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;
import org.gradle.tooling.model.build.BuildEnvironment;
import org.gradle.tooling.model.idea.IdeaProject;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        //Logger.configure();
        BasicConfigurator.configure();

        ProjectConnection connection = GradleConnector.newConnector()
            .forProjectDirectory(new File("D:\\Projects.github\\other_projects\\hibernate-orm"))
            .connect();
        BuildEnvironment environment = connection.model(BuildEnvironment.class).get();
        System.out.println(environment.getBuildIdentifier().getRootDir());

        GradleProject project = connection.getModel(GradleProject.class);
        project.getChildren().forEach(p -> {
            System.out.println("jext.buildtools.Project " + p.getName());
            p.getChildren().forEach(p1 -> {
                System.out.println("... jext.buildtools.Project " + p1.getName());
            });
            // p.getTasks().forEach(t -> {
            //     System.out.println("... Task " + t.getName());
            // });
        });


        System.out.println("Tasks");
        project.getTasks().forEach(task -> {
            System.out.println("... " + task.getName());
        });

        System.out.println("Dependencies");
        connection.newBuild().forTasks("dependencies")
            .setStandardOutput(System.out)
            .setStandardError(System.err)
            .run();

        IdeaProject ip = connection.getModel(IdeaProject.class);

        // try {
        //     connection.newBuild().forTasks("hibernate-core:htmlDependencyReport").run();
        // } finally {
        //     connection.close();
        // }

        System.out.println("Done");
        connection.close();

    }
}
