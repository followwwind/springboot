package com.wind.common.util;

import com.wind.common.constant.Constants;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Title: IdGenUtil
 * @Package com.wind.common.util
 * @Description: id主键生成工具
 * @author wind
 * @date 2018/9/17 18:07
 * @version V1.0
 */
public class IdGenUtil {

    private static AtomicLong next = new AtomicLong(1);

    /**
     * 获取32位的UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace(Constants.MINUS_STR, Constants.BLANK_STR);
    }

    /**
     * 生成一个13位数的唯一id
     * @return
     */
    public static long getPKNum(){
        return next.getAndIncrement() + System.currentTimeMillis();
    }

    /**
     * 生成一个13位数的唯一id
     * @return
     */
    public static String getPKStr(){
        return String.valueOf(getPKNum());
    }

}
