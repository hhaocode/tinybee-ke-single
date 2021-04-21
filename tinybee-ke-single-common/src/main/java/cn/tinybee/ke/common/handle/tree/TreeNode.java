package cn.tinybee.ke.common.handle.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname TreeNode
 * @Description TODO
 * @Date 2020/5/28 13:58
 * @Created by hao.huang
 */
@Data
public class TreeNode<T> implements Serializable {

    private Long id;
    private Long pid;
    private Long value;
    private String label;
    private T data;
    private List<TreeNode<T>> children;
}
