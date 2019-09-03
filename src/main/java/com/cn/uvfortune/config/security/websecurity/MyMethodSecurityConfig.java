package com.cn.uvfortune.config.security.websecurity;

import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * 此处可配置方法的验证
 *
 * @author xiao
 * @date 8/3/18
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return super.createExpressionHandler();
    }

}
