package net.hoyoung.haodf.spider;

import net.hoyoung.haodf.entity.ConsultCat;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/9.
 */
public class LagouSpider implements PageProcessor {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    public void process(Page page) {
        logger.info(page.getJson().toString());
//        try {
//            FileUtils.writeStringToFile(new File("html/consultCat.html"),page.getHtml().get(),"UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        /*Session session = HibernateUtils.openSession();
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();*/
    }

    public Site getSite() {
        return site;
    }
    private Site site = Site.me()
            .setRetryTimes(5)
            .setSleepTime(1000)
            .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36")
//            .addHeader("REQUEST_ID","59be6655-1957-4195-b828-2e6b5962a57a")
            .addHeader("Content-Type", "application/json;charset=UTF-8")
            .addHeader("Host", "www.lagou.com");
//            .addHeader("Cookie","LGMOID=20151218135651-9D30383A7FD9D03467034B2483D3F639; user_trace_token=20151218135654-6ac765d4711d43ae8be296839b4e7791; LGUID=20151218135655-246246ea-a54c-11e5-86bb-525400f775ce; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID=F6A29A05174BA76F878D4C74D1AB3EBB; _gat=1; PRE_UTM=AD__baidu_pinzhuan; PRE_HOST=bzclk.baidu.com; PRE_SITE=http%3A%2F%2Fbzclk.baidu.com%2Fadrc.php%3Ft%3D0fKL00c00fA-iwf00yk50FNkUsj4qM9y000000xOJ1s00000TmsXm9.THL0oUhY1x60UWdBmy-bIy9EUyNxTAT0T1Y4PAD3nHcsrj0snjK9PyRz0ZRqf103PRDYfRn4nW9KPDNjwjmsnD7AnHmLwD7AfHKjPDf0mHdL5iuVmv-b5Hc1rH0LPHfdP1chTZFEuA-b5HDvFhqzpHYkFMPdmhqzpHYhTZFG5Hc0uHdCIZwsrBtEILILQhk9uvqdQhPEUitOIgwVgLPEIgFWuHdKw7qxmh7GuZNxTA-8Xh9dmy3hIgwVgvd-uA-dUHd1uyYhIgwVgvP9UgK9pyI85NP7HfKWThnqnWD4nWm%26tpl%3Dtpl_10085_12986_1%26l%3D1017904903%26wd%3D%25E6%258B%2589%25E5%258B%25BE%25E7%25BD%2591%26issp%3D1%26f%3D8%26ie%3Dutf-8%26tn%3Dbaiduhome_pg%26oq%3Djava%2520Date%26inputT%3D2497; PRE_LAND=http%3A%2F%2Fwww.lagou.com%2F%3Futm_source%3DAD__baidu_pinzhuan%26utm_medium%3Dsem%26utm_campaign%3DSEM; HISTORY_POSITION=948045%2C10k-20k%2C%E7%A9%B7%E6%B8%B8%E7%BD%91%2C%E6%95%B0%E6%8D%AE%E5%88%86%E6%9E%90%E5%B8%88%7C588957%2C5k-10k%2C%E6%96%B0%E6%B8%B8%E4%BA%92%E8%81%94%2C%E6%95%B0%E6%8D%AE%E5%88%86%E6%9E%90%E5%B8%88%7C1145172%2C8k-10k%2C%E9%93%B6%E6%A9%99%E4%BC%A0%E5%AA%92%2CDMP%E6%95%B0%E6%8D%AE%E5%88%86%E6%9E%90%E5%B7%A5%E7%A8%8B%E5%B8%88%EF%BC%88%E5%B9%BF%E5%91%8A%E8%A1%8C%E4%B8%9A%EF%BC%89%7C507477%2C15k-25k%2C%E4%B8%80%E8%B5%B7%E4%BD%9C%E4%B8%9A%E7%BD%91%2C%E6%95%B0%E6%8D%AE%E5%88%86%E6%9E%90%7C391075%2C15k-25k%2C%E5%95%86%E8%AF%A2%E7%A7%91%E6%8A%80%2C%E6%95%B0%E6%8D%AE%E5%88%86%E6%9E%90%2F%E6%95%B0%E6%8D%AE%E6%8C%96%E6%8E%98%7C; SEARCH_ID=3c952795648046f69647aea17708fc06; _ga=GA1.2.1648953821.1450418353; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1450418354,1450768271; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1450768371; LGSID=20151222150844-d68efb0d-a87a-11e5-882f-525400f775ce; LGRID=20151222151024-125842c6-a87b-11e5-882f-525400f775ce");


    public static void main(String[] args) throws UnsupportedEncodingException {
        String keyword = "数据挖掘";

        Spider spider = Spider.create(new LagouSpider());
        for (int i = 19; i <= 30; i++) {
            Request req = new Request("http://www.lagou.com/jobs/positionAjax.json?first=false&kd=数据挖掘&pn="+2);
//            req.setMethod(HttpConstant.Method.POST);
            /*NameValuePair[] nameValuePair = new NameValuePair[3];
            nameValuePair[0] = new BasicNameValuePair("first","false");
            nameValuePair[1] = new BasicNameValuePair("kd", URLEncoder.encode(keyword,"UTF-8"));
            nameValuePair[2] = new BasicNameValuePair("pn",""+i);

            req.putExtra("nameValuePair",nameValuePair);*/

            spider.addRequest(req);
            break;
        }
        spider.thread(1).run();
        System.out.println("Lagou spider complete");
    }
}
