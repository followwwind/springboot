package com.wind.common.util;


import com.wind.common.constant.Constants;
import java.io.*;
import java.util.*;

/**
 * @Title: PropUtil
 * @Package com.wind.common.util
 * @Description: 解析properties文件工具类
 * @author wind
 * @date 2018/9/17 18:08
 * @version V1.0
 */
public class PropUtil {
    /**
     * 解析properties文件，用map存储
     * @param filePath
     * @return
     */
    public static Properties readProp(String filePath){
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    /**
     * 读取properties的全部信息
     * @param filePath
     * @return
     * @desc 文件名称必须放在资源文件名目录下
     */
    public static Map<String,String> getProperties(String filePath){
        Map<String,String> map = new HashMap<>();
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(PropUtil.class.getClassLoader().getResourceAsStream(filePath));
            props.load(in);
            Enumeration<?> enums = props.propertyNames();
            while(enums.hasMoreElements()){
                String key = (String)enums.nextElement();
                String value = props.getProperty(key);
                map.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 写入properties信息
     * @param filepath
     * @param map
     */
    public static void writeProp(String filepath, Map<String,String>map){
        Properties props = new Properties();
        try {
            OutputStream fos = new FileOutputStream(filepath);
            Set<Map.Entry<String, String>> entrys = map.entrySet();
            for(Map.Entry<String, String> entry : entrys){
                String key = entry.getKey();
                String value = entry.getValue();
                props.setProperty(key, value);
            }
            props.store(fos, Constants.BLANK_STR);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // 获取系统信息
        Properties prop = System.getProperties();
        prop.list(System.out);
    }
}
