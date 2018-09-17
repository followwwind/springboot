package com.wind.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: RegexUtil
 * @Package com.wind.common.util
 * @Description: 正则表达式工具类
 * @author wind
 * @date 2018/9/17 18:09
 * @version V1.0
 */
public class RegexUtil {

    private final static String PHONE_NUMBER = "^(13[0-9]|14[579]|15[0-3,5-9]|16[0-9]|17[0135678]|18[0-9]|19[89])\\d{8}$";

    private final static String HK_PHONE_NUMBER = "^(5|6|8|9)\\d{7}$";

    private final static String POSITIVE_INTEGER = "^\\d+$";

    private final static String MONEY = "^(\\d+(?:\\.\\d+)?)$";

    private final static String IMG_BASE64 = "data:image/([a-z]|[A-Z])+;base64,.+";

    /**
     *
     * @param regex
     * @param str
     * @return
     */
    private static boolean checkStr(String regex, String str){
        if(regex == null || str == null){
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     *
     * @param str
     * @return
     */
    public static boolean checkPhone(String str){
        return checkStr(PHONE_NUMBER, str);
    }

    /**
     * 校验香港
     * @param str
     * @return
     */
    public static boolean checkHKPhone(String str){
        return checkStr(HK_PHONE_NUMBER, str);
    }

    /**
     * 校验正整数
     * @param str
     * @return
     */
    public static boolean checkPlusInteger(String str){
        return checkStr(POSITIVE_INTEGER, str);
    }

    /**
     * 校验是否是图片base64编码
     * @param str
     * @return
     */
    public static boolean checkImgBase64(String str){
        return checkStr(IMG_BASE64, str);
    }

    /**
     * 校验money
     * @param str
     * @return
     */
    public static boolean checkMoney(String str){
        return checkStr(MONEY, str);
    }

    public static void main(String[] args) {
        System.out.println(checkPhone("18771933979"));
        System.out.println(checkMoney("3.00"));
        System.out.println(checkImgBase64("data:image/jpeg;base64,/"));
    }
}
