package com.cn.uvfortune.interfaces.api.org;

import com.cn.uvfortune.application.service.orgservice.OrgService;
import com.cn.uvfortune.domain.model.org.Org;
import com.cn.uvfortune.domain.model.org.OrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrg;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.transport.response.OptStatusCode;
import com.cn.uvfortune.infrastructure.transport.response.ResponseFactory;
import com.cn.uvfortune.infrastructure.transport.response.impl.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author renpengfei
 * @version v1.0
 * @description 组织机构 api
 * @date 18-7-5
 */

@RestController
@RequestMapping("/org")
@Api(tags = {"组织机构"}, description = "对于组织机构的操作")
public class OrgController {

    @Autowired
    OrgService orgService;

    @ApiOperation("新增组织")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "sysOrg", value = "组织信息", dataTypeClass = SysOrg.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PostMapping(path = "addOrg", produces = {"application/json"}, consumes = "application/json")
    public ResponseData AddOrg(@RequestBody SysOrg sysOrg) {
        Org org = new Org();
        org.setOrgname(sysOrg.getOrgname());
        org.setOrgdescription(sysOrg.getOrgdescription());
        int effectDataNum = orgService.addOrganization(org);
        if (effectDataNum > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("删除组织")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "组织id", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @DeleteMapping(path = "delOrg", produces = {"application/json;charset=utf-8"})
    public ResponseData DelOrg(@RequestParam("id") String id) {
        int effectDataNum = orgService.deleteOrganization(id);
        if (effectDataNum > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("修改组织")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "sysOrg", value = "组织信息", dataTypeClass = SysOrg.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PutMapping(path = "updOrg", produces = {"application/json"}, consumes = "application/json")
    public ResponseData UpdOrg(@RequestBody SysOrg sysOrg) {
        Org org = new Org();
        org.setId(sysOrg.getId());
        org.setOrgname(sysOrg.getOrgname());
        org.setOrgdescription(sysOrg.getOrgdescription());
        int effectDataNum = orgService.updateOrganization(org);
        if (effectDataNum > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("查询组织")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orgname", value = "组织名称", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pager", value = "查询参数", dataTypeClass = Pager.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryOrgByName", produces = {"application/json"})
    public Pages getOrg(Pager pager, @RequestParam(value = "orgname", required = false) String orgname) {
        return orgService.getOrganizationByName(pager, orgname);
    }

    @ApiOperation("查询组织")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "组织id", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryOrgById", produces = {"application/json"})
    public ResponseData getOrgById(@RequestParam(value = "id") Integer id) {
        ResponseData<SysOrg> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(orgService.getOrganizationById(id));
        return responseData;
    }

    @ApiOperation("新增组织机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "sysOrgAgency", value = "机构信息", dataTypeClass = SysOrgAgency.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PostMapping(path = "addOrgAgency", produces = {"application/json"}, consumes = "application/json")
    public ResponseData AddOrgAgency(@RequestBody SysOrgAgency sysOrgAgency) {
        OrgAgency oa = new OrgAgency();
        oa.setOrgId(sysOrgAgency.getOrgId());
        oa.setParentId(sysOrgAgency.getParentId());
        oa.setAgencyName(sysOrgAgency.getAgencyName());
        oa.setAgencyNum(sysOrgAgency.getAgencyNum());
        oa.setDescription(sysOrgAgency.getDescription());
        int effectDataNum = orgService.addOrgAgency(oa);
        if (effectDataNum > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("删除机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "机构id,批量删除时用,隔开", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @DeleteMapping(path = "delOrgAgency", produces = {"application/json;charset=utf-8"})
    public ResponseData DelOrgAgency(@RequestParam(value = "id") String id) {
        int effectDataNum = orgService.deleteOrgAgency(id);
        if (effectDataNum > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("删除组织下的机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orgId", value = "机构id,批量删除时用,隔开", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @DeleteMapping(path = "delOrgAgencyByOrgId", produces = {"application/json;charset=utf-8"})
    public ResponseData DelOrgAgencyByOrgId(@RequestParam(value = "orgId") String orgId) {
        int effectDataNum = orgService.deleteOrgAgencyByOrgId(orgId);
        if (effectDataNum > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("修改组织机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "sysOrgAgency", value = "机构信息", dataTypeClass = SysOrgAgency.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @PutMapping(path = "updOrgAgency", produces = {"application/json"}, consumes = "application/json")
    public ResponseData UpdOrgAgency(@RequestBody SysOrgAgency sysOrgAgency) {
        OrgAgency oa = new OrgAgency();
        oa.setId(sysOrgAgency.getId());
        oa.setOrgId(sysOrgAgency.getOrgId());
        oa.setParentId(sysOrgAgency.getParentId());
        oa.setAgencyName(sysOrgAgency.getAgencyName());
        oa.setAgencyNum(sysOrgAgency.getAgencyNum());
        oa.setDescription(sysOrgAgency.getDescription());
        int effectDataNum = orgService.updateOrgAgency(oa);
        if (effectDataNum > 0) {
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFaildResponse();
    }

    @ApiOperation("查询机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "agencyName", value = "机构名称", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pager", value = "查询参数", dataTypeClass = Pager.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryOrgAgencyByName", produces = {"application/json"})
    public Pages getOrgAgencyByName(Pager pager, @RequestParam(value = "agencyName", required = false) String agencyName) {
        return orgService.getOrgAgencyByName(pager, agencyName);
    }

    @ApiOperation("查询所属机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "treeId", value = "选中树节点", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pId", value = "选中树节点父节点", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pager", value = "查询参数", dataTypeClass = Pager.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryOrgAgencyByTreeId", produces = {"application/json"})
    public Pages getOrgAgencyByTreeId(Pager pager, @RequestParam(value = "treeId", required = false) String treeId, @RequestParam(value = "pId", required = false) String pId) {
        return orgService.getOrgAgencyByTreeId(pager, treeId, pId);
    }

    @ApiOperation("查询用户所属组织的机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "treeId", value = "选中树节点", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pId", value = "选中树节点父节点", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pager", value = "查询参数", dataTypeClass = Pager.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryAgencyByTreeId", produces = {"application/json"})
    public Pages getOrgAgencyByTreeId(HttpSession session, Pager pager, @RequestParam(value = "treeId", required = false) String treeId, @RequestParam(value = "pId", required = false) String pId) {
        String user_id = session.getAttribute("user_id").toString();
        if (user_id.equals("1")) {
            List<ZNodes> orglistall = orgService.getOrgNodes();
            if (orglistall.size() != 0) {
                if (treeId == null) {
                    return orgService.getOrgAgencyByTreeId(pager, orglistall.get(0).getId() + "", pId);
                } else {
                    return orgService.getOrgAgencyByTreeId(pager, treeId, pId);
                }
            }
            return null;
        }
        List<ZNodes> orglist = orgService.getOrgNodesByUser(user_id);
        if (orglist.size() != 0) {
            if (treeId == null) {
                return orgService.getOrgAgencyByTreeId(pager, orglist.get(0).getId() + "", pId);
            } else {
                return orgService.getOrgAgencyByTreeId(pager, treeId, pId);
            }
        }
        return null;
    }

    @ApiOperation("查询机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "机构id", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryOrgAgencyById", produces = {"application/json"})
    public ResponseData getOrgAgencyById(@RequestParam(value = "id") Integer id) {
        ResponseData<SysOrgAgency> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        responseData.setDataContext(orgService.getOrgAgencyById(id));
        return responseData;
    }

    @ApiOperation("查询组织下的机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orgId", value = "组织id", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pager", value = "查询参数", dataTypeClass = Pager.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "queryOrgAgencyByOrgId", produces = {"application/json"})
    public Pages getOrgAgencyByOrgId(Pager pager, @RequestParam(value = "orgId") Integer orgId) {
        return orgService.getOrgAgencyByOrgId(pager, orgId);
    }

    @ApiOperation("查询组织机构的树结构")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "orgNodes", produces = {"application/json"})
    public ResponseData getOrgNodes() {
        ResponseData<List<ZNodes>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        List<ZNodes> orglist = orgService.getOrgNodes();
        List<ZNodes> agencylist = orgService.getOrgAgencyNodes();
        orglist.addAll(agencylist);
        responseData.setDataContext(orglist);
        return responseData;
    }

    @ApiOperation("查询所有组织")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "orglist", produces = {"application/json"})
    public List<SysOrg> getOrgList() {
        return orgService.getOrgList();
    }

    @ApiOperation("查询所有机构")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "agencylist", produces = {"application/json"})
    public List<ZNodes> getAgencyList() {
        List<ZNodes> orgAgencyNodes = orgService.getOrgAgencyNodes();
        return orgAgencyNodes;
    }

    @ApiOperation("查询用户所属组织机构的树结构")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功", response = ResponseData.class),
            @ApiResponse(code = 401, message = "未授权的访问"),
            @ApiResponse(code = 403, message = "访问被禁止"),
            @ApiResponse(code = 404, message = "路径不正确或没找到")
    })
    @GetMapping(path = "nodesByuser", produces = {"application/json"})
    public ResponseData getNodesByuser(HttpSession session) {
        ResponseData<List<ZNodes>> responseData = new ResponseData<>(OptStatusCode.OPT_SUCCESS, "查询成功");
        String user_id = session.getAttribute("user_id").toString();
        if (user_id.equals("1")) {
            List<ZNodes> orglist = orgService.getOrgNodes();
            if (orglist.size() != 0) {
                List<ZNodes> agencylist = orgService.getOrgAgencyNodesByOrgId(orglist.get(0).getId());
                orglist.addAll(agencylist);
                responseData.setDataContext(orglist);
                return responseData;
            }
            responseData.setDataContext(null);
            return responseData;
        }
        List<ZNodes> orglist = orgService.getOrgNodesByUser(user_id);
        if (orglist.size() != 0) {
            List<ZNodes> agencylist = orgService.getOrgAgencyNodesByOrgId(orglist.get(0).getId());
            orglist.addAll(agencylist);
            responseData.setDataContext(orglist);
            return responseData;
        }
        responseData.setDataContext(null);
        return responseData;
    }

}
