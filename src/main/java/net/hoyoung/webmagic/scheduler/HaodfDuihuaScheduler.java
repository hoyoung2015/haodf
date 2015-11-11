package net.hoyoung.webmagic.scheduler;

import net.hoyoung.haodf.entity.Wenda;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by hoyoung on 2015/11/11.
 */
public class HaodfDuihuaScheduler implements Scheduler {
    BlockingQueue<Wenda> wendaBlockingQueue;
    int queueSize = 500;
    public HaodfDuihuaScheduler() {
        wendaBlockingQueue = new LinkedBlockingQueue<Wenda>();

    }

    public void push(Request request, Task task) {
        wendaBlockingQueue.add((Wenda) request.getExtra("wenda"));
    }

    public Request poll(Task task) {
        if(wendaBlockingQueue.isEmpty()){
            Session session = HibernateUtils.getLocalThreadSession();
            List list = session.createCriteria(Wenda.class)
                    .add(Restrictions.eq("status", 0))
                    .setMaxResults(queueSize)
                    .list();
            HibernateUtils.closeSession();
            if(list.isEmpty()){
                return null;//退出，都爬取完了
            }
            wendaBlockingQueue.addAll(list);
        }
        Wenda wenda = wendaBlockingQueue.poll();
        Request req = new Request(wenda.getUrl());
        req.putExtra("wenda",wenda);
        return req;
    }
}
