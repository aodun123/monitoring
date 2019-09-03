package com.cn.uvfortune.contract.contractinfo.controller;

import com.cn.uvfortune.common.controller.BaseConeroller;
import com.cn.uvfortune.contract.contractinfo.service.ContractService;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("contract")
public class ContractController {
    @Autowired
    private ContractService contractService;
    /**
     * 查询合同列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Pages getContractList(int page,int limit){
        Pages pa =  contractService.getContractList(page,limit);
        return pa;
    }

    @ResponseBody
    @RequestMapping("/typelist")
    public Pages getcontracttypeList(int page,int limit){
        Pages pa =  contractService.getcontracttypeList(page,limit);
        return pa;
    }



}
