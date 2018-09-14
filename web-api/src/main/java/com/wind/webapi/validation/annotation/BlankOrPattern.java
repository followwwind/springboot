package com.wind.webapi.validation.annotation;

import com.wind.webapi.validation.validator.BlankOrPatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern.Flag;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 
 * @fileName BlankOrPattern.java
 * @package com.ancda.palmbaby.ancda.common.validation.annotation
 * @description 选填注解，不为空时必须满足正则表达式
 * @author yujl@ancda.com
 * @date 2017年6月23日 下午1:05:57
 * @version V1.0
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = BlankOrPatternValidator.class)
public @interface BlankOrPattern {
    String regexp();
    Flag[] flags() default {};
    String message() default "{javax.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}