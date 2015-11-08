package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Area;
import net.hoyoung.haodf.utils.HibernateUtils;
import net.hoyoung.haodf.utils.UUIDGenerater;
import org.hibernate.Session;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/7.
 */
public class AreaSpider implements PageProcessor {
    public void process(Page page) {
        List<Selectable> tds = page.getHtml().xpath("//table//table[7]/tbody/tr[3]//table//td//a").nodes();
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        for (int i = 0; i < tds.size()-1; i++) {
            Selectable selectable = tds.get(i);
            Area area = new Area();
            area.setAreaId(UUIDGenerater.randomUUID());
            area.setAreaName(selectable.xpath("/*/text()").get());
            area.setUrl(selectable.xpath("/*/@href").get());
            area.setCreateTime(new Date());
            area.setStatus(0);
            System.out.println(area);
            session.save(area);
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
        Spider.create(new AreaSpider())
                .addUrl("http://q.haodf.com/")
                .thread(1)
                .run();
        System.out.println("area spider over");
        System.exit(0);
    }
}
