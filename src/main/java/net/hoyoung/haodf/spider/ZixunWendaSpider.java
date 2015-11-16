package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.*;
import net.hoyoung.haodf.utils.HaoStringUtils;
import net.hoyoung.haodf.utils.HibernateUtils;
import net.hoyoung.webmagic.scheduler.ZixunWendaScheduler;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ZixunWendaSpider implements PageProcessor {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void process(Page page) {
        Selectable seTmp;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Zixun zixun = (Zixun) page.getRequest().getExtra("zixun");

        logger.info(">>>>"+zixun);
        if(page.getHtml().xpath("//div[@class=content_2]/div/p[@class=info_page_word]").get()!=null){
            //医患设为隐私的
            logger.info("隐私>>"+zixun.getUrl());
            Session session = HibernateUtils.getLocalThreadSession();
            session.beginTransaction();
            session.createQuery("update Zixun set privateFlag=1,status=1 where zixunId=?")
                    .setParameter(0,zixun.getZixunId())
                    .executeUpdate();
            session.getTransaction().commit();
            session.clear();
            return;
        }
        Session session = HibernateUtils.getLocalThreadSession();
        session.beginTransaction();
        //判断是否有下一页
        seTmp = page.getHtml().xpath("//div[@class=page_main]");
        String tmp = seTmp.xpath("/div/div/a[@class=page_cur]/text()").get();//这里这能判断有没有分页
        String nextUrl = null;
        boolean dellOld = false;
        boolean allClear = false;
        if(!StringUtils.isEmpty(tmp)){//有分页代码
            int pageNow = Integer.valueOf(tmp);
            if(pageNow==1){//第一页，删除旧的
                dellOld = true;
            }
            nextUrl = seTmp.links().regex(".*htm\\?p="+(++pageNow)+"$").get();//判断是否到达最后一页
        }else {//没有分页代码
            dellOld = true;
        }
        if(dellOld){//删除旧的
            logger.info("删除旧的");
            session.createQuery("delete ZixunWenda where zixunId=?")
                    .setParameter(0,zixun.getZixunId())
                    .executeUpdate();
        }
        if(!StringUtils.isEmpty(nextUrl)){//有下一页
            logger.info("has next page:"+nextUrl);
            Request request = new Request(nextUrl);
            request.setExtras(page.getRequest().getExtras());
            page.addTargetRequest(request);
        }else {//没有分页或者已经到达最后一页
            //在这里将状态设为1
            allClear = true;
        }

        /**
         * 解析对话内容
         */

        List<Selectable> divs = page.getHtml().xpath("//div[@class=zzx_yh_stream]").nodes();
        for (int i = 0; i < divs.size(); i++) {
            ZixunWenda zixunWenda = new ZixunWenda();
            zixunWenda.setCreateTime(new Date());
            zixunWenda.setZixunId(zixun.getZixunId());
            Selectable div = divs.get(i);
            zixunWenda.setHtml(div.get());//先暂时存html，后面再解析
            //获取内容，内容医生和患者相同

            String publishTime = HaoStringUtils.trim(div.xpath("//div[@class=h_s_time]/text()").regex("发表于：?([\\-:0-9 ]+)", 1).get());
            if(StringUtils.isEmpty(publishTime)){
                System.out.println(div.xpath("//div[@class=h_s_time]/text()"));
            }
            try {
                zixunWenda.setPublishTime(publishTime==null?null:simpleDateFormat.parse(publishTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(div.xpath("//div[@class=yh_l_doctor]").get()!=null){//这是医生
                zixunWenda.setDocId(zixun.getDocId());
//                zixunWenda.setContent("待定");
            }else {//这是患者
                zixunWenda.setPatId(zixun.getPatId());
                //这个content可能为空，因为隐私
//                zixunWenda.setContent("待定");
            }
            session.save(zixunWenda);
        }
        if(allClear){
            logger.info("状态设为已爬取");
            session.createQuery("update Zixun set status=1 where zixunId=?")
                    .setParameter(0,zixun.getZixunId())
                    .executeUpdate();
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
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
        Session session = HibernateUtils.getLocalThreadSession();
        session.beginTransaction();
        System.out.println(">>>>>>>>>>>"+session.createSQLQuery("SET NAMES 'utf8mb4'").executeUpdate());
        session.getTransaction().commit();
        session.close();
        Spider.create(new ZixunWendaSpider())
                .setScheduler(new ZixunWendaScheduler())
                .thread(3)
                .run();

//        Zixun zixun = new Zixun();
//        zixun.setUrl("http://www.haodf.com/wenda/sunxiwen_g_3361735017.htm");
//
//        Request request = new Request(zixun.getUrl());
//        request.putExtra("zixun",zixun);
//        Spider.create(new ZixunWendaSpider())
//                .setScheduler(new ZixunWendaScheduler())
//                .addRequest(request)
//                .thread(1)
//                .run();
    }
}
