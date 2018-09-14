package com.wind.webapi.validator;


import com.wind.webapi.exception.IllegalParameterException;
import com.wind.webapi.util.LocaleMessageSourceUtil;
import com.baidu.unbiz.fluentvalidator.DefaultValidateCallback;
import com.baidu.unbiz.fluentvalidator.ValidateCallback;
import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.validator.element.ValidatorElementList;

import java.util.List;

/**
 * 
 * @fileName CustomValidateCallback.java
 * @package com.ancda.palmbaby.ancda.callback
 * @description 参数检验回调类
 * @author yujl@ancda.com
 * @date 2017年6月23日 下午1:07:50
 * @version V1.0
 */
public class CustomValidateCallback extends DefaultValidateCallback implements ValidateCallback {

    @Override
    public void onFail(ValidatorElementList validatorElementList, List<ValidationError> errors) {
        throw new IllegalParameterException(LocaleMessageSourceUtil.getMessage(errors.get(0).getErrorMsg()));
    }

    @Override
    public void onSuccess(ValidatorElementList validatorElementList) {
    }

    @Override
    public void onUncaughtException(Validator validator, Exception e, Object target) throws Exception {
        throw e;
    }

}
