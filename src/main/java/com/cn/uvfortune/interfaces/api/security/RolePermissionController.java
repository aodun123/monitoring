package com.cn.uvfortune.interfaces.api.security;

import com.cn.uvfortune.application.service.security.RolePermissionSrv;
import com.cn.uvfortune.domain.model.security.ResourcesPage;
import com.cn.uvfortune.domain.model.security.RolePermission;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import com.cn.uvfortune.infrastructure.transport.response.OptStatusCode;
import com.cn.uvfortune.infrastructure.transport.response.ResponseFactory;
import com.cn.uvfortune.infrastructure.transport.response.impl.ResponseData;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/22 10:49
 * @Description: 角色权限 操作
 */
@RequestMapping(value = "/rolepermission")
@RestController
@Api(tags = {"给角色赋予权限"}, description = "对于角色权限操作")
public class RolePermissionController {

    @Autowired
    RolePermissionSrv rolePermissionSrv;

    /**
     * 给角色赋予权限
     *
     * @param rolePermission
     * @return
     * @建议 POST提交时， reId参数应当包含在整个application/json中！！！ 请修改参数获取方式
     */
    @ApiOperation("给角色赋予权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "rolePermission", value = "角色权限信息", dataTypeClass = RolePermission.class),
            @ApiImplicitParam(paramType = "query", name = "reId", value = "资源id字符串", dataType = "String")
    }
    )
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @PostMapping(path = "/rolePermission", produces = {"application/json"}, consumes = "application/json")
    public ResponseData addPe(@RequestBody RolePermission rolePermission, @Param("reId") String reId) {
        int add = rolePermissionSrv.addPermission(rolePermission, reId);
        if (add > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }


    /**
     * @param roleid
     * @return
     * @建议 API不符合规范！
     */
    @ApiOperation("根据角色id查询页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "roleid", value = "需要查询角色信息的主键id", dataTypeClass = Integer.class, required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行错误")
    })
    @RequestMapping(value = "/queryPageById", method = RequestMethod.GET)
    public ResponseData queryPageById(int roleid) {
        ResponseData<List<ZNodes>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(rolePermissionSrv.queryPageById(roleid));
        return responseData;
    }

    @ApiOperation("查询全部页面信息")
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @RequestMapping(value = "/queryAllPage", method = RequestMethod.GET)
    public ResponseData queryAllPage() {
        ResponseData<List<ZNodes>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(rolePermissionSrv.queryAllPage());
        return responseData;
    }


    /**
     * API不符合规范！
     */
    @ApiOperation("根据用户id查询页面信息")
    /*@ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userid", value = "需要查询角色信息的主键id", dataTypeClass = Integer.class, required = true)
    })*/
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行错误")
    })
    @RequestMapping(value = "/queryPageByUserId", method = RequestMethod.GET)
    public ResponseData selectPageByUserId(HttpSession session) {
        ResponseData<List<ResourcesPage>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        int user_id = (int) session.getAttribute("user_id");
        responseData.setDataContext(rolePermissionSrv.selectPageByUserId(user_id));
        return responseData;
    }

    /**
     * API不符合规范！
     */
    @ApiOperation("根据用户id查询页面对应的按钮信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "roleid", value = "需要查询角色信息的主键id", dataTypeClass = Integer.class, required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行错误")
    })
    @RequestMapping(value = "/selectOpeByRoleid", method = RequestMethod.GET)
    public ResponseData selectOpeByRoleid(int roleid) {
        ResponseData<List> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(rolePermissionSrv.selectOpeByRoleid(roleid));
        return responseData;
    }


    @ApiOperation("查询全部按钮信息")
    /*@ApiImplicitParams({
    })*/
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @RequestMapping(value = "/selectAllOperation", method = RequestMethod.GET)
    public ResponseData selectAllOperation() {
        ResponseData<List> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(rolePermissionSrv.selectAllOperation());
        return responseData;
    }

}
