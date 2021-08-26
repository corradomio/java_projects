package jext.lang;

import java.io.File;
import java.io.InvalidObjectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassLoaderConfiguration {

    private List<URL> urls = new ArrayList<>();


    public ClassLoaderConfiguration add(File fileOrDirectory) throws MalformedURLException {
        File[] libs;
        String name = fileOrDirectory.getName().toLowerCase();
        boolean isLibs = false;

        if (!fileOrDirectory.exists())
            return this;

        if (fileOrDirectory.isFile()) {
            // check if it is a jar or jmod
            if (name.endsWith(".jar") || name.endsWith(".zip") || name.endsWith(".jmod"))
                urls.add(fileOrDirectory.getAbsoluteFile().toURL());
            // check if it is a .class
            else if (name.endsWith(".class"))
                urls.add(fileOrDirectory.getAbsoluteFile().getParentFile().toURL());
        }
        else if (fileOrDirectory.isDirectory()) {
            isLibs = isLibs || addLibs(fileOrDirectory, ".jar");
            isLibs = isLibs || addLibs(fileOrDirectory, ".zip");
            isLibs = isLibs || addLibs(fileOrDirectory, ".jmod");

            if (!isLibs)
                urls.add(fileOrDirectory.getAbsoluteFile().toURL());
        }
        else {

        }
        // check if is a directory containing jars/jmods
        // otherwise is a directory containing classes

        return this;
    }

    private boolean addLibs(File directory, String ext) throws MalformedURLException {
        File[] libs = directory.listFiles(file -> file.getName().endsWith(".zip"));
        boolean isLibs =  libs != null && libs.length > 0;
        if (isLibs) {
            for(File lib : libs)
                urls.add(lib.getAbsoluteFile().toURL());
        }
        return isLibs;
    }

    public ClassLoader build(ClassLoader parent) {
        URL[] urls = new URL[this.urls.size()];
        this.urls.toArray(urls);
        if (parent == null)
            return new URLClassLoader(urls);
        else
            return new URLClassLoader(urls, parent);
    }
}
