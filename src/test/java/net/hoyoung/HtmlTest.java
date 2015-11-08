package net.hoyoung;

import net.hoyoung.haodf.entity.Doctor;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/7.
 */
public class HtmlTest {
    @Test
    public void test() throws IOException {
        String s = FileUtils.readFileToString(new File("src/test/resources/doctorList.html"),"UTF-8");
        Html html = new Html(s);
        List<Selectable> trs = html.xpath("//body/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table[1]/tbody/tr")
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
            temp = doctor.getInfoUrl().substring(doctor.getInfoUrl().lastIndexOf("/")+1);
            temp = temp.substring(0,temp.lastIndexOf("."));
            doctor.setDocId(temp);

            System.out.println(doctor);
        }
//        temp = html.xpath("//div[@class=p_bar]/a[@class=p_text]/text()").regex("共\u00A0(\\d+)\u00A0页", 1).get();
//        if(temp != null){
//            int totalPage = Integer.parseInt(temp);
//            for (int i = 2; i <= totalPage; i++) {
//                Request req = new Request("http://haoping.haodf.com/keshi/"+secId+"/daifu.htm?prov=&p="+i);
//                req.putExtra("secId",secId);
//            }
//        }
    }

}
