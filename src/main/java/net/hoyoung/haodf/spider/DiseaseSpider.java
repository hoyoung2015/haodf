package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Disease;
import net.hoyoung.haodf.entity.DiseaseCat;
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
 * Created by hoyoung on 2015/11/8.
 */
public class DiseaseSpider implements PageProcessor {
    public void process(Page page) {
        Selectable ct = page.getHtml().xpath("//div[@class=m_box_green]/div[@class=ct]");
        List<Selectable> m_title_greens = ct.xpath("/div/div[@class=m_title_green]").nodes();
        List<Selectable> m_ctt_greens = ct.xpath("/div/div[@class=m_ctt_green]").nodes();
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        DiseaseCat diseaseCat = (DiseaseCat) page.getRequest().getExtra("diseaseCat");
        for (int i = 0; i < m_title_greens.size(); i++) {
            Selectable m_title_green = m_title_greens.get(i);
            Selectable m_ctt_green = m_ctt_greens.get(i);
            String title = m_title_green.xpath("/div/text()").get().trim();
            System.out.println(title);
            int disType = 2;//检查及手术
            if(title.endsWith("常见疾病")){
                disType = 0;
            }else if (title.endsWith("其他疾病")){
                disType = 1;
            }

            for (Selectable sele : m_ctt_green.xpath("/div/ul/li/a").nodes()){
                Disease disease = new Disease();
                disease.setCreateTime(new Date());
                disease.setStatus(0);
                disease.setUrl(sele.xpath("/a/@href").get());
                disease.setDisName(sele.xpath("/a/text()").get());
                disease.setDisType(disType);
                disease.setDisId(sele.xpath("/a/@href").regex("jibing/(\\S+)\\.htm", 1).get());
                disease.setCatName(diseaseCat.getCatName());
                disease.setCatId(diseaseCat.getCatId());
                System.out.println(disease);
                session.saveOrUpdate(disease);
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
            .setSleepTime(500)
            .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        //查出所有非一级疾病科
        List<DiseaseCat> diseaseCats = session.createQuery("from DiseaseCat a where not exists(from DiseaseCat b where a.catId=b.parentId)")
                .list();
        Spider spider = Spider.create(new DiseaseSpider()).thread(1);
        for (DiseaseCat diseaseCat:diseaseCats){
//            diseaseCat = diseaseCats.get(2);
            System.out.println(diseaseCat);

            Request req = new Request(diseaseCat.getUrl());
            req.putExtra("diseaseCat",diseaseCat);
            spider.addRequest(req);
//            break;
        }
        session.getTransaction().commit();
        session.close();
        spider.run();
        System.out.println("disease spider over. cost " + (System.currentTimeMillis() - start) / 1000 + " seconds");
    }
}
