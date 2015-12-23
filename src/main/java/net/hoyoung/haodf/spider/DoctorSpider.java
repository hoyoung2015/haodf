package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Doctor;
import net.hoyoung.haodf.entity.Section;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hoyoung on 2015/11/7.
 */
public class DoctorSpider implements PageProcessor {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    AtomicInteger atomicInteger = new AtomicInteger();
    public void process(Page page) {
        Session session = HibernateUtils.getLocalThreadSession();

        List<Selectable> trs = page.getHtml().xpath("//body/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table[1]/tbody/tr")
                .nodes();
        String temp;
        for (int i = 4; i < trs.size(); i++) {
            Selectable tr = trs.get(i);
            Selectable seleDoc = tr.xpath("td/table/tbody");
            if(seleDoc.get()==null){
                continue;
            }
            Doctor doctor = new Doctor();
            doctor.setStatus(0);
            doctor.setCreateTime(new Date());
            doctor.setInfoUrl(seleDoc.xpath("/tbody/tr/td[2]/a[1]/@href").get());
            doctor.setDocName(seleDoc.xpath("/tbody/tr/td[2]/a[1]/text()").get());
            doctor.setHomeUrl(seleDoc.xpath("/tbody/tr/td[2]/a[2]/@href").get());
            temp = doctor.getInfoUrl().substring(doctor.getInfoUrl().lastIndexOf("/") + 1);
            temp = temp.substring(0, temp.lastIndexOf("."));
            doctor.setDocId(temp);
            session.beginTransaction();
            try{
                session.saveOrUpdate(doctor);
            }catch (ConstraintViolationException e){
                e.printStackTrace();
            }finally {
                session.getTransaction().commit();
            }

        }

        session.close();
        if(page.getUrl().regex("prov=&p=1$").match()){//第一页
            temp = page.getHtml().xpath("//div[@class=p_bar]/a[@class=p_text]/text()").regex("共\u00A0(\\d+)\u00A0页", 1).get();
            if(temp != null){
                int totalPage = Integer.parseInt(temp);
                String secId = (String) page.getRequest().getExtra("secId");
                atomicInteger.addAndGet(totalPage);
                for (int i = 2; i <= totalPage; i++) {
                    Request req = new Request("http://haoping.haodf.com/keshi/"+secId+"/daifu.htm?prov=&p="+i);
                    req.putExtra("secId",secId);
                    page.addTargetRequest(req);
                }
            }
        }

        logger.info("last "+atomicInteger.decrementAndGet()+" page");
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
        if(args.length==0) System.exit(0);
//        String secId = "DE4r0PiRvNoMDxbIlDLmPYT0z9Sqp5";
        String secId = args[0];
        Spider spider = Spider.create(new DoctorSpider());
        Request req = new Request("http://haoping.haodf.com/keshi/"+secId+"/daifu.htm?prov=&p=1");
        req.putExtra("secId", secId);
        spider.addRequest(req);
        spider.thread(5).run();



        System.out.println("doctor spider over. cost " + (System.currentTimeMillis() - start) / 1000 + " seconds");
//        System.exit(0);
    }
}
