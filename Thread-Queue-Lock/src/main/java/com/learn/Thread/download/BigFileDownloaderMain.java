package com.learn.Thread.download;

/**
 * Created by Mobin on 2017/8/6.
 */
public class BigFileDownloaderMain {
    public static void main(String[] args) throws Exception {
        String url = "http://central.maven.org/maven2/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar";
        BigFileDownloader downloader = new BigFileDownloader(url);
        int workerThreadCount = 3;
        long reportInterval = 2;
        downloader.download(workerThreadCount, reportInterval);
    }
}
