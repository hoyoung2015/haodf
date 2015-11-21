package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Area;
import net.hoyoung.haodf.entity.Hospital;
import net.hoyoung.haodf.utils.HibernateUtils;
import net.hoyoung.haodf.utils.UUIDGenerater;
import org.apache.commons.io.FileUtils;
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
public class HospitalSpider implements PageProcessor {
    public void process(Page page) {
        List<Selectable> trs = page.getHtml().xpath("//table//table//table//table").nodes().get(1).xpath("/table/tbody/tr").nodes();
        String city = null;
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        Area area = (Area) page.getRequest().getExtra("area");
        for (Selectable selectable : trs){
            if(selectable.xpath("/tr/td[@class=lb_line]").get() != null){
                continue;
            }
            //判断第一个是否有城市名
            if(!selectable.xpath("/tr/td[1]/text()").regex("\u00A0").match()){
                //新出现的城市，每个城市的第一行
                city = selectable.xpath("/tr/td[1]/text()").get();
                System.out.println(city);

            }

            List<Selectable> tds = selectable.xpath("/tr/td").nodes();
            for (int i = 1; i < tds.size(); i++) {
                Selectable td = tds.get(i);
                if(td.xpath("/td/text()").regex("\u00A0").match()){//最后一个空格结尾
                    continue;
                }

                Hospital hospital = new Hospital();
                hospital.setCity(city);
                hospital.setStatus(0);
                hospital.setUrl(td.xpath("/td/a/@href").get());
                hospital.setCreateTime(new Date());
                hospital.setHosId(UUIDGenerater.randomUUID());
                hospital.setProvince(area.getAreaName());
                hospital.setHosName(td.xpath("/td/a/text()").get());
                System.out.println(hospital);
                session.save(hospital);
            }
        }
        //更新地区状态为已爬取
//        session.createQuery("update Area a set a.status=1 where a.areaId='"+area.getAreaId()+"'").executeUpdate();
        Area areaDb = (Area) session.get(Area.class,area.getAreaId());
        areaDb.setStatus(1);

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
        List<Area> areas = session.createCriteria(Area.class)
                .add(Restrictions.eq("status",0))
                .list();
        session.getTransaction().commit();
        session.close();

        Spider spider = Spider.create(new HospitalSpider());
        for (Area area : areas){
            Request req = new Request(area.getUrl());
            req.putExtra("area", area);
            System.out.println(area);

            spider.addRequest(req);
//            break;
        }
        spider.thread(5).run();
        System.out.println("hospital spider over. cost " + (System.currentTimeMillis()-start) / 1000 + " seconds");
        System.exit(0);
    }
}
