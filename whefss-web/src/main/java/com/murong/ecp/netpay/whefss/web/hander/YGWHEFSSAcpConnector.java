package com.murong.ecp.netpay.whefss.web.hander;


import com.murong.ecp.netpay.whefss.web.common.utils.HttpClient;
import com.murong.ecp.netpay.whefss.web.common.utils.WhefssConstant;
import com.murong.ecp.netpay.whefss.web.common.utils.YGJsonXmlUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.pubinterface.IHandler;
import com.yuangou.ecp.bp.core.common.util.YGByteBuffer;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.constants.YGMessageCode;
import com.yuangou.ecp.bp.core.framework.event.ContainerEvent;
import com.yuangou.ecp.bp.core.framework.event.IContainerDestroyListener;
import com.yuangou.ecp.bp.core.framework.event.IContainerInitListener;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

public class YGWHEFSSAcpConnector implements IHandler, IContainerInitListener, IContainerDestroyListener {

    private String url;
    private int timeOut = 30000;
    private String apikey;
    private String encoding = "utf-8";
    private String contentType;
    private Logger logger;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public void process(YGBizMessageContext ctx) throws YGException {
        YGBizMessage msg = ctx.getCurrentMsg();
        String reqId = msg.getRequestId();
        YGByteBuffer byteBuffer = (YGByteBuffer) msg.getBody();
        Map<String, String> parameters = new HashMap<String, String>();
        logger.info("===请求武汉房管开始===");
        String reqData = byteBuffer.toString();
        Element reqRoot;
        try {
            reqRoot = DocumentHelper.parseText(reqData).getRootElement();
        } catch (DocumentException e) {
            throw new YGException(YGMessageCode.ERR_SEND_FAILURE, "解析XML报文失败", e);
        }
        Element reqHead = reqRoot.element("head");
        if (reqHead == null) {
            throw new YGException(YGMessageCode.ERR_SEND_FAILURE, "head节点不存在");
        }
        JSONObject reqHeadJson = YGJsonXmlUtils.xml2json(reqHead);

        String method = reqHeadJson.get("method").toString();
        if (StringUtils.isBlank(method)) {
            throw new YGException(YGMessageCode.ERR_SEND_FAILURE, "method节点不存在");
        }
        String url = reqHeadJson.get("url").toString();
        if (StringUtils.isBlank(method)) {
            throw new YGException(YGMessageCode.ERR_SEND_FAILURE, "url节点不存在");
        }
        String param = reqHeadJson.get("param").toString();
        if (StringUtils.isBlank(method)) {
            throw new YGException(YGMessageCode.ERR_SEND_FAILURE, "param节点不存在");
        }
        //拼接url
        String reqUrl = this.url + url + this.apikey;
        reqUrl = reqUrl.replace("param", param);
        String rspData = "";
        //调用http通讯
        if(WhefssConstant.HTTP_METHOD_GET.equals(method)){
            //get调用
             rspData = HttpClient.doGet(reqUrl,encoding,contentType,timeOut,timeOut,timeOut);
        }else if(WhefssConstant.HTTP_METHOD_POST.equals(method)){
            Element reqBody = reqRoot.element("body");
            JSONObject reqBodyJson = YGJsonXmlUtils.xml2json(reqBody);
            logger.info("reqBodyJson="+reqBodyJson.toString());
            String data = reqBodyJson.get("data").toString();
            //Map paramMap = new HashMap();
            rspData = HttpClient.doPost(reqUrl,null,data,encoding,contentType,timeOut,timeOut,timeOut);
        }else{
            throw new YGException(YGMessageCode.ERR_SEND_FAILURE, "method有误");
        }

        if(logger.isInfo2Enabled()){
            logger.info(reqId + "接收：["+reqUrl+"]:数据长度：["+rspData.length()+"]");
        }
        Element rspRoot = DocumentHelper.createElement("root");
        JSONObject localJSONObject = JSONObject.fromObject(rspData);
        Element localElement = YGJsonXmlUtils.json2xml(localJSONObject);
        byteBuffer.clear();
        byteBuffer.append(localElement.asXML());
        msg.setHeadItem(YGBizMessage.REQUEST_RESPONSE,YGBizMessage.TYPE_RESPONSE);
        msg.setBody(byteBuffer);
    }

    @Override
    public void containerDestroy(ContainerEvent containerEvent) throws YGException {

    }

    @Override
    public void containerInit(ContainerEvent containerEvent) throws YGException {
        this.logger = containerEvent.getLog();
    }
}
