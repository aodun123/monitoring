package com.cn.uvfortune.infrastructure.repository.entity.parameter;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/27 12:33
 * @Description:
 */
public class MyParams extends Pager{

    private String sex;

    private String status; // 状态

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
