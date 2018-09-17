package com.wind.common.util;

import java.io.*;

/**
 * @Title: SerializeUtil
 * @Package com.wind.common.util
 * @Description: 序列化工具类
 * @author wind
 * @date 2018/9/17 18:09
 * @version V1.0
 */
public class SerializeUtil {
    
	/**
	 * 序列化对象
	 * @param object
	 * @return
	 * @throws IOException 
	 */
	public static byte[] serialize(Object object) throws IOException {
		ObjectOutputStream oos = null;
		
		if (object != null){
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(bos);
				oos.writeObject(object);
				oos.flush();
				return bos.toByteArray();
			} finally {
				IoUtil.close(oos);
			}
		}
		
		return null;
	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = null;
		
		if (bytes != null && bytes.length > 0){
			try {
				ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				ois = new ObjectInputStream(bis);
				return ois.readObject();
			} finally {
				IoUtil.close(ois);
			}
		}
		
		return null;
	}
	
}
