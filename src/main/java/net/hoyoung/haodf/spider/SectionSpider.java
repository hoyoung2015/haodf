package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Section;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/7.
 */
public class SectionSpider implements PageProcessor{
    public void process(Page page) {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        List<Selectable> divs = page.getHtml().xpath("//div[@class=ct]/div[@class=m_ctt_green]/ul/li/a").nodes();
        for (Selectable sele:divs){
            Section sec = new Section();
            sec.setStatus(0);
            sec.setCreateTime(new Date());
            sec.setUrl(sele.xpath("/a/@href").get());


            String temp = sec.getUrl().substring(sec.getUrl().lastIndexOf("/")+1);
            temp = temp.substring(0,temp.lastIndexOf("."));
            sec.setSecId(temp);

            sec.setSecName(sele.xpath("/a/text()").get());
            System.out.println(sec);
            session.save(sec);
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
        long start = System.currentTimeMillis();
        Spider.create(new SectionSpider())
                .addUrl("http://www.haodf.com/keshi/list.htm")
                .thread(1)
                .run();
        System.out.println("section spider over. cost " + (System.currentTimeMillis() - start) / 1000 + " seconds");
        System.exit(0);
    }
}
