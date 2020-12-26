package com.murong.ecp.netpay.whefss.web.common.utils;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.BuiOperDO;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 公共方法类
 *
 * @author zhang_wei 2019-09-23
 *
 *
 */
public class CommonUtils {
    public static final String DATE_FOR = "YYYYMMDD HH:MM:SS";

    public static final String CMD_SET_SUPERVISION_TYPE = "101";
    public static final String CMD_CANCEL_SUPERVISION_TYPE = "170";
    public static final String CMD_CHECK_ACCOUNT_TYPE = "120";
    public static final String SUPERVISE_SPECIAL_ACCT = "1";
    public static final String SUPERVISE_GENERAL_ACCT = "0";

    public static final String IP = "128.20.72.100";



    public static String getHostIp(){
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            return ip;
        } catch (UnknownHostException e) {
            return IP;
        }
    }
    public static String getId(){
        //获取2位随机数
        int random=(int) ((Math.random()+1)*100000000);

        return String.valueOf(random);
    }

    public static String getUuid(){
        return Long.toString(System.currentTimeMillis()-1300000000000L);
    }

    public static String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        String format = simpleDateFormat.format(new Date());
        return format;
    }

    public static BuiOperDO getBuiOper(YGBizMessageContext bizCtx){
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        YGEDB session = edb.getChildNode("session");
        BuiOperDO buiOperDO = new BuiOperDO();

        buiOperDO.setOperId(session.getData("oper_id"));
        buiOperDO.setOperNo(session.getData("oper_no"));
        buiOperDO.setOperNm(session.getData("oper_nm"));
        buiOperDO.setOperTyp(session.getData("oper_typ"));
        buiOperDO.setOrgId(session.getData("org_id"));
        buiOperDO.setOrgNm(session.getData("org_nm"));

      return buiOperDO;
    }
}
