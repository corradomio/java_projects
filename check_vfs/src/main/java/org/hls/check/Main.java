package org.hls.check;

import jext.vfs.VFile;
import jext.vfs.VFileSystem;
import jext.vfs.VFileSystems;
import jext.vfs.VProgressMonitor;
import jext.vfs.util.ProgressMonitor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {

        // VFileSystems.newFileSystem("https://github.com/hub4j/github-api");
        Properties props = new Properties(){{
            put("username", "corrado.mio@unimi.it");
            put("password", "stargate");
            // put("branch", "splserver3.0");
            put("local", new File("d:/Temp").getAbsolutePath());
        }};

        VFileSystem vfs = VFileSystems.newFileSystem("git+http://10.10.103.124/splgroup/splv3.git", props);
        vfs.connect();
        vfs.copyLocally(null);

        vfs.getVersions().forEach(v -> {
            System.out.println(v);
        });
        // vfs.copyInto(new File("D:/Temp"), vfs.getRoot(), new ProgressMonitor() {
        // });
    }

    public static void main4(String[] args) throws IOException {
        GitHub github = new GitHubBuilder().withPassword("corrado.mio@gmail.com", "G1THubP4$$w0rd!").build();

        github.listAllPublicRepositories().forEach(r -> {
            System.out.println(r);
        });
    }

    public static void main3(String[] args) throws IOException {
        GitHubClient client = new GitHubClient();
        client.setCredentials("corrado.mio@gmail.com", "G1THubP4$$w0rd!");

        RepositoryService service = new RepositoryService();
        for (Repository repo : service.getRepositories("corradomio"))
            System.out.println(repo.getName() + " Watchers: " + repo.getWatchers());
    }

    public static void main2(String[] args) throws GitLabApiException {
        GitLabApi gitLabApi;
        List<Project> projects;

        // gitLabApi = new GitLabApi("http://10.10.103.124/", "MYsYKXTzsNqz2kA2Z1BU");
        // projects = gitLabApi.getProjectApi().getProjects();
        // projects.forEach(p -> {
        //     System.out.println(p);
        // });

        // gitLabApi = GitLabApi.oauth2Login("http://10.10.103.124/", "corrado.mio@unimi.it", "stargate");
        // projects = gitLabApi.getProjectApi().getProjects();
        // projects.forEach(p -> {
        //     System.out.println(p);
        // });
    }

    public static void main1(String[] args) throws IOException {
        System.out.println("Hello World");

        Properties props = new Properties(){{
            put("username", "corrado.mio@unimi.it");
            put("password", "stargate");
        }};

        VFileSystem vfs = VFileSystems.newFileSystem("git+http://10.10.103.124/splgroup/splv3.git", props);
        vfs.connect();
        VFile root = vfs.getRoot();
        root.listFiles().forEach(vfile -> {
            System.out.println(vfile);
        });
    }
}
