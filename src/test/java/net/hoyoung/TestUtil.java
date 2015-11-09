package net.hoyoung;

import org.junit.Test;
import us.codecraft.webmagic.selector.Html;

import java.util.regex.Pattern;

/**
 * Created by hoyoung on 2015/11/8.
 */
public class TestUtil {
    @Test
    public void test(){
        Html html = new Html("<a href='#'>(三甲, 特色:综合)</a>");
        String temp = html.xpath("//a/text()").regex("\\((.*)\\)", 1).get();
        int k = temp.indexOf(",");
        if(k>0){
            temp = temp.substring(0,k);
        }
        System.out.println(temp);
    }
}
