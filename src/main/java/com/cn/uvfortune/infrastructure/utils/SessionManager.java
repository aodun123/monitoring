package com.cn.uvfortune.infrastructure.utils;

import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.http.HttpSession;

public class SessionManager {
    @Autowired
    private static JdbcTemplate template = SpringContextHolder.getBean(JdbcTemplate.class);

    public static int getUserIdFromSession(HttpSession session) {
        Object attribute = session.getAttribute("user_id");
        if (attribute != null) {
            return Integer.valueOf(attribute.toString());
        } else {
            return -1;
        }
    }

    public static SysUser getUserFromSession(HttpSession session) {
        String user_id = session.getAttribute("user_id").toString();
        String sql = "SELECT id,realname,usernumber,sex,cardid,phone,email,`status`,adress,descption FROM sys_user where id = " + user_id;
        try {
            return MapUtil.mapToObject(template.queryForMap(sql), SysUser.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
