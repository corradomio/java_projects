package jext.buildtools.gradle.util;

import org.gradle.api.Incubating;
import org.gradle.tooling.BuildAction;
import org.gradle.tooling.BuildActionExecuter;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnectionException;
import org.gradle.tooling.ModelBuilder;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.ResultHandler;
import org.gradle.tooling.TestLauncher;

import java.nio.file.Path;
import java.util.List;

public class UncloseableProjectConnection implements ProjectConnection {

    private ProjectConnection connection;

    public UncloseableProjectConnection(ProjectConnection connection) {
        this.connection= connection;
    }

    @Override
    public <T> T getModel(Class<T> modelType) throws GradleConnectionException, IllegalStateException {
        return connection.getModel(modelType);
    }

    @Override
    public <T> void getModel(Class<T> modelType, ResultHandler<? super T> handler) throws IllegalStateException {
        connection.getModel(modelType, handler);
    }

    @Override
    public BuildLauncher newBuild() {
        return connection.newBuild();
    }

    @Override
    public TestLauncher newTestLauncher() {
        return connection.newTestLauncher();
    }

    @Override
    public <T> ModelBuilder<T> model(Class<T> modelType) {
        return connection.model(modelType);
    }

    @Override
    public <T> BuildActionExecuter<T> action(BuildAction<T> buildAction) {
        return connection.action(buildAction);
    }

    @Override
    public BuildActionExecuter.Builder action() {
        return connection.action();
    }

    @Override
    @Incubating
    public void notifyDaemonsAboutChangedPaths(List<Path> changedPaths) {
        connection.notifyDaemonsAboutChangedPaths(changedPaths);
    }

    @Override
    public void close() {
        // connection.close();
    }
}
