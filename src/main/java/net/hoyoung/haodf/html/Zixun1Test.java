package net.hoyoung.haodf.html;

import net.hoyoung.haodf.entity.Duihua;
import net.hoyoung.haodf.entity.Wenda;
import net.hoyoung.haodf.entity.Zixun;
import net.hoyoung.haodf.utils.HaoStringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/15.
 */
public class Zixun1Test {

    public static void main(String[] args) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = FileUtils.readFileToString(new File("html/zixun1.html"), "GBK");
        Html html = new Html(s);

        String tmp = html.xpath("//div[@class='mt10']/div[@class='clearfix pb10 bb_c']/p[@class='fl pt10']/span[@class='pl5 fs']/span[@class='f14 orange1']/text()").get();
        if("0".equals(tmp)){//0个患者
            System.out.println("0 patient");
            return;
        }
        List<Selectable> seList = html.xpath("//div[@class='zixun_list']/table/tbody/tr").nodes();
        Selectable seTmp;
        for (int i=1;i<seList.size();i++){
            Selectable se = seList.get(i);
            Zixun zixun = new Zixun();
            zixun.setStatus(0);
            zixun.setCreateTime(new Date());
            zixun.setDocId("待定");
            zixun.setPatId(se.xpath("/tr/td[2]/p/text()").get());
            zixun.setTitle(se.xpath("/tr/td[3]/p/a/text()").get());

            if(se.xpath("/tr/td[3]/p/img/@title").regex("^礼物$").match()){
                System.err.println("有礼物");
            }
            if(se.xpath("/tr/td[3]/p/img/@title").regex("^暖心礼物$").match()){
                System.err.println("暖心礼物");
            }
            if(se.xpath("/tr/td[3]/p/img/@title").regex("^电话咨询$").match()){
                System.err.println("电话咨询");
            }

            zixun.setUrl(se.xpath("/tr/td[3]/p/a/@href").get());
            String zixunId = se.xpath("/tr/td[3]/p/a/@href").regex("/wenda/(\\S+)\\.htm", 1).get();
            zixun.setZixunId(zixunId);
            String jibing = se.xpath("/tr/td[4]/a/text()").get();
            zixun.setJibing(jibing);
            zixun.setTotalChatNum(HaoStringUtils.parseInt(se.xpath("/tr/td[5]/text()").regex(" (\\d+) ", 1).get()));
            seTmp = se.xpath("/tr/td[5]/font/text()");
            zixun.setDocChatNum(HaoStringUtils.parseInt(seTmp.regex("(\\d+)/(\\d+)",1).get()));
            zixun.setPatChatNum(HaoStringUtils.parseInt(seTmp.regex("(\\d+)/(\\d+)", 2).get()));

            seTmp = se.xpath("/tr/td[6]/span[@class='gray3']/text()");
            zixun.setPostTime(HaoStringUtils.parseDate(seTmp.get()));
            System.out.println(zixun);
        }
    }
}
