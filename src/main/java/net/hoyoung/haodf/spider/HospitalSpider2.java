package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Area;
import net.hoyoung.haodf.entity.Hospital;
import net.hoyoung.haodf.utils.HibernateUtils;
import net.hoyoung.haodf.utils.UUIDGenerater;
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
 * Created by hoyoung on 2015/11/7.
 */
public class HospitalSpider2 implements PageProcessor {
    public void process(Page page) {
        String city = null;
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        List<Selectable> tables = page.getHtml().xpath("//div/table/tbody/tr/td/table/tbody/tr/td/table").nodes();
        for (int i = 0; i < tables.size()-4; i++) {
            Selectable table = tables.get(i);
            String province = table.xpath("/table/tbody/tr[2]/td[2]/table/tbody/tr/td[2]/text()").get().replace("地区专家门诊查询","");
            List<Selectable> trs = table.xpath("/table/tbody/tr[3]/td[2]/table/tbody/tr").nodes();
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
                for (int j = 1; j < tds.size(); j++) {
                    Selectable td = tds.get(j);
                    if(td.xpath("/td/text()").regex("\u00A0").match()){//最后一个空格结尾
                        continue;
                    }

                    Hospital hospital = new Hospital();
                    hospital.setCity(city);
                    hospital.setStatus(0);
                    hospital.setUrl(td.xpath("/td/a/@href").get());
                    hospital.setCreateTime(new Date());
                    hospital.setHosId(UUIDGenerater.randomUUID());
                    hospital.setProvince(province);
                    hospital.setHosName(td.xpath("/td/a/text()").get());
                    System.out.println(hospital);
                    session.save(hospital);
                }
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
        Spider.create(new HospitalSpider2())
        .addUrl("http://q.haodf.com/")
                .thread(1)
                .run();
        System.out.println("hospital spider over. cost " + (System.currentTimeMillis()-start) / 1000 + " seconds");
        System.exit(0);
    }
}
