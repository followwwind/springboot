package com.wind.common.util;


import com.wind.common.constant.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 * @Title: StringUtil
 * @Package com.wind.common.util
 * @Description: 字符串工具类
 * @author wind
 * @date 2018/9/17 18:09
 * @version V1.0
 */
public class StringUtil{

    /**
     * 判断空字符串
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str){
        return str != null && !"".equals(str.trim());
    }

    /**
     * 字符串是否为空或空字符串
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }


    /**
     * url编码
     * @param str
     * @return
     */
    public static String encodeUrl(String str){
        String s = null;
        try {
            s = URLEncoder.encode(str, Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return s;
    }

    /**
     * url解码
     * @param str
     * @return
     */
    public static String decodeUrl(String str){
        String s = null;
        try {
            s = URLDecoder.decode(str, Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }


    /**
     * 字符串分隔 StringTokenizer效率是三种分隔方法中最快的
     * @param str
     * @param sign
     * @return
     */
    public static String[] split(String str, String sign){
        if(str == null){
            return new String[]{};
        }
        StringTokenizer token = new StringTokenizer(str,sign);
        String[] strArr = new String[token.countTokens()];
        int i = 0;
        while(token.hasMoreElements()){
            strArr[i] = token.nextElement().toString();
            i++;
        }
        return strArr;
    }


    /**
     * java驼峰命名的类成员字段名
     * @param name 字符串
     * @param flag 首字母小写为false， 大写为true
     * @return
     */
    public static String getCamelCase(String name, boolean flag){
        StringBuilder sb = new StringBuilder();
        if(name != null){
            String[] arr = split(name, Constants.UNDERLINE);
            for(int i = 0; i < arr.length; i++){
                String s = arr[i];
                if(i == 0){
                    if(flag){
                        s = getFirst(s, true);
                    }
                    sb.append(s);
                }else{
                    int len = s.length();
                    if(len == 1){
                        sb.append(s.toUpperCase());
                    }else{
                        sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将单词首字母变大小写
     * @param str
     * @param flag true变大写， false变小写
     * @return
     */
    public static String getFirst(String str, boolean flag){
        StringBuilder sb = new StringBuilder();
        if(str != null && str.length() > 1){
            String first;
            if(flag){
                first = str.substring(0, 1).toUpperCase();
            }else{
                first = str.substring(0, 1).toLowerCase();
            }

            sb.append(first).append(str.substring(1));
        }

        return sb.toString();
    }

    /**
     * 字符串拼接
     * @param sign
     * @param strArr
     * @return
     */
    public static String joinStr(String sign, String... strArr){
        StringBuilder sb = new StringBuilder();
        Optional<String> optional  = Arrays.stream(strArr).filter(Objects::nonNull
        ).reduce((a, b) -> a + sign + b);
        optional.ifPresent(sb::append);
        return sb.toString();
    }



    public static void main(String[] args) {
        System.out.println(StringUtil.joinStr(",", null, "null", "123"));
    }

}
