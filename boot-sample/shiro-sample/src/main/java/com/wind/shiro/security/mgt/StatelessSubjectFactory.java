package com.wind.shiro.security.mgt;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @Title: StatelessSubjectFactory
 * @Package com.wind.shiro.security.mgt
 * @Description: 无状态Subject工厂
 * @author wind
 * @date 2018/10/17 10:15
 * @version V1.0
 */
public class StatelessSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        // 禁用Session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
