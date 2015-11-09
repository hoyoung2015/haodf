package net.hoyoung;

import net.hoyoung.haodf.entity.Section;
import net.hoyoung.haodf.entity.Wenda;
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
public class TestWenda {
    @Test
    public void test() throws IOException {
        String s = FileUtils.readFileToString(new File("html/wenda.html"), "UTF-8");
        Html html = new Html(s);

        for (Selectable a : html.xpath("//div[@class=izixun-askedListContent]/ul/li/a").nodes()){
            Wenda wenda = new Wenda();
            wenda.setConCatName("待定");
            wenda.setStatus(0);
            wenda.setUrl(a.xpath("/a/@href").get());
            wenda.setCreateTime(new Date());
            wenda.setWenName(a.xpath("/a/text()").get());
            wenda.setWenId(a.xpath("/a/@href").regex("/wenda/([A-Za-z_0-9]+)\\.htm",1).get());
            System.out.println(wenda);
        }
    }
}
