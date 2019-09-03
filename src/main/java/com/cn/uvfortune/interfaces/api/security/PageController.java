package com.cn.uvfortune.interfaces.api.security;

import com.cn.uvfortune.application.service.security.PageSrv;
import com.cn.uvfortune.domain.model.security.ResourcesPage;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import com.cn.uvfortune.infrastructure.transport.response.OptStatusCode;
import com.cn.uvfortune.infrastructure.transport.response.ResponseFactory;
import com.cn.uvfortune.infrastructure.transport.response.impl.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 12:23
 * @Description:
 */
@RequestMapping(value = "syspage")
@RestController
@Api(tags = {"页面信息"}, description = "对于页面信息的操作")
public class PageController {
    @Autowired
    PageSrv pageSrv;

    @ApiOperation("新增页面信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "resourcesPage", value = "页面信息", dataTypeClass = ResourcesPage.class)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @PostMapping(path = "/addPage", produces = {"application/json"}, consumes = "application/json")
    public ResponseData addPage(@RequestBody ResourcesPage resourcesPage) {
        int add = pageSrv.addPage(resourcesPage);
        if (add > 0) {
            System.out.println(ResponseFactory.createSuccessResponse());
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("修改页面信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "resourcesPage", value = "要修改的页面信息！", dataTypeClass = ResourcesPage.class)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @PutMapping(path = "/upPage", produces = {"application/json"}, consumes = "application/json")
    public ResponseData upPage(@RequestBody ResourcesPage resourcesPage) {
        int up = pageSrv.upPage(resourcesPage);
        if (up > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("删除页面信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "页面id", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @DeleteMapping(path = "/delPage/{id}", produces = {"application/json;charset=utf-8"})
    public ResponseData delPage(@PathVariable("id") int id) {
        int del = pageSrv.delPage(id);
        if (del > 0) {
            return ResponseFactory.createSuccessResponse();
        } else {
            return ResponseFactory.createFaildResponse();
        }
    }

    @ApiOperation("分页查询页面信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pager", value = "页面信息", dataTypeClass = Pager.class)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @RequestMapping(value = "/selectPage", method = RequestMethod.GET)
    public Pages selectPage(Pager pager) {
        /*ResponseData<Pages> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(pageSrv.selectPage(pager));*/
        return pageSrv.selectPage(pager);
    }

    @ApiOperation("查询全部页面信息")
    /*@ApiImplicitParams({
    })*/
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseData findAll() {
        ResponseData<List<SysPage>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(pageSrv.findAll());
        return responseData;
    }

    @ApiOperation("根据用户id查询对应权限页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userid", value = "需要查询页面信息的用户id", dataTypeClass = String.class, required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "执行失败")
    })
    @RequestMapping(value = "/queryPageById", method = RequestMethod.GET)
    public ResponseData queryPageById(String userid) {
        ResponseData<List<SysPage>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(pageSrv.queryPageById(userid));
        return responseData;
    }


    @RequestMapping(value = "/pageType", method = RequestMethod.GET)
    public ResponseData pageType() {
        ResponseData<List<SysPage>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(pageSrv.queryType());
        return responseData;
    }
}
