package com.cn.uvfortune.infrastructure.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeUtil {
    public static List<TreeNode> toTreeFromTreeNode(List<TreeNode> nodes) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (TreeNode node : nodes) {
            boolean isparent = false;
            for (TreeNode d : nodes) {
                int pid2 = d.getPid();
                boolean isChild = pid2 == node.getId();
                if (isChild) {
                    node.addChildren(d);
                    isparent = true;
                }
            }

            if (isparent) {
                trees.add(node);
            }
        }
        return trees;
    }

    /**
     * 必须包含 id, name,pid
     *
     * @param nodes
     * @return
     */
    public static List<TreeNode> toTreeFromListMap(List<Map<String, Object>> nodes) {
        return toTreeFromTreeNode(MapUtil.listMapToListObj(nodes, TreeNode.class));
    }
}
