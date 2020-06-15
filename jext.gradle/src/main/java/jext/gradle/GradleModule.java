package jext.gradle;

import jext.gradle.util.GradleUtils;
import jext.gradle.util.StringsOutputStream;
import jext.logging.Logger;
import jext.util.FileUtils;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GradleModule {

    private Logger logger;

    private GradleProject project;
    private GradleModule parent;
    private List<GradleModule> modules;
    private File moduleDir;
    private String name;

    public GradleModule(GradleProject project) {
        this.project = project;
        this.moduleDir = project.getProjectDir();
        this.name = FileUtils.relativePath(project.getProjectDir(), moduleDir);
        this.logger = Logger.getLogger(getClass(), name);
    }

    public String getName() {
        return name;
    }

    public GradleProject getProject() {
        return project;
    }

    public List<GradleModule> getModules(){
        if (modules != null)
            return modules;

        /*
            ------------------------------------------------------------
            Root project
            ------------------------------------------------------------

            Root project 'hibernate-orm'
            +--- Project ':documentation' - The Hibernate ORM documentation module
            +--- Project ':hibernate-agroal' - Integration for Agroal as a ConnectionProvider for Hibernate ORM
            +--- Project ':hibernate-c3p0' - Integration for c3p0 Connection pooling into Hibernate ORM
            +--- Project ':hibernate-core' - Hibernate's core ORM functionality
            +--- Project ':hibernate-ehcache' - Integration for using Ehcache 2.x as a Hibernate second-level-cache provider
            +--- Project ':hibernate-enhance-maven-plugin' - Enhance Plugin of the Hibernate project for use with Maven build system.
            +--- Project ':hibernate-entitymanager' - (deprecated - use hibernate-core instead) Hibernate O/RM implementation of the JPA specification
            +--- Project ':hibernate-envers' - Hibernate's entity version (audit/history) support
            +--- Project ':hibernate-graalvm' - Experimental extension to make it easier to compile applications into a GraalVM native image
            +--- Project ':hibernate-gradle-plugin' - Gradle plugin for integrating Hibernate functionality into your build
            +--- Project ':hibernate-hikaricp' - Integration for HikariCP into Hibernate O/RM
            +--- Project ':hibernate-infinispan' - (deprecated - use org.infinispan:infinispan-hibernate-cache-v53 instead)
            +--- Project ':hibernate-integrationtest-java-modules' - Integration tests for running Hibernate ORM in the Java module path
            +--- Project ':hibernate-java8' - (deprecated - use hibernate-core instead) Support for Java8-specific features - mainly Java8 Date/Time (JSR 310)
            +--- Project ':hibernate-jcache' - Integration for javax.cache into Hibernate as a second-level caching service
            +--- Project ':hibernate-jpamodelgen' - Annotation Processor to generate JPA 2 static metamodel classes
            +--- Project ':hibernate-osgi' - Support for running Hibernate O/RM in OSGi environments
            +--- Project ':hibernate-proxool' - Integration for Proxool Connection pooling into Hibernate O/RM
            +--- Project ':hibernate-spatial' - Integrate support for Spatial/GIS data into Hibernate O/RM
            +--- Project ':hibernate-testing' - Support for testing Hibernate ORM functionality
            +--- Project ':hibernate-vibur' - Integration for Vibur Connection pooling as a Hibernate ORM ConnectionProvider
            \--- Project ':release'

         */

        modules = new ArrayList<>();

        StringsOutputStream out = new StringsOutputStream();
        StringsOutputStream err = new StringsOutputStream();
        String projectsTask = GradleUtils.toTask(name, "projects");
        try(ProjectConnection connection = project.getConnection()) {
            connection
                .newBuild().forTasks(projectsTask)
                .setStandardOutput(out)
                .setStandardError(err)
                .run();

            out.close();
            err.close();
        }
        catch (Throwable t) {
            logger.error(t, t);
        }

        return modules;
    }

}
