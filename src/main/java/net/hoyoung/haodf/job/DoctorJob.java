package net.hoyoung.haodf.job;

import net.hoyoung.haodf.entity.Section;
import net.hoyoung.haodf.spider.DoctorSpider;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/8.
 */
public class DoctorJob {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            Method method = DoctorSpider.class.getMethod("main", String[].class);
            Session session = HibernateUtils.getLocalThreadSession();
            List<Section> sections = session.createCriteria(Section.class)
                    .add(Restrictions.eq("status", 0))
                    .list();
            int count = 1;
            for (Section section : sections){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + count++ + "/" + sections.size());
                method.invoke(null, (Object) new String[]{section.getSecId()});

                session.beginTransaction();
                session.createQuery("update Section set status=1 where secId=?")
                        .setParameter(0,section.getSecId())
                        .executeUpdate();
                session.getTransaction().commit();
//                if(count++==5) break;
//                break;
            }
            session.close();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("doctorJob over. cost " + (System.currentTimeMillis() - start) / 1000 + " seconds");
        System.exit(0);
    }
}
