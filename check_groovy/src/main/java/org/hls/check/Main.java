package org.hls.check;

import org.apache.log4j.BasicConfigurator;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;
import org.gradle.tooling.model.Launchable;
import org.gradle.tooling.model.build.BuildEnvironment;
import org.gradle.tooling.model.Dependency;
import org.gradle.tooling.model.eclipse.EclipseProject;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        ProjectConnection connection = GradleConnector.newConnector()
            .forProjectDirectory(new File("D:\\Projects.github\\github_projects\\hibernate-orm"))
            .connect();
        BuildEnvironment environment = connection.model(BuildEnvironment.class).get();
        System.out.println(environment.getBuildIdentifier().getRootDir());

        GradleProject project = connection.getModel(GradleProject.class);
        project.getChildren().forEach(p -> {
            System.out.println("Project " + p.getName());
            p.getChildren().forEach(p1 -> {
                System.out.println("... Project " + p1.getName());
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

        // try {
        //     connection.newBuild().forTasks("hibernate-core:htmlDependencyReport").run();
        // } finally {
        //     connection.close();
        // }

        System.out.println("Done");
        connection.close();

    }
}
