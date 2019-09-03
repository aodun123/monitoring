package com.cn.uvfortune.infrastructure.repository.entity;

public class SysRole {
    private Integer id;

    private String rolename;

    private String roledescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return rolename;
    }

    public void setName(String name) {
        this.rolename = name == null ? null : name.trim();
    }

    public String getRoledescription() {
        return roledescription;
    }

    public void setRoledescription(String roledescription) {
        this.roledescription = roledescription == null ? null : roledescription.trim();
    }
}