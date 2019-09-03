package com.cn.uvfortune.config.security.websecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: hancunyan
 * @Date: 2018/10/24 12:56
 * @Description:
 */
@Component
public class MyLoginSuccessHangler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication arg2) throws IOException,
            ServletException {
//        logger.info("Success hanlder"); //这里加入需要的处理
        Object uesr_id = request.getSession().getAttribute("uesr_id");
        String redirectUrl = "/"; //缺省的登陆成功页面

        response.sendRedirect(redirectUrl);
    }
}