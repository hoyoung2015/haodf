package net.hoyoung.haodf.html;

import net.hoyoung.haodf.entity.Doctor;
import net.hoyoung.haodf.entity.Hospital;
import net.hoyoung.haodf.entity.Zixun;
import net.hoyoung.haodf.utils.HaoStringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by hoyoung on 2015/11/15.
 */
public class HospitalDetailHtml {

    public static void main(String[] args) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = FileUtils.readFileToString(new File("html/hospital_detail.html"), "UTF-8");
        Html html = new Html(s);

        Selectable seTmp;
        Hospital hospital = new Hospital();
        String tmp = HaoStringUtils.trim(html.xpath("//div[@class=panelA_blue]/div[@class=toptr]/ul/li/p/a/text()").get());
        hospital.setHosName(tmp);
        hospital.setStatus(0);
        hospital.setHosId("待定");
        hospital.setCreateTime(new Date());
        hospital.setUrl("待定");
        tmp = html.xpath("//div[@class=panelA_blue]/div[@class=toptr]/ul/li/p/text()").regex("\\((\\S+)\\)", 1).get();
        if(!StringUtils.isEmpty(tmp)){
            if(tmp.contains("等")){
                tmp = tmp.replaceAll("[等级]","");
            }
            hospital.setLevel(tmp);
        }
        seTmp = html.xpath("//div[@class=midtr]/div[@class=lt]/table/tbody/tr[2]/td[2]/text()");
        hospital.setProvince(seTmp.regex("(\\S+)[市省]",1).get());
        hospital.setCity(seTmp.regex("[市省](\\S+)[市区]", 1).get());

        System.out.println(hospital);
    }
}
