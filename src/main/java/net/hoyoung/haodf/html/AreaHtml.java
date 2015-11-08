package net.hoyoung.haodf.html;

import org.apache.commons.io.FileUtils;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;

/**
 * Created by hoyoung on 2015/11/8.
 */
public class AreaHtml {
    public static void main(String[] args) throws IOException {
        String s = FileUtils.readFileToString(new File("html/area.html"), "UTF-8");
        Html html = new Html(s);
        for (Selectable a : html.xpath("//div[@id=el_tree_1000000]/div/a").nodes()){
            System.out.println("\""+a.xpath("/a/@href").get()+"\",");
        }
    }
}
