package org.hls.check;

import jext.logging.Logger;
import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystems;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CheckVersioning {

    public static void main(String[] args) throws Exception {
        Logger.configure();
        Properties p = new Properties();

        String url = "archive:///D:\\Projects.github\\java_projects\\jext.vfs.zip#jext.vfs";
        File local = new File("D:/Temp/jext.vfs.compressed");
        VersioningSystem vs = VersioningSystems.newInstance(url, p, local);

        if (!vs.exists())
            vs.checkout();
        else
            vs.update();
    }

    public static void main8(String[] args) throws IOException {
        Logger.configure();

        Properties p = new Properties();
        p.put("username", "ftpuser");
        p.put("password", "password");
        String url = "ftp://192.168.0.143/jext.vfs";
        File local = new File("D:/Temp/jext.vfs.cloned");
        VersioningSystem vs = VersioningSystems.newInstance(url, p, local);

        // VFileSystem vfs = VFileSystems.newFileSystem(url, p);
        // vfs.connect();
        // VFileUtils.dump(vfs);

        if (!vs.exists())
            vs.checkout();
        else
            vs.update();
    }

    public static void main7(String[] args) {
        Logger.configure();

        Properties p = new Properties();
        p.put("branch", "ANT_18_BRANCH");
        String url = "svn+http://svn.apache.org/repos/asf/ant/core";
        // String url = "svn+http://svn.apache.org/repos/asf/ant/core/branches/ANT_18_BRANCH";

        File local = new File("D:/Temp/ANT_18_BRANCH");
        VersioningSystem vs = VersioningSystems.newInstance(url, p, local);

        if (!vs.exists())
            vs.checkout();
        else
            vs.update();

        // vs.delete(local)
    }

    public static void main6(String[] args) throws Exception {
        Logger.configure();

        Properties p = new Properties();
        p.put("url", "file:///D:\\Projects.github\\java_projects\\check_vfs");
        p.put("exclude", ".*,*.iml");

        File local = new File("D:/Temp/cloned_check_vfs");
        VersioningSystem vs = VersioningSystems.newInstance(p, local);

        if (!vs.exists())
            vs.checkout();
        else
            vs.update();

        // vs.delete(local);
    }

}
