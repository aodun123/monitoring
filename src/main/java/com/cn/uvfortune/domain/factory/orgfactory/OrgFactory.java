package com.cn.uvfortune.domain.factory.orgfactory;

import com.cn.uvfortune.domain.model.org.Org;
import com.cn.uvfortune.domain.repository.orgrepo.IOrgRepository;
import com.cn.uvfortune.domain.repository.orgrepo.QueryOrgRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class OrgFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public OrgFactory() {

    }

    public Org getInstance() {
        Org org = new Org();
        org.setOrgRepository(applicationContext.getBean(IOrgRepository.class));
        org.setQueryOrgRepository(applicationContext.getBean(QueryOrgRepository.class));
        return org;
    }
}
