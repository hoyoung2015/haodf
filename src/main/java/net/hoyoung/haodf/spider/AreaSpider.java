package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.Area;
import net.hoyoung.haodf.entity.Hospital;
import net.hoyoung.haodf.utils.HibernateUtils;
import net.hoyoung.haodf.utils.UUIDGenerater;
import org.hibernate.Session;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/7.
 */
public class AreaSpider implements PageProcessor {
    public void process(Page page) {
        String province = page.getHtml().xpath("//div[@id=el_tree_1000000]/div[@class=kstl2]/a/text()").get();
        List<Selectable> m_title_greens = page.getHtml().xpath("//div[@class=bxmd]/div[@class=ct]/div[@class=m_title_green]").nodes();
        List<Selectable> m_ctt_greens = page.getHtml().xpath("//div[@class=bxmd]/div[@class=ct]/div[@class=m_ctt_green]").nodes();

        for (int i = 0; i < m_title_greens.size(); i++) {
            Selectable m_title_green = m_title_greens.get(i);
            Selectable m_ctt_green = m_ctt_greens.get(i);
            String city = m_title_green.xpath("/div/@id").get();
            for (Selectable li : m_ctt_green.xpath("/div/ul/li").nodes()){
                Hospital hospital = new Hospital();
                hospital.setProvince(province);
                hospital.setStatus(0);
                hospital.setCity(city);
                hospital.setCreateTime(new Date());

                String name = li.xpath("/li/a/text()").get();
                hospital.setUrl(li.xpath("/li/a/@href").get());
                hospital.setHosId(li.xpath("/li/a/@href").regex("hospital/(\\S+)\\.htm", 1).get());
                hospital.setHosName(name);
                String level = li.xpath("/li/span/text()").regex("\\((.*)\\)", 1).get();
                if(level!=null){
                    int k = level.indexOf(",");
                    if(k>0){
                        level = level.substring(0,k);
                    }
                    hospital.setLevel(level);
                }
                Session session = HibernateUtils.openSession();
                session.beginTransaction();
                session.saveOrUpdate(hospital);
                session.getTransaction().commit();
                session.close();
            }

        }

    }
    public Site getSite() {
        return site;
    }
    private Site site = Site.me()
            .setRetryTimes(5)
            .setSleepTime(300)
            .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");
    public static void main(String[] args) {
        String[] urls = new String[]{
                "http://www.haodf.com/yiyuan/beijing/list.htm",
                "http://www.haodf.com/yiyuan/shanghai/list.htm",
                "http://www.haodf.com/yiyuan/guangdong/list.htm",
                "http://www.haodf.com/yiyuan/guangxi/list.htm",
                "http://www.haodf.com/yiyuan/jiangsu/list.htm",
                "http://www.haodf.com/yiyuan/zhejiang/list.htm",
                "http://www.haodf.com/yiyuan/anhui/list.htm",
                "http://www.haodf.com/yiyuan/jiangxi/list.htm",
                "http://www.haodf.com/yiyuan/fujian/list.htm",
                "http://www.haodf.com/yiyuan/shandong/list.htm",
                "http://www.haodf.com/yiyuan/sx/list.htm",
                "http://www.haodf.com/yiyuan/hebei/list.htm",
                "http://www.haodf.com/yiyuan/henan/list.htm",
                "http://www.haodf.com/yiyuan/tianjin/list.htm",
                "http://www.haodf.com/yiyuan/liaoning/list.htm",
                "http://www.haodf.com/yiyuan/heilongjiang/list.htm",
                "http://www.haodf.com/yiyuan/jilin/list.htm",
                "http://www.haodf.com/yiyuan/hubei/list.htm",
                "http://www.haodf.com/yiyuan/hunan/list.htm",
                "http://www.haodf.com/yiyuan/sichuan/list.htm",
                "http://www.haodf.com/yiyuan/chongqing/list.htm",
                "http://www.haodf.com/yiyuan/shanxi/list.htm",
                "http://www.haodf.com/yiyuan/gansu/list.htm",
                "http://www.haodf.com/yiyuan/yunnan/list.htm",
                "http://www.haodf.com/yiyuan/xinjiang/list.htm",
                "http://www.haodf.com/yiyuan/neimenggu/list.htm",
                "http://www.haodf.com/yiyuan/hainan/list.htm",
                "http://www.haodf.com/yiyuan/guizhou/list.htm",
                "http://www.haodf.com/yiyuan/qinghai/list.htm",
                "http://www.haodf.com/yiyuan/ningxia/list.htm",
                "http://www.haodf.com/yiyuan/xizang/list.htm"
        };
        Spider spider = Spider.create(new AreaSpider());
        for (String url : urls){
            spider.addUrl(url);
//            break;
        }

        spider.thread(1)
                .run();
        System.out.println("area spider over");
        System.exit(0);
    }
}
