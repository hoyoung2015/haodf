package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.DiseaseCat;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/8.
 */
public class DiseaseCatChildSpider implements PageProcessor {

    public void process(Page page) {

        //判断是否存在二级
        List<Selectable> childCats = page.getHtml().xpath("//div[@id=el_tree_1000000]/div[@class=ksbd]/ul/li/a").nodes();
        DiseaseCat parentDis = (DiseaseCat) page.getRequest().getExtra("parentDis");

        if(!childCats.isEmpty()){//存在二级
            Session session = HibernateUtils.openSession();
            session.beginTransaction();
            for (Selectable two : childCats){
                DiseaseCat diseaseCat = new DiseaseCat();
                diseaseCat.setCreateTime(new Date());
                diseaseCat.setParentId(parentDis.getCatId());
                diseaseCat.setUrl(two.xpath("/a/@href").get());
                diseaseCat.setCatId(two.xpath("/a/@href").regex("/jibing/(\\S+)/list.htm", 1).get());
                diseaseCat.setStatus(0);
                diseaseCat.setCatName(two.xpath("/a/text()").get());
                System.out.println(diseaseCat);
                session.saveOrUpdate(diseaseCat);
            }
            session.getTransaction().commit();
            session.close();
        }


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
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        List<DiseaseCat> rootCats = session.createCriteria(DiseaseCat.class)
                .add(Restrictions.isNull("parentId"))
                .list();

        Spider spider = Spider.create(new DiseaseCatChildSpider()).thread(1);
        for (DiseaseCat diseaseCat : rootCats){
//            diseaseCat = rootCats.get(4);
            System.out.println(diseaseCat);
            Request req = new Request(diseaseCat.getUrl());
            req.putExtra("parentDis", diseaseCat);
            spider.addRequest(req);
//            break;
        }
        spider.run();
        System.out.println("disease child spider over");
    }
}
