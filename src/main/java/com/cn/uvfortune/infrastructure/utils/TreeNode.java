package com.cn.uvfortune.infrastructure.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    protected int id;
    protected String name;
    protected int pid;
    protected List<TreeNode> children = new ArrayList<TreeNode>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChildren(TreeNode children) {
        this.children.add(children);
    }


}
