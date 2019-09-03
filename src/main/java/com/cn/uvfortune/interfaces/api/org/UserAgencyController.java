package com.cn.uvfortune.interfaces.api.org;

import com.cn.uvfortune.application.service.orgservice.UserAgencyService;
import com.cn.uvfortune.domain.model.org.UserAgency;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import com.cn.uvfortune.infrastructure.transport.response.ResponseFactory;
import com.cn.uvfortune.infrastructure.transport.response.impl.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * @author renpengfei
 * @version v1.0
 * @description 机构用户 api
 * @date 18-7-5
 */

@RestController
@RequestMapping("/useragency")
@Api(tags = {"机构用户"}, description = "对于机构用户关系的操作")
public class UserAgencyController {


    @Autowired
    UserAgencyService userAgencyService;


    @ApiOperation("新增机构用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "sysUserId", value = "用户id", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sysAgencyId", value = "机构id", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PostMapping(path = "addUserAgency", produces = {"application/json"})
    public ResponseData AddUserAgency(@RequestParam(value = "sysUserId") String sysUserId, @RequestParam(value = "sysAgencyId") Integer sysAgencyId) {
        List list = new ArrayList();
        if (sysUserId.indexOf("、") > 0) {
            String[] ids = sysUserId.split("、");
            for (int i = 0; i < ids.length; i++) {
                String userId = ids[i];
                UserAgency ua = new UserAgency();
                ua.setSysUserId(Integer.valueOf(userId));
                ua.setSysAgencyId(sysAgencyId);
                list.add(ua);
            }
        } else {
            UserAgency ua = new UserAgency();
            ua.setSysAgencyId(sysAgencyId);
            ua.setSysUserId(Integer.valueOf(sysUserId));
            list.add(ua);
        }
        int effectDataNum = userAgencyService.addUserAgency(list);
        if (effectDataNum > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("删除机构用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "sysAgencyId", value = "机构id", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @DeleteMapping(path = "delUserAgency", produces = {"application/json;charset=utf-8"})
    public ResponseData DelUserAgency(@RequestParam("sysAgencyId") Integer sysAgencyId) {
        int effectDataNum = userAgencyService.deleteUserAgency(sysAgencyId);
        if (effectDataNum > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("修改机构用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "sysUserId", value = "用户id", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sysAgencyId", value = "机构id", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PutMapping(path = "updUserAgency", produces = {"application/json"})
    public ResponseData UpdUserAgency(@RequestParam(value = "sysUserId") String sysUserId, @RequestParam(value = "sysAgencyId") Integer sysAgencyId) {
        List list = new ArrayList();
        if (sysUserId.indexOf("、") > 0) {
            String[] ids = sysUserId.split("、");
            for (int i = 0; i < ids.length; i++) {
                String userId = ids[i];
                UserAgency ua = new UserAgency();
                ua.setSysUserId(Integer.valueOf(userId));
                ua.setSysAgencyId(sysAgencyId);
                list.add(ua);
            }
        } else {
            UserAgency ua = new UserAgency();
            ua.setSysAgencyId(sysAgencyId);
            ua.setSysUserId(Integer.valueOf(sysUserId));
            list.add(ua);
        }
        int effectDataNum = userAgencyService.updUserAgency(list);
        if (effectDataNum > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("查询机构下的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "sysAgencyId", value = "机构id", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryUserAgency", produces = {"application/json"})
    public List<ZNodes> getUserAgency(@RequestParam(value = "sysAgencyId") Integer sysAgencyId) {
        return userAgencyService.getUserAgency(sysAgencyId);
    }


    /**
     * 根据用户id查询组织机构
     *
     * @return
     */
    @RequestMapping(path = "queryUserAgencyById", produces = {"application/json"})
    public List<SysOrgAgency> queryUserAgencyById() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String user_id = session.getAttribute("user_id").toString();
        if (user_id.equals("1")) {
            List<SysOrgAgency> sysOrgAgencies = userAgencyService.queryUserAgencyall();
            return sysOrgAgencies;
        }
        return userAgencyService.queryUserAgencyById(Integer.valueOf(user_id));
    }

}
