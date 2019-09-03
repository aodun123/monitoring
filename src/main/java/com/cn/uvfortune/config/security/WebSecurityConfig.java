package com.cn.uvfortune.config.security;

import com.cn.uvfortune.config.security.websecurity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hancunyan
 * @Date: 2018/7/31 19:06
 * @Description: Web Security Config
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public MySecurityMetadataSource mySecurityMetadataSource;

    @Autowired
    MyUserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 自定义UserDetailsService 与 加密方法
        auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        // 开启默认登录页面
        httpSecurity.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setSecurityMetadataSource(mySecurityMetadataSource);
                        fsi.setAccessDecisionManager(accessDecisionManager());
                        fsi.setAuthenticationManager(authenticationManagerBean());
                        // 如果没有设置权限的话，默认不拦截!
                        fsi.setRejectPublicInvocations(false);
                        return fsi;
                    }
                })
                .and()
                .formLogin()
//                .defaultSuccessUrl("/index")
                .loginPage("/login")
                .failureUrl("/login?error")
                .successHandler(new MyLoginSuccessHangler())
                .and()
                .headers().frameOptions().disable()
                .and()
                .logout()
                .logoutUrl("/logout")
//                .logoutSuccessHandler(new MyLogoutHandler())
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll();

        // 自定义accessDecisionManager访问控制器,并开启表达式语言
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .expressionHandler(webSecurityExpressionHandler());


        // 自定义登录页面
        httpSecurity.csrf().disable();
        // session管理
//        httpSecurity
//                .sessionManagement()
//                .invalidSessionUrl("/session/invalid")//session失效跳转的链接
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(false)//false之后登录踢掉之前登录,true则不允许之后登录
//                .expiredSessionStrategy(new MerryyounExpiredSessionStrategy());//登录被踢掉时的自定义操作

//        http.addFilterAfter(MyUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * login 表单提交验证处理
     *
     * @return
     */
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
        accessDeniedHandler.setErrorPage("/securityException/accessDenied");
        return accessDeniedHandler;
    }

    /**
     * 这里可以增加自定义的投票器
     */
    @Bean(name = "accessDecisionManager")
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList();
        decisionVoters.add(new RoleVoter());
        decisionVoters.add(new AuthenticatedVoter());
        // 启用表达式投票器
        decisionVoters.add(webExpressionVoter());
        MyAccessDecisionManager accessDecisionManager = new MyAccessDecisionManager(decisionVoters);

        return accessDecisionManager;
    }

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() {
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager = super.authenticationManagerBean();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager;
    }


    /**
     * 表达式控制器
     */
    @Bean(name = "expressionHandler")
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        return webSecurityExpressionHandler;
    }

    /**
     * 表达式投票器
     */
    @Bean(name = "expressionVoter")
    public WebExpressionVoter webExpressionVoter() {
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());
        return webExpressionVoter;
    }


    /////////////// test used /////////////
//    @Bean
//    public LoggerListener loggerListener() {
//        System.out.println("org.springframework.security.authentication.event.LoggerListener");
//        return new LoggerListener();
//    }
//
//    @Bean
//    public org.springframework.security.access.event.LoggerListener eventLoggerListener() {
//        System.out.println("org.springframework.security.access.event.LoggerListener");
//        return new org.springframework.security.access.event.LoggerListener();
//    }

}

