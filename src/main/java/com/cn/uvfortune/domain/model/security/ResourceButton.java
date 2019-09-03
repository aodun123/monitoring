package com.cn.uvfortune.domain.model.security;

import com.github.pagehelper.PageInfo;
import com.cn.uvfortune.domain.repository.sysrepo.PermissionResourceOperationRepo;
import com.cn.uvfortune.domain.repository.sysrepo.QueryPermissionResourceOperationRepo;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;

import java.util.List;

/**
 * @author: hancunyan
 * @Date: 2018/7/21 17:48
 * @Description: 按钮资源模型
 */
public class ResourceButton {

    private int btnId;  // 主键ID

    private String btnCode; // 按钮组件id

    private String btnName; // 按钮名称

    private String btnTitle; // 按钮标题

    private int pageId; // 页面id

    private String name;

    // 按钮模型功能
    PermissionResourceOperationRepo operationRepo;

    // 按钮模型查询功能
    QueryPermissionResourceOperationRepo resourceOperationRepo;

    // 添加按钮信息
    public int addOperation(ResourceButton operation) {
        return operationRepo.insert(operation);
    }

    // 修改按钮信息
    public int upOperation(ResourceButton operation) {
        return operationRepo.updateByPrimaryKey(operation);
    }

    // 删除按钮信息
    public int delOperation(String btnId) {
        return operationRepo.deleteByPrimaryKey(btnId);
    }

    /**
     * 分页查询按钮信息
     */
    public PageInfo<Sys_Resources_Operation> selectOp(int pageNum, int pageSize) {
        return resourceOperationRepo.selectOperation(pageNum, pageSize);
    }

    /**
     * 根据用户id查询对应的权限按钮
     *
     * @param userid
     * @return
     */
    public List<Sys_Resources_Operation> queryOpeationById(String userid) {
        return resourceOperationRepo.queryOpeationById(userid);
    }


    /**
     * 根据用户id和页面id查询对应权限按钮信息
     *
     * @param userid
     * @param pageid
     * @return
     */
    public List<Sys_Resources_Operation> queryOpeationByPageId(String userid, String pageid) {
        return resourceOperationRepo.queryOpeationByPageId(userid, pageid);
    }


    public int getBtnId() {
        return btnId;
    }

    public void setBtnId(int btnId) {
        this.btnId = btnId;
    }

    public String getBtnCode() {
        return btnCode;
    }

    public void setBtnCode(String btnCode) {
        this.btnCode = btnCode;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getBtnTitle() {
        return btnTitle;
    }

    public void setBtnTitle(String btnTitle) {
        this.btnTitle = btnTitle;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PermissionResourceOperationRepo getOperationRepo() {
        return operationRepo;
    }

    public void setOperationRepo(PermissionResourceOperationRepo operationRepo) {
        this.operationRepo = operationRepo;
    }

    public void setResourceOperationRepo(QueryPermissionResourceOperationRepo resourceOperationRepo) {
        this.resourceOperationRepo = resourceOperationRepo;
    }
}
