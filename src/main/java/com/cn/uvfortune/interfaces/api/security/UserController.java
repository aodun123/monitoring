package com.cn.uvfortune.interfaces.api.security;

import com.cn.uvfortune.application.service.security.UserService;
import com.cn.uvfortune.domain.model.org.OrgUser;
import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.transport.response.OptStatusCode;
import com.cn.uvfortune.infrastructure.transport.response.ResponseFactory;
import com.cn.uvfortune.infrastructure.transport.response.impl.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/24 18:38
 * @Description:用户信息操作
 */

@RestController
@RequestMapping("/sysuser")
@Api(tags = {"用户管理"}, description = "对于用户信息的操作")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("新增用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "user", value = "用户信息", dataTypeClass = OrgUser.class)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PostMapping(path = "addPerson", produces = {"application/json"}, consumes = "application/json")
    public ResponseData addPerson(@RequestBody OrgUser user) {
        int add = userService.addUser(user);
        if (add > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @DeleteMapping(path = "delUser/{id}", produces = {"application/json;charset=utf-8"})
    public ResponseData delUser(@PathVariable("id") int id) {
        /*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();*/
        int del = userService.deleteUser(id);
        if (del > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "user", value = "需要修改的用户信息！", dataTypeClass = OrgUser.class)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PutMapping(path = "upUser", produces = {"application/json"}, consumes = "application/json")
    public ResponseData upUser(@RequestBody OrgUser user) {
        int up = userService.updateUser(user);
        if (up > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("分页查询全部用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "sex", value = "性别", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "status", value = "状态", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userNumber", value = "用户编号", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pager", value = "用户信息", dataTypeClass = Pager.class)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @RequestMapping(value = "queryAllUser", method = RequestMethod.GET)
    public Pages queryAllUser(Pager pager, String sex, String status, String userNumber) {
//        ResponseData<PageInfo<SysUser>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
//        responseData.setDataContext(userService.getAllUser(pageNum,pageSize));
        return userService.getAllUser(pager, sex, status, userNumber);
    }

    @ApiOperation("查询全部用户信息")
    @RequestMapping(value = "queryUser", produces = {"application/json"})
    public List<SysUser> queryAllUser(@RequestParam(value = "sysAgencyId") int sysAgencyId) {
        return userService.getAllUser(sysAgencyId);
    }

    @ApiOperation("根据用户id查询用户实体")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "personId", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryUserById", produces = {"application/json"})
    public ResponseData queryUserById(@RequestParam(value = "userid") int userid) {
        ResponseData<OrgUser> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(userService.getUserById(userid));
        return responseData;
    }

    @ApiOperation("批量删除用户信息")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @DeleteMapping(path = "deleteBatch/{idss}", produces = {"application/json;charset=utf-8"})
    public ResponseData deleteBatch(@PathVariable("idss") String idss) {
        int del = userService.deleteBctch(idss);
        if (del > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("根据用户id查询用户角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userid", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryRoleByUserId", produces = {"application/json"})
    public ResponseData queryRoleByUserId(int userid) {
        ResponseData<List<RoleUser>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(userService.queryRole(userid));
        return responseData;
    }

    @ApiOperation("重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "orgUser", value = "修改密码！", dataTypeClass = OrgUser.class)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PutMapping(path = "resetPassword", produces = {"application/json"}, consumes = "application/json")
    public ResponseData resetPassword(@RequestBody OrgUser orgUser) {
        int up = userService.upPassword(orgUser);
        if (up > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

}
