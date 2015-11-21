package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.ConsultCat;
import net.hoyoung.haodf.entity.Wenda;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/9.
 */
public class WendaSpider implements PageProcessor {
    public void process(Page page) {
        ConsultCat consultCat = (ConsultCat) page.getRequest().getExtra("consultCat");

        for (Selectable a : page.getHtml().xpath("//div[@class=izixun-askedListContent]/ul/li/a").nodes()){
            Wenda wenda = new Wenda();
            wenda.setConCatName(consultCat.getConCatName());
            wenda.setStatus(0);
            wenda.setUrl(a.xpath("/a/@href").get());
            wenda.setCreateTime(new Date());
            wenda.setWenName(a.xpath("/a/text()").get().trim());
            wenda.setWenId(a.xpath("/a/@href").regex("/wenda/([A-Za-z_0-9]+)\\.htm", 1).get());
            System.out.println(wenda);
            Session session = HibernateUtils.openSession();
            session.beginTransaction();
            session.saveOrUpdate(wenda);
            session.getTransaction().commit();
            session.close();
        }
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
        Session session = HibernateUtils.openSession();
        List<ConsultCat> consultCats = session.createCriteria(ConsultCat.class)
                .list();
        Spider spider = Spider.create(new WendaSpider());
        for (ConsultCat consultCat : consultCats){
            Request req = new Request(consultCat.getUrl());
            req.putExtra("consultCat",consultCat);
            spider.addRequest(req);
//            break;
        }
        spider.thread(1)
                .run();
    }
}
