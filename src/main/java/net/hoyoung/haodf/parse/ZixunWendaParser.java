package net.hoyoung.haodf.parse;

import net.hoyoung.haodf.entity.ZixunWenda;
import net.hoyoung.haodf.utils.HaoStringUtils;
import net.hoyoung.haodf.utils.HibernateUtils;
import net.hoyoung.haodf.utils.HtmlRegexpUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.selector.Html;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by hoyoung on 2015/11/19.
 */
public class ZixunWendaParser {
    public static void main(String[] args) {
        HtmlRegexpUtil htmlRegexpUtil = new HtmlRegexpUtil();
        int step = 2000;
        Session session = HibernateUtils.getLocalThreadSession();

//        String where = "where content is null and private_chat=0";
        String where = "where content is null and private_chat=0";
        BigInteger countB = (BigInteger) session.createSQLQuery("SELECT count(zw_id) FROM zixun_wenda "+where)
                .uniqueResult();
        int count = countB.intValue();
        while (count > 0){
            List<ZixunWenda> list = session.createQuery("from ZixunWenda "+where)
                    .setMaxResults(step)
                    .list();
            for (ZixunWenda zw : list){
                parse(zw);
                session.beginTransaction();
                session.update(zw);
                session.getTransaction().commit();
                System.out.println(--count);
            }
        }
        session.close();
    }
    private static void parse(ZixunWenda zixunWenda){
        Html html = new Html(zixunWenda.getHtml());
        if(html.xpath("//h3[@class=h_s_cons_info_title]").get()!=null){
            //咨询标题
//            System.out.println("咨询标题");
            zixunWenda.setZwType("咨询标题");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//div[@class='h_s_info_cons']").get()), " ", ""));
        }else if(html.xpath("//h3[@class='h_s_cons_title gifts']").get()!=null){
//            System.out.println("送礼物");
            zixunWenda.setZwType("送礼物");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//p[@class='h_s_cons_main']").get()), " ", ""));
        }else if(html.xpath("//h3[@class='h_s_cons_title hotheart']").get()!=null){
//            System.out.println("送暖心");
            zixunWenda.setZwType("送暖心");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//span[@class='showword']").get()), " ", ""));
        }else if(html.xpath("//h3[@class='h_s_cons_title iconlhz']").get()!=null){
//            System.out.println("随访报到");
            zixunWenda.setZwType("随访报到");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//pre[@class='h_s_cons_main']").get()), " ", ""));
        }else if(html.xpath("//h3[@class='h_s_cons_title iconmails']").get()!=null){
//            System.out.println("感谢信");
            zixunWenda.setZwType("感谢信");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//div[@class='h_s_cons']").get()), " ", ""));
        }else if(html.xpath("//h3[@class='h_s_cons_title iconfucha']").get()!=null){
//            System.out.println("复查报告");
            zixunWenda.setZwType("复查报告");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//div[@class='h_s_cons']").get()), " ", ""));
        }else if(html.xpath("//h3[@class='h_s_cons_title iconjia']").get()!=null){
//            System.out.println("预约加号");
            zixunWenda.setZwType("预约加号");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//p[@class='h_s_cons_main']").get()), " ", ""));
        }else if(html.xpath("//h3[@class='h_s_cons_title iconmoney']").get()!=null){
//            System.out.println("付费咨询");
            zixunWenda.setZwType("付费咨询");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//p[@class='h_s_cons_main']").get())," ",""));
        }else if(html.xpath("//h3[@class=h_s_cons_title]").regex(".*住院患者报到$").match()){
//            System.out.println("患者报到");
            zixunWenda.setZwType("患者报到");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//div[@class='h_s_cons']").get()), " ", ""));
        }else if(html.xpath("//div[@class=h_s_cons_docs]").get()!=null){
            //
//            System.out.println("医生普通对话");
            zixunWenda.setZwType("普通对话");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//h3[@class='h_s_cons_title']").get()), " ", ""));
        }else if(html.xpath("//div[@class='h_s_cons_main']/p/text()").regex("^我已填写了调查表.*").match()){
            zixunWenda.setZwType("填调查表");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//div[@class='h_s_cons_main']").get())," ",""));
        }else {
//            System.out.println("病人普通对话");
            zixunWenda.setZwType("普通对话");

            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//pre[@class='h_s_cons_main']").get()), " ", ""));
        }
        if(html.xpath("//h3[@class=h_s_cons_title]").regex(".*大夫通知.*").match()){
            //医生通知
            zixunWenda.setZwType("大夫通知");
            System.err.println("大夫通知");
            zixunWenda.setContent(HaoStringUtils.replaceAndTrim(HtmlRegexpUtil.filterHtml(html.xpath("//h3[@class='h_s_cons_title']").get()), " ", ""));
        }
        if(zixunWenda.getPatId()!=null){//病人获取就诊状态
            zixunWenda.setCureState(html.xpath("//div[@class='yh_l_states']/span/text()").get());
        }


        Elements elements = html.getDocument().getElementsByTag("script");
        if(elements.size()>0){
//            System.out.println(elements.get(0).toString());
            if(elements.get(0).toString().contains("showBingliDetail")){
                //患者资料隐私
                zixunWenda.setShowBingli(1);
            }

        }
        if(html.xpath("//span[@class='fl bingli_hide_word']/text()").regex(".*此对话涉及隐私.*").match()){
            //患者资料隐私
            zixunWenda.setPrivateChat(1);
        }
        if(zixunWenda.getContent()!=null) {
            zixunWenda.setContentSize(zixunWenda.getContent().length());
        }else {
            zixunWenda.setContentSize(0);
        }
//        System.out.println(zixunWenda);
    }
}
