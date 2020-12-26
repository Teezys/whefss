package com.murong.ecp.netpay.whefss.web.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.message.YGEDBFactory;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.BeanUtils;

public class WhEDBUtil {
    public WhEDBUtil() {
    }

    public static void edb2obj(YGEDB var0, Map var1) {
        edb2obj(var0, (String)null, (Map)var1);
    }

    public static void edb2obj(YGEDB var0, String var1, Map var2) {
        if (var2 != null && var0 != null) {
            YGEDB var3;
            if (var1 != null) {
                var3 = var0.getChildNode(var1);
                if (var3 == null) {
                    return;
                }
            } else {
                var3 = var0;
            }

            String var4 = null;
            int var5 = 0;
            ArrayList var6 = new ArrayList();
            int var7 = 0;

            for(YGEDB var8 = var3.getFirstChild(); var8 != null; var8 = var8.getNext()) {
                String var9 = var8.getName();
                String var10 = var8.getValue();
                if (var9.endsWith("_num")) {
                    var4 = var9.substring(0, var9.indexOf("_num"));
                    var5 = Integer.parseInt(var8.getValue());
                }

                HashMap var11;
                if (var4 != null && var9.matches(var4 + "_[1-9]+$")) {
                    ++var7;
                    var11 = new HashMap();
                    edb2obj(var8, (String)null, (Map)var11);
                    var6.add(var11);
                    var11 = null;
                } else if (var10 != null && !"".equals(var10)) {
                    var2.put(var9, var10);
                } else if (!var8.isEndNode()) {
                    var11 = new HashMap();
                    edb2obj(var3, var9, (Map)var11);
                    var2.put(var9, var11);
                } else {
                    var2.put(var9, var10);
                }

                if (var5 != 0 && var5 == var7) {
                    var2.put(var4, var6);
                    var4 = null;
                    var6 = new ArrayList();
                    var5 = 0;
                    var7 = 0;
                }
            }

        }
    }

    public static void edb2obj(YGEDB var0, Object var1) {
        edb2obj(var0, (String)null, (Object)var1);
    }

