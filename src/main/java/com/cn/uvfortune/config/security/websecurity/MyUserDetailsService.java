package com.cn.uvfortune.config.security.websecurity;

import com.cn.uvfortune.config.security.util.MySecurityUtil;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hancunyan
 * @Date: 2018/7/31 19:08
 * @Description: 自定义权限的类
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("mySecurityUtil")
    public MySecurityUtil securityUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> auths = new ArrayList<>();
        SysUser sysUser = securityUtil.getSysUserByName(username);
        //判断 sysUser是否为 null
        if (sysUser == null) {
            throw new UsernameNotFoundException("not found user:" + username);
        }
        List<SysPage> sysPages = securityUtil.getPagesById(sysUser.getId());
//        List<Sys_Resources_Operation> operation = securityUtil.getOperationById(sysUser.getId());

        for (SysPage sysPage : sysPages) {
            auths.add(new SimpleGrantedAuthority(sysPage.getName().toUpperCase()));
        }
        /*for (Sys_Resources_Operation sysOperation : operation) {
            auths.add(new SimpleGrantedAuthority(sysOperation.getBtnName().toUpperCase()));
        }*/

        return new User(sysUser.getUsername(), sysUser.getPassword(), true, true, true, true, auths);
    }
}

