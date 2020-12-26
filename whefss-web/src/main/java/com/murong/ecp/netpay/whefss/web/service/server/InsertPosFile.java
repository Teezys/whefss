package com.murong.ecp.netpay.whefss.web.service.server;

import com.murong.ecp.netpay.whefss.web.service.server.bo.PosFileAcomaEntity;
import com.murong.ecp.netpay.whefss.web.service.server.bo.PosFileMapsEntity;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * 解析导入POS文件
 **/
public class InsertPosFile {

    //解析Maps文件
    public static void readMaps(InputStream is, List<PosFileMapsEntity> records) throws IOException {

        // 将txt格式的数据存入数组
        try {
            //接受传入的流 改为gbk编码
            InputStreamReader isr = new InputStreamReader(is, "GBK");
            BufferedReader br = new BufferedReader(isr);
        /*    String lineTxt = br.readLine();// 读取文件的方法
            System.out.println(lineTxt);*/
          /*  String[] firstLine = lineTxt.split(""); // 读第一行 按,进行切割
            String time = firstLine[0];
            String ID = firstLine[1];
            String sonid = firstLine[2];
            String workname  = firstLine[11];*/
            String lineTxt = "";

            //验证每一行是否为空  数据为空不执行
            while ((lineTxt = br.readLine()) != null) {
                System.out.println(lineTxt.substring(lineTxt.length() - 2));
                if (!"#&".equals(lineTxt.substring(lineTxt.length() - 2))) {
                    continue;
                }
                String[] arrStrings = lineTxt.split("`"); // 用于把一个字符串分割成字符串数组
                String endStr = arrStrings[arrStrings.length - 1];
                if (endStr.length() != 244 && endStr.length() != 294) {
                    continue;
                }
                PosFileMapsEntity maps = new PosFileMapsEntity();

                maps.setConNo(substringByte(endStr, 7, 18).trim());
                maps.setAccNo(substringByte(endStr, 43, 40).trim());
                maps.setFkqc(substringByte(endStr, 83, 1).trim());
                maps.setStdAmt(changeF2Y(substringByte(endStr, 174, 14).trim()));

                maps.setTranSeq(arrStrings[1].trim());
                maps.setTermId(arrStrings[10].trim());
                maps.setTranDt(arrStrings[2].trim());
                maps.setTranTm(arrStrings[8].trim());
                maps.setPaccNo(arrStrings[19].trim());
                maps.setOrtrnSeq(arrStrings[22].trim());
                records.add(maps);

            }
            //关闭输入流
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //解析Acoma文件
    public static void readAcoma(InputStream is, List<PosFileAcomaEntity> records) throws IOException {

        // 将txt格式的数据存入数组
        try {
            //接受传入的流 改为gbk编码
            InputStreamReader isr = new InputStreamReader(is, "GBK");
            BufferedReader br = new BufferedReader(isr);
            String lineTxt = "";
            //验证每一行是否为空  数据为空不执行
            while ((lineTxt = br.readLine()) != null) {
                String[] keys = lineTxt.split("\t");
                PosFileAcomaEntity acoma = new PosFileAcomaEntity();

                acoma.setTranSeq(substringByte(lineTxt, 25, 6).trim());
                acoma.setPaccNo(substringByte(lineTxt, 43, 19).trim());
                acoma.setTermId(substringByte(lineTxt, 119, 8).trim());
                acoma.setPayAmt(changeF2Y(substringByte(lineTxt, 63, 12).trim()));
                acoma.setPosAmt(changeF2Y(substringByte(lineTxt, 393, 8).trim()));
                acoma.setAmtflag(substringByte(lineTxt, 392, 1));
                acoma.setCzbz(substringByte(lineTxt, 325, 1));
                acoma.setCxbz(substringByte(lineTxt, 327, 1));

                System.out.println(acoma.toString());
                records.add(acoma);

            }
            //关闭输入流
            br.close();


        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
    }


    public static void main(String[] args) {
        // File file = new File("E:\\BEA\\upload\\whef\\MAPS_TRADE_03205210_130911");
        // File file2 = new File("E:\\BEA\\upload\\whef\\IND13091132ACOMA");
      /*  InputStream is;
        try {
            is = new FileInputStream(file);
            readMaps(is);
            //readAcoma(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

    /**
     * 按字节截取字符串 ，指定截取起始字节位置与截取字节长度
     *
     * @param orignal 要截取的字符串
     * @param start   截取开始位置；
     * @return 截取后的字符串
     * @throws UnsupportedEncodingException 使用了JAVA不支持的编码格式
     */
    public static String substringByte(String orignal, int start, int count) {

        // 如果目标字符串为空，则直接返回，不进入截取逻辑；
        if (orignal == null || "".equals(orignal)) {
            return orignal;
        }
        // 截取Byte长度必须>0
        if (count <= 0) {
            return orignal;
        }

        // 截取的起始字节数必须比
        if (start < 0) {
            start = 0;
        }
        // 目标char Pull buff缓存区间；
        StringBuffer buff = new StringBuffer();

        try {
            // 截取字节起始字节位置大于目标String的Byte的length则返回空值
            if (start >= getStringByteLenths(orignal)) {
                return null;
            }
            int len = 0;
            char c;
            // 遍历String的每一个Char字符，计算当前总长度
            // 如果到当前Char的的字节长度大于要截取的字符总长度，则跳出循环返回截取的字符串。
            for (int i = 0; i < orignal.toCharArray().length; i++) {
                c = orignal.charAt(i);

                // 当起始位置为0时候
                if (start == 0) {
                    len += String.valueOf(c).getBytes("GBK").length;
                    if (len <= count) {
                        buff.append(c);
                    } else {
                        break;
                    }
                } else {
                    // 截取字符串从非0位置开始
                    len += String.valueOf(c).getBytes("GBK").length;
                    if (len >= start && len <= start + count - 1) {
                        buff.append(c);
                    }
                    if (len > start + count) {
                        break;
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 返回最终截取的字符结果;
        // 创建String对象，传入目标char Buff对象
        return new String(buff);
    }


    /**
     * 计算当前String字符串所占的总Byte长度
     *
     * @param args 要截取的字符串
     * @return 返回值int型，字符串所占的字节长度，如果args为空或者“”则返回0
     * @throws UnsupportedEncodingException
     */
    public static int getStringByteLenths(String args)
            throws UnsupportedEncodingException {
        return args != null && args != "" ? args.getBytes("GBK").length : 0;
    }


    /**
     * 元转分，确保price保留两位有效数字
     *
     * @return
     */
    public static int changeY2F(double price) {
        DecimalFormat df = new DecimalFormat("#.00");
        price = Double.valueOf(df.format(price));
        int money = (int) (price * 100);
        return money;
    }

    /**
     * 分转元，转换为bigDecimal在toString
     *
     * @return
     */
    public static String changeF2Y(String priceStr) {
        if(StringUtils.isBlank(priceStr)){
            return "";
        }
        int price = Integer.parseInt(priceStr);
        return BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
    }

    public static String changeToDubAmtString(String price){
        Double cny = Double.parseDouble(price);//转换成Double
        DecimalFormat df = new DecimalFormat("0.00");//格式化
        return df.format(cny);
    }



}
