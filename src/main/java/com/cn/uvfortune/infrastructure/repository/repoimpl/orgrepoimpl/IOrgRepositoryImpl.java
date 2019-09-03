package com.cn.uvfortune.infrastructure.repository.repoimpl.orgrepoimpl;

import com.cn.uvfortune.domain.model.org.Org;
import com.cn.uvfortune.domain.model.org.OrgAgency;
import com.cn.uvfortune.domain.repository.orgrepo.IOrgRepository;
import com.cn.uvfortune.infrastructure.repository.mapper.orgmapper.OrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiaojixiang
 * @version: v1.0
 * @description: 组织仓储实现
 * @date:18-7-5
 */
@Component
public class IOrgRepositoryImpl implements IOrgRepository {

    @Autowired
    OrgMapper orgMapper;

    /**
     * 添加组织
     *
     * @param obj
     * @return id
     */
    @Override
    public int add(Org obj) {
        return orgMapper.insertOrgInfo(obj);
    }

    /**
     * 删除组织
     *
     * @param id
     * @return id
     */
    @Override
    public int delete(Object id) {
        String str = id + "";
        List list = new ArrayList();
        if (str != "null" && str != "" && str != null) {
            if (str.indexOf("、") > 0) {
                String[] split = str.split("、");
                for (int i = 0; i < split.length; i++) {
                    String ids = split[i];
                    Integer uid = Integer.valueOf(ids);
                    list.add(uid);
                }
            } else {
                list.add(str);
            }
            return orgMapper.deleteOrgInfo(list);
        }
        return 0;
    }

    /**
     * 修改组织
     *
     * @param obj
     * @return id
     */
    @Override
    public int update(Org obj) {
        return orgMapper.updateOrgInfo(obj);
    }

    /**
     * 删除某机构单元
     *
     * @param id
     */
    @Override
    public int deleteOrgAgency(String id) {
        List list = new ArrayList();
        if (id.indexOf("、") > 0) {
            String[] split = id.split("、");
            for (int i = 0; i < split.length; i++) {
                String ids = split[i];
                Integer uid = Integer.valueOf(ids);
                list.add(uid);
            }
        } else {
            list.add(id);
        }
        return orgMapper.deleteOrgAgencyInfo(list);
    }

    /**
     * 删除某组织下的机构单元
     *
     * @param orgId
     */
    @Override
    public int deleteOrgAgencyByOrgId(String orgId) {
        List list = new ArrayList();
        if (orgId.indexOf("、") > 0) {
            String[] split = orgId.split("、");
            for (int i = 0; i < split.length; i++) {
                String ids = split[i];
                Integer uid = Integer.valueOf(ids);
                list.add(uid);
            }
        } else {
            list.add(orgId);
        }
        return orgMapper.deleteOrgAgencyByOrgId(list);
    }

    /**
     * 添加一个机构单元
     *
     * @param orgAgency
     */
    @Override
    public int addOrgAgency(OrgAgency orgAgency) {
        return orgMapper.insertOrgAgencyInfo(orgAgency);
    }

    /**
     * 修改一个机构单元
     *
     * @param orgAgency
     */
    @Override
    public int updateOrgAgency(OrgAgency orgAgency) {
        return orgMapper.updateOrgAgencyInfo(orgAgency);
    }
}
