package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Area;
import net.hoyoung.haodf.entity.Hospital;
import net.hoyoung.haodf.utils.HaoStringUtils;
import net.hoyoung.haodf.utils.HibernateUtils;
import net.hoyoung.haodf.utils.UUIDGenerater;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
 * Created by hoyoung on 2015/11/7.
 */
public class HospitalFillSpider implements PageProcessor {
    public void process(Page page) {
        String tmp = page.getHtml().xpath("/html/head/title/text()").get();
        if(StringUtils.isEmpty(tmp)){
            System.out.println("没有收录");
            return;
        }else if(tmp.contains("好大夫工作室")){
            System.out.println("好大夫工作室");
            return;
        }else {
            Selectable seTmp;
            Hospital hospital = new Hospital();
            tmp = HaoStringUtils.trim(page.getHtml().xpath("//div[@class=panelA_blue]/div[@class=toptr]/ul/li/p/a/text()").get());
            hospital.setHosName(tmp);
            hospital.setStatus(0);
            hospital.setHosId((String) page.getRequest().getExtra("hosId"));
            hospital.setCreateTime(new Date());
            hospital.setUrl(page.getRequest().getUrl());
            tmp = page.getHtml().xpath("//div[@class=panelA_blue]/div[@class=toptr]/ul/li/p/text()").regex("\\((\\S+)\\)", 1).get();
            if(!StringUtils.isEmpty(tmp)){
                if(tmp.contains("等")){
                    tmp = tmp.replaceAll("[等级]","");
                }
                hospital.setLevel(tmp);
            }
            seTmp = page.getHtml().xpath("//div[@class=midtr]/div[@class=lt]/table/tbody/tr[2]/td[2]/text()");
            hospital.setProvince(seTmp.regex("(\\S+)[市省]\\S+[市区]",1).get());
            hospital.setCity(seTmp.regex("\\S+[市省](\\S+)[市区]", 1).get());
            System.out.println(hospital);
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
        long start = System.currentTimeMillis();
        Spider spider = Spider.create(new HospitalFillSpider());

        Session session = HibernateUtils.getLocalThreadSession();
        List<String> hosIds = session.createSQLQuery("SELECT DISTINCT(a.hos_id) FROM doctor a LEFT JOIN hospital b on a.hos_id=b.hos_id WHERE b.url is NULL")
        .list();
        for (String s : hosIds){
            Request req = new Request("http://www.haodf.com/hospital/"+s+".htm");
            req.putExtra("hosId",s);
            spider.addRequest(req);
        }
        spider.thread(5).run();
        System.out.println("hospital fill spider over. cost " + (System.currentTimeMillis()-start) / 1000 + " seconds");
        System.exit(0);
    }
}
