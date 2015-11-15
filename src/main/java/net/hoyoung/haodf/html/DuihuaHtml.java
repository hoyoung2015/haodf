package net.hoyoung.haodf.html;

import net.hoyoung.haodf.entity.Duihua;
import net.hoyoung.haodf.entity.Wenda;
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
 * Created by hoyoung on 2015/11/11.
 */
public class DuihuaHtml {

    public static void main(String[] args) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = FileUtils.readFileToString(new File("html/duihua.html"), "UTF-8");
        Html html = new Html(s);

        Wenda wenda = new Wenda();
        for (Selectable div : html.xpath("//div[@class=h_s_cons_info]/div[@class=h_s_info_cons]/div").nodes()){
            System.out.println(div);
            if("病情描述：".equals(div.xpath("/div/strong/text()").get())){
                wenda.setDescription(HaoStringUtils.trim(div.xpath("/div/text()").get()));
            }else if("治疗情况：".equals(div.xpath("/div/strong/text()").get())){
                wenda.setZhiliao(HaoStringUtils.trim(div.xpath("/div/text()").get()));
            }else if("用药情况：".equals(div.xpath("/div/strong/text()").get())){
                wenda.setDrug(HaoStringUtils.trim(div.xpath("/div/text()").get()));
            }
        }
        for (Selectable p : html.xpath("//div[@class=h_s_cons_info]/div[@class=h_s_info_cons]/p").nodes()){
            System.out.println(p);
            if("希望提供的帮助：".equals(p.xpath("/p/strong/text()").get())){
                wenda.setWantHelp(p.xpath("/p/text()").get());
            }else if("所就诊医院科室：".equals(p.xpath("/p/strong/text()").get())){
                String temp = html.xpath("//div[@class=h_s_info_cons]/p[4]/text()").get();
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
            }
        }
        //疾病，这里有的有超链接，有的没有
        String disease = html.xpath("//div[@class=h_s_info_cons]/h2/a/text()").get();
        if(disease==null){
            disease = html.xpath("//div[@class=h_s_info_cons]/h2/text()").get();
        }
        wenda.setDisease(disease);
        //医生
        String docId = html.xpath("//div[@class=aus_info]/a/@href").regex("/doctor/(\\S+)\\.htm", 1).get();
        wenda.setDocId(docId);
        //标题
        wenda.setTitle(html.xpath("//h3[@class=h_s_cons_info_title]/text()").regex("咨询标题：(.+)", 1).get());
        System.out.println(wenda);


        /**
         * 解析对话内容
         */
        List<Selectable> divs = html.xpath("//div[@class=zzx_yh_stream]").nodes();
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
    }
}
