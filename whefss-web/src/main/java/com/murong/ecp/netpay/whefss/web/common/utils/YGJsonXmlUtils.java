package com.murong.ecp.netpay.whefss.web.common.utils;

import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.util.DocumentHelper;
import com.yuangou.ecp.bp.core.common.util.YGByteBuffer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;

import java.util.Iterator;

public class YGJsonXmlUtils {

    public void xml2json(YGBizMessageContext paramHiMessageContext, Logger logger) throws YGException {
        YGBizMessage localHiMessage = paramHiMessageContext.getCurrentMsg();
        YGByteBuffer localHiByteBuffer = (YGByteBuffer) localHiMessage.getBody();

        if (logger.isInfoEnabled()) {
            logger.info("msgid:[" + localHiMessage.getRequestId() + "], convert before:[" + localHiByteBuffer + "]");
        }

        JSONObject localJSONObject = xml2json(localHiByteBuffer.toString());
        localHiByteBuffer.clear();
        localHiByteBuffer.append(localJSONObject.toString());
        if (logger.isInfoEnabled()) {
            logger.info("msgid:[" + localHiMessage.getRequestId() + "], convert after:[" + localHiByteBuffer + "]");
        }

        localHiMessage.setBody(localHiByteBuffer);
    }

    public static JSONObject xml2json(String paramString) throws YGException {
        try {
            Element localElement = DocumentHelper.parseText(paramString).getRootElement();

            return xml2json(localElement);
        } catch (DocumentException localDocumentException) {
            throw new YGException("215012", paramString, localDocumentException);
        }
    }

    public static JSONObject xml2json(Element paramElement) {
        JSONObject localJSONObject = new JSONObject();
        return xml2json(paramElement, localJSONObject);
    }

    public static JSONObject xml2json(Element paramElement, JSONObject paramJSONObject) {
        Iterator localIterator1 = paramElement.elementIterator();
        while (localIterator1.hasNext()) {
            Element localElement = (Element) localIterator1.next();
            Iterator localIterator2 = localElement.elementIterator();
            String str1 = localElement.getName();
            if (localIterator2.hasNext()) {
                String str2 = paramElement.elementText(str1 + "_NUM");

                if (StringUtils.isNotBlank(str2)) {
                    Object localObject = paramJSONObject.get(str1);
                    JSONArray localJSONArray = null;
                    if (localObject == null) {
                        localJSONArray = new JSONArray();
                    } else {
                        localJSONArray = (JSONArray) localObject;
                    }
                    JSONObject localJSONObject = new JSONObject();
                    xml2json(localElement, localJSONObject);
                    localJSONArray.add(localJSONObject);
                    paramJSONObject.put(str1, localJSONArray);
                } else {
                    Object localObject = new JSONObject();
                    xml2json(localElement, (JSONObject) localObject);
                    paramJSONObject.put(str1, localObject);
                }
            } else {
                paramJSONObject.put(str1, localElement.getText());
            }
        }
        return paramJSONObject;
    }

    public void json2xml(YGBizMessageContext paramHiMessageContext, Logger logger) throws YGException {
        YGBizMessage localHiMessage = paramHiMessageContext.getCurrentMsg();
        YGByteBuffer localHiByteBuffer = (YGByteBuffer) localHiMessage.getBody();

        if (logger.isInfoEnabled()) {
            logger.info("msgid:[" + localHiMessage.getRequestId() + "], convert before:[" + localHiByteBuffer + "]");
        }

        JSONObject localJSONObject = JSONObject.fromObject(localHiByteBuffer.toString());

        Element localElement = json2xml(localJSONObject);
        localHiByteBuffer.clear();
        localHiByteBuffer.append(localElement.asXML());
        if (logger.isInfoEnabled()) {
            logger.info("msgid:[" + localHiMessage.getRequestId() + "], convert after:[" + localHiByteBuffer + "]");
        }

        localHiMessage.setBody(localHiByteBuffer);
    }

    public static Element json2xml(JSONObject paramJSONObject) {
        Element localElement = DocumentHelper.createElement("Root");
        return json2xml(localElement, paramJSONObject);
    }

    public static Element json2xml(Element paramElement, JSONObject paramJSONObject) {
        Iterator localIterator = paramJSONObject.keys();
        while (localIterator.hasNext()) {
            String str = (String) localIterator.next();
            Object localObject = paramJSONObject.get(str);
            if ((localObject instanceof JSONObject)) {
                Element localElement = paramElement.addElement(str);
                json2xml(localElement, (JSONObject) localObject);
            } else if ((localObject instanceof JSONArray)) {
                json2xml(paramElement, str, (JSONArray) localObject);
            } else {
                paramElement.addElement(str).setText(localObject.toString());
            }
        }
        return paramElement;
    }

    public static Element json2xml(Element paramElement, String paramString, JSONArray paramJSONArray) {
        paramElement.addElement(paramString + "_NUM").setText(String.valueOf(paramJSONArray.size()));

        for (int i = 0; i < paramJSONArray.size(); i++) {
            Object localObject = paramJSONArray.get(i);
            if ((localObject instanceof JSONObject)) {
                Element localElement = paramElement.addElement(paramString);
                json2xml(localElement, (JSONObject) localObject);
            } else {
                throw new IllegalArgumentException("invalid json:[" + paramJSONArray.toString() + "]");
            }

        }

        return paramElement;
    }

    public static void main(String[] paramArrayOfString) {
        JSONObject localJSONObject = JSONObject.fromObject("{'header':{'A':'测试'},'data':{'A':'测试', 'REC':[{'B':'2','C':'3'},{'B':'3','C':'4'}]}}");

        Element localElement = json2xml(localJSONObject);
        System.out.println(localElement.asXML());
        System.out.println(xml2json(localElement));
    }
}
