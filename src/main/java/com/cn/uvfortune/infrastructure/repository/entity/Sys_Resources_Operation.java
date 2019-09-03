package com.cn.uvfortune.infrastructure.repository.entity;

public class Sys_Resources_Operation {
    private int btnId;

    private String btnCode;

    private String btnName;

    private String btnTitle;

    private int pageId;

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
}