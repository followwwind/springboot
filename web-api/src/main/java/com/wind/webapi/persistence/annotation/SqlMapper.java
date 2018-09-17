package com.wind.webapi.persistence.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Title: SqlMapper
 * @Package com.wind.webapi.persistence.annotation
 * @Description: Mybatis Mapper 注解
 * @author wind
 * @date 2018/9/17 17:43
 * @version V1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
@Component
public @interface SqlMapper {
}
