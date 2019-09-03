package com.cn.uvfortune.config.security.util;

import com.cn.uvfortune.domain.factory.orgfactory.UserAgencyFactory;
import com.cn.uvfortune.domain.factory.sysfactory.ResourcesPageFactory;
import com.cn.uvfortune.domain.factory.sysfactory.RolePermissionFactory;
import com.cn.uvfortune.domain.factory.sysfactory.UserFactory;
import com.cn.uvfortune.domain.model.org.OrgUser;
import com.cn.uvfortune.domain.model.org.UserAgency;
import com.cn.uvfortune.domain.model.security.ResourcesPage;
import com.cn.uvfortune.domain.model.security.RolePermission;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import com.cn.uvfortune.infrastructure.repository.entity.SysUserAgency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xiao
 * @date 8/3/18
 */
@Component("mySecurityUtil")
public class MySecurityUtil {


    @Autowired
    ResourcesPageFactory resourcesPageFactory;

    @Autowired
    UserFactory userFactory;

    @Autowired
    RolePermissionFactory rolePermissionFactory;

    @Autowired
    UserAgencyFactory userAgencyFactory;

    /**
     * get all Acl Resources
     *
     * @return
     */
    public List<SysPage> getAclResourceList() {
        ResourcesPage pageInstance = resourcesPageFactory.getPageInstance();
        return pageInstance.findAllPage();
    }

    /*public List<Sys_Resources_Operation> getAlloperationList() {
        ResourcesPage pageInstance = resourcesPageFactory.getPageInstance();
        return pageInstance.findAllOperation();
    }*/


    public SysUser getSysUserByName(String username) {
        OrgUser userInstance = userFactory.getUserInstance();
        SysUser sysUser = userInstance.queryUser(username);
        if (sysUser == null) {
            return null;
        }
        UserAgency userAgency = userAgencyFactory.getInstance();

        List<SysUserAgency> sysAgency = userAgency.getSysAgency(sysUser.getId());
        String agencyid = "";
        for (int i = 0; i < sysAgency.size(); i++) {
            Integer sysAgencyId = sysAgency.get(i).getSysAgencyId();
            agencyid += sysAgencyId + ",";
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("sysAgency_id", agencyid);
        session.setAttribute("user_id", sysUser.getId());
        return sysUser;
    }

    public List<SysPage> getPagesById(int userId) {
        RolePermission roleInstance = rolePermissionFactory.getRoleInstance();
        return roleInstance.queryPagesById(userId);
    }

    /*public List<Sys_Resources_Operation> getOperationById(int userid){
        RolePermission roleInstance = rolePermissionFactory.getRoleInstance();
        return roleInstance.queryOperationById(userid);
    }*/


    public void loadUserDetails() {

    }
}
