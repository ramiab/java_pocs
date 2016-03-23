package com.company;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.company.WebCrawler.getLinksOnPage;

/**
 * Created by rami on 3/23/2016.
 */
public class ParseWorker implements Runnable {
    private BlockingQueue<BQElem> urlsQueue;
    private CountDownLatch latch;
    private final int crawlMaxDepth;

    public ParseWorker(BlockingQueue<BQElem> urlsQueue, CountDownLatch latch, int crawlMaxDepth) {
        this.urlsQueue = urlsQueue;
        this.latch = latch;
        this.crawlMaxDepth = crawlMaxDepth;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" START. ");
        for (int i=0; i<5; i++) {
            while (!urlsQueue.isEmpty()) {
                try {
                    long timeout = 5L;
                    BQElem bqElem = urlsQueue.poll(timeout, TimeUnit.SECONDS);
                    int curDepth = bqElem.curDepth;
                    bqElem.printMe();
                    if(curDepth > crawlMaxDepth-1)
                        continue;
    //                        List<String> parsedUrls = getParsedUrls(bqElem.url);
                    List<String> parsedUrls = getLinksOnPage(bqElem.url);
                    curDepth++;
                    for (String url : parsedUrls) {
                        BQElem bqElem1 = new BQElem(curDepth, url);
                        urlsQueue.put(bqElem1);
                    }
    //                Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
        System.out.println(Thread.currentThread().getName()+" END. ");

    }
}
