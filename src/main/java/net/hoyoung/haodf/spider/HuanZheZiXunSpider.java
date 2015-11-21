package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.ConsultCat;
import net.hoyoung.haodf.entity.Wenda;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hoyoung on 2015/11/14.
 */
public class HuanZheZiXunSpider implements PageProcessor{
    protected Logger logger = LoggerFactory.getLogger(getClass());
    AtomicInteger count = new AtomicInteger();
    public void process(Page page) {
        ConsultCat consultCat = (ConsultCat) page.getRequest().getExtra("consultCat");
        Session session = HibernateUtils.getLocalThreadSession();
        for (Selectable span : page.getHtml().xpath("//div[@class=izixun-askedListContent]/ul/li/span[@class=fl]").nodes()){
            Selectable con2 = span.xpath("/span/a[1]");
            Selectable a = span.xpath("/span/a[2]");

            Wenda wenda = new Wenda();
            wenda.setConCatName2(con2.xpath("/a/text()").regex("\\[(.+)\\]", 1).get());
            wenda.setConCatName(consultCat.getConCatName());
            wenda.setStatus(0);
            wenda.setUrl(a.xpath("/a/@href").get());
            wenda.setCreateTime(new Date());
            wenda.setWenName(a.xpath("/a/text()").get().trim());
            wenda.setWenId(a.xpath("/a/@href").regex("/wenda/([A-Za-z_0-9]+)\\.htm", 1).get());
//            logger.info(wenda.toString());

            session.beginTransaction();
            session.saveOrUpdate(wenda);
            session.getTransaction().commit();

        }
        session.close();
        logger.info("finish "+count.addAndGet(1));
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
        long start = System.currentTimeMillis();
        Session session = HibernateUtils.getLocalThreadSession();
        List<ConsultCat> consultCats = session.createCriteria(ConsultCat.class)
                .list();
        HibernateUtils.closeSession();
        Spider spider = Spider.create(new HuanZheZiXunSpider());
        for (ConsultCat consultCat : consultCats){
            for (int i = 1; i <= 34; i++) {
                Request req = new Request("http://zixun.haodf.com/dispatched/"+consultCat.getConCatId()+".htm?p="+i);
                req.putExtra("consultCat",consultCat);
                spider.addRequest(req);
//                break;
            }
//            break;
        }
        spider.thread(8).run();
        System.out.println("cost "+(System.currentTimeMillis()-start)/1000+" seconds");
    }
}
