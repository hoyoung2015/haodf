package net.hoyoung.haodf.parse;

import net.hoyoung.haodf.entity.ZixunWenda;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/28.
 */
public class JiSuanHuiFuJianGe {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Session session = HibernateUtils.getLocalThreadSession();

//        List<String> list = session.createSQLQuery("SELECT DISTINCT(zixun_id) from zixun_wenda where zixun_id='ahszlyymajie_g_2677857801'")
        List<String> list = session.createSQLQuery("SELECT DISTINCT(zixun_id) from zixun_wenda")
                .list();
        Iterator<String> ite = list.iterator();
        int count = 1;
        while (ite.hasNext()){
            System.out.println(count++);
            String zixunId = ite.next();
            List<ZixunWenda> zixunWendas = session.createQuery("select new ZixunWenda(zwId, zixunId, publishTime, docId, patId) from ZixunWenda where zixunId=? order by publishTime asc")
                    .setParameter(0, zixunId)
                    .list();
            ZixunWenda patZixun = null;
            session.beginTransaction();
            for (ZixunWenda zixunWenda:zixunWendas){
//                System.out.println(zixunWenda.toString());
                if(zixunWenda.getPatId()!=null){//病人
                    patZixun = zixunWenda;
                }else {//医生
                    int replyInter = -1;
                    if(patZixun!=null){
                        //计算时间间隔
                        long t1 = patZixun.getPublishTime().getTime()/1000;
                        long t2 = zixunWenda.getPublishTime().getTime()/1000;
                        replyInter = (int) (t2-t1);
                    }
                    session.createQuery("update ZixunWenda set replyInter=? where zwId=?")
                            .setParameter(0,replyInter)
                            .setParameter(1, zixunWenda.getZwId())
                            .executeUpdate();
                }
            }
            ite.remove();
            session.getTransaction().commit();
        }
        session.close();

        System.out.println("用了"+(System.currentTimeMillis()-start)/1000+"秒");
    }
}
