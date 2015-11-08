package net.hoyoung;

import net.hoyoung.haodf.entity.Disease;
import net.hoyoung.haodf.entity.DiseaseCat;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/8.
 */
public class DiseaseHtmlTest {
    @Test
    public void test() throws IOException {
        String s = FileUtils.readFileToString(new File("html/disease.html"), "UTF-8");
        Html html = new Html(s);
        Selectable ct = html.xpath("//div[@class=m_box_green]/div[@class=ct]");
        List<Selectable> m_title_greens = ct.xpath("/div/div[@class=m_title_green]").nodes();
        List<Selectable> m_ctt_greens = ct.xpath("/div/div[@class=m_ctt_green]").nodes();

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
                disease.setCatName("待定");
                disease.setCatId("待定");
                System.out.println(disease);
            }
        }
    }

}
