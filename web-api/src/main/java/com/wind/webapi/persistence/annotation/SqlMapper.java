package com.wind.webapi.persistence.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 
 * @fileName SqlMapper.java
 * @package com.ancda.palmbaby.ancda.common.persistence.annotation
 * @description Mybatis Mapper 注解
 * @author yujl@ancda.com
 * @date 2017年6月23日 上午11:09:22
 * @version V1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
@Component
public @interface SqlMapper {
}
