package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Duihua;
import net.hoyoung.haodf.entity.Wenda;
import net.hoyoung.haodf.utils.HaoStringUtils;
import net.hoyoung.webmagic.pipeline.DuihuaPipeline;
import net.hoyoung.webmagic.scheduler.HaodfDuihuaScheduler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/11.
 */
public class DuihuaSpider implements PageProcessor {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public void process(Page page) {
        Wenda wenda = (Wenda) page.getRequest().getExtra("wenda");
        //疾病
        String disease = page.getHtml().xpath("//div[@class=h_s_info_cons]/h2/text()").get();
        wenda.setDisease(disease);
        //病情描述
        String desc = page.getHtml().xpath("//div[@class=h_s_info_cons]/div/text()").get();
        wenda.setDesc(HaoStringUtils.trim(desc));
        //需要得到的帮助
        String wantHelp = page.getHtml().xpath("//div[@class=h_s_info_cons]/p[3]/text()").get();
        wenda.setWantHelp(HaoStringUtils.trim(wantHelp));
        //所就诊医院科室
        String temp = page.getHtml().xpath("//div[@class=h_s_info_cons]/p[4]/text()").get();
        temp = HaoStringUtils.trim(temp);
        if(!StringUtils.isEmpty(temp)){
            String[] sarr = temp.split(" ");
            if(sarr.length > 0){
                wenda.setHospital(sarr[0]);
            }
            if(sarr.length > 1){
                wenda.setHosDept(sarr[1]);
            }
        }
        //医生
        String docId = page.getHtml().xpath("//div[@class=aus_info]/a/@href").regex("/doctor/(\\S+)\\.htm", 1).get();
        wenda.setDocId(docId);
        //标题
        wenda.setTitle(page.getHtml().xpath("//h3[@class=h_s_cons_info_title]/text()").regex("咨询标题：(.+)", 1).get());

        /**
         * 解析对话内容
         */
        List<Selectable> divs = page.getHtml().xpath("//div[@class=zzx_yh_stream]").nodes();
        if(divs.size()==1){//没有对话
            return;
        }
        List<Duihua> duihuas = new ArrayList<Duihua>();
        for (int i = 1; i < divs.size(); i++) {
            Duihua duihua = new Duihua();
            duihua.setStatus(0);
            duihua.setWenId(wenda.getWenId());
            duihua.setCreateTime(new Date());
            Selectable div = divs.get(i);
            //获取内容，内容医生和患者相同

            String createTime = div.xpath("//div[@class=h_s_time]/text()").regex("发表于：([\\-:0-9 ]+)", 1).get().trim();
            try {
                duihua.setDuiTime(simpleDateFormat.parse(createTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(div.xpath("//div[@class=yh_l_doctor]").get()!=null){//这是医生
                duihua.setContent(HaoStringUtils.trim(div.xpath("//div[@class=stream_yh_right]/div[@class=h_s_cons_docs]/h3[@class=h_s_cons_title]/text()").get()));
                duihua.setDocId(wenda.getDocId());
                duihua.setDocHomeUrl(div.xpath("//div[@class=yh_l_doctor]/span/a/@href").get());
            }else {//这是患者
                duihua.setPatId("patient");
                //这个content可能为空，因为隐私
                duihua.setContent(HaoStringUtils.trim(div.xpath("//div[@class=h_s_cons]/pre/text()").get()));
            }
            duihuas.add(duihua);
        }
        page.putField("wenda",wenda);
        page.putField("duihuas",duihuas);
    }

    public Site getSite() {
        return site;
    }
    private Site site = Site.me()
            .setRetryTimes(5)
            .setSleepTime(3000)
            .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");


    public static void main(String[] args) {
        Spider.create(new DuihuaSpider())
                .setScheduler(new HaodfDuihuaScheduler())
                .addPipeline(new DuihuaPipeline())
//                .addUrl("http://www.haodf.com/wenda/adele_g_2160051984.htm")
                .thread(1)
                .run();
    }
}
