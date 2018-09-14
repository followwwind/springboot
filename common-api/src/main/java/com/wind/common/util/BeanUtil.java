package com.wind.common.util;

import org.springframework.cglib.beans.BeanCopier;

/**
 * @fileName BeanUtil
 * @package com.ancda.palmbaby.ancda.common.mappers
 * @description 字节码java bean拷贝
 * @author huanghy
 * @date 2018-05-17 10:56:13
 * @version V1.0
 */
public class BeanUtil {

    /**
     * 对象拷贝复制
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target){
        if(source != null && target != null){
            BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            copier.copy(source, target, null);
        }
    }
}
