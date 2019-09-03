package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.domain.repository.sysrepo.RoleUserRepo;
import com.cn.uvfortune.infrastructure.repository.entity.SysRoleUser;
import com.cn.uvfortune.infrastructure.repository.entity.SysUserAgency;
import com.cn.uvfortune.infrastructure.repository.mapper.orgmapper.SysUserAgencyMapper;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SysRoleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/20 19:52
 * @Description:
 */
@Component
public class RoleUserRepoImpl implements RoleUserRepo {

    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    SysUserAgencyMapper sysUserAgencyMapper;

    /**
     * 给用户赋角色
     *
     * @param roleUser
     * @return
     */
    @Override
    public int addUserRole(RoleUser roleUser, String roleid) {
        List<SysRoleUser> list = new ArrayList<>();
        String[] roid = roleid.split("、");
        List<SysRoleUser> rolelist = sysRoleUserMapper.selectByPrimaryKey(roleUser.getSysUserId());
        if (rolelist.size() > 0) {
            int a = sysRoleUserMapper.deleteByPrimaryKey(roleUser.getSysUserId());
            if (a > 0) {
                for (int i = 0; i < roid.length; i++) {
                    SysRoleUser user = new SysRoleUser();
                    String role_id = roid[i];
                    user.setSysRoleId(Integer.valueOf(role_id));
                    user.setSysUserId(roleUser.getSysUserId());
                    user.setAgencyId(0);
                    list.add(user);
                }
                return sysRoleUserMapper.insertBatch(list);
            }
            return 0;
        } else {
            for (int i = 0; i < roid.length; i++) {
                SysRoleUser user = new SysRoleUser();
                String role_id = roid[i];
                Integer rid = Integer.valueOf(role_id);
                user.setSysRoleId(rid);
                user.setSysUserId(roleUser.getSysUserId());
                user.setAgencyId(0);
                list.add(user);
            }
            return sysRoleUserMapper.insertBatch(list);
        }
    }

    /**
     * 给组织赋角色（就是给组织里面的每个人赋同一个角色）
     *
     * @param roleUser
     * @return
     */
    @Override
    public int addUser(RoleUser roleUser) {
        List<SysUserAgency> userAgency = sysUserAgencyMapper.getUserAgencyList(roleUser.getAgencyId());
        if (userAgency.size() > 0) {
            List<SysRoleUser> list = new ArrayList<>();
            String uid = "";
            for (int i = 0; i < userAgency.size(); i++) {
                int userid = userAgency.get(i).getSysUserId();
                uid += "" + userid + "、";
            }
            String[] split = uid.split("、");
            List<SysRoleUser> roles = sysRoleUserMapper.selectByAgencyId(roleUser.getAgencyId());
            if (roles.size() > 0) {
                int del = sysRoleUserMapper.deleteByAgencyId(roleUser.getAgencyId());
                if (del > 0) {
                    for (int i = 0; i < split.length; i++) {
                        SysRoleUser user = new SysRoleUser();
                        String user_id = split[i];
                        user.setSysUserId(Integer.valueOf(user_id));
                        user.setSysRoleId(roleUser.getSysRoleId());
                        user.setAgencyId(roleUser.getAgencyId());
                        list.add(user);
                    }
                    return sysRoleUserMapper.insertBatch(list);
                }
            } else {
                for (int i = 0; i < split.length; i++) {
                    SysRoleUser user = new SysRoleUser();
                    String user_id = split[i];
                    user.setSysUserId(Integer.valueOf(user_id));
                    user.setSysRoleId(roleUser.getSysRoleId());
                    user.setAgencyId(roleUser.getAgencyId());
                    list.add(user);
                }
                return sysRoleUserMapper.insertBatch(list);
            }
        }
        return 0;
    }
}
