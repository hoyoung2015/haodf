package net.hoyoung;

import net.hoyoung.haodf.entity.ConsultCat;
import net.hoyoung.haodf.entity.Section;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hoyoung on 2015/11/9.
 */
public class TestConsultCat {
    @Test
    public void test() throws IOException {
        String s = FileUtils.readFileToString(new File("html/consultCat.html"),  "UTF-8");
        Html html = new Html(s);
        for (Selectable ul : html.xpath("//div[@class=izixun-department]/ul").nodes()){
            String superCatName = ul.xpath("/ul/li[1]/text()").get();
            for (Selectable a : ul.xpath("/ul/li[2]/span/a").nodes()){
                ConsultCat consultCat = new ConsultCat();
                consultCat.setConCatId(a.xpath("/a/@href").regex("/index/(\\d+)\\.htm",1).get());
                consultCat.setUrl(a.xpath("/a/@href").get());
                consultCat.setCreateTime(new Date());
                consultCat.setStatus(0);
                consultCat.setConCatName(a.xpath("/a/text()").get());
                consultCat.setSuperCatName(superCatName);
                System.out.println(consultCat);
            }
        }
    }
}