    public static void edb2obj(YGEDB var0, String var1, Object var2) {
        if (var2 != null && var0 != null) {
            YGEDB var3 = var0;
            if (var1 != null) {
                var3 = var0.getChildNode(var1);
                if (var3 == null) {
                    return;
                }
            }

            PropertyDescriptor[] var4 = BeanUtils.getPropertyDescriptors(var2.getClass());
            PropertyDescriptor[] var5 = var4;
            int var6 = var4.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                PropertyDescriptor var8 = var5[var7];
                String var9 = var8.getName();
                if (!"class".equals(var9) && var8.getReadMethod() != null && var8.getWriteMethod() != null) {
                    Class var11 = var8.getPropertyType();
                    if (!var11.isEnum()) {
                        try {
                            if (!var11.isAssignableFrom(List.class)) {
                                YGEDB var21;
                                if (var11.isAssignableFrom(Map.class)) {
                                    var21 = var3.getChildNode(var9);
                                    if (var21 != null) {
                                        HashMap var23 = new HashMap();
                                        edb2obj(var21, (Map)var23);
                                        PropertyUtils.setProperty(var2, var9, var23);
                                    }
                                } else {
                                    String var10;
                                    if (var11.isAssignableFrom(YGAmt.class)) {
                                        var10 = var3.getChildValue(var9);
                                        if (var10 != null) {
                                            PropertyUtils.setProperty(var2, var9, new YGAmt(var10));
                                        }
                                    } else if (var11.isAssignableFrom(BigDecimal.class)) {
                                        var10 = var3.getChildValue(var9);
                                        if (var10 != null) {
                                            PropertyUtils.setProperty(var2, var9, new BigDecimal(var10));
                                        }
                                    } else if (var11.isAssignableFrom(String.class)) {
                                        var10 = var3.getChildValue(var9);
                                        if (var10 != null) {
                                            PropertyUtils.setProperty(var2, var9, var10);
                                        }
                                    } else if (!var11.isAssignableFrom(Long.TYPE) && !var11.isAssignableFrom(Long.class)) {
                                        if (!var11.isAssignableFrom(Integer.TYPE) && !var11.isAssignableFrom(Integer.class)) {
                                            var21 = var3.getChildNode(var9);
                                            if (var21 != null) {
                                                Object var22 = var11.newInstance();
                                                edb2obj(var21, var22);
                                                PropertyUtils.setProperty(var2, var9, var22);
                                            }
                                        } else {
                                            var10 = var3.getChildValue(var9);
                                            if (var10 != null) {
                                                PropertyUtils.setProperty(var2, var9, Integer.parseInt(var10));
                                            }
                                        }
                                    } else {
                                        var10 = var3.getChildValue(var9);
                                        if (var10 != null) {
                                            PropertyUtils.setProperty(var2, var9, Long.valueOf(var10));
                                        }
                                    }
                                }
                            } else {
                                Field var12 = var11.getDeclaredField(var9);
                                ArrayList var13 = new ArrayList();
                                Type var14 = var12.getGenericType();
                                ParameterizedType var15 = (ParameterizedType)var14;
                                Class var16 = (Class)var15.getActualTypeArguments()[0];
                                int var17 = 0;

                                while(true) {
                                    YGEDB var18 = var3.getChildNode(var9 + "_" + (var17 + 1));
                                    if (var18 == null) {
                                        PropertyUtils.setProperty(var2, var9, var13);
                                        break;
                                    }

                                    Object var19 = var16.newInstance();
                                    edb2obj(var18, var19);
                                    var13.add(var19);
                                    ++var17;
                                }
                            }
                        } catch (Exception var20) {
                            throw new IllegalArgumentException(var20);
                        }
                    }
                }
            }

        }
    }

    public static List<Object> edb2obj(YGEDB var0, String var1, Class var2) throws YGException {
        ArrayList var3 = new ArrayList();
        int var4 = 1;

        while(true) {
            YGEDB var5 = var0.getChildNode(var1 + "_" + var4);
            if (var5 == null) {
                return var3;
            }

            Object var6;
            try {
                var6 = var2.newInstance();
            } catch (Exception var8) {
                throw new YGException(var8);
            }

            if (var6 instanceof Map) {
                edb2obj(var5, (Map)var6);
            } else {
                edb2obj(var5, var6);
            }

            var3.add(var6);
            ++var4;
        }
    }

    public static void obj2edb(YGEDB var0, String var1, List var2) {
        if (var2 != null && var0 != null) {
            for(int var3 = 0; var3 < var2.size(); ++var3) {
                Object var4 = var2.get(var3);
                YGEDB var5 = var0.getChildNode(var1 + "_" + (var3 + 1));
                if (var5 == null) {
                    var5 = var0.addNode(var1 + "_" + (var3 + 1));
                }

                if (var4 instanceof Map) {
                    obj2edb(var4, var5);
                } else {
                    obj2edb(var4, var5);
                }
            }

            var0.setChildValue(var1 + "_num", String.valueOf(var2.size()));
        }
    }

    public static void deleteArrayNode(YGEDB var0, String var1) {
        int var2 = 1;

        while(true) {
            YGEDB var3 = var0.getChildNode(var1 + "_" + var2);
            if (var3 == null) {
                return;
            }

            var0.removeChildNode(var3);
            ++var2;
        }
    }

    public static YGEDB json2edb(JSONObject var0) {
        YGEDB var1 = YGEDBFactory.createEDB();
        return json2edb(var0, var1);
    }

    public static YGEDB json2edb(JSONArray var0, String var1) {
        YGEDB var2 = YGEDBFactory.createEDB();
        return json2edb(var0, var2, var1);
    }

    public static YGEDB json2edb(JSONArray var0, YGEDB var1, String var2) {
        for(int var3 = 0; var3 < var0.size(); ++var3) {
            JSONObject var4 = var0.getJSONObject(var3);
            YGEDB var5 = var1.getArrayChildNode(var2, var3 + 1);
            if (var5 == null) {
                var5 = var1.addArrayNode(var2, var3 + 1);
            }

            json2edb(var4, var5);
        }

        var1.setData(var2 + "_num", String.valueOf(var0.size()));
        return var1;
    }

    public static YGEDB json2edb(JSONObject var0, YGEDB var1) {
        Iterator var2 = var0.keySet().iterator();

        while(var2.hasNext()) {
            String var3 = (String)var2.next();
            Object var4 = var0.get(var3);
            if (var4 != null) {
                if (var4 instanceof JSONObject) {
                    YGEDB var5 = var1.getChildNode(var3);
                    if (var5 == null) {
                        var5 = var1.addNode(var3);
                    }

                    json2edb((JSONObject)var4, var5);
                } else if (var4 instanceof JSONArray) {
                    json2edb((JSONArray)var4, var1, var3);
                } else {
                    var1.setData(var3, var4.toString());
                }
            }
        }

        return var1;
    }

    public static JSONObject edb2json(YGEDB var0) {
        JSONObject var1 = new JSONObject();
        List var2 = var0.getChildNodes();

        for(int var3 = 0; var3 < var2.size(); ++var3) {
            edb2json((YGEDB)var2.get(var3), var1);
        }

        return var1;
    }

    public static JSONObject edb2json(YGEDB var0, JSONObject var1) {
        List var2 = var0.getChildNodes();
        String var3;
        if (var2.size() == 0) {
            var3 = var0.getValue();
            if ("null".equals(var3)) {
                var1.put(var0.getName(), "");
            } else {
                var1.put(var0.getName(), var3);
            }

            return var1;
        } else {
            var3 = var0.getName();
            int var4 = var3.lastIndexOf(95);
            JSONObject var5 = new JSONObject();
            if (var4 != -1 && NumberUtils.isDigits(var3.substring(var4 + 1))) {
                var3 = var3.substring(0, var4);
                JSONArray var9 = (JSONArray)var1.get(var3);
                if (var9 == null) {
                    var9 = new JSONArray();
                    var1.put(var3, var9);
                }

                for(int var10 = 0; var10 < var2.size(); ++var10) {
                    YGEDB var8 = (YGEDB)var2.get(var10);
                    edb2json(var8, var5);
                }

                var9 = (JSONArray)var1.get(var3);
                var9.add(var5);
            } else {
                for(int var6 = 0; var6 < var2.size(); ++var6) {
                    YGEDB var7 = (YGEDB)var2.get(var6);
                    edb2json(var7, var5);
                }

                var1.put(var3, var5);
            }

            return var1;
        }
    }

    public static Object edb2obj(YGEDB var0, Class var1) {
        JSONObject var2 = edb2json(var0);
        return JSON.parseObject(var2.toString(), var1);
    }

    public static List edb2list(YGEDB var0, String var1, Class var2) {
        ArrayList var3 = new ArrayList();
        int var4 = 1;

        while(true) {
            YGEDB var5 = var0.getChildNode(var1 + "_" + var4);
            if (var5 == null) {
                return var3;
            }

            JSONObject var6 = edb2json(var5);
            var3.add(JSON.parseObject(var6.toString(), var2));
            ++var4;
        }
    }

    public static YGEDB obj2edb(Object var0) {
        return json2edb((JSONObject)JSONObject.toJSON(var0));
    }

    public static YGEDB list2edb(Object var0, String var1) {
        return json2edb((JSONArray)JSONObject.toJSON(var0), var1);
    }

    public static YGEDB obj2edb(Object var0, YGEDB var1) {
        return json2edb((JSONObject)JSONObject.toJSON(var0), var1);
    }

    public static YGEDB obj2edb(Object var0, YGEDB var1, String var2) {
        YGEDB var3 = var1.getChildNode(var2);
        if (var3 == null) {
            var3 = var1.addNode(var2);
        }

        return json2edb((JSONObject)JSONObject.toJSON(var0), var3);
    }

    public static YGEDB pageList2edb(List var0, YGPageEntity var1, YGEDB var2) {
        return pageList2edb(var0, var1, var2, "rec");
    }

    public static YGEDB pageList2edb(List var0, YGPageEntity var1, YGEDB var2, String var3) {
        json2edb((JSONArray)JSONObject.toJSON(var0), var2, var3);
        var2.setChildValue("rec_num", String.valueOf(var1.getRec_num()));
        if (var1.getPag_cnt() != -1) {
            var2.setChildValue("pag_cnt", String.valueOf(var1.getPag_cnt()));
        }

        var2.setChildValue("pag_no", String.valueOf(var1.getPag_no()));
        if (var1.getTot_rec_num() != -1) {
            var2.setChildValue("tot_rec_num", String.valueOf(var1.getTot_rec_num()));
        }

        return var2;
    }

    public static void main(String[] var0) throws YGException {
        String var1 = "<root><tot_num>52</tot_num><cd_num>2</cd_num><cd_1><org_jrn_no>01300020914102451303</org_jrn_no><org_tx_dt>20160914</org_tx_dt></cd_1><cd_2><org_jrn_no>01300020914102451303</org_jrn_no><org_tx_dt>20160914</org_tx_dt></cd_2></root>";
        YGEDB var2 = YGEDBFactory.createETF(var1);
        HashMap var3 = new HashMap();
        edb2obj(var2, (Map)var3);
        System.out.println(var3);
    }

    public static String convertName1(String var0) {
        if (var0 == null) {
            return var0;
        } else {
            StringBuffer var1 = new StringBuffer();
            boolean var2 = false;

            for(int var3 = 0; var3 < var0.length(); ++var3) {
                char var4 = var0.charAt(var3);
                if (var4 == '_') {
                    var2 = true;
                } else {
                    if (var2) {
                        var2 = false;
                        var4 = Character.toUpperCase(var4);
                    }

                    var1.append(var4);
                }
            }

            return var1.toString();
        }
    }

    public static String toLetterName(String var0) {
        if (var0 == null) {
            return var0;
        } else {
            StringBuffer var1 = new StringBuffer();

            for(int var2 = 0; var2 < var0.length(); ++var2) {
                char var3 = var0.charAt(var2);
                if (Character.isUpperCase(var3)) {
                    var1.append("_");
                    var1.append(Character.toLowerCase(var3));
                } else {
                    var1.append(var3);
                }
            }

            String var4 = var1.toString();
            if (!StringUtils.isEmpty(var4) && var4.startsWith("_")) {
                var4 = var4.substring(1);
            }

            return var4;
        }
    }

    public static String toCamelCaseName(String var0) {
        if (var0 == null) {
            return var0;
        } else {
            StringBuffer var1 = new StringBuffer();
            boolean var2 = false;

            for(int var3 = 0; var3 < var0.length(); ++var3) {
                char var4 = var0.charAt(var3);
                if (var0.charAt(var3) == '_') {
                    var2 = true;
                } else if (var2) {
                    var1.append(Character.toUpperCase(var4));
                    var2 = false;
                } else {
                    var1.append(var4);
                }
            }

            return var1.toString();
        }
    }

    public static void edb2objNam(YGEDB var0, Object var1) throws YGException {
        try {
            doEdb2objNam(var0, var1);
        } catch (Exception var3) {
            throw YGException.makeException(var3);
        }
    }

    public static void doEdb2objNam(YGEDB var0, Object var1) throws Exception {
        PropertyDescriptor[] var2 = BeanUtils.getPropertyDescriptors(var1.getClass());
        PropertyDescriptor[] var3 = var2;
        int var4 = var2.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            PropertyDescriptor var6 = var3[var5];
            String var7 = var6.getName();
            String var8 = toLetterName(var7);
            String var9 = "";
            if (var6.getReadMethod() != null && var6.getWriteMethod() != null) {
                Class var10 = var6.getPropertyType();
                if (!var10.isEnum()) {
                    if (!var10.isAssignableFrom(List.class)) {
                        YGEDB var19;
                        if (var10.isAssignableFrom(Map.class)) {
                            var19 = var0.getChildNode(var8);
                            if (var19 != null) {
                                HashMap var21 = new HashMap();
                                edb2mapNam(var19, var21);
                                PropertyUtils.setProperty(var1, var7, var21);
                            }
                        } else if (var10.isAssignableFrom(YGAmt.class)) {
                            var9 = var0.getChildValue(var8);
                            if (!StringUtils.isEmpty(var9)) {
                                PropertyUtils.setProperty(var1, var7, new YGAmt(var9));
                            }
                        } else if (var10.isAssignableFrom(BigDecimal.class)) {
                            var9 = var0.getChildValue(var8);
                            if (!StringUtils.isEmpty(var9)) {
                                PropertyUtils.setProperty(var1, var7, new BigDecimal(var9));
                            }
                        } else if (var10.isAssignableFrom(String.class)) {
                            var9 = var0.getChildValue(var8);
                            if (var9 != null) {
                                PropertyUtils.setProperty(var1, var7, var9);
                            }
                        } else if (!var10.isAssignableFrom(Long.TYPE) && !var10.isAssignableFrom(Long.class)) {
                            if (!var10.isAssignableFrom(Integer.TYPE) && !var10.isAssignableFrom(Integer.class)) {
                                var19 = var0.getChildNode(var8);
                                if (var19 != null) {
                                    Object var20 = var10.newInstance();
                                    edb2objNam(var19, var20);
                                    PropertyUtils.setProperty(var1, var7, var20);
                                }
                            } else {
                                var9 = var0.getChildValue(var8);
                                if (var9 != null) {
                                    PropertyUtils.setProperty(var1, var7, Integer.parseInt(var9));
                                }
                            }
                        } else {
                            var9 = var0.getChildValue(var8);
                            if (var9 != null) {
                                PropertyUtils.setProperty(var1, var7, Long.valueOf(var9));
                            }
                        }
                    } else {
                        Field var11 = var1.getClass().getDeclaredField(var7);
                        ArrayList var12 = new ArrayList();
                        Type var13 = var11.getGenericType();
                        ParameterizedType var14 = (ParameterizedType)var13;
                        Class var15 = (Class)var14.getActualTypeArguments()[0];
                        int var16 = 0;

                        while(true) {
                            YGEDB var17 = var0.getChildNode(var8 + "_" + (var16 + 1));
                            if (var17 == null) {
                                PropertyUtils.setProperty(var1, var7, var12);
                                break;
                            }

                            Object var18 = var15.newInstance();
                            edb2objNam(var17, var18);
                            var12.add(var18);
                            ++var16;
                        }
                    }
                }
            }
        }

    }

    public static void edb2mapNam(YGEDB var0, Map var1) {
        for(YGEDB var2 = var0.getFirstChild(); var2 != null; var2 = var2.getNext()) {
            var1.put(var2.getName(), var2.getValue());
        }

    }

    public static void obj2edbNam(Object var0, YGEDB var1) throws YGException {
        try {
            doObj2edbNam(var0, var1);
        } catch (Exception var3) {
            throw YGException.makeException(var3);
        }
    }

    public static void doObj2edbNam(Object var0, YGEDB var1) throws YGException {
        PropertyDescriptor[] var2 = PropertyUtils.getPropertyDescriptors(var0.getClass());
        PropertyDescriptor[] var3 = var2;
        int var4 = var2.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            PropertyDescriptor var6 = var3[var5];
            String var7 = var6.getName();
            String var8 = "";
            if (var6.getReadMethod() != null && var6.getWriteMethod() != null) {
                String var9 = toLetterName(var7);
                Object var10 = null;

                try {
                    var10 = PropertyUtils.getProperty(var0, var7);
                } catch (Exception var12) {
                    throw new YGException("211007", "获取对象[" + var0.getClass().getSimpleName() + "]属性[" + var7 + "]失败", var12);
                }

                if (var10 != null && !var10.getClass().isEnum()) {
                    if (var10 instanceof List) {
                        list2edbNam((List)var10, var9, var1);
                    } else {
                        YGEDB var11;
                        if (var10 instanceof Map) {
                            var11 = var1.addNode(var9);
                            map2edbNam((Map)var10, var11, true);
                        } else if (var10 instanceof YGAmt) {
                            var8 = var10.toString();
                            var1.setChildValue(var9, var8);
                        } else if (var10 instanceof BigDecimal) {
                            var8 = var10.toString();
                            var1.setChildValue(var9, var8);
                        } else if (var10 instanceof String) {
                            var8 = var10.toString();
                            var1.setChildValue(var9, var8);
                        } else if (var10 instanceof Boolean) {
                            var8 = var10.toString();
                            var1.setChildValue(var9, var8);
                        } else if (var10 instanceof Long) {
                            var8 = var10.toString();
                            var1.setChildValue(var9, var8);
                        } else if (var10 instanceof Integer) {
                            var8 = var10.toString();
                            var1.setChildValue(var9, var8);
                        } else {
                            var11 = var1.getChildNode(var9);
                            if (var11 == null) {
                                var11 = var1.addNode(var9);
                            }

                            doObj2edbNam(var10, var11);
                        }
                    }
                }
            }
        }

    }

    public static void map2edbNam(Map var0, YGEDB var1, boolean var2) throws YGException {
        Iterator var3 = var0.keySet().iterator();

        while(var3.hasNext()) {
            String var4 = (String)var3.next();
            String var5 = (String)var0.get(var4);
            if (var5 != null) {
                if (var2) {
                    var4 = toCamelCaseName(var4);
                }

                var1.setChildValue(var4, var5);
            }
        }

    }

    public static void list2edbNam(List var0, String var1, YGEDB var2) throws YGException {
        for(int var3 = 0; var3 < var0.size(); ++var3) {
            YGEDB var4 = var2.addArrayNode(var1, var3 + 1);
            Object var5 = var0.get(var3);
            if (var5 instanceof Map) {
                map2edbNam((Map)var5, var4, false);
            } else {
                doObj2edbNam(var5, var4);
            }
        }
        if(var0.size()==0){
            var2.setChildValue(var1 , "[]");
        }

        //var2.setChildValue(var1 + "_num", String.valueOf(var0.size()));
    }

    public static void map2Obj(Map var0, Object var1) throws YGException {
        if (var1 instanceof Map) {
            ((Map)var1).putAll(var0);
        } else {
            PropertyDescriptor[] var2 = PropertyUtils.getPropertyDescriptors(var1.getClass());
            PropertyDescriptor[] var3 = var2;
            int var4 = var2.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                PropertyDescriptor var6 = var3[var5];
                String var7 = var6.getName();
                String var8 = (String)var0.get(var7);
                if (var8 != null && var6.getReadMethod() != null && var6.getWriteMethod() != null) {
                    Class var9 = var6.getPropertyType();

                    try {
                        if (!var9.isEnum()) {
                            if (var9.isAssignableFrom(YGAmt.class)) {
                                if (StringUtils.isNotBlank(var8)) {
                                    PropertyUtils.setProperty(var1, var7, new YGAmt(var8));
                                }
                            } else if (var9.isAssignableFrom(BigDecimal.class)) {
                                if (StringUtils.isNotBlank(var8)) {
                                    PropertyUtils.setProperty(var1, var7, new BigDecimal(var8));
                                }
                            } else if (var9.isAssignableFrom(String.class)) {
                                PropertyUtils.setProperty(var1, var7, var8);
                            } else if (!var9.isAssignableFrom(Long.TYPE) && !var9.isAssignableFrom(Long.class)) {
                                if (!var9.isAssignableFrom(Integer.TYPE) && !var9.isAssignableFrom(Integer.class)) {
                                    PropertyUtils.setProperty(var1, var7, var8);
                                } else {
                                    PropertyUtils.setProperty(var1, var7, NumberUtils.toInt(var8));
                                }
                            } else {
                                PropertyUtils.setProperty(var1, var7, NumberUtils.toLong(var8));
                            }
                        }
                    } catch (Exception var11) {
                        throw new YGException(var11);
                    }
                }
            }

        }
    }
}
