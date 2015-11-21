package net.hoyoung.webmagic.scheduler;

import net.hoyoung.haodf.entity.Doctor;
import net.hoyoung.haodf.entity.Wenda;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.Scheduler;

import javax.print.Doc;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hoyoung on 2015/11/11.
 */
public class DoctorWebScheduler implements Scheduler {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    BlockingQueue<Doctor> wendaBlockingQueue;
    int queueSize = 500;
    AtomicLong total = new AtomicLong();
    long start;
    public DoctorWebScheduler() {
        wendaBlockingQueue = new LinkedBlockingQueue<Doctor>();
        Session session = HibernateUtils.getLocalThreadSession();

        Long count = (Long) session.createQuery("select count(*) from Doctor where status=0").uniqueResult();
        total.addAndGet(count);
        session.close();
        start = System.currentTimeMillis();
    }

    public void push(Request request, Task task) {
        wendaBlockingQueue.add((Doctor) request.getExtra("wenda"));
        total.addAndGet(1);
    }

    public Request poll(Task task) {
        if(wendaBlockingQueue.isEmpty()){
            Session session = HibernateUtils.getLocalThreadSession();
            List list = session.createCriteria(Doctor.class)
                    .add(Restrictions.eq("status", 0))
                    .setMaxResults(queueSize)
                    .list();
            session.close();
            if(list.isEmpty()){
                return null;//退出，都爬取完了
            }
            wendaBlockingQueue.addAll(list);
        }
        Doctor doctor = wendaBlockingQueue.poll();
        Request req = new Request(doctor.getHomeUrl());
        req.putExtra("doctor",doctor);
        logger.info(time((System.currentTimeMillis() - start) / 1000) + " has passed,last " + total.decrementAndGet());
        return req;
    }

    private String time(long seconds){
        long t;
        StringBuffer sb = new StringBuffer();
        t = seconds/60/60;
        if(t>0){
            sb.append(t+" hour ");
            seconds = seconds%(3600);
        }
        t = seconds/60;
        if(t>0){
            sb.append(t+" minutes ");
            seconds = seconds%60;
        }
        sb.append(seconds+" seconds");
        return sb.toString();
    }
}
