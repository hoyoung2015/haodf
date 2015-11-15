package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Doctor;
import net.hoyoung.haodf.utils.HaoStringUtils;
import net.hoyoung.haodf.utils.HibernateUtils;
import net.hoyoung.webmagic.scheduler.DoctorWebScheduler;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import javax.print.Doc;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hoyoung on 2015/11/14.
 */
public class DoctorWebSpiderTest implements PageProcessor {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    public void process(Page page) {
        Doctor doctor = (Doctor) page.getRequest().getExtra("doctor");
//        logger.info(doctor.toString());
        //职称
        doctor.setDocTitle(page.getHtml().xpath("//h3[@class='doc_name f22 fl']/text()").regex("\u00A0{2}(\\S+)", 1).get());;

        Selectable selectable = page.getHtml().xpath("//div[@class='doc_hospital']//a[1]");
        //医院名称
        doctor.setHosName(selectable.xpath("/a/text()").get());
        //医院url
        doctor.setHosUrl(selectable.xpath("/a/@href").get());
        //医院id
        doctor.setHosId(selectable.xpath("/a/@href").regex("/hospital/(\\S+)\\.htm",1).get());

        selectable = page.getHtml().xpath("//div[@class='doc_hospital']//a[2]");
        doctor.setDeptId(selectable.xpath("/a/@href").regex("/faculty/(\\S+)\\.htm",1).get());
        //科室名称
        doctor.setDeptName(selectable.xpath("/a/text()").get());
        //科室url
        doctor.setDeptUrl(selectable.xpath("/a/@href").get());

        List<Selectable> selectables = page.getHtml().xpath("//ul[@class='space_statistics']/li").nodes();

        int i=1;
        if(selectables.size() < 12){
            i = 0;
        }

        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("totalView",0);
        map.put("totalArticle",1+i);
        map.put("totalPatient", 2 + i);
        map.put("wexinBaodao", 4 + i);
        map.put("totalBaodao", 5 + i);
        map.put("totalVote", 6 + i);
        map.put("totalThankLetter", 7 + i);
        map.put("totalGift", 8 + i);
        try {
            for (Map.Entry<String, Integer> entry:map.entrySet()){

                    Method m = Doctor.class.getMethod("set"+ HaoStringUtils.capitalHead(entry.getKey()),Integer.class);
                    m.invoke(doctor,HaoStringUtils.parseInt(selectables.get(entry.getValue()).xpath("/li/span/text()").replace(",", "").get()));

            }
            doctor.setStartTime(HaoStringUtils.parseDateMin(selectables.get(selectables.size()-1).xpath("/li/span/text()").replace(",", "").get()));
            doctor.setStatus(1);
            Session session = HibernateUtils.getLocalThreadSession();
            session.beginTransaction();
            session.update(doctor);
            session.getTransaction().commit();
            session.close();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
            logger.warn(doctor.toString());
            System.exit(0);
        }
    }

    public Site getSite() {
        return site;
    }
    private Site site = Site.me()
            .setRetryTimes(5)
            .setSleepTime(400)
            .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");


    public static void main(String[] args) {
        Request req = new Request("http://doctorzwb.haodf.com/");
        Doctor doctor = new Doctor();
        doctor.setDocId("DE4r0BCkuHzduxIzeC8hoLSNZn1O5");
        req.putExtra("doctor",doctor);
        Spider.create(new DoctorWebSpiderTest())
                .addRequest(req)
                .thread(5)
                .run();
        System.out.println("over");
    }
}
