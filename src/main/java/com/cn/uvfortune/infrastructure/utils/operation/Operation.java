package com.cn.uvfortune.infrastructure.utils.operation;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/28 20:36
 * @Description:
 */
public class Operation {

    private int pageId;
    private String pagename;
    private StringBuffer opeartionArray = new StringBuffer();

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public StringBuffer getOpeartionArray() {
        return opeartionArray;
    }

    public void setOpeartionArray(StringBuffer opeartionArray) {
        this.opeartionArray = opeartionArray;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }
}
