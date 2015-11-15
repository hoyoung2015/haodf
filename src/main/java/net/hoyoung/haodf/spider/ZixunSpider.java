package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Zixun;
import net.hoyoung.haodf.utils.HaoStringUtils;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hoyoung on 2015/11/15.
 */
public class ZixunSpider implements PageProcessor {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    AtomicInteger total = new AtomicInteger();
    public void process(Page page) {
        String tmp = page.getHtml().xpath("//div[@class='mt10']/div[@class='clearfix pb10 bb_c']/p[@class='fl pt10']/span[@class='pl5 fs']/span[@class='f14 orange1']/text()").get();
        if("0".equals(tmp)){//0个患者
            System.out.println("0 patient");
            return;
        }
        List<Selectable> seList = page.getHtml().xpath("//div[@class='zixun_list']/table/tbody/tr").nodes();
        Selectable seTmp;
        Session session = HibernateUtils.getLocalThreadSession();
        String docId = (String) page.getRequest().getExtra("docId");
        for (int i=1;i<seList.size();i++){
            Selectable se = seList.get(i);

            Zixun zixun = new Zixun();
            zixun.setStatus(0);
            zixun.setCreateTime(new Date());
            zixun.setDocId(docId);
            zixun.setPatId(se.xpath("/tr/td[2]/p/text()").get());

            seTmp = se.xpath("/tr/td[3]/p");
            zixun.setTitle(seTmp.xpath("/p/a/text()").get());
            if(seTmp.xpath("/p/img/@title").regex("^礼物$").match()){
                zixun.setGiftFlag(1);
            }
            if(seTmp.xpath("/p/img/@title").regex("^暖心礼物$").match()){
                zixun.setHotGiftFlag(1);
            }
            if(seTmp.xpath("/p/img/@title").regex("^电话咨询$").match()){
                zixun.setTellFlag(1);
            }
            if(seTmp.xpath("/p/img/@title").regex("^付费咨询$").match()){
                zixun.setPayFlag(1);
            }


            zixun.setUrl(se.xpath("/tr/td[3]/p/a/@href").get());
            String zixunId = se.xpath("/tr/td[3]/p/a/@href").regex("/wenda/(\\S+)\\.htm", 1).get();
            zixun.setZixunId(zixunId);
            String jibing = se.xpath("/tr/td[4]/a/text()").get();
            zixun.setJibing(jibing);
            zixun.setTotalChatNum(HaoStringUtils.parseInt(se.xpath("/tr/td[5]/text()").regex(" (\\d+) ", 1).get()));
            seTmp = se.xpath("/tr/td[5]/font/text()");
            zixun.setDocChatNum(HaoStringUtils.parseInt(seTmp.regex("(\\d+)/(\\d+)", 1).get()));
            zixun.setPatChatNum(HaoStringUtils.parseInt(seTmp.regex("(\\d+)/(\\d+)", 2).get()));

            seTmp = se.xpath("/tr/td[6]/span[1]/text()");
            tmp = seTmp.get();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try{
                zixun.setPostTime(tmp==null?null:simpleDateFormat.parse(tmp));
                session.beginTransaction();
                session.saveOrUpdate(zixun);
                session.getTransaction().commit();
            }catch (ConstraintViolationException e){
                e.printStackTrace();
                if(session.getTransaction().isActive()){
                    session.close();
                    session = HibernateUtils.getLocalThreadSession();//重新拿session
                }
            }catch (ParseException e) {
                e.printStackTrace();
            }
        }
        session.close();
        if(!page.getUrl().regex("\\?type=&p=\\d+").match()){//首页
            System.out.println("shouye");
            String temp = page.getHtml().xpath("//div[@class='page_turn']/a[@class='page_turn_a']/text()").regex("\u00A0(\\d+)\u00A0",1).get();
            if(temp!=null){
                int totalPage = Integer.valueOf(temp);
                total.addAndGet(totalPage);
                for (int i=2;i<=totalPage;i++){
                    Request req = new Request(HOME_URL+"?type=&p="+i);
                    req.setExtras(page.getRequest().getExtras());
                    page.addTargetRequest(req);
                }
            }
        }
        logger.info("last " + total.decrementAndGet() + " page");
    }

    public Site getSite() {
        return site;
    }
    private Site site = Site.me()
            .setRetryTimes(5)
            .setSleepTime(500)
            .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");


    private static String HOME_URL;
    public static void main(String[] args) {
//        if(args==null){
//            System.out.println("args is null");
//            System.exit(0);
//        }
//        if(args.length==0){
//            System.out.println("args length is 0");
//        }
//        String homeUrl = args[0];
//        String docId = args[1];
        String homeUrl = "http://sunxiwen.haodf.com/";
        String docId = "DE4r08xQdKSLBDMfyD-CyPwMiuK4";
        if(!homeUrl.endsWith("/")){
            homeUrl += "/";
        }
        HOME_URL = homeUrl+"zixun/list.htm";
        Request req = new Request(HOME_URL);
        req.putExtra("docId",docId);
        Spider.create(new ZixunSpider())
                .addRequest(req)
                .thread(5)
                .run();
        System.out.println(docId+"\t"+docId+"\tover");
    }
}
