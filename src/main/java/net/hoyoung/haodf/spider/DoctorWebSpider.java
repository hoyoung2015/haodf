package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Doctor;
import net.hoyoung.haodf.utils.HaoStringUtils;
import net.hoyoung.haodf.utils.HibernateUtils;
import net.hoyoung.webmagic.scheduler.DoctorWebScheduler;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hoyoung on 2015/11/14.
 */
public class DoctorWebSpider implements PageProcessor {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    public void process(Page page) {
        String tmp;
        Doctor doctor = (Doctor) page.getRequest().getExtra("doctor");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
        }else if(selectables.size() == 13){
            i = 2;
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
        Session session = HibernateUtils.getLocalThreadSession();
        try {
            for (Map.Entry<String, Integer> entry:map.entrySet()){

                    Method m = Doctor.class.getMethod("set"+ HaoStringUtils.capitalHead(entry.getKey()),Integer.class);
                    m.invoke(doctor,HaoStringUtils.parseInt(selectables.get(entry.getValue()).xpath("/li/span/text()").replace(",", "").get()));

            }
            tmp = selectables.get(selectables.size()-1).xpath("/li/span/text()").replace(",", "").get();
            doctor.setStartTime(tmp == null ? null : simpleDateFormat.parse(tmp));
            doctor.setStatus(1);

            session.beginTransaction();
            session.update(doctor);
            session.getTransaction().commit();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (ConstraintViolationException e){
            e.printStackTrace();
            if(session.getTransaction().isActive()){
                session.close();
                session = HibernateUtils.getLocalThreadSession();//重新拿session
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            try {
                FileUtils.writeStringToFile(new File("html/zixun.html"),page.getHtml().get(),"UTF-8");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.warn(doctor.toString());
            System.exit(0);
        }
        session.close();
    }

    public Site getSite() {
        return site;
    }
    private Site site = Site.me()
            .setRetryTimes(5)
            .setSleepTime(800)
            .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");


    public static void main(String[] args) {
        Spider.create(new DoctorWebSpider())
                .setScheduler(new DoctorWebScheduler())
                .thread(5)
                .run();
        System.out.println("over");
    }
}
