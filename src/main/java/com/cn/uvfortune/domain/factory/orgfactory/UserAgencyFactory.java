package com.cn.uvfortune.domain.factory.orgfactory;

import com.cn.uvfortune.domain.model.org.UserAgency;
import com.cn.uvfortune.domain.repository.orgrepo.IUserAgencyRepository;
import com.cn.uvfortune.domain.repository.orgrepo.QueryUserAgencyRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class UserAgencyFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public UserAgencyFactory() {

    }

    public UserAgency getInstance() {
        UserAgency userAgency = new UserAgency();
        userAgency.setUserAgencyRepository(applicationContext.getBean(IUserAgencyRepository.class));
        userAgency.setQueryUserAgencyRepository(applicationContext.getBean(QueryUserAgencyRepository.class));
        return userAgency;
    }
}
