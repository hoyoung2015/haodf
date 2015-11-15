package net.hoyoung.haodf.html;

import net.hoyoung.haodf.entity.Doctor;
import net.hoyoung.haodf.utils.HaoStringUtils;
import org.apache.commons.io.FileUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hoyoung on 2015/11/14.
 */
public class DoctorWebHtml {
    public void process(){

    }
    public static void main(String[] args) throws IOException {
        String s = FileUtils.readFileToString(new File("html/doctor_web.html"),"UTF-8");
        Html html = new Html(s);

        Doctor doctor = new Doctor();
        //职称
        doctor.setDocTitle(html.xpath("//h3[@class='doc_name f22 fl']/text()").regex("\u00A0{2}(\\S+)", 1).get());;

        Selectable selectable = html.xpath("//div[@class='doc_hospital']//a[1]");
        //医院名称
        doctor.setHosName(selectable.xpath("/a/text()").get());
        //医院url
        doctor.setHosUrl(selectable.xpath("/a/@href").get());
        //医院id
        doctor.setHosId(selectable.xpath("/a/@href").regex("/hospital/(\\S+)\\.htm",1).get());

        selectable = html.xpath("//div[@class='doc_hospital']//a[2]");
        doctor.setDeptId(selectable.xpath("/a/@href").regex("/faculty/(\\S+)\\.htm",1).get());
        //科室名称
        doctor.setDeptName(selectable.xpath("/a/text()").get());
        //科室url
        doctor.setDeptUrl(selectable.xpath("/a/@href").get());

        List<Selectable> selectables = html.xpath("//ul[@class='space_statistics']/li").nodes();

        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("totalView",0);
        map.put("totalArticle",2);
        map.put("totalPatient",3);
        map.put("wexinBaodao",5);
        map.put("totalBaodao",6);
        map.put("totalVote",7);
        map.put("totalThankLetter",8);
        map.put("totalGift", 9);
        for (Map.Entry<String, Integer> entry:map.entrySet()){
            try {
                Method m = Doctor.class.getMethod("set"+HaoStringUtils.capitalHead(entry.getKey()),Integer.class);
                m.invoke(doctor,HaoStringUtils.parseInt(selectables.get(entry.getValue()).xpath("/li/span/text()").replace(",", "").get()));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        doctor.setStartTime(HaoStringUtils.parseDateMin(selectables.get(11).xpath("/li/span/text()").replace(",", "").get()));
        System.out.println(doctor);
    }
}
