package jext.sourcecode.project.lfm;

import jext.configuration.Configuration;
import jext.configuration.ConfigurationUtils;
import jext.configuration.HierarchicalConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloaderConfiguration {

    protected String name;
    protected HierarchicalConfiguration configuration;
    protected List<RepositoryConfiguration> repositories = new ArrayList<>();
    protected List<File> localDirectories = new ArrayList<>();
    protected long downloadTimeout;
    protected long checkTimeout;
    protected int parallel;
    protected File downloadDirectory;


    public DownloaderConfiguration() {

    }

    public String getName() {
        return name;
    }

    public long getDownloadTimeout() {
        return downloadTimeout;
    }

    public long getCheckTimeout() {
        return checkTimeout;
    }

    public int getParallelDownloads() {
        return parallel;
    }

    public File getDownloadDirectory() {
        return downloadDirectory;
    }

    public List<RepositoryConfiguration> getRepositories() {
        return repositories;
    }

    public List<File> getLocalDirectories() {
        return localDirectories;
    }

    public void configure(Configuration configuration) {
        this.name = configuration.getString("@name");
        this.configuration = (HierarchicalConfiguration) configuration;

        configureDownloadDirectory();

        this.configuration.configurationsAt("local")
            .forEach(this::configureLocalDirectory);

        // scan the list of repositories
        this.configuration.configurationsAt("repository")
            .forEach(this::configureRepository);
    }

    private void configureDownloadDirectory() {
        Configuration downloadConfiguration = configuration.configurationAt("download");
        downloadTimeout = ConfigurationUtils.getTimeout(downloadConfiguration, "@timeout", 1000);
        parallel = downloadConfiguration.getInt("@parallel", 1);
        downloadDirectory = ConfigurationUtils.getFile(downloadConfiguration, "@path", "." + name);
        checkTimeout = ConfigurationUtils.getTimeout(configuration, "check/@timeout", "12h");
    }

    private void configureLocalDirectory(Configuration configuration) {
        String path = configuration.getString("@path");
        localDirectories.add(new File(path));
    }

    private void configureRepository(Configuration configuration) {
        RepositoryConfiguration rconfig = new RepositoryConfiguration();
        rconfig.configure(configuration);
        repositories.add(rconfig);
    }
}
