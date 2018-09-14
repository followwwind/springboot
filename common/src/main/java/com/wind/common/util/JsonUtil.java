package com.wind.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @fileName JsonUtil
 * @package com.ancda.palmbaby.ancda.common.utils
 * @description json工具类
 * @author huanghy
 * @date 2018-05-03 15:39:05
 * @version V1.0
 */
public class JsonUtil{

    private static ObjectMapper mapper;

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static ObjectMapper getInstance(){
        return getInstance(null);
    }

    public static ObjectMapper getInstance(JsonInclude.Include include) {
        if (mapper == null){
            mapper = new ObjectMapper();

            //设置输出时包含属性的风格
            if(include != null){
                mapper.setSerializationInclusion(include);
            }
            //生成json字符串不加双引号
//            mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
            // 反序列化允许单引号、允许不带引号的字段名称
            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            // 空值处理为空串
            mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
                @Override
                public void serialize(Object value, JsonGenerator jsonGenerator,
                                      SerializerProvider provider) throws IOException {
                    jsonGenerator.writeString("");
                }
            });
            // 设置时区 getTimeZone("GMT+8:00")
            mapper.setTimeZone(TimeZone.getDefault());
        }
        return mapper;
    }

    /**
     * json字符串转list数组
     * @param jsonStr
     * @return
     */
    public static <T> List<T> toList(String jsonStr, TypeReference<List<T>> typeReference){
        try {
            if(jsonStr != null){
                return getInstance().readValue(jsonStr, typeReference);
            }
        } catch (IOException e) {
            logger.warn("json list to bean:" + jsonStr, e);
        }
        return new ArrayList<>();
    }


    /**
     * json字符串转bean对象，bean对象必须要有默认构造器，私有的也可以
     * @param jsonStr
     * @param <T>
     * @return
     */
    public static <T> T toBean(String jsonStr, Class<T> c){
        try {
            if(jsonStr != null){
                return getInstance().readValue(jsonStr, c);
            }
        } catch (IOException e) {
            logger.warn("json string to bean error:" + jsonStr, e);
        }
        return null;
    }

    /**
     * 对象序列化json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj){
        String result = null;
        try {
            result = getInstance().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.warn("obj to json string error:" + obj, e);
        }

        return result;
    }
}
