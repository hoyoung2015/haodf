package net.hoyoung.haodf.job;

import net.hoyoung.haodf.spider.DoctorSpider;
import net.hoyoung.haodf.spider.ZixunSpider;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by hoyoung on 2015/11/16.
 */
public class ZixunJob {
    public static void main(String[] args) {
        try {
            List<String> list = FileUtils.readLines(new File(args[0]));
            Method method = ZixunSpider.class.getMethod("main", String[].class);
            for (String docId : list){
                method.invoke(null,(Object) new String[]{docId});
            }
            System.out.println("all is done");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}
