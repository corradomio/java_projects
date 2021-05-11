package jext.versioning.svn.util;

import jext.net.URL;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;

public class SVNUtils {

    private static boolean httpInitialized;
    private static boolean svnInitialized;
    private static boolean fileInitialized;


    public static SVNURL setup(URL url) throws SVNException {
        initRepositories(url);
        return parseURL(url);
    }

    private static void initRepositories(URL url) {
        synchronized (SVNUtils.class) {
            if (url.getProtocol().equals("svn+http") && !httpInitialized) {
                DAVRepositoryFactory.setup();
                httpInitialized = true;
            }
            else if (url.getProtocol().equals("svn+https") && !httpInitialized) {
                DAVRepositoryFactory.setup();
                httpInitialized = true;
            }
            else if (url.getProtocol().equals("svn+file") && !fileInitialized) {
                FSRepositoryFactory.setup();
                fileInitialized = true;
            }
            else if (!svnInitialized){
                SVNRepositoryFactoryImpl.setup();
                svnInitialized = true;
            }
        }
    }

    /**
     * Convert the VFS protocol into the SVNURL syntax
     *
     * @param url
     * @return
     */
    private static SVNURL parseURL(URL url) throws SVNException {
        String protocol = url.getProtocol();
        String hpp = url.getHostPortPath();
        String svnurl;

        if ("svn+http".equals(protocol))
            svnurl = "http://" + hpp;
        else if ("svn+https".equals(protocol))
            svnurl = "https://" + hpp;
        else if ("svn+file".equals(protocol))
            svnurl = "file://" + hpp;
        else
            svnurl = protocol + "://" + hpp;

        return SVNURL.parseURIEncoded(svnurl);
    }
}
