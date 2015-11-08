package net.hoyoung;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.hoyoung.haodf.entity.Area;
import net.hoyoung.haodf.entity.Hospital;
import net.hoyoung.haodf.entity.Section;
import net.hoyoung.haodf.utils.HibernateUtils;
import net.hoyoung.haodf.utils.UUIDGenerater;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws IOException {
        String s = FileUtils.readFileToString(new File("src/test/resources/area.html"),"UTF-8");
        Html html = new Html(s);
        List<Selectable> tds = html.xpath("//table//table[7]/tbody/tr[3]//table//td//a").nodes();
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        for (int i = 0; i < tds.size()-1; i++) {
            Selectable selectable = tds.get(i);
            Area area = new Area();
            area.setAreaId(UUIDGenerater.randomUUID());
            area.setAreaName(selectable.xpath("/*/text()").get());
            area.setUrl(selectable.xpath("/*/@href").get());
            area.setCreateTime(new Date());
            area.setStatus(0);
            System.out.println(area);
//            session.save(area);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void testUUId() throws Exception {
        System.out.println(UUIDGenerater.randomUUID().length());

    }

    public void testHospital() throws Exception {
        String s = FileUtils.readFileToString(new File("src/test/resources/hospital.html"),"UTF-8");
        Html html = new Html(s);
        List<Selectable> trs = html.xpath("//table//table//table//table").nodes().get(1).xpath("/table/tbody/tr").nodes();
        String city = null;
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        for (Selectable selectable : trs){
//            System.out.println(selectable.xpath("/tr/td[@class=lb_line]").get()==null);
            if(selectable.xpath("/tr/td[@class=lb_line]").get() != null){
                continue;
            }
            //判断第一个是否有城市名
            if(!selectable.xpath("/tr/td[1]/text()").regex("\u00A0").match()){
                //新出现的城市，每个城市的第一行
                city = selectable.xpath("/tr/td[1]/text()").get();
                System.out.println(city);

            }

            List<Selectable> tds = selectable.xpath("/tr/td").nodes();
            for (int i = 1; i < tds.size(); i++) {
                Selectable td = tds.get(i);
                if(td.xpath("/td/text()").regex("\u00A0").match()){//最后一个空格结尾
                    continue;
                }

                Hospital hospital = new Hospital();
                hospital.setCity(city);
                hospital.setStatus(0);
                hospital.setUrl(td.xpath("/td/a/@href").get());
                hospital.setCreateTime(new Date());
                hospital.setHosId(UUIDGenerater.randomUUID());
                hospital.setProvince("待定");
                hospital.setHosName(td.xpath("/td/a/text()").get());
                System.out.println(hospital);
//                session.save(hospital);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    public void testNanoTime() throws Exception {
        System.out.println(System.nanoTime());

    }

    public void testSection() throws Exception {
        String s = FileUtils.readFileToString(new File("src/test/resources/section.html"),"UTF-8");
        Html html = new Html(s);
        List<Selectable> divs = html.xpath("//div[@class=ct]/div[@class=m_ctt_green]/ul/li/a").nodes();
        for (Selectable sele:divs){
            Section sec = new Section();
            sec.setStatus(0);
            sec.setCreateTime(new Date());
            sec.setUrl(sele.xpath("/a/@href").get());

            String temp = sec.getUrl().substring(sec.getUrl().lastIndexOf("/")+1);
            temp = temp.substring(0,temp.lastIndexOf("."));

            sec.setSecId(temp);
            sec.setSecName(sele.xpath("/a/text()").get());
            System.out.println(sec);
        }
    }

    public void testHospital2() throws Exception {
        String s = FileUtils.readFileToString(new File("src/test/resources/area.html"),"UTF-8");
        Html html = new Html(s);

        List<Selectable> tables = html.xpath("//div/table/tbody/tr/td/table/tbody/tr/td/table").nodes();
        for (int i = 0; i < tables.size()-4; i++) {
            Selectable table = tables.get(i);
            String province = table.xpath("/table/tbody/tr[2]/td[2]/table/tbody/tr/td[2]/text()").get().replace("地区专家门诊查询","");
            List<Selectable> trs = table.xpath("/table/tbody/tr[3]/td[2]/table/tbody/tr").nodes();
            String city = null;
            for (Selectable selectable : trs){
                if(selectable.xpath("/tr/td[@class=lb_line]").get() != null){
                    continue;
                }
                //判断第一个是否有城市名
                if(!selectable.xpath("/tr/td[1]/text()").regex("\u00A0").match()){
                    //新出现的城市，每个城市的第一行
                    city = selectable.xpath("/tr/td[1]/text()").get();
                    System.out.println(city);

                }

                List<Selectable> tds = selectable.xpath("/tr/td").nodes();
                for (int j = 1; j < tds.size(); j++) {
                    Selectable td = tds.get(j);
                    if(td.xpath("/td/text()").regex("\u00A0").match()){//最后一个空格结尾
                        continue;
                    }

                    Hospital hospital = new Hospital();
                    hospital.setCity(city);
                    hospital.setStatus(0);
                    hospital.setUrl(td.xpath("/td/a/@href").get());
                    hospital.setCreateTime(new Date());
                    hospital.setHosId(UUIDGenerater.randomUUID());
                    hospital.setProvince(province);
                    hospital.setHosName(td.xpath("/td/a/text()").get());
                    System.out.println(hospital);
//                    session.save(hospital);
                }
            }
        }
        

    }
}
