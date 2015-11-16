package net.hoyoung.webmagic.scheduler;

import net.hoyoung.haodf.entity.Zixun;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ZixunWendaScheduler implements Scheduler {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private BlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();
    private AtomicLong totalLast = new AtomicLong();
    long start;
    public ZixunWendaScheduler() {
        start = System.currentTimeMillis();
        Session session = HibernateUtils.getLocalThreadSession();
        Long count = (Long) session.createQuery("select count(*) from Zixun where status=0").uniqueResult();
        totalLast.addAndGet(count);
        session.clear();
    }

    @Override
    public void push(Request request, Task task) {
        logger.info("add new request:"+request.toString());
        requests.add(request);
    }

    @Override
    public Request poll(Task task) {
        if(requests.isEmpty()){
            Session session = HibernateUtils.getLocalThreadSession();
            List<Zixun> list = session.createQuery("from Zixun where status=0")
                    .setMaxResults(200)
                    .list();
            session.close();

            if(list.isEmpty()){
                return null;
            }
            for (Zixun zixun:list){
                Request request = new Request(zixun.getUrl());
                request.putExtra("zixun",zixun);
                requests.add(request);
            }
        }
        Request req = requests.poll();
        logger.info(time((System.currentTimeMillis() - start) / 1000) + " has passed,last " + totalLast.decrementAndGet());
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
