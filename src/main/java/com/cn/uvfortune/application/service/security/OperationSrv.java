package com.cn.uvfortune.application.service.security;

import com.github.pagehelper.PageInfo;
import com.cn.uvfortune.domain.factory.sysfactory.ResourcesOperationFactory;
import com.cn.uvfortune.domain.model.security.ResourceButton;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 18:39
 * @Description: 按钮信息操作服务
 */
@Service
public class OperationSrv {
    @Autowired
    ResourcesOperationFactory operationFactory;

    /**
     * 添加按钮信息
     *
     * @param recourcesOperation
     * @return
     */
    public int addOp(ResourceButton recourcesOperation) {
        ResourceButton opInstance = operationFactory.getOperationInstance();
        /*ResponseData<Integer> responseData = new SuccessResponseData();
        if(add>0){
            responseData.setDataContext(add);
        }*/
        return opInstance.addOperation(recourcesOperation);
    }

    /**
     * 修改按钮信息
     *
     * @param recourcesOperation
     * @return
     */
    public int upOp(ResourceButton recourcesOperation) {
        ResourceButton opInstance = operationFactory.getOperationInstance();
        /*int<Integer> responseData = new SuccessResponseData();
        if(up>0){
            responseData.setDataContext(up);
        }*/
        return opInstance.upOperation(recourcesOperation);
    }

    /**
     * 删除按钮信息
     *
     * @param btnid
     * @return
     */
    public int delOp(String btnid) {
        ResourceButton opInstance = operationFactory.getOperationInstance();
        /*int<Integer> responseData = new SuccessResponseData();
        if(del>0){
            responseData.setDataContext(del);
        }*/
        return opInstance.delOperation(btnid);
    }

    /**
     * 分页查询按钮信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Sys_Resources_Operation> selectOp(int pageNum, int pageSize) {
        ResourceButton opInstance = operationFactory.getOperationInstance();
        return opInstance.selectOp(pageNum, pageSize);
    }

    /**
     * 根据用户id查询对应的权限按钮
     *
     * @param userid
     * @return
     */
    public List<Sys_Resources_Operation> queryOpeationById(String userid) {
        ResourceButton operationInstance = operationFactory.getOperationInstance();
        return operationInstance.queryOpeationById(userid);
    }

    /**
     * 根据用户id和页面id查询对应权限按钮信息
     *
     * @param userid
     * @param pageid
     * @return
     */
    public List<Sys_Resources_Operation> queryOpeationByPageId(String userid, String pageid) {
        ResourceButton operationInstance = operationFactory.getOperationInstance();
        return operationInstance.queryOpeationByPageId(userid, pageid);
    }

}
