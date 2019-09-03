package com.cn.uvfortune.interfaces.api.security;

import com.cn.uvfortune.application.service.security.RoleUserSrv;
import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.infrastructure.transport.response.OptStatusCode;
import com.cn.uvfortune.infrastructure.transport.response.ResponseFactory;
import com.cn.uvfortune.infrastructure.transport.response.impl.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/20 20:19
 * @Description:
 */
@RequestMapping("roleUser")
@RestController
@Api(tags = {"给用户赋予角色"}, description = "对于用户角色操作")
public class RoleUserController {

    @Autowired
    RoleUserSrv roleUserSrv;

    @ApiOperation("给用户赋予角色")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "roleUser", value = "角色用户信息", dataTypeClass = RoleUser.class),
            @ApiImplicitParam(paramType = "query", name = "rid", value = "角色id字符串", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @PostMapping(path = "/addRoleUser", produces = {"application/json"}, consumes = "application/json")
    public ResponseData addRoleUser(@RequestBody RoleUser roleUser, @RequestParam("rid") String rid) {
        int add = roleUserSrv.addRoleUser(roleUser, rid);
        if (add > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("给组织赋予角色：给组织下的每个用户赋予同一角色")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "roleUser", value = "角色用户信息", dataTypeClass = RoleUser.class),
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @PostMapping(path = "/addUserRole", produces = {"application/json"}, consumes = "application/json")
    public ResponseData addUserRole(@RequestBody RoleUser roleUser) {
        int add = roleUserSrv.addRole(roleUser);
        if (add > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("根据机构id查询角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "agencyId", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryRoleByAid", produces = {"application/json"})
    public ResponseData queryRoleByAid(int agencyId) {
        ResponseData<List<RoleUser>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(roleUserSrv.queryRoleByAid(agencyId));
        return responseData;
    }
}
