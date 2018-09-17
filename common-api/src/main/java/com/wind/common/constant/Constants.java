package com.wind.common.constant;

import java.io.File;
import java.math.BigDecimal;

/**
 * @Title: Constants
 * @Package com.wind.common.constant
 * @Description: 常量工具集
 * @author wind
 * @date 2018/9/17 18:06
 * @version V1.0
 */
public interface Constants {

    /**********************************************分隔符常量************************************************/

    String POINT_STR = ".";

    String BLANK_STR = "";

    String COMMA_STR = ",";

    String MINUS_STR = "-";

    String SPACE_STR = " ";

    String SYS_SEPARATOR = File.separator;

    String FILE_SEPARATOR = "/";

    String BRACKET_LEFT = "[";

    String BRACKET_RIGHT = "]";

    String UNDERLINE = "_";

    /**
     * 左括号
     */
    String LEFT_BRACKET = "(";

    /**
     * 右括号
     */
    String RIGHT_BRACKET = ")";


    /**********************************************编码格式************************************************/
    String UTF8 = "UTF-8";

    String GBK = "GBK";

    /**********************************************系统信息************************************************/
    String OS_NAME = System.getProperty("os.name");

    String WIN_DIR = System.getenv("windir");

    String USER_DIR = "user.dir";

    String TOKEN = "X-API-TOKEN";


}


