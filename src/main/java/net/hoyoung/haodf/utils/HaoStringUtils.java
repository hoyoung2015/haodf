package net.hoyoung.haodf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hoyoung on 2015/11/7.
 */
public class HaoStringUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat simpleDateFormatMin = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static String getHost(String url){

        if(url==null||url.trim().equals("")){
            return "";
        }
        String host = "";
        Pattern p =  Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
        Matcher matcher = p.matcher(url);
        if(matcher.find()){
            host = matcher.group();
        }
        return host;
    }
    public static final String trim(String s){
        if (s!=null) return s.trim();
        return s;
    }
    public static final String replaceAndTrim(String source,String old,String now){
        if(source==null) return source;
        return  source.replace(old,now).trim();
    }
    public static final int parseInt(String input){
        if(input!=null) return Integer.valueOf(input);
        return 0;
    }
    public static final String capitalHead(String s){
        if(s==null) return s;
        return s.substring(0,1).toUpperCase()+s.substring(1,s.length());
    }

    public static void main(String[] args) {
        System.out.println(HaoStringUtils.capitalHead("name"));
    }

    public static Date parseDateMin(String input){
        if(input!=null){
            try {
                return simpleDateFormatMin.parse(input);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    public static Date parseDate(String input){
        if(input!=null){
            try {
                return simpleDateFormat.parse(input);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
