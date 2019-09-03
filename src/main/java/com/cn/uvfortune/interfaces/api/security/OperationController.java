package com.cn.uvfortune.interfaces.api.security;

import com.github.pagehelper.PageInfo;
import com.cn.uvfortune.application.service.security.OperationSrv;
import com.cn.uvfortune.domain.model.security.ResourceButton;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import com.cn.uvfortune.infrastructure.transport.response.OptStatusCode;
import com.cn.uvfortune.infrastructure.transport.response.ResponseFactory;
import com.cn.uvfortune.infrastructure.transport.response.impl.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 18:50
 * @Description: 按钮信息操作
 */
@RestController
@RequestMapping(value = "/operatio")
@Api(tags = {"按钮信息"}, description = "对于按钮信息的操作")
public class OperationController {
    @Autowired
    OperationSrv operationSrv;

    @ApiOperation("新增按钮信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "operation", value = "按钮信息", dataTypeClass = ResourceButton.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PostMapping(path = "/addOperation", produces = {"application/json"}, consumes = "application/json")
    public ResponseData addOperation(ResourceButton operation) {
        int add = operationSrv.addOp(operation);
        if (add > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("修改按钮信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "operation", value = "要修改的按钮信息", dataTypeClass = ResourceButton.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PutMapping(path = "/upOperation", produces = {"application/json"}, consumes = "application/json")
    public ResponseData upOperation(@RequestBody ResourceButton operation) {
        int up = operationSrv.upOp(operation);
        if (up > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("删除按钮信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "btnid", value = "按钮id", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @DeleteMapping(path = "delOperation/{btnid}", produces = {"application/json;charset=utf-8"})
    public ResponseData delOperation(@PathVariable String btnid) {
        int del = operationSrv.delOp(btnid);
        if (del > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("分页查询按钮信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "查询的页数,必填项", dataTypeClass = int.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "查询的每页显示行数,必填项", dataTypeClass = int.class, required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到"),
            @ApiResponse(code = 500, message = "服务器内部其他错误")
    })
    @RequestMapping(value = "/selOp", method = RequestMethod.GET)
    public ResponseData selOp(int pageNum, int pageSize) {
        ResponseData<PageInfo<Sys_Resources_Operation>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(operationSrv.selectOp(pageNum, pageSize));
        return responseData;
    }

    @ApiOperation("根据用户id及页面id查询该页面对应的权限按钮")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userid", value = "需要查询按钮信息的用户id", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "pageid", value = "需要查询按钮信息的页面id", dataTypeClass = String.class, required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @RequestMapping(value = "/queryOpeationByPageId", method = RequestMethod.GET)
    public ResponseData queryOpeationByPageId(String pageid, HttpSession session) {
        String userid = session.getAttribute("user_id").toString();
        ResponseData<List<Sys_Resources_Operation>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(operationSrv.queryOpeationByPageId(userid, pageid));
        return responseData;
    }

    @ApiOperation("根据用户id查询对应的权限按钮")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userid", value = "需要查询按钮信息的用户id", dataTypeClass = String.class, required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @RequestMapping(value = "/queryOpeationById", method = RequestMethod.GET)
    public ResponseData queryOpeationById(String userid) {
        ResponseData<List<Sys_Resources_Operation>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(operationSrv.queryOpeationById(userid));
        return responseData;
    }
}
