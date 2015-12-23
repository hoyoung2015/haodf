package net.hoyoung.haodf.utils;

import java.util.UUID;

/**
 * Created by hoyoung on 2015/11/7.
 */
public class UUIDGenerater {
    public static String randomUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
