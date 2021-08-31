package org.hls.check;

/*
    ClassLoader
        SecureClassLoader
            URLClassLoader
                AppClassLoader
                ExtClassLoader

    class sun.misc.Launcher$ExtClassLoader          <== root classLoader
        class sun.misc.Launcher$AppClassLoader      <== system classLoader
 */

import org.apache.maven.cli.CliRequest;
import org.apache.maven.cli.MavenCli;

import java.io.File;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        MavenCli maven = new MavenCli();

        System.setProperty("maven.multiModuleProjectDirectory", "true");

        maven.doMain(
            new String[]{"dependency:list-repositories"},
            "D:\\SPLGroup\\SPLDevelopment3.0\\splserver3.0\\splserver",
            System.out, System.err);
    }

    // public static void main1(String[] args) throws MavenInvocationException {
    //     System.setProperty("maven.home", "D:\\Java\\Maven-3.8.2");
    //     InvocationRequest request = new DefaultInvocationRequest();
    //     request.setPomFile( new File( "D:\\SPLGroup\\SPLDevelopment3.0\\splserver3.0\\splserver\\pom.xml" ) );
    //     request.setGoals( Collections.singletonList( "install" ) );
    //
    //     Invoker invoker = new DefaultInvoker();
    //     invoker.execute( request );
    // }
}
