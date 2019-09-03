package com.cn.uvfortune.config.security.websecurity;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Auther: hancunyan
 * @Date: 2018/10/28 18:02
 * @Description:
 */

public class MerryyounExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write("账号在另一处登录，您被迫下线（并发登录）!");
    }
}
