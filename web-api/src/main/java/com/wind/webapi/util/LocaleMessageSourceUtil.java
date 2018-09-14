package com.wind.webapi.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * 
 * @fileName LocaleMessageSourceUtil.java
 * @package com.ancda.palmbaby.ancda.common.utils
 * @description 国际化消息Util
 * @author yujl@ancda.com
 * @date 2017年6月23日 上午11:07:27
 * @version V1.0
 */
@Component
public class LocaleMessageSourceUtil {
    
    private static MessageSource messageSource;

    public static String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public static String getMessage(String code, Object[] args){
        return getMessage(code, args, "");
    }

    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
    public static String getMessage(String code, Object[] args, String defaultMessage){
        // 这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    @Resource
    public void setMessageSource(MessageSource messageSource) {
        LocaleMessageSourceUtil.messageSource = messageSource;
    }
    
}