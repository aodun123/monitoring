package com.cn.uvfortune.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

//@RequestMapping("static")
@Controller
public class StaticController {

    @RequestMapping("list")
    public String list() {
        return "systemManage/user/list";
    }

    @RequestMapping("userlist")
    public String userlist() {
        return "systemManage/user/userlist";
    }

    @RequestMapping("userform")
    public String userform() {
        return "systemManage/user/userform";
    }

    @RequestMapping("userrole")
    public String userrole() {
        return "systemManage/user/userrole";
    }

    @RequestMapping("resetPasswordform")
    public String resetPasswordform() {
        return "systemManage/user/resetPasswordform";
    }

    @RequestMapping("rolelist")
    public String rolelist() {
        return "systemManage/role/rolelist";
    }

    @RequestMapping("roleform")
    public String roleform() {
        return "systemManage/role/roleform";
    }

    @RequestMapping("rolePagePermission")
    public String rolePagePermission() {
        return "systemManage/role/rolePagePermission";
    }

    @RequestMapping("roleFunctionPermission")
    public String roleFunctionPermission() {
        return "systemManage/role/roleFunctionPermission";
    }

    @RequestMapping("orglist")
    public String orglist() {
        return "systemManage/org/orglist";
    }

    @RequestMapping("orgform")
    public String orgform() {
        return "systemManage/org/orgform";
    }

    @RequestMapping("organilist")
    public String organilist(Model model, HttpSession session) {
        String user_id = session.getAttribute("user_id").toString();
        model.addAttribute("userid", user_id);
        return "systemManage/org/organilist";
    }

    @RequestMapping("organiform")
    public String organiform() {
        return "systemManage/org/organiform";
    }

    @RequestMapping("organirole")
    public String organirole() {
        return "systemManage/org/organirole";
    }

    @RequestMapping("organiper")
    public String organiper() {
        return "systemManage/org/organiper";
    }


    @RequestMapping("pagelist")
    public String pagelist() {
        return "systemManage/page/pagelist";
    }

    @RequestMapping("pageform")
    public String pageform() {
        return "systemManage/page/pageform";
    }

    @RequestMapping("contractlist")
    public String contractlist() {
        return "contract/contractList";
    }
    @RequestMapping("contractadd")
    public String contractadd() {
        return "contract/contractadd";
    }

    @RequestMapping("contracttype")
    public String contracttype() {
        return "contract/contracttype";
    }

}
