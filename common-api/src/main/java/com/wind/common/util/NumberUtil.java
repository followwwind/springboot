package com.wind.common.util;

import java.math.BigDecimal;

/**
 * @Title: NumberUtil
 * @Package com.wind.common.util
 * @Description: 数字计算工具类
 * @author wind
 * @date 2018/9/17 18:08
 * @version V1.0
 */
public class NumberUtil {


    /**
     * 只保留四位小数点，多余的舍弃
     * @param a
     * @param b
     * @return
     */
    public static double countPercent(double a, double b){
        return countPercent(a, b, 4, BigDecimal.ROUND_DOWN);
    }

    /**
     * 计算百分比
     * @param a
     * @param b
     * @return
     */
    public static double countPercent(double a, double b, int newScale, int roundingMode){
        if(b == 0){
            return 0;
        }
        double s = a/b;
        return countPercent(s, newScale, roundingMode);
    }

    /**
     * double舍入计算
     * @param d
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static double countPercent(double d, int newScale, int roundingMode){
        return countPercent(new BigDecimal(d), newScale, roundingMode);
    }

    /**
     * double舍入计算
     * @param bigDecimal
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static double countPercent(BigDecimal bigDecimal, int newScale, int roundingMode){
        if(bigDecimal == null){
            return 0;
        }
        return bigDecimal.setScale(newScale, roundingMode).doubleValue();
    }


    public static void main(String[] args) {
        System.out.println(countPercent(2, 10));
        System.out.println(countPercent(0.2, 4, BigDecimal.ROUND_DOWN));
        System.out.println(countPercent(0.2, 4, BigDecimal.ROUND_UP));
        System.out.println(countPercent(0.2, 4, BigDecimal.ROUND_FLOOR));
        System.out.println(countPercent(0.2, 4, BigDecimal.ROUND_CEILING));
        System.out.println(countPercent(3.55, 1, BigDecimal.ROUND_DOWN));
        System.out.println(countPercent(3.55, 1, BigDecimal.ROUND_UP));
        System.out.println(countPercent(3.55, 1, BigDecimal.ROUND_FLOOR));
        System.out.println(countPercent(3.55, 1, BigDecimal.ROUND_CEILING));
        System.out.println(countPercent(-3.55, 1, BigDecimal.ROUND_DOWN));
        System.out.println(countPercent(-3.55, 1, BigDecimal.ROUND_UP));
        System.out.println(countPercent(-3.55, 1, BigDecimal.ROUND_FLOOR));
        System.out.println(countPercent(-3.55, 1, BigDecimal.ROUND_CEILING));
    }
}
