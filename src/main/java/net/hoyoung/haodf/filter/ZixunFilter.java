package net.hoyoung.haodf.filter;

import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/18.
 */
public class ZixunFilter {
    public static void main(String[] args) {
        Session session = HibernateUtils.getLocalThreadSession();

        List<String> docIds = session.createSQLQuery("select doc_id from zixun_origin group by doc_id")
                .list();
        System.out.println(docIds.size());
        session.beginTransaction();
        session.createSQLQuery("DELETE FROM zixun WHERE 1=1").executeUpdate();
//        session.getTransaction().commit();
//        session.beginTransaction();
        for (String docId : docIds){
            BigInteger total = (BigInteger) session.createSQLQuery("SELECT COUNT(zixun_id) FROM zixun_origin WHERE doc_id=?")
                    .setParameter(0, docId)
                    .uniqueResult();

            int limit = (int) Math.ceil(total.intValue()*0.15f);

            session.createSQLQuery("INSERT INTO zixun SELECT * FROM zixun_origin WHERE doc_id=? ORDER BY RAND() limit ?")
                    .setParameter(0,docId)
                    .setParameter(1,limit)
                    .executeUpdate();
            System.out.println(docId+"\t"+limit+"/"+total+"\tsuccess");
        }
        session.getTransaction().commit();
        session.close();
    }
}
