package net.hoyoung.haodf.job;

import net.hoyoung.haodf.spider.DiseaseCatChildSpider;
import net.hoyoung.haodf.spider.DiseaseCatSpider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hoyoung on 2015/11/8.
 */
public class DiseaseCatJob {
    public static void main(String[] args) {
        try {
            Method method = DiseaseCatSpider.class.getMethod("main", String[].class);
            method.invoke(null, (Object) new String[]{});

            method = DiseaseCatChildSpider.class.getMethod("main", String[].class);
            method.invoke(null, (Object) new String[]{});

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
