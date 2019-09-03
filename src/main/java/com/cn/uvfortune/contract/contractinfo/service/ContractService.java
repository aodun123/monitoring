package com.cn.uvfortune.contract.contractinfo.service;

import com.cn.uvfortune.contract.contractinfo.dao.ContractDao;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
    @Autowired
    private ContractDao contractDao;

    public Pages getContractList(int page, int limit) {
        Pages pages = new Pages();
        PageHelper.startPage(page,limit);
        PageInfo result = new PageInfo(contractDao.getList());
        pages.setData(result.getList());
        pages.setCount(result.getTotal());
        return pages;
    }


    public Pages getcontracttypeList(int page, int limit) {
        Pages pages = new Pages();
        PageHelper.startPage(page,limit);
        PageInfo result = new PageInfo(contractDao.getcontracttypeList());
        pages.setData(result.getList());
        pages.setCount(result.getTotal());
        return pages;
    }
}
