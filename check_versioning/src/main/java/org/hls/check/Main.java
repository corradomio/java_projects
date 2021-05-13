package org.hls.check;

import jext.logging.Logger;
import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystems;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        Logger.configure();
        Properties p = new Properties();

        String url = "archive:///D:\\Projects.github\\java_projects\\jext.vfs.zip#jext.vfs";
        VersioningSystem vs = VersioningSystems.newInstance(url, p);
        File local = new File("D:/Temp/jext.vfs.compressed");

        if (!vs.exists(local))
            vs.checkout(local);
        else
            vs.update(local);
    }

    public static void main8(String[] args) throws IOException {
        Logger.configure();

        Properties p = new Properties();
        p.put("username", "ftpuser");
        p.put("password", "password");
        String url = "ftp://192.168.0.143/jext.vfs";
        VersioningSystem vs = VersioningSystems.newInstance(url, p);
        File local = new File("D:/Temp/jext.vfs.cloned");

        // VFileSystem vfs = VFileSystems.newFileSystem(url, p);
        // vfs.connect();
        // VFileUtils.dump(vfs);

        if (!vs.exists(local))
            vs.checkout(local);
        else
            vs.update(local);
    }

    public static void main7(String[] args) {
        Logger.configure();

        Properties p = new Properties();
        p.put("branch", "ANT_18_BRANCH");
        String url = "svn+http://svn.apache.org/repos/asf/ant/core";
        // String url = "svn+http://svn.apache.org/repos/asf/ant/core/branches/ANT_18_BRANCH";

        VersioningSystem vs = VersioningSystems.newInstance(url, p);
        File local = new File("D:/Temp/ANT_18_BRANCH");

        if (!vs.exists(local))
            vs.checkout(local);
        else
            vs.update(local);

        // vs.delete(local)
    }

    public static void main6(String[] args) throws Exception {
        Logger.configure();

        Properties p = new Properties();
        p.put("url", "file:///D:\\Projects.github\\java_projects\\check_vfs");
        p.put("exclude", ".*,*.iml");
        VersioningSystem vs = VersioningSystems.newInstance(p);

        File local = new File("D:/Temp/cloned_check_vfs");

        if (!vs.exists(local))
            vs.checkout(local);
        else
            vs.update(local);

        // vs.delete(local);
    }

}
