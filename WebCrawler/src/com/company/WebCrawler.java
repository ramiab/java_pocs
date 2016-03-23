package com.company;

import jdk.nashorn.internal.runtime.ParserException;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class WebCrawler {

    public static void main(String[] args) throws InterruptedException {
        WebCrawler webCrawler = new WebCrawler();
	    webCrawler.crawl(10, 3, 1000000, new String[]{"http://edition.cnn.com/tech"});
    }

    BlockingQueue<BQElem> urlsQueue;
    ExecutorService threadPool;
    CountDownLatch latch;

    private void crawl(int threadsNum, int crawlMaxDepth, int queueSize, String[] seedUrls) throws InterruptedException {
        urlsQueue = new LinkedBlockingDeque<>(queueSize);
        for (String url:seedUrls) {
            urlsQueue.put(new BQElem(1, url));
        }
        threadPool = Executors.newFixedThreadPool(threadsNum);
        latch = new CountDownLatch(threadsNum);
        for (int i = 0; i < 20; i++) {
            Runnable worker = new ParseWorker(urlsQueue,latch, crawlMaxDepth);
            threadPool.execute(worker);
        }
//        threadPool.submit(task);
        Thread monitor = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000*3);
                        System.out.println("================================");
                        System.out.println("QUEUE-SIZE = "+urlsQueue.size());
                        System.out.println("================================");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        monitor.start();

        latch.await();
        System.out.println("Shutting Down!!");
        threadPool.shutdown();
        monitor.interrupt();
        System.out.println("Done.");
    }

    public static List<String> getLinksOnPage(final String url) throws org.htmlparser.util.ParserException {
        final Parser htmlParser = new Parser(url);
        final List<String> result = new LinkedList<String>();

        try {
            final org.htmlparser.util.NodeList tagNodeList = htmlParser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
            for (int j = 0; j < tagNodeList.size(); j++) {
                final LinkTag loopLink = (LinkTag) tagNodeList.elementAt(j);
                final String loopLinkStr = loopLink.getLink();
                result.add(loopLinkStr);
            }
        } catch (ParserException e) {
            e.printStackTrace(); // TODO handle error
        }

        return result;
    }
/*    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }*/
}
