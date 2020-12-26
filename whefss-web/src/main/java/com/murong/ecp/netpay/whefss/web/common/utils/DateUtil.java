package com.murong.ecp.netpay.whefss.web.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static String getTimestamp(Date date){
        return sdf.format(date);
    }
    public static String getTimestamp(){
        return sdf.format(new Date());
    }
    public static String getDate(Date date){
        return sd.format(date);
    }
    public static String getDate(){
        return sd.format(new Date());
    }

    public static String formatSdfToSdt(String date) throws Exception{
        return sdt.format(sdf.parse(date));

    }

    public static String formatSdtToSdf(String date) throws Exception{
        return sdf.format(sdt.parse(date));
    }

    public static String getDay(String day,int i)throws Exception{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.sd.parse(day));
        calendar.add(Calendar.DAY_OF_MONTH,i);
        Date date = calendar.getTime();
        return DateUtil.getDate(date);
    }

    public static void main(String[] args) throws Exception{
        String time = "2012-04-04T12:12:12";
       System.out.println(DateUtil.formatSdtToSdf(time));
    }


}
