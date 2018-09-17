package com.wind.webapi.validation.validator;


import com.wind.webapi.validation.annotation.BlankOrPattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Pattern.Flag;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @Title: BlankOrPatternValidator
 * @Package com.wind.webapi.validation.validator
 * @Description: 选填注解验证器，不为空时必须满足正则表达式
 * @author wind
 * @date 2018/9/17 18:02
 * @version V1.0
 */
public class BlankOrPatternValidator implements ConstraintValidator<BlankOrPattern, String> {

    private Pattern pattern;

    @Override
    public void initialize(BlankOrPattern parameters) {
        Flag[] flags = parameters.flags();
        int intFlag = 0;
        for (Flag flag : flags) {
            intFlag = intFlag | flag.getValue();
        }

        try {
            pattern = Pattern.compile(parameters.regexp(), intFlag);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Invalid regular expression.", e);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.length() == 0) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}