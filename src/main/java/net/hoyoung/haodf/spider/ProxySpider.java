package net.hoyoung.haodf.spider;

import org.apache.http.HttpHost;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public class ProxySpider implements PageProcessor{

    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().xpath("//title"));
    }

    @Override
    public Site getSite() {



        return site;
    }
//    static List<String[]> httpPools;
//    static {
//        httpPools = new ArrayList<>();
//        httpPools.add(new String[]{"192.168.25.57","80"});
//    }
    private Site site = Site.me()
//            .setHttpProxyPool(httpPools)
            .setRetryTimes(5)
            .setSleepTime(300)
            .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");

    public static void main(String[] args) {
        Spider.create(new ProxySpider())
                .addUrl("http://192.168.25.56:81/dashboard/")
        .thread(1)
        .run();
    }
}
