package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.DiseaseCat;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;

/**
 * Created by hoyoung on 2015/11/8.
 */
public class DiseaseCatSpider implements PageProcessor {
    public void process(Page page) {

        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        for (Selectable sele : page.getHtml().xpath("//div[@id=el_tree_1000000]/div/a").nodes()){
            DiseaseCat diseaseCat = new DiseaseCat();
            diseaseCat.setCreateTime(new Date());

            diseaseCat.setUrl(sele.xpath("/a/@href").get());

            diseaseCat.setCatId(sele.xpath("/a/@href").regex("/jibing/(\\w+)/list.htm", 1).get());
            diseaseCat.setStatus(0);
            diseaseCat.setCatName(sele.xpath("/a/text()").get());
            System.out.println(diseaseCat);
            session.saveOrUpdate(diseaseCat);
        }
        session.getTransaction().commit();
        session.close();
    }

    public Site getSite() {
        return site;
    }
    private Site site = Site.me()
            .setRetryTimes(5)
            .setSleepTime(500)
            .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");


    public static void main(String[] args) {

        Spider.create(new DiseaseCatSpider())
                .addUrl("http://www.haodf.com/jibing/yundongyixueke/list.htm")
                .thread(1)
                .run();
        System.out.println("disease root spider over");
    }
}
