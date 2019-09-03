package com.cn.uvfortune.interfaces.api.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.uvfortune.infrastructure.log.annotation.LogModel;
import com.cn.uvfortune.infrastructure.log.annotation.LogService;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.transport.response.impl.ResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 日志查询controller
 *
 * @author WangXiao
 * 2018-07-25
 */
@RestController
@RequestMapping("log")
@Api(tags = {"系统日志"}, description = "日志查询")
public class LogController {


    @Autowired
    LogService logService;

    /**
     * 根据id查询日志信息
     *
     * @param id 日志id
     * @return
     */
    @ApiOperation("查询指定日志详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "要查询的日志id", dataTypeClass = Integer.class, required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到"),
            @ApiResponse(code = 500, message = "服务器内部其他错误")
    })
    @GetMapping("getLog")
    public LogModel getLog(Integer id) {
        return logService.getLog(id);
    }

    /**
     * 根据条件或无条件、分页查询日志信息
     *
     * @param page      查询页码
     * @param limit     查询条数
     * @param startTime 记录起始时间
     * @param endTime   记录截止时间
     * @param log_type  日志类型
     * @return
     */
    @ApiOperation("分页查询日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "查询页码", dataTypeClass = int.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "查询条数", dataTypeClass = int.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "operator", value = "操作人", dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "logInner", value = "日志内容", dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "startTime", value = "记录起始时间", dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "endTime", value = "记录截止时间", dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "log_type", value = "查询日志类型", dataTypeClass = String.class),
    })
    @ApiResponses({
            @ApiResponse(code = 10400, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 10500, message = "未授权的访问")
    })
    @GetMapping("listLog")
    public Pages listLog(int page, int limit, String startTime, String endTime, String operator, String logInner, String log_type) {
        return logService.listLog(page, limit, startTime, endTime, operator, logInner, log_type);
    }
}
