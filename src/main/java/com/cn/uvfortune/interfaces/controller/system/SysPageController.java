package com.cn.uvfortune.interfaces.controller.system;

import com.cn.uvfortune.infrastructure.log.annotation.Log;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 系统级页面
 *
 * @author xiao
 * 2018-07-29
 */

@Controller
public class SysPageController {

    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "index")
    public String index(Model model, HttpSession session, HttpServletResponse response) {
        if(session.getAttribute("user_id") == null){
            return "login";
        }
        String user_id = session.getAttribute("user_id").toString();
        model.addAttribute("userid", user_id);
        return "index";
    }

    @RequestMapping(value = {"/"})
    @Log(content = "系统登录", type = "2")
    public void index(HttpSession session, HttpServletResponse response) throws IOException {
        if (session.getAttribute("user_id") != null) {
            response.sendRedirect("index");
            return;
        }
        response.sendRedirect("login");
        return;
    }

    @RequestMapping("loglist")
    public String loglist() {
        return "logManage/loglist";
    }

    @RequestMapping("logform")
    public String logform() {
        return "logManage/logform";
    }

    @RequestMapping(value = "loginErr", method = RequestMethod.GET)
    public String loginErr() {
        return "loginErr";
    }

    @RequestMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String sessionInvalid() {

        return null;
    }
}
