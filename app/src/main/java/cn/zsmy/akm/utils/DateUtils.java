package cn.zsmy.akm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qinwei on 2015/12/9 10:13
 */
public class DateUtils {
    public static final int TYPE_YMD = 0;
    public static final int TYPE_Y_M_D = 2;
    public static final int TYPE_Y_M_D_H_M = 3;
    public static final int TYPE_YMDHM = 4;
    public static final int TYPE_HM = 1;
    public static final int TYPE_Y = -1;
    public static final int TYPE_YYMD = 5;

    /**
     * 获取系统时间 格式为:yyyy/MM/dd
     **/
    public static String getCurrentDate() {
        Date d = new Date();
        SimpleDateFormat sf = getSimpleDateFormat();
        return sf.format(d);
    }

    /**
     * 获取系统时间戳
     **/
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time, int type) {
        Date d = new Date(time);
        SimpleDateFormat sf = null;
        switch (type) {
            case TYPE_YMD:
                sf = getSimpleDateFormat();
                break;
            case TYPE_HM:
                sf = getSimpleDateFormatHM();
                break;
            case TYPE_Y_M_D:
                sf = getDateFormat();
                break;
            case TYPE_Y_M_D_H_M:
                sf = getDateFormatY_M_DHM();
                break;
            case TYPE_Y:
                sf = getYearFormat();
                break;
            case TYPE_YMDHM:
                sf = getDateFormatYMDHM();
                break;
            case TYPE_YYMD:
                sf = getSimpleYMD();
        }

        return sf.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("yyyy年MM月dd日");
    }

    public static SimpleDateFormat getYearFormat() {
        return new SimpleDateFormat("yyyy");
    }

    public static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static SimpleDateFormat getDateFormatY_M_DHM() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public static SimpleDateFormat getDateFormatYMDHM() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm");
    }

    public static SimpleDateFormat getSimpleDateFormatHM() {
        return new SimpleDateFormat("HH:mm");
    }

    public static SimpleDateFormat getSimpleYMD() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }

    public static String getAge(long beforeTime) {
        int before = Integer.parseInt(getDateToString(beforeTime, -1));
        int cur = Integer.parseInt(getDateToString(getCurrentTime(), -1));
        int age = cur - before;
//            long affterTime=getCurrentTime();
//          Log.i("TAG",affterTime+"");
//          long age=affterTime-beforeTime;
//          Log.i("TAG",age+"");
//          long m=365*24*60*60*1000;
//          Log.i("TAG",m+"");
//          int a=(int)(age/m);
//          Log.i("TAG",a+"");


        return String.valueOf(age);
    }
}
