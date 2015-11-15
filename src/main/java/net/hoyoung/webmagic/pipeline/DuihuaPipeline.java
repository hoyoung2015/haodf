package net.hoyoung.webmagic.pipeline;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import net.hoyoung.haodf.entity.Doctor;
import net.hoyoung.haodf.entity.Duihua;
import net.hoyoung.haodf.entity.Wenda;
import net.hoyoung.haodf.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by hoyoung on 2015/11/11.
 */
public class DuihuaPipeline implements Pipeline {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void process(ResultItems resultItems, Task task) {
        Wenda wenda = resultItems.get("wenda");
        Doctor doctor = resultItems.get("doctor");

        List<Duihua> duihuas = resultItems.get("duihuas");

        Session session = HibernateUtils.getLocalThreadSession();

        session.getTransaction().begin();
        wenda.setStatus(1);
        session.update(wenda);//更新问答
        //插入医生记录，可能有重复
        session.saveOrUpdate(doctor);

        //插入对话内容
        session.createQuery("delete from Duihua where wen_id=?")
                .setParameter(0,wenda.getWenId())
                .executeUpdate();
        for (Duihua duihua:duihuas){
            session.save(duihua);
        }
            session.getTransaction().commit();//这里可能抛出语法错误，导致事务没有提交
            HibernateUtils.closeSession();
        System.exit(0);
    }
}
