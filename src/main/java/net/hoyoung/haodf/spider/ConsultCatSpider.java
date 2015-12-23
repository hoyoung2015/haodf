package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.ConsultCat;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hoyoung on 2015/11/9.
 */
public class ConsultCatSpider implements PageProcessor {
    public void process(Page page) {
//        try {
//            FileUtils.writeStringToFile(new File("html/consultCat.html"),page.getHtml().get(),"UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        for (Selectable ul : page.getHtml().xpath("//div[@class=izixun-department]/ul").nodes()){
            String superCatName = ul.xpath("/ul/li[1]/text()").get();
            for (Selectable a : ul.xpath("/ul/li[2]/span/a").nodes()){
                ConsultCat consultCat = new ConsultCat();
                consultCat.setConCatId(a.xpath("/a/@href").regex("/index/(\\d+)\\.htm",1).get());
                consultCat.setUrl(a.xpath("/a/@href").get());
                consultCat.setCreateTime(new Date());
                consultCat.setStatus(0);
                consultCat.setConCatName(a.xpath("/a/text()").get());
                consultCat.setSuperCatName(superCatName);
                System.out.println(consultCat);
                session.saveOrUpdate(consultCat);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    public Site getSite() {
        return site;
    }
    private Site site = Site.me()
            .setRetryTimes(5)
            .setSleepTime(300)
            .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");


    public static void main(String[] args) {
        Spider.create(new ConsultCatSpider())
                .addUrl("http://zixun.haodf.com/index/1.htm")
                .thread(1)
                .run();
        System.out.println("consult spider complete");
    }
}
