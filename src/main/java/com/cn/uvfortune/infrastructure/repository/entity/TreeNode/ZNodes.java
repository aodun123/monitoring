package com.cn.uvfortune.infrastructure.repository.entity.TreeNode;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/28 13:32
 * @Description: 树结构转换
 */
public class ZNodes {

    private Integer id;

    private Integer pId;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
