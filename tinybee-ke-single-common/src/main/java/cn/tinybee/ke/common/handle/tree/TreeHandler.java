package cn.tinybee.ke.common.handle.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hao.huang
 * @description
 * @date 2020/4/14
 */
public class TreeHandler {


    public static <T> List<TreeNode<T>> list(List<? extends ITreeNode> list) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        return list.stream().map(v -> {
            TreeNode<T> treeNode = new TreeNode<>();
            treeNode.setId(v.getId());
            treeNode.setPid(v.getPid());
            treeNode.setLabel(v.getLabel());
            treeNode.setValue(v.getId());
            treeNode.setData((T) v);
            return treeNode;
        }).collect(Collectors.toList());
    }

    public static <T> List<TreeNode<T>> tree (List<? extends ITreeNode> dataList) {
        long current = System.currentTimeMillis();
        List<TreeNode<T>> list = list(dataList);
        if (list.isEmpty()) {
            return list;
        }
        // 处理孩子 统计孩子
        Map<Long, List<TreeNode<T>>> pidMap = new HashMap<>();
        List<TreeNode<T>> resDataList = new ArrayList<>();
        list.forEach(v -> {
            Long pid = v.getPid();
            List<TreeNode<T>> children = pidMap.get(pid);
            if (children != null) {
                children.add(v);
            }else {
                children = new ArrayList<>();
                children.add(v);
                pidMap.put(pid, children);
            }
            resDataList.add(v);
        });
        List<TreeNode<T>> res = new ArrayList<>();
        // 找出 没有父级的节点 组装成list
        resDataList.forEach( v -> {
            v.setChildren(pidMap.get(v.getId()));
            if (v.getPid() == null || v.getPid() == 0 || !pidMap.containsKey(v.getPid())) {
                res.add(v);
            }
        });
        return res;
    }

}
