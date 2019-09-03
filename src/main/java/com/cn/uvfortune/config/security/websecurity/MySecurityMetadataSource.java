package com.cn.uvfortune.config.security.websecurity;

import com.cn.uvfortune.config.security.util.MySecurityUtil;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: hancunyan
 * @Date: 2018/7/31 19:10
 * @Description:
 */

@Component("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);

    private Map<String, Collection<ConfigAttribute>> aclResourceMap = new HashMap<>();

    /**
     * 构造方法
     */
    public MySecurityMetadataSource() {
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Iterator<String> ite = aclResourceMap.keySet().iterator();
        String resURL;
        while (ite.hasNext()) {
            resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(request)) {
                return aclResourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


    @Autowired
    @Qualifier("mySecurityUtil")
    public MySecurityUtil securityUtil;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("security: afterPropertiesSet init MySecurityMetadataSource");
        loadResourceDefine();
    }

    /**
     * 的因为只有权限控制的资源才需要被拦截验证,所以只加载有权限控制资源
     */
    private void loadResourceDefine() {

        // get all permission resources
        List<SysPage> aclResourceList = securityUtil.getAclResourceList();
//        List<Sys_Resources_Operation> alloperationList = securityUtil.getAlloperationList();

        //轮询所有获取的资源
        /*for (Sys_Resources_Operation opeantion : alloperationList) {
            ConfigAttribute ca = new SecurityConfig(opeantion.getBtnName().toUpperCase());
            String url = opeantion.getBtnCode();
            //加载
            if (aclResourceMap.containsKey(url)) {
                Collection<ConfigAttribute> value = aclResourceMap.get(url);
                value.add(ca);
                aclResourceMap.put(url, value);
            } else {
                Collection<ConfigAttribute> atts = new ArrayList<>();
                atts.add(ca);
                aclResourceMap.put(url, atts);
            }
        }*/


        for (SysPage alcResource : aclResourceList) {
            ConfigAttribute ca = new SecurityConfig(alcResource.getName().toUpperCase());
            String url = alcResource.getUrl();
            //加载
            if (aclResourceMap.containsKey(url)) {
                Collection<ConfigAttribute> value = aclResourceMap.get(url);
                value.add(ca);
                aclResourceMap.put(url, value);
            } else {
                Collection<ConfigAttribute> atts = new ArrayList<>();
                atts.add(ca);
                aclResourceMap.put(url, atts);
            }
        }
    }

}


