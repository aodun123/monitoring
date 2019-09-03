package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.cn.uvfortune.domain.model.org.OrgUser;
import com.cn.uvfortune.domain.repository.sysrepo.UserRepo;
import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/24 18:52
 * @Description: 用户信息功能操作
 */
@Component
public class UserRepoImpl implements UserRepo {

    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 添加用户信息
     *
     * @param orgUser
     * @return
     */
    @Override
    public int addUser(OrgUser orgUser) {

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String password = encode.encode(orgUser.getPassword());


        SysUser sysUser = new SysUser();
        sysUser.setUsername(orgUser.getUsername());
        sysUser.setPassword(password);
        sysUser.setRealname(orgUser.getRealname());
        sysUser.setUsernumber(orgUser.getUsernumber());
        sysUser.setSex(orgUser.getSex());
        sysUser.setCardid(orgUser.getCardid());
        sysUser.setPhone(orgUser.getPhone());
        sysUser.setEmail(orgUser.getEmail());
        sysUser.setStatus(orgUser.getStatus());
        sysUser.setImage(orgUser.getImage());
        sysUser.setAdress(orgUser.getAdress());
        sysUser.setDescption(orgUser.getDescption());
        return sysUserMapper.insert(sysUser);
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @Override
    public int delUser(Integer id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改用户信息
     *
     * @param orgUser
     * @return
     */
    @Override
    public int upUser(OrgUser orgUser) {

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String password = encode.encode(orgUser.getPassword());

        SysUser sysUser = new SysUser();
        sysUser.setId(orgUser.getId());
        sysUser.setUsername(orgUser.getUsername());
        sysUser.setPassword(password);
        sysUser.setRealname(orgUser.getRealname());
        sysUser.setUsernumber(orgUser.getUsernumber());
        sysUser.setSex(orgUser.getSex());
        sysUser.setCardid(orgUser.getCardid());
        sysUser.setPhone(orgUser.getPhone());
        sysUser.setEmail(orgUser.getEmail());
        sysUser.setStatus(orgUser.getStatus());
        sysUser.setImage(orgUser.getImage());
        sysUser.setAdress(orgUser.getAdress());
        sysUser.setDescption(orgUser.getDescption());
        return sysUserMapper.updateByPrimaryKey(sysUser);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteBatch(String ids) {
        List list = new ArrayList();
        String[] split = ids.split("、");
        for (int i = 0; i < split.length; i++) {
            String id = split[i];
            Integer uid = Integer.valueOf(id);
            list.add(uid);
        }
        return sysUserMapper.deleteBatch(list);
    }

    /**
     * 重置密码
     *
     * @param orgUser
     * @return
     */
    @Override
    public int upPass(OrgUser orgUser) {

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String password = encode.encode(orgUser.getPassword());

        SysUser sysUser = new SysUser();
        sysUser.setId(orgUser.getId());
        sysUser.setPassword(password);
        return sysUserMapper.upPass(sysUser);
    }
}
