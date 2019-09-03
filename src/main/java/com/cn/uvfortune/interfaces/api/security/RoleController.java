package com.cn.uvfortune.interfaces.api.security;

import com.cn.uvfortune.application.service.security.RoleSrv;
import com.cn.uvfortune.domain.model.security.Role;
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
 * @Date: 2018/7/20 11:23
 * @Description:
 */
@RequestMapping("sysrole")
@RestController
@Api(tags = {"角色信息"}, description = "对于角色信息的操作")
public class RoleController {
    @Autowired
    RoleSrv roleSrv;

    @ApiOperation("新增角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "role", value = "角色信息", dataTypeClass = Role.class)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @PostMapping(path = "/role", produces = {"application/json"}, consumes = "application/json")
    public ResponseData addRole(@RequestBody Role role) {
        int add = roleSrv.insert(role);
        if (add > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }


    @ApiOperation("删除角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "主键id", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @DeleteMapping(path = "role/{id}", produces = {"application/json;charset=utf-8"})
    public ResponseData delRole(@PathVariable("id") int id) {
        int del = roleSrv.delrole(id);
        if (del > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }


    @ApiOperation("修改角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "role", value = "该请求所有参数均不需要传递值！", dataTypeClass = Role.class)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @PutMapping(path = "/role", produces = {"application/json"}, consumes = "application/json")
    public ResponseData upRole(@RequestBody Role role) {
        int up = roleSrv.uprole(role);
        if (up > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }


    @ApiOperation("分页查询角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pager", value = "角色信息", dataTypeClass = Pager.class)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = Pages.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @RequestMapping(value = "/roleList", method = RequestMethod.GET)
    public Pages roleList(Pager pager) {
//        ResponseData<PageInfo<SysRole>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
//        responseData.setDataContext(roleSrv.queryRolePageInfo(pager));
        return roleSrv.queryRolePageInfo(pager);
    }


    @ApiOperation("根据id查询角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "需要查询角色信息的主键id", dataTypeClass = Integer.class, required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行错误")
    })
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public ResponseData queryRole(int id) {
        ResponseData<Role> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(roleSrv.queryrole(id));
        return responseData;
    }

    @ApiOperation("查询全部角色信息")
    /*@ApiImplicitParams({
    })*/
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行错误")
    })
    @RequestMapping(value = "/selectAllRole", method = RequestMethod.GET)
    public ResponseData selectAllRole() {
        ResponseData<List<Role>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(roleSrv.selectRoleAll());
        return responseData;
    }

}
