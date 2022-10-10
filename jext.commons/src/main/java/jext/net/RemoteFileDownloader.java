package jext.net;

import jext.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class RemoteFileDownloader {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static void downloadFile(File file, String url) throws IOException {
        RemoteFileDownloader downloader = new RemoteFileDownloader(file, url);
        downloader.download();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(RemoteFileDownloader.class);

    private File downloadFile;
    private String downloadUrl;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private RemoteFileDownloader(File file, String url) {
        this.downloadFile = file;
        this.downloadUrl = url;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    private void download() throws IOException {
        try(InputStream in = new URL(downloadUrl).openStream()) {
            Files.copy(in, downloadFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // private void download() throws IOException {
    //     File tempFile = downloadFile;
    //     mkdirs(tempFile);
    //     delete(tempFile);
    //
    //     // HttpClient is able to handle '301 Moved Permanently' !!!
    //
    //     logger.debugft("Try to download from %s", downloadUrl);
    //
    //     RequestConfig globalConfig = RequestConfig.custom()
    //             .setCookieSpec(CookieSpecs.DEFAULT)
    //             .build();
    //     CloseableHttpClient httpClient = HttpClients.custom()
    //             .setDefaultRequestConfig(globalConfig)
    //             .build();
    //     RequestConfig localConfig = RequestConfig.copy(globalConfig)
    //             .setCookieSpec(CookieSpecs.STANDARD)
    //             .build();
    //
    //     HttpGet httpGet = new HttpGet(downloadUrl);
    //     httpGet.setConfig(localConfig);
    //     HttpResponse response = httpClient.execute(httpGet);
    //     if (response.getStatusLine().getStatusCode() >= 400) {
    //         throw new IOException("Unable to download from " + downloadUrl + ": " + response.getStatusLine());
    //     }
    //
    //     HttpEntity entity = response.getEntity();
    //
    //     try (InputStream istream = entity.getContent()) {
    //         try (OutputStream ostream = new FileOutputStream(tempFile)) {
    //             byte[] buffer = new byte[1024];
    //             for(int len = istream.read(buffer); len > 0; len = istream.read(buffer)) {
    //                 ostream.write(buffer, 0, len);
    //             }
    //         }
    //     }
    // }
    //
    // private static void mkdirs(File file) {
    //     File parentDir = file.getParentFile();
    //     if (!parentDir.exists() && !parentDir.mkdirs())
    //         logger.errorf("Unable to create the directory %s", parentDir);
    // }
    //
    // private static void delete(File file) {
    //     if (file != null)
    //         file.delete();
    // }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
