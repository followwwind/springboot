package com.wind.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 * @Title: ImgUtil
 * @Package com.ancda.palmbaby.hm.common.utils
 * @Description: img工具类
 * @author huanghy
 * @date 2018/9/5 13:47
 * @version V1.0
 */
public class ImgUtil {

    public final static String JPG = "jpg";

    private final static String IMG_BASE64 = "data:image/([a-z]|[A-Z])+;base64,.+";

    private final static String BASE64 = "base64";

    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param imageUrl
     * @return
     */
    public static String encodeBase64(URL imageUrl) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodeBase64(bi, JPG);
    }

    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param imageFile
     * @return
     */
    public static String encodeBase64(File imageFile) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodeBase64(bi, JPG);
    }


    /**
     * 将BufferedImage进行base64位编码
     * @param bi
     * @param formatName
     * @return
     */
    public static String encodeBase64(BufferedImage bi, String formatName){
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            ImageIO.write(bi, formatName != null ? formatName : JPG, os);
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            // 返回Base64编码过的字节数组字符串
            return encoder.encode(os.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 将base64图片解码并保存
     * @param base64
     * @param file
     */
    public static void decodeBase64(String base64, File file) {
        BASE64Decoder decoder = new BASE64Decoder();
        FileOutputStream os = null;
        try {
            if(file != null && base64 != null){
                if(base64.matches(IMG_BASE64)){
                    os = new FileOutputStream(file);
                    int index = base64.indexOf(BASE64);
                    byte[] bytes = decoder.decodeBuffer(base64.substring(index + BASE64.length() + 1));
                    // 调整异常数据
//                    for (int i = 0; i < bytes.length; ++i) {
//                        if (bytes[i] < 0) {
//                            bytes[i] += 256;
//                        }
//                    }
                    os.write(bytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将base64图片编码转换成字节数组
     * @param base64
     * @return
     */
    public static byte[] decodeBase64(String base64){
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = null;
        if(base64 != null){
            if(base64.matches(IMG_BASE64)){
                int index = base64.indexOf(BASE64);
                try {
                    bytes = decoder.decodeBuffer(base64.substring(index + BASE64.length() + 1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("E:/doc/base64.txt"));
            StringBuilder sb = new StringBuilder();
            br.lines().forEach(sb::append);
            decodeBase64(sb.toString(), new File("E:/doc/base64.jpeg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
