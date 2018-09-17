package com.wind.common.util;

import de.ailis.pherialize.Mixed;
import de.ailis.pherialize.Pherialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * @Title: PhpUtil
 * @Package com.wind.common.util
 * @Description: php序列化
 * @author wind
 * @date 2018/9/17 18:08
 * @version V1.0
 */
public class PhpUtil {

    private static Logger logger = LoggerFactory.getLogger(PhpUtil.class);

    /**
     * 反序列化
     * @param str
     * @return
     */
    public static String deserialize(String str){
        if(str == null || "".equals(str.trim())){
            return "";
        }
        return Pherialize.unserialize(str).toString();
    }

    /**
     * 反序列化list
     * @param str
     * @return
     */
    public static List<String> deserializeList(String str){
        List<String> list = new ArrayList<>();
        try {
            Mixed mixed = Pherialize.unserialize(str);
            if(mixed != null && mixed.isArray()){
                mixed.toArray().values().forEach(val -> list.add(val != null ? val.toString() : null));
            }
        } catch (Exception e) {
            logger.error("Unable to unserialize unknown type 1", e);
        }
        return list;
    }

    /**
     * 序列化
     * @param obj
     * @return
     */
    public static String serialize(Object obj){
        return Pherialize.serialize(obj);
    }

    public static void main(String[] args) {
        String str = "a:2:{i:5;s:6:\"rdtest\";i:6;s:6:\"01202a\";}";
//        String str = "1";
        System.out.println(deserializeList(str));
        List<String> list = new ArrayList<>();
        list.add("9:30");
        list.add("12:30");
        System.out.println(serialize(list));
    }
}
