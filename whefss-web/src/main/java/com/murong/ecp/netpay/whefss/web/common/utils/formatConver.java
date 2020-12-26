package com.murong.ecp.netpay.whefss.web.common.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @ Author     ：li_jc
 * @ Date       ：Created in 16:28 2020/5/17
 * @ Description：时间格式转换
 * @ Modified By：
 * @Version: 1.0$
 */

public class formatConver {
    public static String convertDtl(String str){
        String str1 =str.substring(0,4);
        String str2 =str.substring(4,6);
        String str3 =str.substring(6,8);
        String str4 =str.substring(8,10);
        String str5 =str.substring(10,12);
        String str6 =str.substring(12,14);
        return str1+"-"+str2+"-"+str3+" "+str4+":"+str5+":"+str6;
    }
    public static String convert(String str){
        String str1 =str.substring(0,4);
        String str2 =str.substring(4,6);
        String str3 =str.substring(6,8);
        return str1+"-"+str2+"-"+str3;
    }

    public static void main(String[] args) {
        String str = convert("20200302143456");
        String str1 = convertDtl("20200302143456");
        System.out.println(str+"  "+str1);
    }

    public static String addBlack(String str, int length) throws UnsupportedEncodingException {
        if(str==null){
            str=" ";
        }
        int len = str.getBytes("UTF-8").length;
        if(len<length){
            int lenComp = length-len;
            for(int i=0;i<lenComp;i++){
                str = str+" ";
            }
        }
        return str;
    }

    //创建文件存放路径
    public static String creDirect(String dir,String taskDir,String recDate) {
        String separator = File.separator;
        String str1 = recDate.substring(0,4);
        String str2 = recDate.substring(4,6);
        String str3 = recDate.substring(6,8);
        int i = dir.length();
        String s = dir.substring(i-1);
        if("/".equals(s)|| "\\".equals(s)){
            return dir.replace("/", separator).replace("\\", separator)+taskDir+separator+str1+separator+str2+separator+str3;
        }else {
            return dir.replace("/", separator).replace("\\", separator)+separator+taskDir+separator+str1+separator+str2+separator+str3;
        }
    }
    

    //创建文件名
    public static String creFileName(String prefix,String date,String svsAcNo) {
        return prefix+date+svsAcNo+".dat";
    }

    //创建文件的全目录
    public static String creFilePath(String dir,String filename) {
        String separator = File.separator;
        int i = dir.length();
        String s = dir.substring(i-1);
        if("/".equals(s)|| "\\".equals(s)){
            return dir.replace("/", separator).replace("\\", separator)+filename;
        }else {
            return dir.replace("/", separator).replace("\\", separator)+separator+filename;
        }
    }

    //创建业务校验码 固定是6位的随机数
    public static String creBusChkCode() {
        String s = String.valueOf(((int) ((Math.random() * 9 + 1) * 100000)));
        return s;
    }
}
